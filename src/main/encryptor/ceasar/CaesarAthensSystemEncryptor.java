package main.encryptor.ceasar;

import main.encryptor.Encryptor;
import main.util.CryptographyConstants;

import java.util.stream.Collectors;

import static main.util.CryptographyConstants.ALPHABET;

public class CaesarAthensSystemEncryptor implements Encryptor {
    private int keyA = 3;
    private int keyB = 5;

    public CaesarAthensSystemEncryptor(int keyA, int keyB) throws Exception {
        if (ALPHABET.size() % keyA == 0) {
            throw new Exception("Invalid key");
        }
        this.keyA = keyA;
        this.keyB = keyB;
    }

    @Override
    public String encrypt(String input) {
        return input.chars()
                .mapToObj(c -> (char) c)
                .map(c -> encryptCharacter(input, c))
                .collect(Collectors.joining());
    }

    private String encryptCharacter(String input, char character) {
        String characterString = String.valueOf(character);
        if (ALPHABET.contains(characterString)) {
            int newIndex = (keyA * ALPHABET.indexOf(characterString) + keyB) % ALPHABET.size();
            return ALPHABET.get(newIndex);
        } else {
            return CryptographyConstants.EMPTY_STRING;
        }
    }

    public static int gcd(int x, int y) {
        return (y == 0) ? x : gcd(y, x % y);
    }

    public int getKeyA() {
        return keyA;
    }

    public void setKeyA(int keyA) {
        this.keyA = keyA;
    }

    public int getKeyB() {
        return keyB;
    }

    public void setKeyB(int keyB) {
        this.keyB = keyB;
    }

}