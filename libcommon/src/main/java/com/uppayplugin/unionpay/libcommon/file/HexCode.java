package com.uppayplugin.unionpay.libcommon.file;

import java.io.UnsupportedEncodingException;

public class HexCode {

	public static byte[]hex2byte(byte[]b){
		if((b.length%2)!=0)
		throw new IllegalArgumentException("长度不是偶数");
		byte[]b2=new byte[b.length/2];
		for(int n=0;n<b.length;n+=2){
			String item=new String(b,n,2);
			//两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
			b2[n/2]=(byte)Integer.parseInt(item,16);
		}
		return b2;
	}

	public static byte[] unHex(String sRcv) throws UnsupportedEncodingException {
		return hex2byte(sRcv.getBytes("GBK"));
	}

	/**
	 * Combines two ASCII string(HEX string) to one byte
	 * @param bRcv ASCII strings
	 * @return HEX strings
	 */
	public static String toHex(byte[] bRcv) {
		return toHex(bRcv,0,bRcv.length);
	}

	/**
	 * Combines two ASCII string(HEX string) to one byte
	 * @param bRcv ASCII strings
	 * @param offset position
	 * @param length length
	 * @return HEX string
	 */
	public static String toHex(byte[] bRcv,int offset,int length) {

		String sRet = "";
		// 计算真实可以处理的长度
		int reallength = (offset+length > bRcv.length)?bRcv.length-offset:(offset+length);
		for (int i = offset; i < reallength; i++) {
			String sHex = Integer.toHexString(bRcv[i] & 0xFF);
			if (sHex.length() == 1) {
				sHex = '0' + sHex;
			}
			sRet += sHex.toUpperCase();
		}
		return sRet;
	}

}
