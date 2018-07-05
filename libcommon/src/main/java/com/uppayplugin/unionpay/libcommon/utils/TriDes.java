package com.uppayplugin.unionpay.libcommon.utils;

import com.uppayplugin.unionpay.libcommon.file.BytesImpl;
import com.uppayplugin.unionpay.libcommon.file.HexCode;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class TriDes {

	/**
	 * Master key for 3DES,it is the key for DES algorithm and it takes the
	 * first 8 bytes	 */
	byte [] masterkey = new byte[16];

	/**
	 * Workkey for 3DES,it is the work key for DES algorithm and it takes the
	 * first 8 bytes
	 *
	 */
	byte [] workkey = new byte[16];

	/**
	 * 设置主密钥
	 * @param mkey
	 */
	public void setMasterKey(byte [] mkey)
	{
		if (mkey == null || mkey.length == 0) return;
		System.arraycopy(mkey, 0, masterkey, 0, mkey.length>16?16:mkey.length);
		reSetWorkKey();
	}

	/**
	 * 打印bytes的16进制到控制台，测试使用
	 * @param b
	 */
	public static void printHexString(byte[] b) {
		System.out.println(BytesImpl.Byte2HexString(b,0,b.length));
	}

	public static void printHexString(byte[] b,int off,int length) {
		System.out.println(BytesImpl.Byte2HexString(b,off,length));
	}

	/**
	 * bytes are padded according to different parameters
	 * IFbits>1,process is valid,ifbits==1,it is padded a byte
	 * @param bts raw data need to be processed
	 * @param bt character need to be padded
	 * @param bits 需要不为多少的倍数
	 * @param rflag right alignment
	 * @return byte[]
	 */
	public static byte[] fillbase(byte[] bts,byte bt,int bits,boolean rflag) {
		int iLen;

		iLen = (bts.length) % bits;

		byte[] bRet;

		if (iLen != 0 || bits == 1) {

			iLen = bits - iLen;
			byte[] bSpace = new byte[iLen];
			bRet = new byte[bts.length + iLen];
			for (int i = 0; i < iLen; i++) {
				bSpace[i] = bt;
			}

			if(rflag){
				System.arraycopy(bSpace, 0, bRet, 0, bSpace.length);
				System.arraycopy(bts, 0, bRet, bSpace.length, bts.length);
				} else {
				System.arraycopy(bts, 0, bRet, 0, bts.length);
				System.arraycopy(bSpace, 0, bRet, bts.length, bSpace.length);
			}
			} else {
			bRet = new byte[bts.length];
			System.arraycopy(bts, 0, bRet, 0, bts.length);
		}

		return bRet;
	}

	/**
	 * Setup bytes
	 * @param bt
	 * @param b
	 */
	public static void zero (byte [] bt,byte b)
	{
		for(int i=0;
		i<bt.length;
		i++ )
		bt[i] =b;

	}
	/**
	 * AND and OR data array
	 * @param base data1 involved
	 * @param param data2 involved
	 * @param length length of data
	 * @return result
	 */
	public static byte [] xor (byte [] base,byte [] param,int length)
	{
		byte [] retbs = new byte[length];

		for(int i=0;
		i<length;

		i++ )
		retbs[i] = (byte)(base[i] ^ param[i]);
		return retbs;
	}

	/**
	 * Reset a key
	 */
	public void reSetWorkKey()
	{
		System.arraycopy(masterkey, 0, workkey, 0, masterkey.length);
	}

	/**
	 * Generate a sub-key
	 * @param param seperated parameters
	 */
	public void diversifykey(byte [] param)
	{
		byte [] leftwkey = new byte[8];
		byte [] rightwkey = new byte[8];

		System.arraycopy(this.workkey, 0, leftwkey, 0, leftwkey.length);
		System.arraycopy(this.workkey, leftwkey.length, rightwkey, 0, leftwkey.length);

		byte [] base = new byte[8];
		zero(base, (byte)0xFF);
		byte [] tempParam = xor(param,base,8);

		byte [] wkey1 = new byte[8];
		byte [] wkey2 = new byte[8];

		// 3des加密码
		des_block(param, wkey1, leftwkey, true);
		des_block(wkey1, param, rightwkey, false);
		des_block(param, wkey1, leftwkey, true);

		// 3des加密码
		des_block(tempParam, wkey2, leftwkey, true);
		des_block(wkey2, tempParam, rightwkey, false);
		des_block(tempParam, wkey2, leftwkey, true);

		// 新产生的工作密钥
		System.arraycopy(wkey1, 0, workkey, 0, wkey1.length);
		System.arraycopy(wkey2, 0, workkey, wkey1.length, wkey2.length);

		// 测试生成子密钥
		System.out.print("生成子密钥:");
		printHexString(workkey);
	}

	/**
	 * Get normal MAC
	 * @param data data for MAC
	 * @param bsingle it is true for DES,false for 3DES
	 * @return result
	 */
	public byte [] getDataMac(byte [] data,boolean bsingle)
	{
		byte [] leftwkey = new byte[8];
		byte [] rightwkey = new byte[8];

		System.arraycopy(this.workkey, 0, leftwkey, 0, leftwkey.length);
		System.arraycopy(this.workkey, leftwkey.length, rightwkey, 0, leftwkey.length);

		byte [] init = new byte[8];
		zero(init, (byte)0);
		byte [] tmpdata = fillbase(data, (byte)0, 8, false);
		byte[] block = new byte[8];

		for (int i = 0; i < tmpdata.length; i = i + 8) {
			System.arraycopy(tmpdata, i, block, 0, 8);
			byte [] tmp = xor(init, block, 8);
			des_block(tmp, init, leftwkey, true);
		}

		if (!bsingle) {
			des_block(init, block, rightwkey, false);
			des_block(block, init, leftwkey, true);
		}

		return init;
	}

	/**
	 * Get the cipher of the PIN
	 * @param passwd password
	 * @param cardno Card No. of the user and it will be null if no card no.
	 * @param bsingle if it is true ,it will use single DES
	 * @return result
	 * @throws UnsupportedEncodingException This exception will be thrown if the string is unsuitable
	 */
	public byte [] getPinEncode(String passwd,String cardno,boolean bsingle) throws UnsupportedEncodingException
	{
		if (passwd == null || passwd.length() == 0) return null;

		byte [] leftwkey = new byte[8];
		byte [] rightwkey = new byte[8];

		System.arraycopy(this.workkey, 0, leftwkey, 0, leftwkey.length);
		System.arraycopy(this.workkey, leftwkey.length, rightwkey, 0, leftwkey.length);

		int pl = passwd.length();
		String lenstr = ""+pl;
		if (lenstr.length() != 2) {
			lenstr = "0"+lenstr;
		}
		String pwdstr = lenstr+passwd;

		// 密码补位处理
		if (pwdstr.length()%2 != 0) {
			pwdstr+="F";
		}

		byte [] block = new byte[8];
		byte [] dpwd = null;

		if ( cardno != null && cardno.length() > 13 )
		{

			byte [] cad = HexCode.unHex( cardno.substring(cardno.length()-13, cardno.length() - 1) );

			cad = fillbase(cad,(byte)0, 8, true);

			byte [] pwdbcd = fillbase(HexCode.unHex(pwdstr), (byte)0xFF, 8, false);
			dpwd = xor(pwdbcd, cad, 8);
		}
		else
		{
			dpwd = fillbase(HexCode.unHex(pwdstr), (byte)0xFF, 8, false);
		}

		// printHexString(dpwd);

		if (bsingle) {
			des_block(dpwd, block, leftwkey, true);
			} else {
			des_block(dpwd, block, leftwkey, true);
			des_block(block, dpwd, rightwkey, false);
			des_block(dpwd, block, leftwkey, true);
		}

		// printHexString(block);

		return block;
	}

	/**
	 * Get the simple of the PIN获取PIN明文信息
	 * @param encode code of encryption
	 * @param cardno Card No. of the user and it will be null if no card no.
	 * @param bsingle if it is false ,it will use 3DES
	 * @return the simple of the PIN
	 * @throws UnsupportedEncodingException This exception will be thrown if the string is unsuitable
	 */
	public String getPinDecode(byte [] encode,String cardno,boolean bsingle) throws UnsupportedEncodingException
	{
		byte [] leftwkey = new byte[8];
		byte [] rightwkey = new byte[8];

		System.arraycopy(this.workkey, 0, leftwkey, 0, leftwkey.length);
		System.arraycopy(this.workkey, leftwkey.length, rightwkey, 0, leftwkey.length);

		// 检查加密参数
		if(encode == null) {
			return null;
		}

		byte [] block = new byte[8];

		// 接密处理
		if (bsingle) {
			des_block(encode, block, leftwkey, false);
			} else {
			des_block(encode, block, leftwkey, false);
			des_block(block, encode, rightwkey, true);
			des_block(encode, block, leftwkey, false);
		}

		byte [] dpwd = null;

		if ( cardno != null && cardno.length() > 13 )
		{
			byte [] cad = HexCode.unHex(cardno.substring(cardno.length()-13, cardno.length() - 1));
			cad = fillbase(cad,(byte)0, 8, true);
			dpwd = xor(block, cad, 8);
		}
		else
		{
			dpwd = new byte[8];
			System.arraycopy(block, 0, dpwd, 0, 8);
		}

		// 获取密码
		String pwdstr = HexCode.toHex(dpwd);
		// System.out.println(pwdstr);
		int pwdlens = Integer.parseInt(pwdstr.substring(0,2));
		// System.out.println(pwdlens);

		return pwdstr.substring(2,2+pwdlens);
	}

	/**
	 * Counting according to ANSIX99-MAC PPOC
	 * @param data data for MAC
	 * @param bsingle true or false for DES operation
	 * @return result	 */
	public byte [] getDataANSIX99(byte [] data,boolean bsingle)
	{
		byte [] leftwkey = new byte[8];
		byte [] rightwkey = new byte[8];

		System.arraycopy(this.workkey, 0, leftwkey, 0, leftwkey.length);
		System.arraycopy(this.workkey, leftwkey.length, rightwkey, 0, leftwkey.length);

		byte [] init = new byte[8];
		zero(init, (byte)0);
		byte [] tmpdata = fillbase(data, (byte)0x80, 1, false);
		tmpdata = fillbase(tmpdata, (byte)0, 8, false);
		byte[] block = new byte[8];

		for (int i = 0; i < tmpdata.length; i = i + 8) {
			System.arraycopy(tmpdata, i, block, 0, 8);
			byte [] tmp = xor(init, block, 8);
			des_block(tmp, init, leftwkey, true);
		}

		if (!bsingle) {
			des_block(init, block, rightwkey, false);
			des_block(block, init, leftwkey, true);
		}

		return init;
	}

	/**
	 * Array for encryptiong
	 * @param decodeBody simple data
	 * @return encrypted array,it is a multiple of 8
	 */
	public byte [] single_enc(byte[] decodeBody)
	{
		//补足8位，不足补空格
		decodeBody = fillbase(decodeBody, (byte)0x00, 8, false);
		byte[] encodeBody = new byte[decodeBody.length];

		//明文
		byte[] bDecode = new byte[8];
		//密文
		byte[] bEncode = new byte[8];

		byte[] workKeyTemp = new byte[8];
		System.arraycopy(workkey, 0, workKeyTemp, 0, 8);

		for (int i = 0; i < decodeBody.length; i = i + 8) {
			System.arraycopy(decodeBody, i, bDecode, 0, 8);
			des_block(bDecode, bEncode, workKeyTemp, true);
			System.arraycopy(bEncode, 0, encodeBody, i, 8);
		}
		return encodeBody;
	}

	/**
	 * Array for decryptiong
	 * @param encodeBody the cipher data
	 * @return decrypted array,it is a multiple of 8
	 *
	 */
	public byte [] single_dec(byte[] encodeBody)
	{
		//补足8位，不足补空格
		encodeBody = fillbase(encodeBody, (byte)0x00, 8,false);
		byte[] decodeBody = new byte[encodeBody.length];

		//明文
		byte[] bDecode = new byte[8];
		//密文
		byte[] bEncode = new byte[8];

		byte[] workKeyTemp = new byte[8];
		System.arraycopy(workkey, 0, workKeyTemp, 0, 8);

		for (int i = 0; i < encodeBody.length; i = i + 8) {
			System.arraycopy(encodeBody, i, bEncode, 0, 8);
			des_block(bEncode, bDecode, workKeyTemp, false);
			System.arraycopy(bDecode, 0, decodeBody, i, 8);
		}
		return decodeBody;
	}

	/**
	 * Array for encryptiong
	 * @param decodeBody the simple data
	 * @return the cipher
	 */
	public byte [] tri_enc(byte[] decodeBody)
	{
		byte [] leftwkey = new byte[8];
		byte [] rightwkey = new byte[8];

		System.arraycopy(this.workkey, 0, leftwkey, 0, leftwkey.length);
		System.arraycopy(this.workkey, leftwkey.length, rightwkey, 0, leftwkey.length);

		//补足8位，不足补空格
		decodeBody = fillbase(decodeBody, (byte)0x00, 8, false);
		byte[] encodeBody = new byte[decodeBody.length];

		//明文
		byte[] bDecode = new byte[8];
		//密文
		byte[] bEncode = new byte[8];

		for (int i = 0; i < decodeBody.length; i = i + 8) {
			System.arraycopy(decodeBody, i, bDecode, 0, 8);
			des_block(bDecode, bEncode, leftwkey, true);
			des_block(bEncode, bDecode, rightwkey, false);
			des_block(bDecode, bEncode, leftwkey, true);
			System.arraycopy(bEncode, 0, encodeBody, i, 8);
		}
		return encodeBody;
	}

	/**
	 * Array for decryptiong
	 * @param encodeBody the cipher data
	 * @return the simple data
	 */
	public byte [] tri_dec(byte[] encodeBody)
	{
		byte [] leftwkey = new byte[8];
		byte [] rightwkey = new byte[8];

		System.arraycopy(this.workkey, 0, leftwkey, 0, leftwkey.length);
		System.arraycopy(this.workkey, leftwkey.length, rightwkey, 0, leftwkey.length);

		//补足8位，不足补空格
		encodeBody = fillbase(encodeBody, (byte)0x00, 8,false);
		byte[] decodeBody = new byte[encodeBody.length];

		//明文
		byte[] bDecode = new byte[8];
		//密文
		byte[] bEncode = new byte[8];

		for (int i = 0; i < encodeBody.length; i = i + 8) {
			System.arraycopy(encodeBody, i, bEncode, 0, 8);
			des_block(bEncode, bDecode, leftwkey, false);
			des_block(bDecode, bEncode, rightwkey, true);
			des_block(bEncode, bDecode, leftwkey, false);
			System.arraycopy(bDecode, 0, decodeBody, i, 8);
		}
		return decodeBody;
	}

	// private static byte[] iv = {1,2,3,4,5,6,7,8};
	private static byte[] iv = {0,0,0,0,0,0,0,0};

	/**
	 * DES algorithm
	 * @param input  input,it is processed 8 bytes输入,只处理8字节
	 * @param output output,it is processed 8 bytes输出,同输入
	 * @param deskey key,it is processed 8 bytes
	 * @param enc （true.for encryption   false for decryption）
	 */
	public void des_block(byte[] input, byte[] output, byte[] deskey, boolean enc) {
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(deskey, "DES");
        Cipher cipher;
		try {
			// cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher = Cipher.getInstance("DES/CBC/NoPadding");

			if (enc)
	        	cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
	        else cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
	        byte[] encryptedData = cipher.doFinal(input);

	        System.arraycopy(encryptedData, 0, output, 0, encryptedData.length);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
