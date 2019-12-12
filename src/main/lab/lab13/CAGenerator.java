package main.lab.lab13;

import main.util.TriFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CAGenerator {
    private TriFunction<Short, Short, Short, Short> interactionFunction;
    private short[] array = new short[512];
    private List<Integer> indexes = Arrays.asList(1, 27, 312, 404, 5, 61, 197, 18, 509, 201, 34, 444, 237, 500, 410, 111);

    public CAGenerator(TriFunction<Short, Short, Short, Short> interactionFunction) {
        this.interactionFunction = interactionFunction;
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = (short) random.nextInt(2);
        }

    }

    public char getNext() {
        applyInteractionFunction();
        List<Short> resultList = getResultBits();
        return convertBinToDec(resultList);
    }

    private void applyInteractionFunction() {
        for (int i = 0; i < array.length; i++) {
            short b = array[i];
            short a, c;
            if (i == 0) {
                a = array[array.length - 1];
            } else {
                a = array[i - 1];
            }

            if (i == array.length - 1) {
                c = array[0];
            } else {
                c = array[i + 1];
            }

            array[i] = interactionFunction.apply(a, b, c);
        }
    }

    private List<Short> getResultBits() {
        List<Short> result = new ArrayList<>();
        for (Integer index : indexes) {
            result.add(array[index]);
        }
        return result;
    }

    private char convertBinToDec(List<Short> bitList) {
        short dec = 0;
        for (int i = bitList.size() - 1; i >= 0; i--) {
            dec += bitList.get(i) * Math.pow(2, bitList.size() - 1 - i);
        }
        return (char) dec;
    }

    public TriFunction<Short, Short, Short, Short> getInteractionFunction() {
        return interactionFunction;
    }

    public void setInteractionFunction(TriFunction<Short, Short, Short, Short> interactionFunction) {
        this.interactionFunction = interactionFunction;
    }
}
