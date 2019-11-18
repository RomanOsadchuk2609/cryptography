package main.lab;

import main.encryptor.SDESEncryptor;
import main.util.FileReader;

public class Lab4 extends Lab{
    static {
        INPUT = FileReader.readFile("D:\\IdeaProjects\\cryptography\\src\\res\\text3.txt");
    }

    public static void main(String[] args) {
        String encryptedtext = SDESEncryptor.encrypt(INPUT, 10);
        String decryptedText = SDESEncryptor.decrypt(encryptedtext, 10);
        showResult(INPUT, encryptedtext, decryptedText);
    }
}
