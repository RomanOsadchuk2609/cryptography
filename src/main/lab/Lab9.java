package main.lab;

import main.decryptor.Decryptor;
import main.decryptor.SubstitutionDecryptor;
import main.encryptor.Encryptor;
import main.encryptor.SubstitutionEncryptor;
import main.util.CryptographyConstants;
import main.util.FileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lab9 extends Lab {
    static {
        INPUT = FileReader.readFile("/Users/dron/IdeaProjects/cryptography/src/res/text1_2.txt");
    }

    public static void main(String[] args) {

        List<Integer> key = new ArrayList<>();
        for (int i = 0; i < CryptographyConstants.ALPHABET.size(); i++) {
            key.add(i);//[0, ..., 32]
        }
        Collections.shuffle(key);
        System.out.println("Key: " + key);

        Encryptor encryptor = new SubstitutionEncryptor(key);
        String encodedText = encryptor.encrypt(INPUT);
        Decryptor decryptor = new SubstitutionDecryptor(key);
        String decodedText = decryptor.decrypt(encodedText);
        showResult(INPUT, encodedText, decodedText);
    }
}
