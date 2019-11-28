package bingo;

import java.util.Arrays;
import java.util.Map;

public class Ticket {
    private final int id;
    private final TicketNumber[][] ticketNumbers;
    private final Map<Integer, TicketNumber> numberToTicketNumber;
    private int countMarked = 0;


    public Ticket(int id, TicketNumber[][] ticketNumbers, Map<Integer, TicketNumber> numberToTicketNumber) {
        this.id = id;
        this.ticketNumbers = ticketNumbers;
        this.numberToTicketNumber = numberToTicketNumber;
    }

    public int getId() {
        return id;
    }

    public int getCountMarked() {
        return countMarked;
    }

    public TicketNumber[][] getTicketNumbers() {
        return ticketNumbers;
    }

    public Map<Integer, TicketNumber> getNumberToTicketNumber() {
        return numberToTicketNumber;
    }

    public void markNumber(int number) {
        TicketNumber ticketNumber = numberToTicketNumber.get(number);
        if (ticketNumber != null && !ticketNumber.isMarked()) {
            ticketNumber.setMarked(true);
            ++countMarked;
        }
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", ticketNumbers=" + Arrays.deepToString(ticketNumbers) +
                ", countMarked=" + countMarked +
                '}';
    }
}
