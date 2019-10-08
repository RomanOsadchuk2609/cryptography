package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CryptographyConstants {

    //English alphabet can be used in future
    /*public static final List<String> ALPHABET = new ArrayList<>(Arrays.asList(
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
            "v", "w", "x", "y", "z"
    ));*/

    public static final List<String> ALPHABET = new ArrayList<>(Arrays.asList(
            "а", "б", "в", "г", "ґ", "д", "е", "є", "ж", "з", "и", "і", "ї", "й", "к", "л", "м", "н", "о", "п", "р", "с",
            "т", "у", "ф", "х", "ц", "ч", "ш", "щ", "ь", "ю", "я"
    ));

    private static final List<String> characterFrequencySortedList =
            CryptographyUtils.convertStringToList("оанвиіетклрсудмпбгзяєжїйхцчшщьюф");

    public static final String WHITE_SPACE = " ";

}
