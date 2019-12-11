package main.lab.lab14;

public class LinearCongruentialGenerator {
    private int last;

    public LinearCongruentialGenerator(int last) {
        this.last = last;
    }

    public int nextInt() {
        last = (last * 32719 + 16954) % 32749;
        return last;
    }
}
