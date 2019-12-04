package bingo;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class GameManager {
    private int numberOfTickets;
    private int maxNumber;
    private int ticketRows;
    private int ticketCols;
    private int numbersPerRow;
    private List<WinningPattern> winningPatterns;

    private List<Ticket> ticketList;
    private Map<Integer, TicketNumber> numberToTicketNumber = new HashMap<>();
    private Map<Integer, List<Ticket>> numberToTickets = new HashMap<>();

    private UniqueNumberGenerator uniqueNumberGenerator;

    public GameManager() {
        gatherInput(true);
        createTickets();
        createWinningPatterns();
        createNumberGenerator();
    }

    private void createTickets() {
        ticketList = new ArrayList<>();
        TicketGenerator ticketGenerator = new TicketGenerator(ticketRows, ticketCols, maxNumber, numbersPerRow);
        for (int i=0;i<numberOfTickets;i++) {
            ticketList.add(ticketGenerator.generateNextTicket(i+1, numberToTicketNumber, numberToTickets));
        }
    }

    private void createWinningPatterns() {
        winningPatterns = new ArrayList<>();
        winningPatterns.add(new FirstFiveWinningPattern());
        winningPatterns.add(new FirstRowWinningPattern());
        winningPatterns.add(new FullHouseWinningPattern());
    }

    private void createNumberGenerator() {
        this.uniqueNumberGenerator = new UniqueNumberGenerator(new NumberGenerator(1, maxNumber));
    }


    private void markNumber(int number) {
        // check if this number is part of 1 or more tickets
        TicketNumber ticketNumber = numberToTicketNumber.get(number);
        if (ticketNumber != null) {
            // if yes mark it (marks it for all tickets that have this number)
            ticketNumber.setMarked(true);
            // increment marked count within each ticket
            numberToTickets.get(number).forEach(ticket -> ticket.incrementMarkedCount());
        }
    }

    private void checkForWinningTickets() {
        winningPatterns.forEach(winningPattern -> {
            for (Ticket ticket: ticketList) {
                if (winningPattern.matches(ticket)) {
                    System.out.println("We have a winner: Player #"+ticket.getId() +" has won "+ winningPattern.name() + " winning combination");
                }
            }
        });
    }


    public void gatherInput(boolean pickDefaults) {
        // gather input parameters
        Scanner scanner = new Scanner(System.in);
        System.out.println("**** Let's Play Housie *****");
        System.out.println("Note: - Press 'Q' to quit any time.");
        if (pickDefaults) {
            maxNumber = Defaults.MAX_NUMBER;
            numberOfTickets = Defaults.TICKET_COUNT;
            ticketRows = Defaults.TICKET_ROWS;
            ticketCols = Defaults.TICKET_COLS;
            numbersPerRow = Defaults.TICKET_NUMBERS_PER_ROW;
            return;
        }
        System.out.println(">> Enter the number range(1-n) :");
        maxNumber = scanner.nextInt();
        System.out.println(">> Enter Number of players playing the game :");
        numberOfTickets = scanner.nextInt();

        gatherTicketSize(scanner);
        gatherNumbersPerRow(scanner);
    }

    private void gatherNumbersPerRow(Scanner scanner) {
        System.out.println(">> Enter numbers per row. Default to 5 :");
        String numbersPerRowStr = scanner.next();
        if (!StringUtils.isBlank(numbersPerRowStr)) {
            numbersPerRow = Integer.parseInt(numbersPerRowStr);
        } else {
            numbersPerRow = Defaults.TICKET_NUMBERS_PER_ROW;
        }
    }

    private void gatherTicketSize(Scanner scanner) {
        boolean isValidSize = false;
        do {
            System.out.println(">> Enter Ticket Size : Default to 3X10 ( 3 rows and 10 columns) :");
            String ticketSize = scanner.next();
            if (StringUtils.isBlank(ticketSize)) {
                ticketRows = Defaults.TICKET_ROWS;
                ticketCols = Defaults.TICKET_COLS;
                isValidSize = true;
            } else {
                String[] parts = ticketSize.split("[x|X]");
                try {
                    ticketRows = Integer.parseInt(parts[0]);
                    ticketCols = Integer.parseInt(parts[1]);
                } catch (NumberFormatException ex) {
                    System.out.println(">> Ticket Size is Invalid...");
                    isValidSize = false;
                }
            }
        } while (!isValidSize);
    }


    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        String input;
        ticketList.forEach(ticket -> System.out.println(ticket));
        do {
            System.out.println(">> Press 'N' to generate next number.");
            input = scanner.next();
            if (input.equalsIgnoreCase("N")) {
                int nextNumber = uniqueNumberGenerator.next();
                if (nextNumber == -1) {
                    System.out.println(">> All numbers are generated. Gamed ends.");
                    break; // we have generated all the numbers in the range
                }
                System.out.println("Next number is: "+nextNumber);
                markNumber(nextNumber);
                checkForWinningTickets();
            }
            System.out.println("Updated tickets:");
            ticketList.forEach(ticket -> System.out.println(ticket));
        } while(!input.equalsIgnoreCase("Q"));
    }

    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.startGame();
    }
}
