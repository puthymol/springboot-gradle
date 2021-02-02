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
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class AESUtils {
    private static final Logger log = LogManager.getLogger(AESUtils.class);
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String KEY_ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final String ENCRYPTION_KEY="Asiwaw0AwRr1M7yARUA2GSERdiNNwkkf";
    private static final String ENCRYPTION_IV="UHIqAoGqqoeBIxTO";
    private static final String ENCRYPTION_SALT="bWX3R1DB9WavFoTz";
    private static final int ITERATION_COUNT = 1024;
    public static SecretKeySpec skeySpec = null;

    public AESUtils() throws Exception {
        generateKey();
    }

    public static void generateKey() throws Exception {
        try {
            KeySpec spec = new PBEKeySpec(ENCRYPTION_KEY.toCharArray(), ENCRYPTION_SALT.getBytes(StandardCharsets.UTF_8), ITERATION_COUNT, 256);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
            SecretKey secretKey = factory.generateSecret(spec);
            skeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public String encrypt(String strToEncrypt) throws Exception {
        IvParameterSpec gcmSpec = new IvParameterSpec(ENCRYPTION_IV.getBytes(StandardCharsets.UTF_8));
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, gcmSpec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public String decrypt(String strToDecrypt)  throws Exception {
        IvParameterSpec gcmSpec = new IvParameterSpec(ENCRYPTION_IV.getBytes(StandardCharsets.UTF_8));
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, gcmSpec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}
