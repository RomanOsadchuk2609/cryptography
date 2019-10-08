package decoder;

import util.CryptographyConstants;

import java.util.*;
import java.util.stream.Collectors;

public class FrequencyAnalysisDecoder implements Decoder {

    @Override
    public String decode(String encodedText) {
        Set<String> characterFrequencySet = buildCharacterFrequencySet(encodedText);
        List<String> characterFrequencyList = new ArrayList<>(characterFrequencySet);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < encodedText.length(); i++) {
            String character = String.valueOf(encodedText.charAt(i));
            if (character.equals(CryptographyConstants.WHITE_SPACE)) {
                result.append(character);
            } else {
                result.append(CryptographyConstants.ALPHABET.get(characterFrequencyList.indexOf(character)));
            }
        }
        return result.toString();
    }

    private Set<String> buildCharacterFrequencySet(String text) {
        Map<String, Integer> characterFrequencyMap = new HashMap<>();
        for (String character : CryptographyConstants.ALPHABET) {
            characterFrequencyMap.put(character, getAmountOfCharactersInString(character, text));
        }
        //sort map by values in descending order
        characterFrequencyMap = characterFrequencyMap
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

        return characterFrequencyMap.keySet();
    }

    private int getAmountOfCharactersInString(String character, String text) {
        int amount = 0;
        while (text.contains(character)) {
            text = text.replace(character, "");
            amount++;
        }
        return amount;
    }
}
