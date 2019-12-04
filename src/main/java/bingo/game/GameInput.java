package bingo.game;

import bingo.patterns.WinningPattern;

import java.util.List;

public class GameInput {
    private final int numberOfTickets;
    private final int maxNumber;
    private final int ticketRows;
    private final int ticketCols;
    private final int numbersPerRow;
    private final List<WinningPattern> winningPatterns;

    public GameInput(int numberOfTickets, int maxNumber, int ticketRows, int ticketCols, int numbersPerRow, List<WinningPattern> winningPatterns) {
        this.numberOfTickets = numberOfTickets;
        this.maxNumber = maxNumber;
        this.ticketRows = ticketRows;
        this.ticketCols = ticketCols;
        this.numbersPerRow = numbersPerRow;
        this.winningPatterns = winningPatterns;
        if ((numbersPerRow*ticketRows) > maxNumber) {
            throw new RuntimeException("Invalid input: ticket cannot be generated for given ticket size and given max number");
        }
        // add other validations
        // count of number combinations C1 = maxNumber(Choose)numberOfTickets*numbersPerRow
        // count of column combinations C2 = ticketCols(Choose)numbersPerRow^ticketRows
        // numberOfTickets <= C1*C2
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public int getTicketRows() {
        return ticketRows;
    }

    public int getTicketCols() {
        return ticketCols;
    }

    public int getNumbersPerRow() {
        return numbersPerRow;
    }

    public List<WinningPattern> getWinningPatterns() {
        return winningPatterns;
    }

    @Override
    public String toString() {
        return "GameInput{" +
                "numberOfTickets=" + numberOfTickets +
                ", maxNumber=" + maxNumber +
                ", ticketRows=" + ticketRows +
                ", ticketCols=" + ticketCols +
                ", numbersPerRow=" + numbersPerRow +
                ", winningPatterns=" + winningPatterns +
                '}';
    }
}
