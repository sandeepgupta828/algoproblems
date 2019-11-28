package bingo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class GameManager {
    private int numberOfTickets;
    private int maxNumber;
    private int ticketRows;
    private int ticketCols;
    private int numbersPerRow;
    private List<WinningPattern> winningPatterns;
    private List<Ticket> ticketList;
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
            ticketList.add(ticketGenerator.generateNextTicket(i+1));
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

    private void markTicketsForNumber(int number) {
        ticketList.forEach(ticket -> ticket.markNumber(number));
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
        Pattern ticketSizePattern = Pattern.compile("^\\d+[X|x]\\d+.*$");
        // gather input parameters
        Scanner scanner = new Scanner(System.in);
        System.out.println("**** Let's Play Housie *****");
        System.out.println("Note: - Press 'Q' to quit any time.");
        if (pickDefaults) {
            maxNumber = 50;
            numberOfTickets = 5;
            ticketRows = 3;
            ticketCols = 5;
            numbersPerRow = 4;
            return;
        }
        System.out.println(">> Enter the number range(1-n) :");
        maxNumber = scanner.nextInt();
        System.out.println(">> Enter Number of players playing the game :");
        // TBD: validate ticketSize > 0
        numberOfTickets = scanner.nextInt();
        String ticketSize = null;
        boolean isValidSize = false;
        do {
            System.out.println(">> Enter Ticket Size : Default to 3X10 ( 3 rows and 10 columns) :");
            ticketSize = scanner.next();
            isValidSize = ticketSize.isEmpty() || ticketSizePattern.matcher(ticketSize).matches();
            if (!isValidSize) {
                System.out.println(">> Ticket Size is Invalid...");
            }
        } while (!isValidSize);

        if (!ticketSize.isEmpty() ) {
                String[] parts = ticketSize.split("[x|X]");
                // TBD: validate rows and columns > 0
                ticketRows = Integer.parseInt(parts[0]);
                ticketCols = Integer.parseInt(parts[1]);
        } else {
            // defaults
            ticketRows = 3;
            ticketCols = 10;
        }
        System.out.println(">> Enter numbers per row. Default to 5 :");
        String numbersPerRowStr = scanner.next();
        if (!numbersPerRowStr.isEmpty()) {
            numbersPerRow = Integer.parseInt(numbersPerRowStr);
        } else {
            numbersPerRow = 5;
        }
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
                markTicketsForNumber(nextNumber);
                checkForWinningTickets();
            }
            ticketList.forEach(ticket -> System.out.println(ticket));
        } while(!input.equalsIgnoreCase("Q"));
    }

    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.startGame();
    }
}
