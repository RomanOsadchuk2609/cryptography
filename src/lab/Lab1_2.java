package lab;

import decoder.Decoder;
import decoder.FrequencyAnalysisDecoder;
import decoder.FrequencyAnalysisDecoderForAthensSystem;
import encoder.ceasar.CaesarAthensSystemEncoder;
import encoder.ceasar.CeasarEncoder;
import encoder.Encoder;

public class Lab1_2 {
    private static final String INPUT = ("Частотний аналіз, частотний криптоаналіз метод криптоаналізу," +
            " який ґрунтується на частоті появи знаків шифротексту. Власне на припущенні про існування" +
            " нетривіального статистичного розподілу окремих символів і їх послідовностей як у відкритому тексті," +
            " так і в шифротексті, який, з точністю до заміни символів, буде зберігатися в процесі шифрування і" +
            " дешифрування. Спрощено, частотний аналіз передбачає, що частота появи заданої літери алфавіту в " +
            "досить довгих текстах одна і та ж для різних текстів однієї мови. При цьому у випадку моноалфавітного" +
            " шифрування якщо в шифротексті буде символ з аналогічною ймовірністю появи, то можна припустити, що він " +
            "і є зазначеною зашифрованою буквою. Аналогічні міркування застосовуються до біграм " +
            "(двобуквених послідовностей), триграм і т.д. у разі поліалфавітного шифрів.У автоматиці функціональна схема, " +
            "або блок-схема САР, складається з функціональних блоків, які являють собою конструктивно відособлені частини" +
            " (елементи або пристрої) автоматичних систем, які виконують певні функції. Функціональні блоки на схемі" +
            " позначають прямокутниками, всередині яких надписують їх найменування відповідно до функцій, що виконуються." +
            " Зв'язки між функціональними блоками (внутрішні впливи) позначаються лініями зі стрілками, які вказують" +
            " напрям впливів.Функціональні схеми можуть виконуватися в укрупненому і розгорненому вигляді. У першому " +
            "випадку на схемі зображають найважливіші блоки системи і зв'язки між ними.У другому варіанті схема з" +
            "ображаються більш детально, що полегшує її читання та ілюструє принцип роботиЗахідний фронт Першої світової війни (1914–1918) — воєнні дії, що тривали на території Західної Європи з серпня 1914 до листопада 1918 року за часів Першої світової війни. Географічно Західний фронт (Західноєвропейський театр воєнних дій) охоплював Бельгію, Люксембург, німецькі Ельзас, Лотарингію та рейнські провінції, а також північний схід Франції. На сході зона бойових дій обмежувалася Рейном, з півночі — нейтральними Нідерландами, із заходу — морським узбережжям від гирла р. Шельди до гирла р. Сени і з півдня — умовною лінією р. Сена, Париж, швейцарський кордон. Протяжність фронту від річки Шельди до швейцарського кордону сягала 480 км, у глибину доходила до 500 км, від Рейну до Кале. Західна частина театру воєнних дій становила рівнину з розгалуженою дорожньою мережею, зручну для маневрування та дій великих військових об'єднань; східна частина була переважно гірською (Арденни, Аргон, Вогези), що обмежувало свободу маневру військ. Особливістю Західного фронту було промислове значення регіону (вугільні копальні, залізна руда, розвинена обробна промисловість), де точилися затяті бої та щільна насиченість населеними пунктами, важливими об'єктами виробництва, інфраструктури, історичними та культурними цінностями Західної Європи..").toLowerCase();

    public static void main(String[] args) {
        ceasarEncoding();
        ceasarAthensSystemEncoding();
    }

    private static void ceasarEncoding() {
        System.out.println("************CEASAR***********");
        Encoder encoder = new CeasarEncoder(5);
        String encodedText = encoder.encode(INPUT);
        Decoder decoder = new FrequencyAnalysisDecoder();
        String decodedText = decoder.decode(encodedText);
        showResult(INPUT, encodedText, decodedText);
    }

    private static void ceasarAthensSystemEncoding() {
        System.out.println("************CEASAR AthensSystem***********");
        Encoder encoder = new CaesarAthensSystemEncoder(3, 5);
        String encodedText = encoder.encode(INPUT);
        Decoder decoder = new FrequencyAnalysisDecoderForAthensSystem();
        String decodedText = decoder.decode(encodedText);
        showResult(INPUT, encodedText, decodedText);
    }

    private static void showResult (String input, String encodedText, String decodedText) {
        System.out.println("Inputed text: " + INPUT);
        System.out.println("Encoded text: " + encodedText);
        System.out.println("Decoded text: " + decodedText);
    }
}
