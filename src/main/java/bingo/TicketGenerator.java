package bingo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TicketGenerator {

    private NumberGenerator ticketNumGenerator;
    private NumberGenerator ticketColIndexGenerator;
    private int rows;
    private int cols;
    private int numbersPerRow;

    public TicketGenerator(int rows, int cols, int maxNumber, int numbersPerRow) {
        this.ticketNumGenerator = new NumberGenerator(1, maxNumber);
        this.ticketColIndexGenerator = new NumberGenerator(0, cols-1);
        this.rows = rows;
        this.cols = cols;
        this.numbersPerRow = numbersPerRow;
    }

    public Ticket generateNextTicket(int id) {
        TicketNumber[][] ticketArr = new TicketNumber[rows][];
        Map<Integer, TicketNumber> numberToTicketNumber = new HashMap<>();
        for (int i=0;i<rows;i++) {
            Set<Integer> columnIndexes = new HashSet<>();
            ticketArr[i] = new TicketNumber[cols];
            for (int j=0;j<numbersPerRow;j++) {
                int nextCol = getNextCol(columnIndexes);
                columnIndexes.add(nextCol);
                int nextNum = getNextNum(numberToTicketNumber.keySet());
                ticketArr[i][nextCol] = new TicketNumber(nextNum);
                numberToTicketNumber.put(nextNum, ticketArr[i][nextCol]);
            }
            // places that are not initialized with TicketNumber are null
        }
        return new Ticket(id, ticketArr, numberToTicketNumber);
    }

    private int getNextNum(Set<Integer> numbers) {
        int nextNum = ticketNumGenerator.next();
        while (numbers.contains(nextNum)) nextNum = ticketNumGenerator.next();
        return nextNum;
    }

    private int getNextCol(Set<Integer> columnIndexes) {
        int nextCol = ticketColIndexGenerator.next();
        while (columnIndexes.contains(nextCol)) nextCol = ticketColIndexGenerator.next();
        return nextCol;
    }
}

