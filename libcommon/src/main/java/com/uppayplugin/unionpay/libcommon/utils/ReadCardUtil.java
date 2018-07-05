package com.uppayplugin.unionpay.libcommon.utils;

import java.nio.ByteBuffer;

/**
 * @description 刷卡操作
 * @author King_wangyao
 * @date 2014-5-20
 * @version 1.0.0
 *
 */
public class ReadCardUtil {

	/**
	 * ANSIX9.8格式
	 *
	 * @param strPassword
	 * @param strCardNo
	 * @return
	 */
	public static byte[] pinBlock(String strPassword, String strCardNo) {
		// PIN BLOCK - 8位
		byte[] bytesPin = new byte[] { (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF };
		bytesPin[0] = (byte) strPassword.length();
		byte[] bcdPwd = str2bcd(strPassword);
		System.arraycopy(bcdPwd, 0, bytesPin, 1, bcdPwd.length);
		// PAN - 这里没算了前面的0，是6位
		int nLength = strCardNo.length();
		String strCardNo12 = strCardNo.substring(nLength - 13, nLength - 1);
		byte[] bcdPAN = str2bcd(strCardNo12);
		// 异或
		byte[] bytesPinBlock = new byte[8];
		bytesPinBlock[0] = bytesPin[0];
		bytesPinBlock[1] = bytesPin[1];
		for (int i = 2; i < 8; i++) {
			bytesPinBlock[i] = (byte) (bytesPin[i] ^ bcdPAN[i - 2]);
		}
		return bytesPinBlock;
	}

	/**
	 * 功能：10进制串转为BCD码
	 *
	 * @param str
	 *            待转的字符串
	 * @return bcd码编码到的byte数组
	 */
	public static byte[] str2bcd(String str) {
		if (str.length() % 2 != 0) {
			str = "0" + str;
		}

		StringBuffer sb = new StringBuffer(str);
		ByteBuffer bb = ByteBuffer.allocate(str.length() / 2);

		int i = 0;
		while (i < str.length()) {
			bb.put((byte) ((Integer.parseInt(sb.substring(i, i + 1))) << 4 | Integer.parseInt(sb.substring(i + 1, i + 2))));
			i = i + 2;
		}
		return bb.array();
	}

	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int i = 0; i < b.length; i++) {
			stmp = Integer.toHexString(b[i] & 0xFF);
			if (stmp.length() == 1) {
				hs += "0" + stmp;
			} else {
				hs += stmp;
			}
		}
		return hs.toUpperCase();
	}

}
