package main.encryptor.ceasar;

import main.encryptor.Encryptor;
import main.util.CryptographyConstants;

import java.util.stream.Collectors;

import static main.util.CryptographyConstants.UKRAINIAN_ALPHABET;

public class CeasarEncryptor implements Encryptor {
    private int key = 3;

    public CeasarEncryptor(int key) {
        this.key = key;
    }

    @Override
    public String encrypt(String input) {
        return input.chars()
                .mapToObj(c -> (char) c)
                .map(c -> encryptCharacter(input, c))
                .collect(Collectors.joining());
    }

    private String encryptCharacter(String input, char character) {
        String characterString = String.valueOf(character).toLowerCase();
        if (!CryptographyConstants.ALPHABET.contains(characterString)) {
            return CryptographyConstants.WHITE_SPACE;
        } else if (CryptographyConstants.ALPHABET.contains(characterString)) {
            int newIndex = CryptographyConstants.ALPHABET.indexOf(characterString) + key;
            while (newIndex >= CryptographyConstants.ALPHABET.size()) {
                newIndex -= CryptographyConstants.ALPHABET.size();
            }
            System.out.println(CryptographyConstants.ALPHABET.get(newIndex));
            return CryptographyConstants.ALPHABET.get(newIndex);
        } else {
            return CryptographyConstants.EMPTY_STRING;
        }
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}