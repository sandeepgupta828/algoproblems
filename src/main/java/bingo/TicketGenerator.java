package bingo;

import java.util.*;

public class TicketGenerator {

    private int rows;
    private int cols;
    private int maxNumber;
    private int numbersPerRow;

    public TicketGenerator(int rows, int cols, int maxNumber, int numbersPerRow) {
        this.rows = rows;
        this.cols = cols;
        this.maxNumber = maxNumber;
        this.numbersPerRow = numbersPerRow;
    }

    public Ticket generateNextTicket(int id, Map<Integer, TicketNumber> numberToTicketNumber, Map<Integer, List<Ticket>> numberToTickets) {
        UniqueNumberGenerator ticketNumGenerator = new UniqueNumberGenerator(new NumberGenerator(1, maxNumber));
        TicketNumber[][] ticketArr = new TicketNumber[rows][];
        // create new ticket
        Ticket ticket = new Ticket(id, ticketArr, this.rows*this.numbersPerRow);
        // fill the ticket with unique numbers
        for (int i=0;i<rows;i++) {
            ticketArr[i] = new TicketNumber[cols];
            // random col index generator
            UniqueNumberGenerator ticketColIndexGenerator = new UniqueNumberGenerator(new NumberGenerator(0, cols-1));
            for (int j=0;j<numbersPerRow;j++) {
                int nextCol = ticketColIndexGenerator.next();
                int nextNum = ticketNumGenerator.next();
                // update number to TicketNumber map
                // generate new TicketNumber if not already present (re-use)
                numberToTicketNumber.putIfAbsent(nextNum, new TicketNumber(nextNum));
                ticketArr[i][nextCol] = numberToTicketNumber.get(nextNum);
                numberToTicketNumber.put(nextNum, ticketArr[i][nextCol]);
                // update number to list of Tickets map
                numberToTickets.putIfAbsent(nextNum, new ArrayList<>());
                numberToTickets.get(nextNum).add(ticket);
            }
            // places that are not initialized with TicketNumber are null
        }
        return ticket;
    }

}

