package main.lab;

import main.util.FileReader;

public class Lab {

    protected static final String INPUT = FileReader.readFile("D:\\IdeaProjects\\cryptography\\src\\res\\text.txt");

    protected static void showResult(String input, String encryptedText, String decryptedText) {
        System.out.println("Inputted text: " + INPUT);
        System.out.println("Encrypted text: " + encryptedText);
        System.out.println("Decrypted text: " + decryptedText);
    }
}
