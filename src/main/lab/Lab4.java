package main.lab;

import main.encryptor.SDESEncryptor;
import main.util.FileOperationsHelper;

import java.util.Arrays;

public class Lab4 extends Lab{
    static {
        INPUT = FileOperationsHelper.readFile("/Users/dron/IdeaProjects/cryptography/src/res/lab9.txt");
    }

    public static void main(String[] args) {
        /*String encryptedtext = SDESEncryptor.encrypt("h", 113);
        Lab4.decryptAttack(encryptedtext, "h");*/
        String text = "hello";
        byte[] textBytes = text.getBytes();
        System.out.println("Text: " + "hello");
        System.out.println("Bytes:" + Arrays.toString(textBytes)+ "\n\n");

        Lab4.second_task();
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

    static void second_task() {
        int[] keys = {0b0000000000,
                        0b0000000001,
                        0b0000000010,
                        0b0000000011,
                        0b0000000100,
                        0b0000000101,
                        0b0000001000,
                        0b0000010000,
                        0b0000100000,
                        0b0001000000,
                        0b0010000000,
                        0b0100000000,
                        0b1000000000
        };

        for (int i = 0; i < keys.length; i++) {
            Lab4.encryptAndShow(keys[i]);
        }
    }

    static void encryptAndShow(int key) {
        String encryptedtext = SDESEncryptor.encrypt("hello", key);
        byte[] encryptedtextBytes = encryptedtext.getBytes();
        System.out.println("\n------------------Key: " + key + " -------------------");
        System.out.println("Encrypted: " + encryptedtext);
        System.out.println("Bytes:" + Arrays.toString(encryptedtextBytes));
    }
}
