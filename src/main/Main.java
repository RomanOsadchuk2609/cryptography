package main;

import decoder.Decoder;
import decoder.FrequencyAnalysisDecoder;
import encoder.ceasar.CaesarAthensSystemEncoder;
import encoder.ceasar.CeasarEncoder;
import encoder.Encoder;

public class Main {
    private static final String INPUT = ("Частотний аналіз, частотний криптоаналіз метод криптоаналізу," +
            " який ґрунтується на частоті появи знаків шифротексту. Власне на припущенні про існування" +
            " нетривіального статистичного розподілу окремих символів і їх послідовностей як у відкритому тексті," +
            " так і в шифротексті, який, з точністю до заміни символів, буде зберігатися в процесі шифрування і" +
            " дешифрування. Спрощено, частотний аналіз передбачає, що частота появи заданої літери алфавіту в " +
            "досить довгих текстах одна і та ж для різних текстів однієї мови. При цьому у випадку моноалфавітного" +
            " шифрування якщо в шифротексті буде символ з аналогічною ймовірністю появи, то можна припустити, що він " +
            "і є зазначеною зашифрованою буквою. Аналогічні міркування застосовуються до біграм " +
            "(двобуквених послідовностей), триграм і т.д. у разі поліалфавітного шифрів.").toLowerCase();

    public static void main(String[] args) {
        ceasarEncoding();
        ceasarAthensSystemEncoding();
    }

    private static void ceasarEncoding() {
        System.out.println("************CEASAR***********");
        Encoder encoder = new CeasarEncoder(5);
        System.out.println("Inputed text: " + INPUT);
        String encodedText = encoder.encode(INPUT);
        System.out.println("Encoded text: " + encodedText);
        Decoder decoder = new FrequencyAnalysisDecoder();
        String decodedText = decoder.decode(encodedText);
        System.out.println("Decoded text: " + decodedText);
    }

    private static void ceasarAthensSystemEncoding() {
        System.out.println("************CEASAR AthensSystem***********");
        Encoder encoder = new CaesarAthensSystemEncoder(3, 5);
        System.out.println("Inputed text: " + INPUT);
        String encodedText = encoder.encode(INPUT);
        System.out.println("Encoded text: " + encodedText);
        Decoder decoder = new FrequencyAnalysisDecoder();
        String decodedText = decoder.decode(encodedText);
        System.out.println("Decoded text: " + decodedText);
    }
}
