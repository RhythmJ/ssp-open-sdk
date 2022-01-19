package com.wn.ssp.open.sdk.util;

import java.security.MessageDigest;

/**
 * md5工具
 *
 * @author majunjie
 * @date 2022/1/19 10:07
 */
public class Md5Util {

    public Md5Util() {
    }

    public static String getMD5(String encrypt) {
        String md5 = "";

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] encryptByte = encrypt.getBytes("UTF-8");
            byte[] md5Byte = md.digest(encryptByte);
            md5 = bytesToHex(md5Byte);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return md5;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuffer hexStr = new StringBuffer();

        for (int i = 0; i < bytes.length; ++i) {
            int num = bytes[i];
            if (num < 0) {
                num += 256;
            }

            if (num < 16) {
                hexStr.append("0");
            }

            hexStr.append(Integer.toHexString(num));
        }

        return hexStr.toString().toUpperCase();
    }
}

