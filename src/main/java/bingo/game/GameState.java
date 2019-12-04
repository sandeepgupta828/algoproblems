package bingo.game;

import bingo.ticket.Ticket;
import bingo.ticket.TicketNumber;

import java.util.List;
import java.util.Map;

/**
 * Captures complete state of game
 * This can be serialized to persistent storage
 */
public class GameState {
    private List<Ticket> ticketList ;

    transient private Map<Integer, TicketNumber> numberToTicketNumber;
    transient private Map<Integer, List<Ticket>> numberToTickets;

    public GameState(List<Ticket> ticketList, Map<Integer, TicketNumber> numberToTicketNumber, Map<Integer, List<Ticket>> numberToTickets) {
        this.ticketList = ticketList;
        this.numberToTicketNumber = numberToTicketNumber;
        this.numberToTickets = numberToTickets;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public Map<Integer, TicketNumber> getNumberToTicketNumber() {
        return numberToTicketNumber;
    }

    public Map<Integer, List<Ticket>> getNumberToTickets() {
        return numberToTickets;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "ticketList=" + ticketList +
                '}';
    }
}
