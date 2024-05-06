package Server.src.main.java.se.ifmo.ru;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Slf4j
//MD2 hashing
public class Hasher implements Serializable {
    public static byte[] generateSalt() {
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[16];
            sr.nextBytes(salt);
            return salt;
        } catch (NoSuchAlgorithmException e) {
            log.error("[HASHER]: Generating salt failed " + e.getMessage());
        }
        return null;
    }

    public static byte[] getMD2Hash(String password, byte[] salt) {
        try {
        String combined = password;
        MessageDigest md = MessageDigest.getInstance("MD2");
        md.update(combined.getBytes());
        return md.digest();
    } catch (NoSuchAlgorithmException e) {
        log.error("[HASHER]: Generating salt failed " + e.getMessage());
    }
        return null;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
