package main.lab;

import main.encryptor.SDESEncryptor;
import main.util.FileOperationsHelper;

public class Lab4 extends Lab{
    static {
        INPUT = FileOperationsHelper.readFile("/Users/dron/IdeaProjects/cryptography/src/res/lab9.txt");
    }

    public static void main(String[] args) {
        String encryptedtext = SDESEncryptor.encrypt("h", 515);
        String decryptedText = SDESEncryptor.decrypt(encryptedtext, 515);
        showResult(INPUT, encryptedtext, decryptedText);
    }
}
