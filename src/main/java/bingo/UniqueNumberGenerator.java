package bingo;

import java.util.HashSet;
import java.util.Set;

/**
 * stateful generator
 */
public class UniqueNumberGenerator {
    private NumberGenerator numberGenerator;
    private Set<Integer> generatedNumbers;

    public UniqueNumberGenerator(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
        this.generatedNumbers = new HashSet<>();
    }

    public int next() {
        if (generatedNumbers.size() >= numberGenerator.maxNumberCount()) {
            return -1; // signalling that all numbers are generated
        }
        int nextNum = this.numberGenerator.next();
        while (generatedNumbers.contains(nextNum)) nextNum = this.numberGenerator.next();
        generatedNumbers.add(nextNum);
        return nextNum;
    }
}
