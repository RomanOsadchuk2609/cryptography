package main.lab;

import main.util.FileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lab8 extends Lab {
    static {
        INPUT = FileReader.readFile("D:\\IdeaProjects\\cryptography\\src\\res\\text8.txt");
    }

    public static void main(String[] args) {
        //TODO: read inputted p,q and generate x
        long p = 19, q = 23, n = p * q, x = 233;
        List<Byte> sList = generateSList(p, q, n, x, 8);
        System.out.println(sList);
        int sNumber = convertBinToDec(sList);
        System.out.println(sNumber);
        char sChar = (char) sNumber;
        System.out.println(sChar);
        String encryptedText = xOrText(INPUT, sChar);
        String decryptedText = xOrText(encryptedText, sChar);
        showResult(INPUT, encryptedText, decryptedText);
    }

    private static String xOrText(String text, char sChar) {
        return text.chars()
                .mapToObj(c -> (char) c)
//                .peek(c -> System.out.println(c))
                .map(c -> c ^ sChar)
                .map(c ->String.valueOf((char) c.intValue()))
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
