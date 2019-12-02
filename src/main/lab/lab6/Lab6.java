package main.lab.lab6;

import main.decryptor.Decryptor;
import main.decryptor.SubstitutionDecryptor;
import main.encryptor.Encryptor;
import main.encryptor.SubstitutionEncryptor;
import main.lab.Lab;
import main.util.CryptographyConstants;
import main.util.CryptographyUtils;
import main.util.FileOperationsHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lab6 extends Lab {
    private static final String ENCRYPTED_MESSAGE_FILE_PATH = "/Users/dron/IdeaProjects/cryptography/src/main/lab/lab6/encyptedMessage.txt";

    private static final String SIGNATURE_FILE_PATH = "/Users/dron/IdeaProjects/cryptography/src/main/lab/lab6/signature.txt";

    private static final int p = 3, q = 11, n = p * q, E = 7, D = 3;

    static {
        INPUT = FileOperationsHelper.readFile("/Users/dron/IdeaProjects/cryptography/src/main/lab/lab6/text6.txt");
    }

    public static void main(String[] args) {
        encrypt();
        System.out.println("\n\n");
        decrypt();
    }

    private static void encrypt() {
        String incomingMessage = getIncomingMessage();
        System.out.println("Incoming message: " + incomingMessage);
        List<Long> xList = CryptographyUtils.buildEncryptedLongNumbersList(incomingMessage);
        List<Long> cList = xList.stream()
                .map(x -> (long) Math.pow(x, E) % n)
                .collect(Collectors.toList());
        String encryptedMessage = cList.stream()
                .map(number -> number < 10
                        ? "0" + number.toString()
                        : number.toString())
                .collect(Collectors.joining());
        System.out.println("Encrypted message: " + encryptedMessage);
        FileOperationsHelper.writeToFile(ENCRYPTED_MESSAGE_FILE_PATH, encryptedMessage);
        Long hash = getHash(xList);
        System.out.println("Hash: " + hash);
        Long encryptedSignature = (long) Math.pow(hash, E) % n;
        System.out.println("Encrypted signature: " + encryptedSignature);
        FileOperationsHelper.writeToFile(SIGNATURE_FILE_PATH, encryptedSignature.toString());
    }

    private static void decrypt() {
        String encryptedMessage = FileOperationsHelper.readFile(ENCRYPTED_MESSAGE_FILE_PATH);
        //Removing '\n' at the end
        encryptedMessage = encryptedMessage.replaceAll("\n", "");
        List<Long> cList = CryptographyUtils.buildEncryptedLongNumbersList(encryptedMessage);
        List<Long> xList = cList.stream()
                .map(c -> (long) Math.pow(c, D) % n)
                .collect(Collectors.toList());
        String decryptedMessage = cList.stream()
                .map(c -> (long) Math.pow(c, D) % n)
                .map(number -> number < 10
                        ? "0" + number.toString()
                        : number.toString())
                .collect(Collectors.joining());
        System.out.println("Decrypted message: " + decryptedMessage);

        String encryptedSignature = FileOperationsHelper.readFile(SIGNATURE_FILE_PATH);
        encryptedSignature = encryptedSignature.replaceAll("\n", "");
        System.out.println("Encrypted signature: " + encryptedSignature);
        Long decryptedSignature = (long) Math.pow(Long.valueOf(encryptedSignature), D) % n;
        System.out.println("Decrypted signature: " + decryptedSignature);
        Long hash = getHash(xList);
        System.out.println("Hash: " + hash);
        System.out.println("Signature valid (Hash == DecryptedSignature): " + hash.equals(decryptedSignature));

    }

    private static Long getHash(List<Long> list) {
        return list.stream()
//                .map(x -> x % n)
                .reduce(0L, Long::sum) % n;
    }

    private static String getIncomingMessage() {
        List<Integer> key = new ArrayList<>();
        for (int i = 0; i < CryptographyConstants.ALPHABET.size(); i++) {
            key.add(i);//[0, ..., 25]
        }
        Encryptor substitutionEncryptor = new SubstitutionEncryptor(key);
        return substitutionEncryptor.encrypt(INPUT);
    }
}
