package main.lab.lab13;

import main.lab.Lab;
import main.util.BlumBlumShub;
import main.util.FileOperationsHelper;
import main.util.TriFunction;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Lab13 extends Lab {
    static {
        INPUT = FileOperationsHelper.readFile("D:\\IdeaProjects\\cryptography\\src\\res\\text8.txt");
    }

    private static final String ENCRYPTED_SUBSTITUTION_MESSAGE_FILE_PATH = "D:\\IdeaProjects\\cryptography\\src\\res\\encrypted18.txt";

    private static final TriFunction<Short, Short, Short, Short> interactionFunction150 = (a, b, c) -> (short) (a ^ b ^ c);

    private static final TriFunction<Short, Short, Short, Short> interactionFunction54 = (a, b, c) -> (short) ((a | c) ^ b);

    private static final TriFunction<Short, Short, Short, Short> interactionFunction86 = (a, b, c) -> (short) ((a | b) ^ c);

    public static void main(String[] args) {
        //cellAvtomat();
        //bbs();
    }

    private static void cellAvtomat() {
        CAGenerator caGenerator = new CAGenerator(interactionFunction150);
        // and do something
        List<Character> sequenceList = new ArrayList<>();
        for (int i = 0; i < INPUT.length(); i++) {
            sequenceList.add((char) caGenerator.getNext());
        }
        System.out.println(INPUT);
        String encrypted = xOrText(INPUT, sequenceList);
        System.out.println(encrypted);
        System.out.println(xOrText(encrypted, sequenceList));
        FileOperationsHelper.writeToFile(ENCRYPTED_SUBSTITUTION_MESSAGE_FILE_PATH, encrypted);
    }

    private static void bbs() {

        // First use the internal, stock "true" random number
        // generator to get a "true random seed"
        SecureRandom r = new SecureRandom();
        r.nextInt(); // need to do something for SR to be triggered.

        // Use this seed to generate a n-value for Blum-Blum-Shub
        // This value can be re-used if desired.
        int bitsize = 512;
        BigInteger nval = BlumBlumShub.generateN(bitsize, r);

        // now get a seed
        byte[] seed = new byte[bitsize / 8];
        r.nextBytes(seed);

        // now create an instance of BlumBlumShub
        BlumBlumShub bbs = new BlumBlumShub(nval, seed);

        // and do something
        List<Character> sequenceList = new ArrayList<>();
        for (int i = 0; i < INPUT.length(); i++) {
            sequenceList.add((char) bbs.next(16));
        }
        System.out.println(INPUT);
        String encrypted = xOrText(INPUT, sequenceList);
        System.out.println(encrypted);
        System.out.println(xOrText(encrypted, sequenceList));
        FileOperationsHelper.writeToFile(ENCRYPTED_SUBSTITUTION_MESSAGE_FILE_PATH, encrypted);
    }

    private static String xOrText(String text, List<Character> sequence) {
        String encryptedText = "";
        for (int i = 0; i < text.length(); i++) {
            encryptedText += (char) (text.charAt(i) ^ sequence.get(i));
        }
        return encryptedText;
    }

}
