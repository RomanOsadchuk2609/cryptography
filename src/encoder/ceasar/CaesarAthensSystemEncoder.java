package encoder.ceasar;

import encoder.Encoder;

import java.awt.*;
import java.util.stream.Collectors;

import static util.CryptographyConstants.ALPHABET;
import static util.CryptographyConstants.WHITE_SPACE;

public class CaesarAthensSystemEncoder implements Encoder {
    private int keyA = 7;
    private int keyB = 5;

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
        if (ALPHABET.contains(characterString)) {
            int newIndex = (keyA * ALPHABET.indexOf(characterString) + keyB) % ALPHABET.size();
            return ALPHABET.get(newIndex);
        } else {
            return "";
        }
    }

    public String decode(String input) {
        String output = input.chars()
                .mapToObj(c -> (char) c)
                .map(c -> decodeCharacter(input, c))
                .collect(Collectors.joining());
        return output;
    }

    public String decodeCharacter(String input, char character) {
        String characterString = String.valueOf(character);
        if (WHITE_SPACE.equals(characterString)) {
            return WHITE_SPACE;
        } else if (input.contains(characterString)) {
            int newIndex = (modInverse(keyA, ALPHABET.size()) * (ALPHABET.indexOf(characterString) +  ALPHABET.size() - keyB));
            if (newIndex < 0) {
                newIndex *= -1;
            }
            newIndex = newIndex % ALPHABET.size();
            return ALPHABET.get(newIndex);
        } else {
            return "";
        }
    }

    public static int gcd(int x, int y){
        return (y == 0) ? x : gcd(y, x % y);
    }

    int modInverse(int a, int m)
    {
        a = a%m;
        for (int x=1; x<m; x++){
            if ((a*x) % m == 1)
                return x;
        }
        return 0;
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