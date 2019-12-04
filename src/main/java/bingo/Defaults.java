package bingo;

import java.util.ArrayList;
import java.util.List;

public class Defaults {
    public static final int MAX_NUMBER = 90;
    public static final int TICKET_ROWS = 3;
    public static final int TICKET_COLS = 10;
    public static final int TICKET_NUMBERS_PER_ROW = 5;
    public static final int TICKET_COUNT = 5;
    public static final List<WinningPattern> WINNING_PATTERNS;
    static {
        WINNING_PATTERNS = new ArrayList<>();
        WINNING_PATTERNS.add(new FirstFiveWinningPattern());
        WINNING_PATTERNS.add(new FirstRowWinningPattern());
        WINNING_PATTERNS.add(new FullHouseWinningPattern());
    }
}
