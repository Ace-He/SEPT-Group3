package com.Group3.common.util;

import org.springframework.util.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;


/**
 * Encryption Tools Class
 */
public class EncryptUtils {
    private static String strParam = "Passw0rd";
    private static Cipher cipher;
    private static IvParameterSpec iv;

    public EncryptUtils() {
    }

    private static DESKeySpec getDesKeySpec(String source) throws Exception {
        if (source != null && source.length() != 0) {
            cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            String strKey = "Passw0rd";
            return new DESKeySpec(strKey.getBytes(StandardCharsets.UTF_8));
        } else {
            return null;
        }
    }

    public static String desEncrypt(String source) throws Exception {
        DESKeySpec desKeySpec = getDesKeySpec(source);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        cipher.init(1, secretKey, iv);
        return byte2hex(cipher.doFinal(source.getBytes(StandardCharsets.UTF_8))).toUpperCase();
    }

    public static String desDecrypt(String source) throws Exception {
        byte[] src = hex2byte(source.getBytes());
        DESKeySpec desKeySpec = getDesKeySpec(source);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        cipher.init(2, secretKey, iv);
        byte[] retByte = cipher.doFinal(src);
        return new String(retByte);
    }

    private static String byte2hex(byte[] inStr) {
        StringBuilder out = new StringBuilder(inStr.length * 2);
        byte[] var3 = inStr;
        int var4 = inStr.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            byte b = var3[var5];
            String stmp = Integer.toHexString(b & 255);
            if (stmp.length() == 1) {
                out.append("0").append(stmp);
            } else {
                out.append(stmp);
            }
        }

        return out.toString();
    }

    private static byte[] hex2byte(byte[] b) {
        int size = 2;
        if (b.length % size != 0) {
            throw new IllegalArgumentException("长度不是偶数");
        } else {
            byte[] b2 = new byte[b.length / 2];

            for (int n = 0; n < b.length; n += size) {
                String item = new String(b, n, 2);
                b2[n / 2] = (byte) Integer.parseInt(item, 16);
            }

            return b2;
        }
    }

    public static String encryptPassword(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }

    static {
        iv = new IvParameterSpec(strParam.getBytes(StandardCharsets.UTF_8));
    }
}
