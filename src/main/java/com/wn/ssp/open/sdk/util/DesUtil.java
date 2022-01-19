package com.wn.ssp.open.sdk.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.SecureRandom;
import java.util.Random;

/**
 * des工具
 *
 * @author majunjie
 * @date 2022/1/19 10:07
 */
public class DesUtil {

    public static final String ALL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        System.out.println(generateStringByKey(32));
    }

    /**
     * 返回一个定长的带因子的固定的随机字符串(只包含大小写字母、数字)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String generateStringByKey(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALL_CHAR.charAt(random.nextInt(ALL_CHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 3重DES加密
     *
     * @param data
     * @param desKey 密钥长度不少于24的倍数位
     * @return
     */
    public static String encryptBy3DesHex(String data, String desKey) {
        String result = null;
        try {
            SecureRandom secureRandom = new SecureRandom();
            DESedeKeySpec sedeKeySpec = new DESedeKeySpec(desKey.getBytes());

            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey key = secretKeyFactory.generateSecret(sedeKeySpec);

            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, secureRandom);

            byte[] bytesresult = cipher.doFinal(data.getBytes());
            result = bytesToHexString(bytesresult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 3重DES解密
     *
     * @param data
     * @param desKey
     * @return
     */
    public static String decryptBy3DesHex(String data, String desKey) {
        String deresult = null;
        try {
            SecureRandom secureRandom = new SecureRandom();
            DESedeKeySpec sedeKeySpec = new DESedeKeySpec(desKey.getBytes());

            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey key = secretKeyFactory.generateSecret(sedeKeySpec);

            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, secureRandom);

            byte[] bytesresult = cipher.doFinal(hexStringToByteArray(data));
            deresult = new String(bytesresult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deresult;
    }

    /**
     * 16进制表示的字符串转换为字节数组
     *
     * @param s 16进制表示的字符串
     * @return byte[] 字节数组
     */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] b = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
            b[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
                    .digit(s.charAt(i + 1), 16));
        }
        return b;
    }

    /**
     * byte数组转16进制字符串
     *
     * @param bArray
     * @return
     */
    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

}
