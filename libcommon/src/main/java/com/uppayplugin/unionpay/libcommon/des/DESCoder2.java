package com.uppayplugin.unionpay.libcommon.des;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;


/**
 * DES安全编码组件
 *
 * <pre>
 * 支持 DES、DESede(TripleDES,就是3DES)、AES、Blowfish、RC2、RC4(ARCFOUR)
 * DES          		key size must be equal to 56
 * DESede(TripleDES) 	key size must be equal to 112 or 168
 * AES          		key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available
 * Blowfish     		key size must be multiple of 8, and can only range from 32 to 448 (inclusive)
 * RC2          		key size must be between 40 and 1024 bits
 * RC4(ARCFOUR) 		key size must be between 40 and 1024 bits
 * 具体内容 需要关注 JDK Document http://.../docs/technotes/guides/security/SunProviders.html
 * </pre>
 *
 */
public abstract class DESCoder2 extends Coder {
	/**
	 * ALGORITHM 算法 <br>
	 * 可替换为以下任意一种算法，同时key值的size相应改变。
	 *
	 * <pre>
	 * DES          		key size must be equal to 56
	 * DESede(TripleDES) 	key size must be equal to 112 or 168
	 * AES          		key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available
	 * Blowfish     		key size must be multiple of 8, and can only range from 32 to 448 (inclusive)
	 * RC2          		key size must be between 40 and 1024 bits
	 * RC4(ARCFOUR) 		key size must be between 40 and 1024 bits
	 * </pre>
	 *
	 * 在Key toKey(byte[] key)方法中使用下述代码
	 * <code>SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);</code> 替换
	 * <code>
	 * DESKeySpec dks = new DESKeySpec(key);
	 * SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
	 * SecretKey secretKey = keyFactory.generateSecret(dks);
	 * </code>
	 */
	public static final String ALGORITHM_DES = "DES";

	public static final String ALGORITHM_3DES = "DESede";

	//向量
	private final static String IV = "01234567";

	/**
	 * 转换密钥<br>
	 *
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static Key toKey(byte[] key) throws Exception {
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM_DES);
		SecretKey secretKey = keyFactory.generateSecret(dks);

		// 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码
		// SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);

		return secretKey;
	}

	private static Key toKey3des(byte[] key) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance(ALGORITHM_3DES);
		deskey = keyfactory.generateSecret(spec);


		return deskey;
	}

	/**
	 * 解密
	 *
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, String key, String iv) throws Exception {
		Key k = toKey3des(Coder.decryptBASE64(key));

		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, k, ips);

		return cipher.doFinal(data);
	}

	/**
	 * 加密
	 *
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, String key, String iv) throws Exception {
		Key k = toKey3des(Coder.decryptBASE64(key));

		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());

		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, k, ips);

		return cipher.doFinal(data);
	}


	/**
	 * 生成密钥
	 *
	 * @return
	 * @throws Exception
	 */
	public static String initKey() throws Exception {
		return initKey(null);
	}

	/**
	 * 生成密钥
	 *
	 * @param seed
	 * @return
	 * @throws Exception
	 */
	public static String initKey(String seed) throws Exception {
		SecureRandom secureRandom = null;

		if (seed != null) {
			secureRandom = new SecureRandom(Coder.decryptBASE64(seed));
		} else {
			secureRandom = new SecureRandom();
		}

		KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_3DES);
		kg.init(secureRandom);

		SecretKey secretKey = kg.generateKey();

		return Coder.encryptBASE64(secretKey.getEncoded());
	}

	/**
	 * 解密
	 * @param password 密文
	 * @return 明文
	 */
	public static String decryptMode(String password,String key, String iv) {
		//DES解密密码
		try {
			password = new String(DESCoder2.decrypt(hexStringToByte(password), DESCoder2.encryptBASE64(key.getBytes()), iv));
		} catch (Exception e1) {
			e1.printStackTrace();
			password = "";
		}
		return password;
	}

	public static byte[] hexStringToByte(String hex) {
	    int len = (hex.length() / 2);
	    byte[] result = new byte[len];
	    char[] achar = hex.toCharArray();
	    for (int i = 0; i < len; i++) {
	     int pos = i * 2;
	     result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
	    }
	    return result;
	}

	private static byte toByte(char c) {
	    byte b = (byte) "0123456789ABCDEF".indexOf(c);
	    return b;
	}


	/**
	 * 加密
	 * @param password 明文
	 * @return 密文
	 */
	public static String encryptMode(String password,String key, String iv) {
		try {
			byte[] inputData = password.getBytes();
			inputData = DESCoder2.encrypt(inputData, DESCoder2.encryptBASE64(key.getBytes()), iv);

			password = bytesToHexString(inputData);



		} catch (Exception e1) {
			e1.printStackTrace();
			password = "";
		}
		password=password.replaceAll("\r\n","").replaceAll("\n", "");
		return password;
	}

	public static final String bytesToHexString(byte[] bArray) {
	    StringBuffer sb = new StringBuffer(bArray.length);
	    String sTemp;
	    for (int i = 0; i < bArray.length; i++) {
	     sTemp = Integer.toHexString(0xFF & bArray[i]);
	     if (sTemp.length() < 2)
	      sb.append(0);
	     sb.append(sTemp.toUpperCase());
	    }
	    return sb.toString();
	}
	/**
	 * SHA256加密
	 * @return 密文
	 */
	public static String enParams(String querystring)
	{

		try {
			byte [] querystringbyte= Coder.encryptSHA256(querystring.getBytes("UTF-8"));
			querystring=bytesToHexString(querystringbyte);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return querystring;
	}

	public static void main(String[] args) {
		System.out.println(DESCoder2.decryptMode("A6A97494050C0C7B5A169A168CAAEA93", "J2OKyoMA0fcKROy51sWNhPpk", "omcZNJ4X"));//解密
	}
}
