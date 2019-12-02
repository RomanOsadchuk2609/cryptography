package main.lab;

import main.encryptor.SDESEncryptor;
import main.util.FileOperationsHelper;

public class Lab4 extends Lab{
    static {
        INPUT = FileOperationsHelper.readFile("/Users/dron/IdeaProjects/cryptography/src/res/lab9.txt");
    }

    public static void main(String[] args) {
        String encryptedtext = SDESEncryptor.encrypt("h", 515);
        Lab4.decryptAttack(encryptedtext, "h");
        String decryptedText = SDESEncryptor.decrypt(encryptedtext, 113);
        showResult(INPUT, encryptedtext, decryptedText);
    }

    static void decryptAttack(String encryptedText, String finalText) {
        int key = 0;
        String decryptedText = encryptedText;
        while(!decryptedText.equals(finalText)) {
            key++;
            decryptedText = SDESEncryptor.decrypt(encryptedText, key);
            System.out.println("Key: " + key + "  DecryptedText: " + decryptedText);
        }
    }
}
