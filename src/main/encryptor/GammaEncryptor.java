package main.encryptor;

import main.util.CryptographyConstants;
import main.util.CryptographyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GammaEncryptor implements Encryptor {
    private List<Integer> key;

    public GammaEncryptor(List<Integer> key) {
        this.key = key;
    }

    @Override
    public String encrypt(String text) {
        List<Integer> result = new ArrayList<>();
        List<String> characters = CryptographyUtils.convertStringToList(text);
        int gammaIndex = 0;
        while (!characters.isEmpty()) {
            String character = characters.get(0);
            if (CryptographyConstants.ALPHABET.contains(character)) {
                result.add(encryptCharacter(character, gammaIndex));
                gammaIndex++;
            }
            characters.remove(0);
        }
        return result.stream()
                .map(number -> number > 9
                        ? number.toString()
                        : "0" + number.toString())
                .collect(Collectors.joining());
    }

    private int encryptCharacter(String character, int gammaIngex) {
        int charIndex = CryptographyConstants.ALPHABET.indexOf(character);
        gammaIngex = gammaIngex % key.size();
        return (charIndex + key.get(gammaIngex)) % CryptographyConstants.ALPHABET.size();
    }
}
