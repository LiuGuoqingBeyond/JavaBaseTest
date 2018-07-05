package com.uppayplugin.unionpay.libcommon.rsa;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

public class RSACoder2 extends Coder2 {
	 public static final String KEY_ALGORITHM = "RSA";  
	    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";  
	  
	    private static final String PUBLIC_KEY = "RSAPublicKey";  
	    private static final String PRIVATE_KEY = "RSAPrivateKey";  
	  
	    /** 
	     * 用私钥对信息生成数字签名 
	     *  
	     * @param data 
	     *            加密数据 
	     * @param privateKey 
	     *            私钥 
	     *  
	     * @return 
	     * @throws Exception 
	     */  
	    public static String sign(byte[] data, String privateKey) throws Exception {  
	        // 解密由base64编码的私钥  
	        byte[] keyBytes = Coder2.decryptBASE64(privateKey);
	  
	        // 构造PKCS8EncodedKeySpec对象  
	        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
	  
	        // KEY_ALGORITHM 指定的加密算法  
	        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
	  
	        // 取私钥匙对象  
	        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);  
	  
	        // 用私钥对信息生成数字签名  
	        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);  
	        signature.initSign(priKey);  
	        signature.update(data);  
	  
	        return Coder2.encryptBASE64(signature.sign());
	    }  
	  
	    /** 
	     * 校验数字签名 
	     *  
	     * @param data 
	     *            加密数据 
	     * @param publicKey 
	     *            公钥 
	     * @param sign 
	     *            数字签名 
	     *  
	     * @return 校验成功返回true 失败返回false 
	     * @throws Exception 
	     *  
	     */  
	    public static boolean verify(byte[] data, String publicKey, String sign)  
	            throws Exception {  
	  
	        // 解密由base64编码的公钥  
	        byte[] keyBytes = Coder2.decryptBASE64(publicKey);
	  
	        // 构造X509EncodedKeySpec对象  
	        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);  
	  
	        // KEY_ALGORITHM 指定的加密算法  
	        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
	  
	        // 取公钥匙对象  
	        PublicKey pubKey = keyFactory.generatePublic(keySpec);  
	  
	        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);  
	        signature.initVerify(pubKey);  
	        signature.update(data);  
	  
	        // 验证签名是否正常  
	        return signature.verify(Coder2.decryptBASE64(sign));
	    }  
	  
	    /** 
	     * 解密<br> 
	     * 用私钥解密 
	     *  
	     * @param data 
	     * @param key 
	     * @return 
	     * @throws Exception 
	     */  
	    public static byte[] decryptByPrivateKey(byte[] data, String key)  
	            throws Exception {  
	        // 对密钥解密  
	        byte[] keyBytes = Coder2.decryptBASE64(key);
	  
	        // 取得私钥  
	        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
	        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
	        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);  
	  
	        // 对数据解密  
	        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	        cipher.init(Cipher.DECRYPT_MODE, privateKey);  
	  
	        return cipher.doFinal(data);  
	    }  
	  
	    /** 
	     * 解密<br> 
	     * 用公钥解密 
	     *  
	     * @param data 
	     * @param key 
	     * @return 
	     * @throws Exception 
	     */  
	    public static byte[] decryptByPublicKey(byte[] data, String key)  
	            throws Exception {  
	        // 对密钥解密  
	        byte[] keyBytes = Coder2.decryptBASE64(key);
	  
	        // 取得公钥  
	        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);  
	        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
	        Key publicKey = keyFactory.generatePublic(x509KeySpec);  
	  
	        // 对数据解密  
	        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
	        cipher.init(Cipher.DECRYPT_MODE, publicKey);  
	  
	        return cipher.doFinal(data);  
	    }  
	  
	    /** 
	     * 加密<br> 
	     * 用公钥加密 
	     *  
	     * @param data 
	     * @param key 
	     * @return 
	     * @throws Exception 
	     */  
	    public static byte[] encryptByPublicKey(byte[] data, String key)  
	            throws Exception {  
	        // 对公钥解密  
	        byte[] keyBytes = Coder2.decryptBASE64(key);
	  
	        // 取得公钥  
	        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);  
	        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
	        Key publicKey = keyFactory.generatePublic(x509KeySpec);  
	  
	        // 对数据加密  
	        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
	        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
	  
	        return cipher.doFinal(data);  
	    }  
	  
	    /** 
	     * 加密<br> 
	     * 用私钥加密 
	     *  
	     * @param data 
	     * @param key 
	     * @return 
	     * @throws Exception 
	     */  
	    public static byte[] encryptByPrivateKey(byte[] data, String key)  
	            throws Exception {  
	        // 对密钥解密  
	        byte[] keyBytes = Coder2.decryptBASE64(key);
	  
	        // 取得私钥  
	        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
	        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
	        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);  
	  
	        // 对数据加密  
	        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
	        cipher.init(Cipher.ENCRYPT_MODE, privateKey);  
	  
	        return cipher.doFinal(data);  
	    }  
	  
	    /** 
	     * 取得私钥 
	     *  
	     * @param keyMap 
	     * @return 
	     * @throws Exception 
	     */  
	    public static String getPrivateKey(Map<String, Object> keyMap)  
	            throws Exception {  
	        Key key = (Key) keyMap.get(PRIVATE_KEY);  
	  
	        return Coder2.encryptBASE64(key.getEncoded());
	    }  
	  
	    /** 
	     * 取得公钥 
	     *  
	     * @param keyMap 
	     * @return 
	     * @throws Exception 
	     */  
	    public static String getPublicKey(Map<String, Object> keyMap)  
	            throws Exception {  
	        Key key = (Key) keyMap.get(PUBLIC_KEY);  
	  
	        return Coder2.encryptBASE64(key.getEncoded());
	    }  
	  
	    /** 
	     * 初始化密钥 
	     *  
	     * @return 
	     * @throws Exception 
	     */  
	    public static Map<String, Object> initKey() throws Exception {  
	        KeyPairGenerator keyPairGen = KeyPairGenerator  
	                .getInstance(KEY_ALGORITHM);  
	        keyPairGen.initialize(1024);  
	  
	        KeyPair keyPair = keyPairGen.generateKeyPair();  
	  
	        // 公钥  
	        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  
	  
	        // 私钥  
	        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();  
	  
	        Map<String, Object> keyMap = new HashMap<String, Object>(2);  
	  
	        keyMap.put(PUBLIC_KEY, publicKey);  
	        keyMap.put(PRIVATE_KEY, privateKey);  
	        return keyMap;  
	    }  
	    
	    public static void main(String[] args) throws Exception {
	    	/*String str = "abc";
	    	String privateKey = "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";
	    	String signature = RSACoder.sign(str.getBytes(), privateKey).replaceAll("\r\n", "");
	    	System.out.println(signature);*/
	    	
	    	
	    	//客户端使用服务端公钥加密
	    	/*String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwUje6Zui/mX7Uns+uTb8VGQEK4Qoq4veeobUljfsiNuTg8FQECw6dMUje6pic/gwW2SGqzQJjfP1YqFc2FCHOsoCGPNazU74kYmRzpL1m1jTwatS8Km7dE9oENyhpItXMYemuEV9RTR/z8OsQbPVo/mp7axYYJtkmcR3P9eeAFkOTNO51N775JXTRbchceTDiyxNAg4Nq/fHjBOzPx5VmgYBY2qF1YNZqGZ0/kIU31Ei9z7aLjM5aPuGYTDEe5nz0E3zXizfpAPoNBlIX9rgI7rhhye1jTRwzoOVIsx7JGF9QeWoir0krzLzF6Yt7Jfn9GKO5PzC3KsnUjJDWQfC7QIDAQAB";
	    	String randomKey = "wrkhsfksfhksdfkjhkhs24234lkl";
	    	byte[] signStr = RSACoder.encryptByPublicKey(randomKey.getBytes(), publicKey);
	    	String base64 = RSACoder.encryptBASE64(signStr);
	    	System.out.println("服务端公钥加密并base64=" + base64);
	    	
	    	
	    	//服务端使用服务端私钥解密
	    	String privateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDBSN7pm6L+ZftSez65NvxUZAQrhCiri956htSWN+yI25ODwVAQLDp0xSN7qmJz+DBbZIarNAmN8/VioVzYUIc6ygIY81rNTviRiZHOkvWbWNPBq1Lwqbt0T2gQ3KGki1cxh6a4RX1FNH/Pw6xBs9Wj+antrFhgm2SZxHc/154AWQ5M07nU3vvkldNFtyFx5MOLLE0CDg2r98eME7M/HlWaBgFjaoXVg1moZnT+QhTfUSL3PtouMzlo+4ZhMMR7mfPQTfNeLN+kA+g0GUhf2uAjuuGHJ7WNNHDOg5UizHskYX1B5aiKvSSvMvMXpi3sl+f0Yo7k/MLcqydSMkNZB8LtAgMBAAECggEBALk7s92feUJiy8uNL1xjNMlPnKtzmWJlxg4Yny1jZPz3qdSP88G8ET2NHGdMr9PCG93KZkE2up1fGmCukgMAkcimhgIRXoj50L/xXlnhfx2ksDOfk/nuQCbEXtAiEYtWunZOMVnRxq8HFCIxvSiuRuVBxDdxfrT+1xeCMGJvqg1bn04ATVRlnVMtlLiBAYgLaNC5LYArhX+9pcy5Z/OyvFKKUzuZqIZW+YdG33tNQmVwTDqcbHUGGOszcJAUyTHVOTs0pKLkyhWqmnhsx9kUbARJ/malhdt5EtCp51vT/CvDVQUxXRY1bAxqeCcvlzojRkdg/9kXYJZw17gSq6FS50UCgYEA9KA8TLL6+LvzXnSwi1Q0PhY4RTfQVbMlf12lfoEwxubYVbU13YHuo4exYT41U3raj+FZkf0hYhI8mqlINLZMUJFLySc0GmiW51baaaV8LZzSGHVqacH52mZyhQzx0Avxtnfd5tVGqyh7bHpRzzPMDzdE4rF30uWmp4hflf9iaLMCgYEAykWGGh9KWVzJgS25BkIXo9vpmV3CQjMWQT4W7aw3DBeaktRdji3Y9+N58Z8OSxagHkfT6fpJSXT2sHSjFulOAAvX3Gf70cF5Sa514eVG0eSQI2LkefVauKuWHWtCxd4VNpZJy2c8Clm6a+oLa6fyvfPrnmQ2zmVZNrLBYkyYtd8CgYEAvlxe8DSrVpoz9YIKzDGXgeUansqjJb2F5YpDOaFhCrcjC/c61ofA46K5ZvnY14CXyjVL1W5M5xtX6+blmJdv5ZZbikomopJXM+kDiAjODFSnrX7ZczkOrGTioyUCm6pfy2WV2YaIOrMC8NRzCzG8ju6sfVgq7YPmXyKhd4FH3D8CgYA9W2macvNyuBrH/L6rj1Dse8EYrMBIpIaTs+nwntTz7hUNhS37+xxd1bsf+Ee1r19S0CcQSsQAbGKIlFkEkjD/ApiwWzqgHjhUiX9ozsv0z4iJs23rMOocKrCWq/rNWtjEfkJUWTR1T1KleZGNTpzr0hofKfcWFNhDJ2ZSEzofsQKBgQDZXi2wN4gVvRjfc5Xr8QFRDpjqg5RYYcr3jNaEBQzjezM9rPm/6dApwcia2L9WNTBBk3r0xt1w/ubehJDqp7a4BzL0efvmVL3iOQpfRvlhev0H90IgdmHdPXG6wiJhLDqk23xxgj8KW9PZCIaAOp71/6MrcJ5m1pqDOkdfYc4VGg==";
	    	byte[] decryptByte = RSACoder.decryptBASE64(base64);
	    	byte[] decrptStr = RSACoder.decryptByPrivateKey(decryptByte,privateKey);
	    	
	    	System.out.println(new String(decrptStr,"UTF-8"));
	    	
	    	
	    	
	    	//服务端使用客户端公钥加密
	    	String client_public_key = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAz46OBie7GG1CbPvpbySoRrkRifaFNFR0gWLg5Zsu8kbXyS+WAr1Uwm0/C1JTBkKMfKdd9u6B3xOCIw/TeybYzoZmG8slvVNFxUzHd0ixy2mxm/63dMQqW92UYXHQDmX3TMYBMPKv3Fo+wVLMHcD47wt09pjCEzvgcjA+TvXxNFO3Mfp16PNGhEAmGxoCtjj+G2ANetRwYYPkUfIo0FOrsu9bvr/2som0C/d0wF7boa5fotCPqJTH1PhUp9maSor4Dd40V3/isQMoAkZo8JwAUlbeOVrbamVE07YzJUmCKE+q/KxoulDkFTagVAJ/Wo6Zvojhk0YgaIb9/Gzo+4ZepBo9Xa1Mwp4ku/ZWv0LVDk4/WEqabQE1qWnzz0XaoYAYzqUX8my4jgksIdbq+EQBHLexsf0V+KBGVLEg0CuGuPshXyzcSqSkjbxoUKVYGag4lBdZuyOp7pAISHCJGs1y/3DSXulqUZH/xS9qEKxsB48Ujow1bvJxegBbyCIGS9L75qxjJCNahl/nKq2eMvYQL/RdFndKZU840gY5SryquTSqZRqJOPgCXEZLe5tfDyYjFnFNxF7iF5biLBIYyllBUI9lzAk4hbRct6wYnzqZWq/M1Ka6XMnrD/lRnKJzP4ifDPdraMrpRgMLNibharO14kywK+qImti59jr8/p0LsnUCAwEAAQ==";
	    	signStr = RSACoder.encryptByPublicKey(randomKey.getBytes(), client_public_key);
	    	base64 = RSACoder.encryptBASE64(signStr);
	    	System.out.println("客户端公钥加密并base64=" + base64);
	    	//客服端使用客户端私钥解密
	    	String server_private_key = "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";
	    	decryptByte = RSACoder.decryptBASE64(base64);
	    	decrptStr = RSACoder.decryptByPrivateKey(decryptByte,server_private_key);
	    	
	    	System.out.println(new String(decrptStr,"UTF-8"));*/
	    	
	    	String privateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDBSN7pm6L+ZftSez65NvxUZAQrhCiri956htSWN+yI25ODwVAQLDp0xSN7qmJz+DBbZIarNAmN8/VioVzYUIc6ygIY81rNTviRiZHOkvWbWNPBq1Lwqbt0T2gQ3KGki1cxh6a4RX1FNH/Pw6xBs9Wj+antrFhgm2SZxHc/154AWQ5M07nU3vvkldNFtyFx5MOLLE0CDg2r98eME7M/HlWaBgFjaoXVg1moZnT+QhTfUSL3PtouMzlo+4ZhMMR7mfPQTfNeLN+kA+g0GUhf2uAjuuGHJ7WNNHDOg5UizHskYX1B5aiKvSSvMvMXpi3sl+f0Yo7k/MLcqydSMkNZB8LtAgMBAAECggEBALk7s92feUJiy8uNL1xjNMlPnKtzmWJlxg4Yny1jZPz3qdSP88G8ET2NHGdMr9PCG93KZkE2up1fGmCukgMAkcimhgIRXoj50L/xXlnhfx2ksDOfk/nuQCbEXtAiEYtWunZOMVnRxq8HFCIxvSiuRuVBxDdxfrT+1xeCMGJvqg1bn04ATVRlnVMtlLiBAYgLaNC5LYArhX+9pcy5Z/OyvFKKUzuZqIZW+YdG33tNQmVwTDqcbHUGGOszcJAUyTHVOTs0pKLkyhWqmnhsx9kUbARJ/malhdt5EtCp51vT/CvDVQUxXRY1bAxqeCcvlzojRkdg/9kXYJZw17gSq6FS50UCgYEA9KA8TLL6+LvzXnSwi1Q0PhY4RTfQVbMlf12lfoEwxubYVbU13YHuo4exYT41U3raj+FZkf0hYhI8mqlINLZMUJFLySc0GmiW51baaaV8LZzSGHVqacH52mZyhQzx0Avxtnfd5tVGqyh7bHpRzzPMDzdE4rF30uWmp4hflf9iaLMCgYEAykWGGh9KWVzJgS25BkIXo9vpmV3CQjMWQT4W7aw3DBeaktRdji3Y9+N58Z8OSxagHkfT6fpJSXT2sHSjFulOAAvX3Gf70cF5Sa514eVG0eSQI2LkefVauKuWHWtCxd4VNpZJy2c8Clm6a+oLa6fyvfPrnmQ2zmVZNrLBYkyYtd8CgYEAvlxe8DSrVpoz9YIKzDGXgeUansqjJb2F5YpDOaFhCrcjC/c61ofA46K5ZvnY14CXyjVL1W5M5xtX6+blmJdv5ZZbikomopJXM+kDiAjODFSnrX7ZczkOrGTioyUCm6pfy2WV2YaIOrMC8NRzCzG8ju6sfVgq7YPmXyKhd4FH3D8CgYA9W2macvNyuBrH/L6rj1Dse8EYrMBIpIaTs+nwntTz7hUNhS37+xxd1bsf+Ee1r19S0CcQSsQAbGKIlFkEkjD/ApiwWzqgHjhUiX9ozsv0z4iJs23rMOocKrCWq/rNWtjEfkJUWTR1T1KleZGNTpzr0hofKfcWFNhDJ2ZSEzofsQKBgQDZXi2wN4gVvRjfc5Xr8QFRDpjqg5RYYcr3jNaEBQzjezM9rPm/6dApwcia2L9WNTBBk3r0xt1w/ubehJDqp7a4BzL0efvmVL3iOQpfRvlhev0H90IgdmHdPXG6wiJhLDqk23xxgj8KW9PZCIaAOp71/6MrcJ5m1pqDOkdfYc4VGg==";
	    	String str = "6214467880010090936";
	    	System.out.println(RSACoder.encryptBASE64(RSACoder.encryptByPrivateKey(str.getBytes(), privateKey)));
	    	
	    	for (int i = 0; i < str.getBytes().length; i++) {
				System.out.print(i);
			}
	    	System.out.println();
	    	for (int i = 0; i < RSACoder.encryptByPrivateKey(str.getBytes(), privateKey).length; i++) {
	    		System.out.print(i);
			}
	    	System.out.println();
	    	System.out.println(RSACoder.encryptBASE64(RSACoder.encryptByPrivateKey(str.getBytes(), privateKey)));
	    	
	    	
//	    	String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwUje6Zui/mX7Uns+uTb8VGQEK4Qoq4veeobUljfsiNuTg8FQECw6dMUje6pic/gwW2SGqzQJjfP1YqFc2FCHOsoCGPNazU74kYmRzpL1m1jTwatS8Km7dE9oENyhpItXMYemuEV9RTR/z8OsQbPVo/mp7axYYJtkmcR3P9eeAFkOTNO51N775JXTRbchceTDiyxNAg4Nq/fHjBOzPx5VmgYBY2qF1YNZqGZ0/kIU31Ei9z7aLjM5aPuGYTDEe5nz0E3zXizfpAPoNBlIX9rgI7rhhye1jTRwzoOVIsx7JGF9QeWoir0krzLzF6Yt7Jfn9GKO5PzC3KsnUjJDWQfC7QIDAQAB";
//	    	String str = "ZmhnKyYaDu6uFq2+3khBqD+10CqHqaA5lTMoQ1f8TkmStHB2hoktj4yeAy9Z8mN0AukLPLUb8SpPPAUvQ8OWNCZ9q2OBARC9Re64ICPWYZTa3NePDn8/cvvQJn184hRYTLhpktDZNvoTLOISvyZpHXIaS79UFVfmal5wXheb33IoNI7W6+MCdq2Eez/m2RD9zYLBWdVO3NykFkc+C8BNWdOIkEEPGhXZzkKlhpMAQir37iukIZylPnmAT75MnUu5Qe9LDKy3fkwpVvER9EPu6XJCF8Dhbk9tTSOQZpxMCGJrWbuo+wHiPQaCwFCKRMcxHUCuqWZiuhPAJrtalW87gw==";
//	    	byte[] decryptByte = RSACoder.decryptBASE64(str);
//	    	
//	    	byte[] decrptStr = RSACoder.decryptByPublicKey(decryptByte,publicKey);
//	    	System.out.println(new String(decrptStr,"UTF-8"));
	    	
	    	
	    	
		}
}
