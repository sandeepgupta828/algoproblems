package bingo;

import java.util.*;

/**
 * Sets up the game and then plays the game by doing necessary game steps
 * (1) Generate next number
 * (2) Check for winning combinations
 * (3) Interact with user
 */
public class GamePlay {
    private UniqueNumberGenerator uniqueNumberGenerator;
    private GameInput gameInput;
    private GameState gameState;

    public GamePlay(GameInput gameInput) {
        this.gameInput = gameInput;
    }

    /**
     * sets up game state -- determined by gathered input
     */
    public void setup() {
        this.uniqueNumberGenerator = new UniqueNumberGenerator(new NumberGenerator(1, gameInput.getMaxNumber()));
        List<Ticket> ticketList = new ArrayList<>();
        Map<Integer, TicketNumber> numberToTicketNumber = new HashMap<>();
        Map<Integer, List<Ticket>> numberToTickets = new HashMap<>();
        // generate tickets and associated lookup maps for fast search
        TicketGenerator ticketGenerator = new TicketGenerator(gameInput.getTicketRows(), gameInput.getTicketCols(), gameInput.getMaxNumber(), gameInput.getNumbersPerRow());
        for (int i=0;i<gameInput.getNumberOfTickets();i++) {
            ticketList.add(ticketGenerator.generateNextTicket(i+1, numberToTicketNumber, numberToTickets));
        }
        // setup game state
        this.gameState = new GameState(ticketList, numberToTicketNumber, numberToTickets);
    }

    /**
     * plays the game, mutates game state as game progresses
     */
    public void play(boolean interactive) {
        Scanner scanner = new Scanner(System.in);
        String input;
        gameState.getTicketList().forEach(ticket -> System.out.println(ticket));
        do {
            if (interactive) {
                System.out.println(">> Press 'N' to generate next number.");
                input = scanner.next();
            } else {
                input = "N";
            }
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
            gameState.getTicketList().forEach(ticket -> System.out.println(ticket));
        } while(!input.equalsIgnoreCase("Q"));
    }

    /**
     * Mutates game state by marking a given number across all tickets that have the number
     * @param number
     */
    private void markNumber(int number) {
        // check if this number is part of 1 or more tickets
        TicketNumber ticketNumber = gameState.getNumberToTicketNumber().get(number);
        if (ticketNumber != null) {
            // if yes mark it (marks it for all tickets that have this number)
            ticketNumber.setMarked(true);
            // increment marked count within each ticket
            gameState.getNumberToTickets().get(number).forEach(ticket -> ticket.incrementMarkedCount());
        }
    }

    /**
     * checks for winning combinations across tickets
     */
    private void checkForWinningTickets() {
        gameInput.getWinningPatterns().forEach(winningPattern -> {
            for (Ticket ticket: gameState.getTicketList()) {
                if (!ticket.getPatternsWon().contains(winningPattern.name()) && winningPattern.matches(ticket)) {
                    System.out.println("We have a winner: Player #"+ticket.getId() +" has won "+ winningPattern.name() + " winning combination");
                    ticket.addWonPattern(winningPattern.name());
                }
            }
        });
    }

    public GameState getGameState() {
        return gameState;
    }

    public UniqueNumberGenerator getUniqueNumberGenerator() {
        return uniqueNumberGenerator;
    }
}
