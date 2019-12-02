package main.lab;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import javax.xml.bind.DatatypeConverter;

public class Lab15 {

    public static void main(String[] args) {
        try {
            String key = "key";
            String message = "message";

            Mac hasher = Mac.getInstance("HmacSHA256");
            hasher.init(new SecretKeySpec(key.getBytes(), "HmacSHA256"));

            byte[] hash = hasher.doFinal(message.getBytes());

            // to lowercase hexits
            System.out.println(DatatypeConverter.printHexBinary(hash));

            // to base64
            System.out.println(DatatypeConverter.printBase64Binary(hash));
        }
        catch (NoSuchAlgorithmException | InvalidKeyException ignored) {}
    }
}