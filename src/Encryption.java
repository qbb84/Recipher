import Ciphers.BinaryZeroCipher;
import Ciphers.Cipher;
import Ciphers.CipherType;
import Errors.NoAnnotationFoundError;
import Errors.NoCipherFoundError;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Encryption implements ISecurity {

    private LinkedList<Character> set = new LinkedList<>();
    private ArrayList<String> list = new ArrayList<>();

    private LinkedList<Cipher> cipherList = new LinkedList<>();

    private Scanner scanner;
    private String entryInput;

    public Encryption(Scanner scanner) {
        ISecurity.output("Input string:");
        this.scanner = new Scanner(System.in);
        this.entryInput = scanner.nextLine();

    }


    public void run(Cipher cipher) throws NoCipherFoundError, NoAnnotationFoundError {
        if (Cipher.getCiphers().isEmpty() || !Cipher.getCiphers().contains(cipher)) {
            throw new NoCipherFoundError("No Ciphers.Cipher(s) found see: Cipher.addCipher");
        }

        if(getEntryInput().isEmpty()){ ISecurity.output("Encoded string is not valid."); return;}


        //Check if classes have the Cipher Annoation
        Class<? extends Cipher> clazz = cipher.getClass();
        Annotation annotation = clazz.getAnnotation(CipherType.class);
        CipherType cipherType = annotation != null ? (CipherType) annotation : null;

        if (cipherType == null || cipherType.name().isEmpty()) {
            throw new NoAnnotationFoundError("No Annotation found.");
        }

        //Logic based on Cipher name
        switch (cipherType.name()) {
            case "bZeroCipher":
                convertInputTo7Bits();
                new BinaryZeroCipher(getList());
                break;

        }


    }

    public void convertInputTo7Bits() {
        String input = getEntryInput();

        for (int k = 0; k < input.length(); k++)
            set.add(input.charAt(k));

        for (Character character : set)
            list.add(String.format("%7s", Integer.toBinaryString(character)).replace(' ', '0'));
    }

    //Eventually switch to reflection getting extends from the package
    public void addCipher(Cipher cipher) {
        getCipherList().add(cipher);
    }

    private static StringBuilder getStringBuilder(StringBuilder binaryCounted, String x) {
        binaryCounted.append("0");
        System.out.print(x + binaryCounted + " ");
        binaryCounted = new StringBuilder();
        return binaryCounted;
    }


    public LinkedList<Character> getSet() {
        return set;
    }

    public void setSet(LinkedList<Character> set) {
        this.set = set;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getEntryInput() {
        return entryInput;
    }

    public void setEntryInput(String entryInput) {
        this.entryInput = entryInput;
    }

    public LinkedList<Cipher> getCipherList() {
        return cipherList;
    }

    public void setCipherList(LinkedList<Cipher> cipherList) {
        this.cipherList = cipherList;
    }
}



