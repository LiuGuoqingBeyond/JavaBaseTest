package com.sinopaylib.utils;

import java.security.MessageDigest;

/**
 * @author King_wangyao
 */
public class MD5Util {
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };

	/**
	 * 转换字节数组为16进制字串
	 *
	 * @param b
	 *            字节数组
	 * @return 16进制字串
	 */
	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * MD5 摘要计算(byte[]).
	 *
	 * @param src
	 *            byte[]
	 * @throws Exception
	 * @return byte[] 16 bit digest
	 */
	public static byte[] md5Digest(byte[] src) throws Exception {
		MessageDigest alg = MessageDigest
				.getInstance("MD5"); // MD5 is 16 bit message digest

		return alg.digest(src);
	}

	/**
	 * MD5 摘要计算(String).
	 *
	 * @param src
	 *            String
	 * @throws Exception
	 * @return String
	 */
	public static String md5Digest(String src) throws Exception {
		return byteArrayToHexString(md5Digest(src.getBytes()));
	}

	public static String generateMd5String(String s) {
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	/** Test crypt */
	public static void main(String[] args) {
		try {
			// 获得的明文数据
			String desStr = "MERCHANTID=2300000003&ORDERSEQ=5465646&ORDERDATE=20100919&ORDERAMOUNT=1";
			System.out.println("原文字符串：" + desStr);
			// 生成MAC
			String MAC = MD5Util.md5Digest(desStr);
			System.out.println("     MAC：" + MAC);
			// 使用key值生成 SIGN
			String keyStr = "123456";// 使用固定key
			// 获得的明文数据
			desStr = "UPTRANSEQ=20080101000001&MERCHANTID=0250000001&ORDERID=2006050112564931556&PAYMENT=10000&RETNCODE=00&RETNINFO=00&PAYDATE =20060101";
			// 将key值和明文数据组织成一个待签名的串
			desStr = desStr + "&KEY：" + keyStr;
			System.out.println("原文字符串：" + desStr);
			// 生成 SIGN
			String SIGN = md5Digest(desStr);
			System.out.println("    SIGN：" + SIGN);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
