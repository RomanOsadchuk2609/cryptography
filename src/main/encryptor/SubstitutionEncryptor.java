package main.encryptor;

import main.util.CryptographyConstants;
import main.util.CryptographyUtils;

import java.util.List;
import java.util.stream.Collectors;

public class SubstitutionEncryptor implements Encryptor {
    private List<Integer> key;

    public SubstitutionEncryptor(List<Integer> key) {
        this.key = key;
    }

    @Override
    public String encrypt(String text) {
        List<String> characters = CryptographyUtils.convertStringToList(text);
        return characters.stream()
                .filter(CryptographyConstants.ALPHABET::contains)
                .map(this::encryptCharacter)
                .map(number -> number < 10
                        ? "0" + number.toString()
                        : number.toString())
                .collect(Collectors.joining());
    }

    private Integer encryptCharacter(String character) {
        return key.get(CryptographyConstants.ALPHABET.indexOf(character));
    }
}
