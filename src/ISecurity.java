import Ciphers.Cipher;
import Errors.NoAnnotationFoundError;
import Errors.NoCipherFoundError;

import java.util.Scanner;

public interface ISecurity {

    void run(Cipher cipher) throws NoCipherFoundError, NoAnnotationFoundError;

    static <T> void output(T t) {
        System.out.println(t);
    }


}
