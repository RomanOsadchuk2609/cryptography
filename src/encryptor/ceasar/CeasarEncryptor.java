package encryptor.ceasar;

import encryptor.Encryptor;
import util.CryptographyConstants;

import java.util.stream.Collectors;

import static util.CryptographyConstants.ALPHABET;

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
        String characterString = String.valueOf(character);
        if (!CryptographyConstants.ALPHABET.contains(characterString)) {
            return "";
        } else if (input.contains(characterString)) {
            int newIndex = ALPHABET.indexOf(characterString) + key;
            while (newIndex >= ALPHABET.size()) {
                newIndex -= ALPHABET.size();
            }
            return ALPHABET.get(newIndex);
        } else {
            return "";
        }
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}