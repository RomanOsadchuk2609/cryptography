package main.lab;

import main.decryptor.Decryptor;
import main.decryptor.GammaDecryptor;
import main.encryptor.Encryptor;
import main.encryptor.GammaEncryptor;
import main.util.CryptographyConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lab3 extends Lab {

    public static void main(String[] args) {
        List<Integer> key = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < CryptographyConstants.ALPHABET.size(); i++) {
            key.add(random.nextInt(CryptographyConstants.ALPHABET.size()));//[0, ..., 32]
        }
        Encryptor encryptor = new GammaEncryptor(key);
        String encryptedText = encryptor.encrypt(INPUT);
        Decryptor decryptor = new GammaDecryptor(key);
        String decryptedText = decryptor.decrypt(encryptedText);
        showResult(INPUT, encryptedText, decryptedText);
    }
}
