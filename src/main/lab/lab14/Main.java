package main.lab.lab14;

import javax.sound.midi.Soundbank;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static final int LCG_MAX = 32749;

    public static void main(String... ignored) throws FileNotFoundException, UnsupportedEncodingException {
        //Size in Gbs of my file that I want
        double wantedSize = Double.parseDouble(System.getProperty("size", "0.0125"));//12,5 mb
        String separator = " ";
        List<Integer> mouseInputList = getMouseInput();
        int hash = getHash(mouseInputList);
        System.out.println("Hash: " + hash);
        LinearCongruentialGenerator lcg = new LinearCongruentialGenerator(hash);
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

    private static List<Integer> getMouseInput() {
        System.out.println("Move the mouse");
        List<Integer> mouseInputList = new ArrayList<>();
        long lastMousePositionTime = 0;
        while (mouseInputList.size() < 50) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastMousePositionTime >= 200) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) b.getX();
                int y = (int) b.getY();
                System.out.println(x + " : " + y + " : " + x * y);
                mouseInputList.add(x * y);
                lastMousePositionTime = currentTime;
            }
        }
        System.out.println("Stop moving mouse");
        return mouseInputList;
    }

    private static Integer getHash(List<Integer> list) {
        return list.stream()
                .map(x -> x % LCG_MAX)
                .reduce(0, Integer::sum) % LCG_MAX;
    }
}
