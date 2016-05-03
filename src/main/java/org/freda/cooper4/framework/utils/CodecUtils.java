package org.freda.cooper4.framework.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 *
 * 加密与解密工具类.
 *
 * Created by rally on 16/5/2.
 */
public class CodecUtils
{
    private static final Log log = LogFactory.getLog(CodecUtils.class);

    /**
     * DES算法密钥
     */
    private static final byte[] DES_KEY = { 21, 1, -110, 82, -32, -85, -128, -65 };

    /**
     * 基于MD5算法的单向加密
     *
     * @param strSrc
     *            明文
     * @return 返回密文
     */
    public static String encryptBasedMd5(String strSrc)
    {
        return DigestUtils.md5Hex(strSrc);
    }

    /**
     * SHA1加密
     * @param strSrc
     * @return
     */
    public static String encryptBaseSha1(String strSrc)
    {
        return DigestUtils.sha1Hex(strSrc);
    }

    /**
     * DES 加密
     * @param data
     * @return
     */
    public static String encryptBasedDes(String data)
    {
        String encryptedData = null;
        try
        {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();

            DESKeySpec deskey = new DESKeySpec(DES_KEY);
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            //解密钥匙
            SecretKey key = keyFactory.generateSecret(deskey);
            // 加密对象
            Cipher cipher = Cipher.getInstance("DES");

            cipher.init(Cipher.ENCRYPT_MODE, key, sr);
            // 加密，并把字节数组编码成字符串
            encryptedData = base64Encode(cipher.doFinal(data.getBytes()));
        }
        catch (Exception e)
        {
            log.error("加密错误，错误信息：", e);
            throw new RuntimeException("加密错误，错误信息：", e);
        }

        return encryptedData;
    }

    /**
     * 解密 DES
     * @param cryptData
     * @return
     */
    public static String decryptBasedDes(String cryptData)
    {
        String decryptedData = null;
        try
        {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();

            DESKeySpec deskey = new DESKeySpec(DES_KEY);
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");

            SecretKey key = keyFactory.generateSecret(deskey);
            // 解密对象
            Cipher cipher = Cipher.getInstance("DES");

            cipher.init(Cipher.DECRYPT_MODE, key, sr);

            decryptedData = new String(cipher.doFinal(base64Decode(cryptData)));
        }
        catch (Exception e)
        {
            log.error("解密错误，错误信息：", e);
            throw new RuntimeException("解密错误，错误信息：", e);
        }
        return  decryptedData;
    }

    /**
     * BASE64编码
     *
     * @param info
     * @return
     */
    public static String base64Encode(byte[] info)
    {
        return Base64.encodeBase64String(info);
    }

    /**
     * BASE64解码
     *
     * @param info
     * @return
     */
    public static byte[] base64Decode(String info)
    {
        return Base64.decodeBase64(info);
    }

}
