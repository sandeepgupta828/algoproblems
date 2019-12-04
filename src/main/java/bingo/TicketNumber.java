package bingo;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketNumber that = (TicketNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "(" + number + ","
                + marked +
                ")";
    }
}
