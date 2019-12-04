package bingo;

public class FullHouseWinningPattern extends WinningPattern {

    @Override
    public String name() {
        return "Full House";
    }

    @Override
    public boolean matches(Ticket ticket) {
        return ticket.getMarkedCount() == ticket.getNumberCount();
    }

    @Override
    public String toString() {
        return "FullHouseWinningPattern{}";
    }
}
