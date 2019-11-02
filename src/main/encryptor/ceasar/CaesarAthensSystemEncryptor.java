package main.encryptor.ceasar;

import main.encryptor.Encryptor;
import main.util.CryptographyConstants;

import java.util.stream.Collectors;

import static main.util.CryptographyConstants.ALPHABET;

public class CaesarAthensSystemEncryptor implements Encryptor {
    private int keyA = 3;
    private int keyB = 5;

    public CaesarAthensSystemEncryptor(int keyA, int keyB) {
        this.keyA = keyA;
        this.keyB = keyB;
    }

    public String encrypt(String input) {
        return input.chars()
                .mapToObj(c -> (char) c)
                .map(c -> encryptCharacter(input, c))
                .collect(Collectors.joining());
    }

    private String encryptCharacter(String input, char character) {
        String characterString = String.valueOf(character);
        if (!ALPHABET.contains(characterString)) {
            return CryptographyConstants.EMPTY_STRING;
        } else if (input.contains(characterString)) {
            int newIndex = (keyA * ALPHABET.indexOf(characterString) + keyB) % ALPHABET.size();
            return ALPHABET.get(newIndex);
        } else {
            return CryptographyConstants.EMPTY_STRING;
        }
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