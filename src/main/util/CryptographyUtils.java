package main.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CryptographyUtils {
    private CryptographyUtils() {
    }

    /**
     * Converts string into list of strings where each elements is a character from given string
     *
     * @param string - text needed to be converted into List<String>
     * @return List of characters
     */
    public static List<String> convertStringToList(String string) {
        return string.chars()
                .mapToObj(c -> (char) c)
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    /**
     * Converts encrypted with numbers text (e.g. 09180345...) to list of numbers
     *
     * @param text - encrypted text
     * @return list of numbers
     */
    public static List<Integer> buildEncryptedNumbersList(String text) {
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

    public static List<Long> buildEncryptedLongNumbersList(String text) {
        List<Long> resultList = new ArrayList<>();
        if (text.length() % 2 == 0) {
            do {
                String number = text.substring(0, 2);
                resultList.add(Long.valueOf(number));
                text = text.replaceFirst(number, CryptographyConstants.EMPTY_STRING);
            } while (text.length() > 0);
        }
        return resultList;
    }

    /**
     * Verifies number is simple
     */
    public static boolean isSimple(long p) {
        if (p < 2 && p > -2) {
            return false;
        }
        for (int i = 2; i < p; i++) {
            if (p % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Encrypts/Decrypts text using XOR method
     */
    public static String xOrText(String text, int key) {
        return text.chars()
                .mapToObj(c -> (char) c)
                .map(c -> c ^ key)
                .map(c -> String.valueOf((char) c.intValue()))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
