package bingo.patterns;

import bingo.ticket.Ticket;

/**
 * Abstract class for winning patterns
 */
public abstract class WinningPattern {
    abstract public String name();
    abstract public boolean matches(Ticket ticket);
}
