package Ciphers;

import java.util.ArrayList;
import java.util.List;

@CipherType(name = "bZeroCipher")
public class BinaryZeroCipher extends Cipher {


    public BinaryZeroCipher() {
    }


    public BinaryZeroCipher(ArrayList<String> binaryList) {
        System.out.println("Encoded string:");

        StringBuilder addBinaryInList = new StringBuilder();
        StringBuilder binaryCounted = new StringBuilder();

        for (String s : binaryList) addBinaryInList.append(s);

        for (int k = 0; k < addBinaryInList.length() - 1; k++) {
            char i0 = addBinaryInList.charAt(k), i1 = addBinaryInList.charAt(k + 1);
            if (i0 == i1) {
                binaryCounted.append("0");
            } else if (i0 == '1') {
                binaryCounted = getStringBuilder(binaryCounted, "0 ");
            } else if (i0 == '0') {
                binaryCounted = getStringBuilder(binaryCounted, "00 ");
            }
            if (k + 1 == addBinaryInList.length() - 1) {
                String x = i1 == '0' ? "00 " : "0 ";
                binaryCounted = getStringBuilder(binaryCounted, x);
            }

        }
    }

    private StringBuilder getStringBuilder(StringBuilder binaryCounted, String x) {
        binaryCounted.append("0");
        System.out.print(x + binaryCounted + " ");
        binaryCounted = new StringBuilder();
        return binaryCounted;
    }
}
