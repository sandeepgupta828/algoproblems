package bingo;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * Input parser, validator etc.
 */
public class InputParser {
    private int numberOfTickets;
    private int maxNumber;
    private int ticketRows;
    private int ticketCols;
    private int numbersPerRow;

    public GameInput parseInput(boolean pickDefaults) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("**** Let's Play Housie *****");
        System.out.println("Note: - Press 'Q' to quit any time.");

        if (pickDefaults) {
            maxNumber = Defaults.MAX_NUMBER;
            numberOfTickets = Defaults.TICKET_COUNT;
            ticketRows = Defaults.TICKET_ROWS;
            ticketCols = Defaults.TICKET_COLS;
            numbersPerRow = Defaults.TICKET_NUMBERS_PER_ROW;
        } else {
            // ask from user
            maxNumber = gatherRange(scanner);
            numberOfTickets = gatherPlayers(scanner);
            gatherTicketSize(scanner);
            numbersPerRow = gatherNumbersPerRow(scanner);
        }
        return new GameInput(numberOfTickets, maxNumber, ticketRows, ticketCols, numbersPerRow, Defaults.WINNING_PATTERNS);
    }

    private int gatherRange(Scanner scanner) {
        System.out.println(">> Enter the number range(1-n) :");
        String numberRange = scanner.nextLine();
        if (!StringUtils.isBlank(numberRange)) {
            return Integer.parseInt(numberRange);
        } else {
            return Defaults.MAX_NUMBER;
        }            }

    private int gatherPlayers(Scanner scanner) {
        System.out.println(">> Enter Number of players playing the game :");
        String numPlayers = scanner.nextLine();
        if (!StringUtils.isBlank(numPlayers)) {
            return Integer.parseInt(numPlayers);
        } else {
            return Defaults.TICKET_COUNT;
        }
    }

    private int gatherNumbersPerRow(Scanner scanner) {
        System.out.println(">> Enter numbers per row. Default to 5 :");
        String numbersPerRowStr = scanner.nextLine();
        if (!StringUtils.isBlank(numbersPerRowStr)) {
            return Integer.parseInt(numbersPerRowStr);
        } else {
            return Defaults.TICKET_NUMBERS_PER_ROW;
        }
    }

    private void gatherTicketSize(Scanner scanner) {
        boolean isValidSize = false;
        do {
            System.out.println(">> Enter Ticket Size : Default to 3X10 ( 3 rows and 10 columns) :");
            String ticketSize = scanner.nextLine();
            if (StringUtils.isBlank(ticketSize)) {
                ticketRows = Defaults.TICKET_ROWS;
                ticketCols = Defaults.TICKET_COLS;
                isValidSize = true;
            } else {
                String[] parts = ticketSize.split("[x|X]");
                try {
                    ticketRows = Integer.parseInt(parts[0]);
                    ticketCols = Integer.parseInt(parts[1]);
                    isValidSize = true;
                } catch (NumberFormatException ex) {
                    System.out.println(">> Ticket Size is Invalid...");
                }
            }
        } while (!isValidSize);
    }
}
