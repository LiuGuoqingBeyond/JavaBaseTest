package com.uppayplugin.unionpay.javabasetest.utils.device;

/**
 * @project：xzfpos
 * @author：- Richard on 2017/11/28 16:01
 * @email：zhangh@qtopay.cn
 */
public class ByteUtils {

    /**
     * 十六进制转二进制字符串
     *
     * @param bArray 十六进制字节数组
     * @return
     */
    public static String bytes2BinaryStr(byte[] bArray) {
        String[] binaryArray = {"0000", "0001", "0010", "0011",
                "0100", "0101", "0110", "0111",
                "1000", "1001", "1010", "1011",
                "1100", "1101", "1110", "1111"};
        StringBuilder outStr = new StringBuilder();
        int pos = 0;
        for (byte b : bArray) {
            //高四位
            pos = (b & 0xF0) >> 4;
            outStr.append(binaryArray[pos]);
            //低四位
            pos = b & 0x0F;
            outStr.append(binaryArray[pos]);
        }
        return outStr.toString();
    }

    public static byte[] str2bytes(String src) {
        if (src == null || src.length() == 0 || src.length() % 2 != 0) {
            return null;
        }
        int nSrcLen = src.length();
        byte byteArrayResult[] = new byte[nSrcLen / 2];
        StringBuffer strBufTemp = new StringBuffer(src);
        String strTemp;
        int i = 0;
        while (i < strBufTemp.length() - 1) {
            strTemp = src.substring(i, i + 2);
            byteArrayResult[i / 2] = (byte) Integer.parseInt(strTemp, 16);
            i += 2;
        }
        return byteArrayResult;
    }

    public static String convertHexToString(String hex) {
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder(); // 49204c6f7665204a617661
        // split into two
        // characters 49, 20, 4c...
        for (int i = 0; i < hex.length() - 1; i += 2) { // grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            if ("00".equals(output)) {
                return sb.toString();
            }
            // convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            // convert the decimal to character
            sb.append((char) decimal);
            temp.append(decimal);
        }
        return sb.toString();
    }
}
