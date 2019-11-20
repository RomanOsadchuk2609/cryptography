package main.decryptor;

import main.util.CryptographyConstants;
import main.util.CryptographyUtils;

import java.util.List;

public class GammaDecryptor implements Decryptor {
    private List<Integer> key;

    public GammaDecryptor(List<Integer> key) {
        this.key = key;
    }

    @Override
    public String decrypt(String encryptedText) {
        List<Integer> encryptedList = CryptographyUtils.buildEncryptedNumbersList(encryptedText);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < encryptedList.size(); i++) {
            int charIndex = (encryptedList.get(i) + (CryptographyConstants.ALPHABET.size() - key.get(i)))
                    % CryptographyConstants.ALPHABET.size();
            result.append(CryptographyConstants.ALPHABET.get(charIndex));
        }
        return result.toString();
    }
}
