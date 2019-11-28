package bingo;

public abstract class WinningPattern {
    abstract public String name();
    abstract public boolean matches(Ticket ticket);
}
