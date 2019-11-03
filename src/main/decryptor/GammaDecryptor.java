package main.decryptor;

import main.util.CryptographyConstants;

import java.util.ArrayList;
import java.util.List;

public class GammaDecryptor implements Decryptor {
    private List<Integer> key;

    public GammaDecryptor(List<Integer> key) {
        this.key = key;
    }

    @Override
    public String decrypt(String encryptedText) {
        List<Integer> encryptedList = buildEncryptedList(encryptedText);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < encryptedList.size(); i++) {
            int gammaIndex = i % key.size();
            int charIndex = (encryptedList.get(i) + (CryptographyConstants.ALPHABET.size() - key.get(gammaIndex)))
                    % CryptographyConstants.ALPHABET.size();
            result.append(CryptographyConstants.ALPHABET.get(charIndex));
        }
        return result.toString();
    }

    private List<Integer> buildEncryptedList(String text) {
        List<Integer> resultList = new ArrayList<>();
        if (text.length() % 2 == 0) {
            do {
                String number = text.substring(0, 2);
                resultList.add(Integer.valueOf(number));
                text = text.replaceFirst(number, CryptographyConstants.EMPTY_STRING);
            } while (text.length() > 0);
        }
        return resultList;
    }

    /*private String decryptCharacter (int encryptedCharacter, int gammaIndex) {
        int index = gammaIndex % CryptographyConstants.ALPHABET.size();
        int charIndex = (encryptedList.get(index) + (CryptographyConstants.ALPHABET.size() - key.get(index)))
                % CryptographyConstants.ALPHABET.size();

    }*/
}
