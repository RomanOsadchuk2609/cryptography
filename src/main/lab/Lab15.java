package main.lab;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.util.Scanner;
import javax.xml.bind.DatatypeConverter;

public class Lab15 {

    public static void main(String[] args) {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter a message");
            String message = keyboard.next();
            System.out.println("Enter a key");
            String key = keyboard.next();

            Mac hasher = Mac.getInstance("HmacSHA256");
            hasher.init(new SecretKeySpec(key.getBytes(), "HmacSHA256"));

            byte[] hash = hasher.doFinal(message.getBytes());
            System.out.println("HMAC: ");
            String HMAC1 = DatatypeConverter.printBase64Binary(hash);
            System.out.println(HMAC1);

            System.out.println("Checking HMAC: ");
            System.out.println("Enter a message");
            message = keyboard.next();
            System.out.println("Enter a key");
            key = keyboard.next();

            hasher = Mac.getInstance("HmacSHA256");
            hasher.init(new SecretKeySpec(key.getBytes(), "HmacSHA256"));

            hash = hasher.doFinal(message.getBytes());
            String HMAC2 = DatatypeConverter.printBase64Binary(hash);

            if (HMAC1.equals(HMAC2)) {
                System.out.println("HMAC is OK!");
            } else {
                System.out.println("HMAC is not OK!");
            }
        }
        catch (NoSuchAlgorithmException | InvalidKeyException ignored) {
            System.out.println("Пєчалька.");
        }
    }
}