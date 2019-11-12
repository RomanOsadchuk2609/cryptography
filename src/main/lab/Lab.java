package main.lab;


public abstract class Lab {

    protected static String INPUT;

    protected static void showResult(String input, String encryptedText, String decryptedText) {
        System.out.println("Inputted text: " + INPUT);
        System.out.println("Encrypted text: " + encryptedText);
        System.out.println("Decrypted text: " + decryptedText);
    }
}
