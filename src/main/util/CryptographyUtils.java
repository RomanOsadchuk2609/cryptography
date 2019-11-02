package main.util;

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
}
