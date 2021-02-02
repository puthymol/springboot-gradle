package com.softvider.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class AESHexUtils {
    private static final Logger log = LogManager.getLogger(AESHexUtils.class);
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String KEY_ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final String ENCRYPTION_KEY="59efb32d2d046e12dc1852838091413d";
    private static final String ENCRYPTION_IV="260fe0cd5066318395d01192a2b53f32";
    private static final String ENCRYPTION_SALT="ab7fd354c349c23414532bb580fe1db9";
    private static final int ITERATION_COUNT = 1024;
    public static SecretKeySpec skeySpec = null;

    public AESHexUtils() throws Exception {
        generateKey();
    }

    public static void generateKey() throws Exception {
        try {
            KeySpec spec = new PBEKeySpec(ENCRYPTION_KEY.toCharArray(), fromHex(ENCRYPTION_SALT), ITERATION_COUNT, 256);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
            SecretKey secretKey = factory.generateSecret(spec);
            skeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
            log.info(String.valueOf(skeySpec));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new Exception(e.getMessage());
        }
    }

    private static byte[] fromHex(String str) {
        return DatatypeConverter.parseHexBinary(str);
    }

    public String encrypt(String strToEncrypt) throws Exception {
        IvParameterSpec gcmSpec = new IvParameterSpec(fromHex(ENCRYPTION_IV));
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, gcmSpec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new Exception(e.getMessage());
        }
    }

    public String decrypt(String strToDecrypt)  throws Exception {
        IvParameterSpec gcmSpec = new IvParameterSpec(fromHex(ENCRYPTION_IV));
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, gcmSpec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new Exception(e.getMessage());
        }
    }
}
