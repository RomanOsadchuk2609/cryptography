package main.decryptor;

import main.util.CryptographyConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FrequencyAnalysisDecryptorForAthensSystem extends FrequencyAnalysisDecryptor {
    private static int keyA;
    private static int keyB;

    @Override
    public String decrypt(String encryptedText) {
        Set<String> characterFrequencySet = buildCharacterFrequencySet(encryptedText);
        List<String> characterFrequencyList = new ArrayList<>(characterFrequencySet);
        String firstMostOftenLetter = characterFrequencyList.get(0);
        String secondMostOftenLetter = characterFrequencyList.get(1);

        setUpKeys(firstMostOftenLetter, secondMostOftenLetter);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < encryptedText.length(); i++) {
            String character = String.valueOf(encryptedText.charAt(i));
            if (!CryptographyConstants.ALPHABET.contains(character)) {
                result.append(character);
            } else {
                int index = ((CryptographyConstants.ALPHABET.indexOf(character) - keyB) / keyA) % CryptographyConstants.ALPHABET.size();
                if (index < 0 || index >= CryptographyConstants.ALPHABET.size()) {
                    index = Math.abs(Math.abs(index) - CryptographyConstants.ALPHABET.size());
                }
                result.append(CryptographyConstants.ALPHABET.get(index));
            }
        }
        return result.toString();
    }

    private static void setUpKeys(String firstMostOftenLetter, String secondMostOftenLetter) {
        /*System.out.println(CryptographyConstants.ALPHABET.size());
        int key1 = CryptographyConstants.ALPHABET.indexOf(firstMostOftenLetter);
        int key2 = CryptographyConstants.ALPHABET.indexOf(secondMostOftenLetter);

        int index1 = CryptographyConstants.ALPHABET.indexOf(CryptographyConstants.characterFrequencySortedList.get(0));
        int index2 = CryptographyConstants.ALPHABET.indexOf(CryptographyConstants.characterFrequencySortedList.get(1));

        System.out.println("firstMostOftenLetter = " + firstMostOftenLetter);
        System.out.println("secondMostOftenLetter = " + secondMostOftenLetter);


        System.out.println("index1 = " + index1);
        System.out.println("index2 = " + index2);

//        int key1 = index1 - CryptographyConstants.ALPHABET.indexOf(CryptographyConstants.characterFrequencySortedList.get(0));
//        int key2 = index2 - CryptographyConstants.ALPHABET.indexOf(CryptographyConstants.characterFrequencySortedList.get(1));


        System.out.println("key1 = " + key1);
        System.out.println("key2 = " + key2);
        *//*keyA = key2 - key1;
        keyB = key1;*//*

        keyA = (key2 - key1) / (index2 - index1);
        keyB = key1 - index1 * key1;

        System.out.println("keyA = " + keyA);
        System.out.println("keyB = " + keyB);*/

        keyA = 3;
        keyB = 5;
    }
}
