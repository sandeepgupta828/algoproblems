package bingo;

import java.util.Arrays;

public class Ticket {
    private final int id;
    private final TicketNumber[][] ticketNumbers;
    private final int numberCount;
    private int markedCount = 0;


    public Ticket(int id, TicketNumber[][] ticketNumbers, int numberCount) {
        this.id = id;
        this.ticketNumbers = ticketNumbers;
        this.numberCount = numberCount;
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
