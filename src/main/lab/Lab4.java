package main.lab;

import main.encryptor.SDESEncryptor;
import main.util.FileOperationsHelper;

public class Lab4 extends Lab{
    static {
        INPUT = FileOperationsHelper.readFile("/Users/dron/IdeaProjects/cryptography/src/res/lab9.txt");
    }

    public static void main(String[] args) {
        String encryptedtext = SDESEncryptor.encrypt("h", 113);
        Lab4.decryptAttack(encryptedtext, "h");
    }

    static void decryptAttack(String encryptedText, String finalText) {
        int key = 0;
        String decryptedText = encryptedText;
        long begin = System.currentTimeMillis();
        while(!decryptedText.equals(finalText)) {
            key++;
            decryptedText = SDESEncryptor.decrypt(encryptedText, key);
            System.out.println("Key: " + key + "  DecryptedText: " + decryptedText);
        }
        long end = System.currentTimeMillis() - begin ;
        System.out.println(end + "ms.");
    }
}
