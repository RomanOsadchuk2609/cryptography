package main.lab;

import main.util.CryptographyUtils;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Lab7 {

    public static void main(String args[]) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input p: ");
        long p = scanner.nextInt();//10007
        if (!CryptographyUtils.isSimple(p)) {
            throw new Exception("Invalid value of p!");
        }
        long a = getPrimaryRoot(p);
        System.out.println("Calculated a: " + a);
        Random random = new Random();

        int x = random.nextInt(20);
        System.out.println("*************************************");
        System.out.println("x = "+ x);
        long X = (long) Math.pow(a, x) % p;
        System.out.println("A: sending X = " + X);

        int y = random.nextInt(20);
        System.out.println("*************************************");
        System.out.println("y = "+ y);
        long Y = (long) Math.pow(a, y) % p;
        System.out.println("B: sending Y = " + Y);

        System.out.println("*************************************");
        long k = (long) Math.pow(Y, x) % p;
        System.out.println("k = " + k);

        long k1 = (long) Math.pow(X, y) % p;
        System.out.println("k1 = " + k1);
    }

    private static long getPrimaryRoot(long p) {
        for (long i = 0; i < p; i++)
            if (isPrimaryRoot(p, i))
                return i;
        return 0;
    }

    private static boolean isPrimaryRoot(long p, long a) {
        if (a == 0 || a == 1) {
            return false;
        }
        long last = 1;

        // a^p - a =mod p
        Set<Long> set = new HashSet<>();
        for (long i = 0; i < p - 1; i++) {
            last = (last * a) % p;
            if (set.contains(last)) {
                return false;
            }
            set.add(last);
        }
        return true;
    }
}
