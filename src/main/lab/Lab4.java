package main.lab;

import main.encryptor.SDESEncryptor;
import main.util.FileOperationsHelper;

public class Lab4 extends Lab{
    static {
        INPUT = FileOperationsHelper.readFile("D:\\IdeaProjects\\cryptography\\src\\res\\text3.txt");
    }

    public static void main(String[] args) {
        String encryptedtext = SDESEncryptor.encrypt(INPUT, 151);
        String decryptedText = SDESEncryptor.decrypt(encryptedtext, 151);
        showResult(INPUT, encryptedtext, decryptedText);
    }
}
