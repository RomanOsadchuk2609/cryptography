package main.lab;

import main.decryptor.Decryptor;
import main.decryptor.FrequencyAnalysisDecryptor;
import main.decryptor.FrequencyAnalysisDecryptorForAthensSystem;
import main.encryptor.ceasar.CaesarAthensSystemEncryptor;
import main.encryptor.ceasar.CeasarEncryptor;
import main.encryptor.Encryptor;
import main.util.FileReader;

public class Lab1_2 {

    private static final String INPUT = FileReader.readFile("D:\\IdeaProjects\\cryptography\\src\\res\\text.txt");

    public static void main(String[] args) {
        ceasarEncoding();
        ceasarAthensSystemEncoding();
    }

    private static void ceasarEncoding() {
        System.out.println("************CEASAR***********");
        Encryptor encryptor = new CeasarEncryptor(5);
        String encodedText = encryptor.encrypt(INPUT);
        Decryptor decryptor = new FrequencyAnalysisDecryptor();
        String decodedText = decryptor.decrypt(encodedText);
        showResult(INPUT, encodedText, decodedText);
    }

    private static void ceasarAthensSystemEncoding() {
        System.out.println("************CEASAR AthensSystem***********");
        Encryptor encryptor = new CaesarAthensSystemEncryptor(3, 13);
        String encodedText = encryptor.encrypt(INPUT);
        Decryptor decryptor = new FrequencyAnalysisDecryptorForAthensSystem();
        String decodedText = decryptor.decrypt(encodedText);
        showResult(INPUT, encodedText, decodedText);
    }

    private static void showResult (String input, String encodedText, String decodedText) {
        System.out.println("Inputed text: " + INPUT);
        System.out.println("Encoded text: " + encodedText);
        System.out.println("Decoded text: " + decodedText);
    }
}
