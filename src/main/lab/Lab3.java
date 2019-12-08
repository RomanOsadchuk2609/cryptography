package main.lab;

import main.decryptor.Decryptor;
import main.decryptor.GammaDecryptor;
import main.encryptor.GammaEncryptor;
import main.util.CryptographyConstants;
import main.util.FileOperationsHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Lab3 extends Lab {
    static {
        INPUT = FileOperationsHelper.readFile("/Users/dron/IdeaProjects/cryptography/src/res/text3.txt");
    }

    public static void main(String[] args) {
        List<Integer> key = new ArrayList<>(Arrays.asList(1,2,3));

        GammaEncryptor encryptor = new GammaEncryptor(key);
        String encryptedText = encryptor.encrypt(INPUT);
        List<Integer> gamma = encryptor.getGamma();
        Decryptor decryptor = new GammaDecryptor(gamma);
        String decryptedText = decryptor.decrypt(encryptedText);
        showResult(INPUT, encryptedText, decryptedText);
    }
}
