package bingo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Ticket {
    private final int id;
    private final TicketNumber[][] ticketNumbers;
    private final int numberCount;
    private int markedCount = 0;
    private final Set<String> patternsWon;


    public Ticket(int id, TicketNumber[][] ticketNumbers, int numberCount) {
        this.id = id;
        this.ticketNumbers = ticketNumbers;
        this.numberCount = numberCount;
        this.patternsWon = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public TicketNumber[][] getTicketNumbers() {
        return ticketNumbers;
    }

    public int getNumberCount() {
        return numberCount;
    }

    public int incrementMarkedCount() {
        return markedCount++;
    }

    public Set<String> getPatternsWon() {
        return patternsWon;
    }

    public void addWonPattern(String pattern) {
        patternsWon.add(pattern);
    }

    public int getMarkedCount() {
        return markedCount;
    }

    public int computeMarkedCountForRow(int rowIndex) {
        int countMarked = 0;
        for (TicketNumber number : ticketNumbers[rowIndex]) {
            if (number != null && number.isMarked()) {
                countMarked++;
            }
        }
        return countMarked;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", ticketNumbers=" + Arrays.deepToString(ticketNumbers) +
                ", countMarked=" + markedCount +
                '}';
    }
}
