import Ciphers.BinaryZeroCipher;
import Ciphers.Cipher;
import Errors.NoAnnotationFoundError;
import Errors.NoCipherFoundError;

import java.util.*;

public class Main {


    public static void main(String[] args) throws NoCipherFoundError, NoAnnotationFoundError {
        BinaryZeroCipher zeroCipher = new BinaryZeroCipher();
        Cipher.addCipher(zeroCipher);

        while(true) {
            ISecurity.output("\n" + "Please input operation (encode/decode/exit):");

            Scanner scanner = new Scanner(System.in);
            String systemInput = scanner.nextLine();



            switch (systemInput) {
                case "encode" -> {
                    Encryption encryption = new Encryption(scanner);
                    encryption.run(zeroCipher);
                }
                case "decode" -> {
                    Decryption decryption = new Decryption(scanner);
                    decryption.run(zeroCipher);
                }
                case "exit" -> {
                    ISecurity.output("Bye!");
                    System.exit(0);
                }
                default -> {
                    ISecurity.output("There is no '" + systemInput + "' operation");
                }
            }
        }


    }


}