package main.lab.lab14;

import java.io.*;
import java.util.Random;

public class Main {

    public static void main(String... ignored) throws FileNotFoundException, UnsupportedEncodingException {
        //Size in Gbs of my file that I want
        double wantedSize = Double.parseDouble(System.getProperty("size", "0.1"));

        LinearCongruentialGenerator lcg = new LinearCongruentialGenerator(32749);
        String separator = " ";

        File file = new File("AvgNumbers.txt");
        long start = System.currentTimeMillis();
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8")), false);
        int counter = 0;
        while (true) {
            for (int i = 0; i < 100; i++) {
                int number = lcg.nextInt();
                writer.print(number);
                writer.print(separator);
            }
            writer.println();
            //Check to see if the current size is what we want it to be
            if (++counter == 20000) {
                System.out.printf("Size: %.3f GB%n", file.length() / 1e9);
                if (file.length() >= wantedSize * 1e9) {
                    writer.close();
                    break;
                } else {
                    counter = 0;
                }
            }
        }
        long time = System.currentTimeMillis() - start;
        System.out.printf("Took %.1f seconds to create a file of %.3f GB", time / 1e3, file.length() / 1e9);
    }

}
