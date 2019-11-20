package main.decryptor;

import main.util.CryptographyConstants;
import main.util.CryptographyUtils;

import java.util.List;
import java.util.stream.Collectors;

public class SubstitutionDecryptor implements Decryptor {
    private List<Integer> key;

    public SubstitutionDecryptor(List<Integer> key) {
        this.key = key;
    }

    @Override
    public String decrypt(String encryptedText) {
        List<Integer> encryptedList = CryptographyUtils.buildEncryptedNumbersList(encryptedText);
        return encryptedList.stream()
                .map(this::decryptCharacter)
                .collect(Collectors.joining());
    }

    private String decryptCharacter(Integer number) {
        return CryptographyConstants.ALPHABET.get(key.indexOf(number));
    }
}
