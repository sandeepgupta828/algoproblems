package bingo;

public class TicketNumber {
    private final int number;
    private boolean marked = false;

    public TicketNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public boolean isMarked() {
        return marked;
    }

    @Override
    public String toString() {
        return "(" + number + ","
                + marked +
                ")";
    }
}
