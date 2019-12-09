package main.lab.lab14;

public class LinearCongruentialGenerator {

    private int max;
    private int last;

    // constructor that takes the max int
    public LinearCongruentialGenerator(int max) {
        this.max = max;
        last = (int) (System.currentTimeMillis() % max);
    }

    // Note that the result can not be bigger then 32749
    public int nextInt() {
        last = (last * 32719 + 3) % 32749;
        return last % max;
    }
}
