package bingo;

import java.util.Arrays;
import java.util.Objects;

public class FirstRowWinningPattern extends WinningPattern {

    @Override
    public String name() {
        return "First Row";
    }

    @Override
    public boolean matches(Ticket ticket) {
        TicketNumber[] firstRow = ticket.getTicketNumbers()[0];
        return Arrays.stream(firstRow).filter(Objects::nonNull).allMatch(ticketNumber -> ticketNumber.isMarked());
    }
}
