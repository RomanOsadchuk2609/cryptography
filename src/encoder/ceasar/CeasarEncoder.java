package encoder.ceasar;

import encoder.Encoder;
import util.CryptographyConstants;

import java.util.stream.Collectors;

import static util.CryptographyConstants.ALPHABET;
import static util.CryptographyConstants.WHITE_SPACE;

public class CeasarEncoder implements Encoder {
    private int key = 3;

    public CeasarEncoder(int key) {
        this.key = key;
    }

    @Override
    public String encode(String input) {
        String output = input.chars()
                .mapToObj(c -> (char) c)
                .map(c -> encodeCharacter(input, c))
                .collect(Collectors.joining());
        return output;
    }

    private String encodeCharacter(String input, char character) {
        String characterString = String.valueOf(character);
        if (!CryptographyConstants.ALPHABET.contains(characterString)) {
            return characterString;
        } else if (input.contains(characterString)) {
            int newIndex = ALPHABET.indexOf(characterString) + key;
            while (newIndex >= ALPHABET.size()) {
                newIndex -= ALPHABET.size();
            }
            return ALPHABET.get(newIndex);
        } else {
            return "";
        }
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}