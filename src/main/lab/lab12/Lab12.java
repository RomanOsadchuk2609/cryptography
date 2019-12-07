package main.lab.lab12;

import main.encryptor.Encryptor;
import main.encryptor.SubstitutionEncryptor;
import main.lab.Lab;
import main.util.CryptographyConstants;
import main.util.FileOperationsHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lab12 extends Lab {
    private static final String ENCRYPTED_XOR_MESSAGE_FILE_PATH = "D:\\IdeaProjects\\cryptography\\src\\main\\lab\\lab12\\xOrOutput.txt";
    private static final String ENCRYPTED_SUBSTITUTION_MESSAGE_FILE_PATH = "D:\\IdeaProjects\\cryptography\\src\\main\\lab\\lab12\\substitutionOutput.txt";

    static {
        INPUT = FileOperationsHelper.readFile("D:\\IdeaProjects\\cryptography\\src\\main\\lab\\lab12\\input.txt");
    }

    public static void main(String[] args) {
        /*String xOrEncryptedText = xOrText(INPUT, (char) 72);
        FileOperationsHelper.writeToFile(ENCRYPTED_XOR_MESSAGE_FILE_PATH, xOrEncryptedText);*/

        List<Integer> key = new ArrayList<>();
        for (int i = 0; i < CryptographyConstants.ALPHABET.size(); i++) {
            key.add(i);//[0, ..., 32]
        }
        Collections.shuffle(key);
        Encryptor encryptor = new SubstitutionEncryptor(key);
        String substitionEncryptedText = encryptor.encrypt(INPUT);
        FileOperationsHelper.writeToFile(ENCRYPTED_SUBSTITUTION_MESSAGE_FILE_PATH, substitionEncryptedText);

    }



    private static String xOrText(String text, char key) {
        return text.chars()
                .mapToObj(c -> (char) c)
                .map(c -> c ^ key)
                .map(c -> String.valueOf((char) c.intValue()))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
