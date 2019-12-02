package main.lab;

import main.util.CryptographyUtils;
import main.util.FileOperationsHelper;

import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Lab8 extends Lab {
    static {
        INPUT = FileOperationsHelper.readFile("/Users/dron/IdeaProjects/cryptography/src/res/text8.txt");
    }

    public static void main(String[] args) throws Exception {
        long p = 107, q = 307;
        validatePQ(p, q);
        long n = p * q, x = 0;
        System.out.println("n = " + n);
        Random random = new Random();
        while (!CryptographyUtils.isSimple(x) && gcd(x, p) != 1) {
            x = random.nextInt(1000);
        }
        System.out.println("x = " + x);

        int length = INPUT.length();

        List<Byte> sList = generateSList(p, q, n, x, 8*length);
        System.out.println(sList);
        int sNumber = convertBinToDec(sList);
        System.out.println(sNumber);
        char sChar = (char) sNumber;
        System.out.println(sChar);
        String encryptedText = xOrText(INPUT, sChar);
        String decryptedText = xOrText(encryptedText, sChar);
        showResult(INPUT, encryptedText, decryptedText);
    }

    private static void validatePQ(long p, long q) throws Exception {
        if (!CryptographyUtils.isSimple(p)) {
            throw new Exception("p is not simple");
        } else if (!CryptographyUtils.isSimple(q)) {
            throw new Exception("q is not simple");
        } else if (p % 4 != 3) {
            throw new Exception("p % 4 != 3");
        } else if (q % 4 != 3) {
            throw new Exception("q % 4 != 3");
        }
    }

    private static long gcd(long a, long b) {
        BigInteger b1 = BigInteger.valueOf(a);
        BigInteger b2 = BigInteger.valueOf(b);
        BigInteger gcd = b1.gcd(b2);
        return gcd.longValue();
    }

    private static String xOrText(String text, char sChar) {
        return text.chars()
                .mapToObj(c -> (char) c)
//                .peek(c -> System.out.println(c))
                .map(c -> c ^ sChar)
                .map(c -> String.valueOf((char) c.intValue()))
//                .peek(c -> System.out.println(c))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private static List<Byte> generateSList(long p, long q, long n, long x, long amountOfBits) {
        List<Long> xList = new ArrayList<>();
        xList.add((x * x) % n);
        System.out.println("x[0] = " + xList.get(0));
        for (int i = 1; i < amountOfBits; i++) {
            long
                    newX = (long) Math.pow(xList.get(i - 1), 2) % n;
            xList.add(newX);
            System.out.println("x[" + i + "] = " + newX + ", ");
        }
        return xList.stream()
                .map(xi -> xi % 2)
                .map(Long::byteValue)
                .collect(Collectors.toList());
    }

    private static int convertBinToDec(List<Byte> bitList) {
        int dec = 0;
        for (int i = bitList.size() - 1; i >= 0; i--) {
            dec += bitList.get(i) * Math.pow(2, bitList.size() - 1 - i);
        }
        return dec;
    }
}
