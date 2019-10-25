package decoder;

import util.CryptographyConstants;

import java.util.*;
import java.util.stream.Collectors;

public class FrequencyAnalysisDecoder implements Decoder {

    @Override
    public String decode(String encodedText) {
        Set<String> characterFrequencySet = buildCharacterFrequencySet(encodedText);
        List<String> characterFrequencyList = new ArrayList<>(characterFrequencySet);
        String mostOftenLetter = characterFrequencyList.get(0);
        int key = CryptographyConstants.ALPHABET.indexOf(mostOftenLetter) -
                CryptographyConstants.ALPHABET.indexOf(CryptographyConstants.characterFrequencySortedList.get(0));
        System.out.println("Key = " + key);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < encodedText.length(); i++) {
            String character = String.valueOf(encodedText.charAt(i));
            if (!CryptographyConstants.ALPHABET.contains(character)) {
                result.append(character);
            } else {
                int index = CryptographyConstants.ALPHABET.indexOf(character) - key;
                if (index < 0 || index >= CryptographyConstants.ALPHABET.size()) {
                    index = Math.abs(Math.abs(index)- CryptographyConstants.ALPHABET.size());
                }
                result.append(CryptographyConstants.ALPHABET.get(index));
            }
        }
        return result.toString();
    }

    protected Set<String> buildCharacterFrequencySet(String text) {
        Map<String, Double> characterFrequencyMap = new HashMap<>();
        for (String character : CryptographyConstants.ALPHABET) {
            characterFrequencyMap.put(character, getCharacterFrequencyInString(character, text));
        }
        //sort map by values in descending order
        characterFrequencyMap = characterFrequencyMap
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        System.out.println(characterFrequencyMap);
        return characterFrequencyMap.keySet();
    }

    private double getCharacterFrequencyInString(String character, String text) {
        double amount = 0;
        double textSize = text.replace(CryptographyConstants.WHITE_SPACE, "").length();
        while (text.contains(character)) {
            text = text.replaceFirst(character, "");
            amount++;
        }
        return amount / textSize;
    }
}
