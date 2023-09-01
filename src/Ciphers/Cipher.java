package Ciphers;

import java.lang.annotation.Annotation;
import java.util.LinkedList;

public class Cipher {

    private static final LinkedList<Cipher> ciphers = new LinkedList<>();

    public static void addCipher(Cipher cipher) {
        Class<? extends Cipher> clazz = cipher.getClass();
        Annotation annotation = clazz.getAnnotation(CipherType.class);
        CipherType cipherType = annotation != null ? (CipherType) annotation : null;

        if (cipherType == null || cipherType.name().isEmpty()) return;

        getCiphers().add(cipher);
    }

    public static LinkedList<Cipher> getCiphers() {
        return ciphers;
    }
}
