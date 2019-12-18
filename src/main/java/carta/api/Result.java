package carta.api;

import java.math.BigDecimal;
import java.util.List;

public class Result {
    private final String date;
    private final Double cash_raised;
    private final Long total_number_of_shares;
    private final List<ShareOwner> ownership;

    public Result(String date, Double cash_raised, Long total_number_of_shares, List<ShareOwner> ownership) {
        this.date = date;
        this.cash_raised = cash_raised;
        this.total_number_of_shares = total_number_of_shares;
        this.ownership = ownership;
    }

    public String getDate() {
        return date;
    }

    public Double getCash_raised() {
        return cash_raised;
    }

    public Long getTotal_number_of_shares() {
        return total_number_of_shares;
    }

    public List<ShareOwner> getOwnership() {
        return ownership;
    }
}
