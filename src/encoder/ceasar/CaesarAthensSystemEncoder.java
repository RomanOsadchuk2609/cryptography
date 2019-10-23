package encoder.ceasar;

import encoder.Encoder;
import util.CryptographyConstants;

import java.util.stream.Collectors;

import static util.CryptographyConstants.ALPHABET;
import static util.CryptographyConstants.WHITE_SPACE;

public class CaesarAthensSystemEncoder implements Encoder {
    private int keyA = 3;
    private int keyB = 3;

    public CaesarAthensSystemEncoder(int keyA, int keyB) {
        this.keyA = keyA;
        this.keyB = keyB;
    }

    public String encode(String input) {
        String output = input.chars()
                .mapToObj(c -> (char) c)
                .map(c -> encodeCharacter(input, c))
                .collect(Collectors.joining());
        return output;
    }

    private String encodeCharacter(String input, char character) {
        String characterString = String.valueOf(character);
        if (WHITE_SPACE.equals(characterString)) {
            return WHITE_SPACE;
        } else if (input.contains(characterString)) {
            int newIndex = (keyA * ALPHABET.indexOf(characterString) + keyB)% ALPHABET.size();
            return ALPHABET.get(newIndex);
        } else {
            return "";
        }
    }

    public int getKeyA() {
        return keyA;
    }

    public void setKeyA(int keyA) {
        this.keyA = keyA;
    }

    public int getKeyB() {
        return keyB;
    }

    public void setKeyB(int keyB) {
        this.keyB = keyB;
    }

}