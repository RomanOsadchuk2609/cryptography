package main.lab;

import main.decryptor.Decryptor;
import main.decryptor.GammaDecryptor;
import main.encryptor.Encryptor;
import main.encryptor.GammaEncryptor;
import main.util.CryptographyConstants;
import main.util.FileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lab3 extends Lab {
    static {
        INPUT = FileReader.readFile("/Users/dron/IdeaProjects/cryptography/src/res/text3.txt");
    }

    public static void main(String[] args) {
        List<Integer> key = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            key.add(random.nextInt(CryptographyConstants.ALPHABET.size()));//[0, ..., 32]
        }

        GammaEncryptor encryptor = new GammaEncryptor(key);
        String encryptedText = encryptor.encrypt(INPUT);
        List<Integer> gamma = encryptor.getGamma();
        Decryptor decryptor = new GammaDecryptor(gamma);
        String decryptedText = decryptor.decrypt(encryptedText);
        showResult(INPUT, encryptedText, decryptedText);
    }
}
