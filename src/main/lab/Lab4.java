package main.lab;

import main.encryptor.SDESEncryptor;
import main.util.FileReader;

public class Lab4 extends Lab{
    static {
        INPUT = FileReader.readFile("/Users/dron/IdeaProjects/cryptography/src/res/text3.txt");
    }

    public static void main(String[] args) {
        String encryptedtext = SDESEncryptor.encrypt(INPUT, 51);
        String decryptedText = SDESEncryptor.decrypt(encryptedtext, 51);
        showResult(INPUT, encryptedtext, decryptedText);
    }
}
