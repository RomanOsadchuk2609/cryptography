package main.encryptor;

import main.util.CryptographyConstants;
import main.util.CryptographyUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GammaEncryptor implements Encryptor {
    private List<Integer> key;
    private List<Integer> gamma;

    public GammaEncryptor(List<Integer> key) {
        this.key = key;
    }

    @Override
    public String encrypt(String text) {
        gamma = buildGamma(text);
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
                .map(number -> number > 9 || number < 0
                        ? number.toString()
                        : "0" + number.toString())
                .collect(Collectors.joining());
    }

    private int encryptCharacter(String character, int gammaIngex) {
        int charIndex = CryptographyConstants.ALPHABET.indexOf(character);
        gammaIngex = gammaIngex % gamma.size();
        return (charIndex + gamma.get(gammaIngex)) % CryptographyConstants.ALPHABET.size();
    }

    private List<Integer> buildGamma(String text) {
        List<Integer> yArray = new ArrayList<>(key);
        for (int i = 2; i < text.length(); i++) {
            int y = yArray.get(i) + yArray.get(i - 2);
            yArray.add(y);
        }
        List<Integer> gamma = new ArrayList<>();
        for (int i = 0; i < yArray.size() - 1; i++) {
            gamma.add((yArray.get(i) + yArray.get(i + 1))%CryptographyConstants.ALPHABET.size());
        }
        return gamma;
    }

    public List<Integer> getGamma() {
        return gamma;
    }

    public void setGamma(List<Integer> gamma) {
        this.gamma = gamma;
    }
}
