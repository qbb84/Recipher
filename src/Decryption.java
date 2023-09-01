
import Ciphers.Cipher;
import Ciphers.CipherType;
import Errors.NoAnnotationFoundError;
import Errors.NoCipherFoundError;

import java.lang.annotation.Annotation;
import java.util.*;

public class Decryption implements ISecurity {

    private Scanner scanner = null;
    private String input;


    public Decryption(Scanner scanner) {
        ISecurity.output("Input encoded string: ");
        this.scanner = scanner;
        input = scanner.nextLine();

    }


    @Override
    public void run(Cipher cipher) throws NoCipherFoundError, NoAnnotationFoundError {
        if (Cipher.getCiphers().isEmpty() || !Cipher.getCiphers().contains(cipher)) {
            throw new NoCipherFoundError("No Ciphers.Cipher(s) found see: Cipher.addCipher");
        }

        Class<? extends Cipher> clazz = cipher.getClass();
        Annotation annotation = clazz.getAnnotation(CipherType.class);
        CipherType cipherType = annotation != null ? (CipherType) annotation : null;

        if (cipherType == null || cipherType.name().isEmpty()) {
            throw new NoAnnotationFoundError("No Annotation found.");
        }

        switch (cipherType.name()) {
            case "bZeroCipher":
                zeroCipherDecryption();
                break;
        }


    }

    public void zeroCipherDecryption() {
        String[] s = null;

        String input = getInput();

        if(input.isEmpty()) { ISecurity.output("Encoded string is not valid."); return; }


        StringBuilder binaryBuilder = new StringBuilder();
        StringBuilder binaryToASCII = new StringBuilder();

        int binary = 0;
        for (char c : input.toCharArray()) {
            if (useCase(c)) return;

            if (c != ' ') {
                s = input.split(" ");
                break;
            }
        }



        if(s == null) {ISecurity.output("Encoded string is not valid.");  return;}

        int lengthCount = 0;
        for(int i = 1; i < s.length; i += 2) lengthCount += s[i].length();


        if(lengthCount % 7 != 0) {ISecurity.output("Encoded string is not valid.");  return;}

        if(s.length % 2 != 0) {ISecurity.output("Encoded string is not valid."); return;}



        for (int i = 0; i < s.length; i += 2) {
            ;
            if(s[i].length() > 2){
                ISecurity.output("Encoded string is not valid");
                return;
            }
            binary = (s[i].equals("0")) ? 1 : 0;
            for (int k = 0; k < s[i + 1].length(); k++) {
                binaryBuilder.append(binary);

            }

        }
        String[] splitBinary = binaryBuilder.toString().split("(?<=\\G.{" + 7 + "})");

        for (String s1 : splitBinary) {
            int binaryToDecimal = 0;
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) == '1') {
                    binaryToDecimal += 1 << ((s1.length() - 1) - i);
                }
            }
            binaryToASCII.append((char) binaryToDecimal);
        }
        ISecurity.output("Decoded string:");
        ISecurity.output(binaryToASCII);

    }

    public boolean useCase(char c) {
        if (c != '0' && c != ' ') {
            ISecurity.output("Encoded string is not valid.");
            return true;
        }
        int charCount = 0;

        return false;

    }

    public Scanner getScanner() {
        return scanner;
    }

    public String getInput() {
        return input;
    }
}
