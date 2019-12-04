package bingo;

public class FirstFiveWinningPattern extends WinningPattern {

    @Override
    public String name() {
        return "First Five";
    }

    @Override
    public boolean matches(Ticket ticket) {
        return ticket.getMarkedCount() >=5;
    }
}
