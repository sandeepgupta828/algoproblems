package bingo;

public class FirstRowWinningPattern extends WinningPattern {

    @Override
    public String name() {
        return "First Row";
    }

    @Override
    public boolean matches(Ticket ticket) {
        return ticket.computeMarkedCountForRow(0) == (ticket.getNumberCount()/ticket.getTicketNumbers().length); // numbers per row = total numbers/number of rows
    }

    @Override
    public String toString() {
        return "FirstRowWinningPattern{}";
    }
}
