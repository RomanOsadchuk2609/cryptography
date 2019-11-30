package main.encryptor.ceasar;

import main.encryptor.Encryptor;
import main.util.CryptographyConstants;

import java.util.stream.Collectors;

import static main.util.CryptographyConstants.UKRAINIAN_ALPHABET;

public class CaesarAthensSystemEncryptor implements Encryptor {
    private int keyA = 3;
    private int keyB = 5;

    public CaesarAthensSystemEncryptor(int keyA, int keyB) throws Exception {
        if (UKRAINIAN_ALPHABET.size() % keyA == 0) {
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
        if (UKRAINIAN_ALPHABET.contains(characterString)) {
            int newIndex = (keyA * UKRAINIAN_ALPHABET.indexOf(characterString) + keyB) % UKRAINIAN_ALPHABET.size();
            return UKRAINIAN_ALPHABET.get(newIndex);
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