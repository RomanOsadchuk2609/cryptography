package main.decryptor;

import main.util.CryptographyConstants;

import java.util.stream.Collectors;

public class CaesarAthensSystemDecryptor implements Decryptor {
    private int keyA = 7;
    private int keyB = 5;

    public CaesarAthensSystemDecryptor(int keyA, int keyB) {
        this.keyA = keyA;
        this.keyB = keyB;
    }


    @Override
    public String decrypt(String encryptedText) {
        return encryptedText.chars()
                .mapToObj(c -> (char) c)
                .map(c -> decryptCharacter(encryptedText, c))
                .collect(Collectors.joining());
    }

    private String decryptCharacter(String input, char character) {
        String characterString = String.valueOf(character);
        if (CryptographyConstants.UKRAINIAN_ALPHABET.contains(characterString) && input.contains(characterString)) {
            int newIndex = (modInverse(keyA, CryptographyConstants.UKRAINIAN_ALPHABET.size())
                    * (CryptographyConstants.UKRAINIAN_ALPHABET.indexOf(characterString) + CryptographyConstants.UKRAINIAN_ALPHABET.size() - keyB));
            if (newIndex < 0) {
                newIndex *= -1;
            }
            newIndex = newIndex % CryptographyConstants.UKRAINIAN_ALPHABET.size();
            return CryptographyConstants.UKRAINIAN_ALPHABET.get(newIndex);
        } else {
            return CryptographyConstants.EMPTY_STRING;
        }
    }

    private int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1)
                return x;
        }
        return 0;
    }
}
