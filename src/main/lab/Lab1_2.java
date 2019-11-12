package main.lab;

import main.decryptor.CaesarAthensSystemDecryptor;
import main.decryptor.Decryptor;
import main.decryptor.FrequencyAnalysisDecryptor;
import main.encryptor.Encryptor;
import main.encryptor.ceasar.CaesarAthensSystemEncryptor;
import main.encryptor.ceasar.CeasarEncryptor;
import main.util.FileReader;

public class Lab1_2 extends Lab {
    static {
        INPUT = FileReader.readFile("D:\\IdeaProjects\\cryptography\\src\\res\\text1_2.txt");
    }

    public static void main(String[] args) {
        ceasarEncryption();
        ceasarAthensSystemEncryption();
    }

    private static void ceasarEncryption() {
        System.out.println("************CEASAR***********");
        Encryptor encryptor = new CeasarEncryptor(5);
        String encodedText = encryptor.encrypt(INPUT);
        Decryptor decryptor = new FrequencyAnalysisDecryptor();
        String decodedText = decryptor.decrypt(encodedText);
        showResult(INPUT, encodedText, decodedText);
    }

    private static void ceasarAthensSystemEncryption() {
        System.out.println("************CEASAR AthensSystem***********");
        Encryptor encryptor = new CaesarAthensSystemEncryptor(7, 5);
        String encodedText = encryptor.encrypt(INPUT);
        Decryptor decryptor = new CaesarAthensSystemDecryptor(7, 5);
        String decodedText = decryptor.decrypt(encodedText);
        showResult(INPUT, encodedText, decodedText);
    }
}
