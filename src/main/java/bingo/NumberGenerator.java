package bingo;

import java.util.Random;

/**
 * Generates random numbers within given bounds both inclusive
 */
public class NumberGenerator {
    private int minNumber;
    private int maxNumber;
    private Random random;

    public NumberGenerator(int minNumber, int maxNumber) {
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.random = new Random();
    }

    public int maxNumberCount() {
        return maxNumber-minNumber+1;
    }

    public int next() {
        return minNumber + random.nextInt(maxNumber-minNumber+1);
    }
}
