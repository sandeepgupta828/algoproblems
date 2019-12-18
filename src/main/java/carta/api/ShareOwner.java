package carta.api;

import java.math.BigDecimal;

public class ShareOwner {
    private final String investor;
    private final Long shares;
    private final Double cash_paid;
    private final BigDecimal ownership;

    public ShareOwner(String investor, Long shares, Double cash_paid, BigDecimal ownership) {
        this.investor = investor;
        this.shares = shares;
        this.cash_paid = cash_paid;
        this.ownership = ownership;
    }

    public String getInvestor() {
        return investor;
    }

    public Long getShares() {
        return shares;
    }

    public Double getCash_paid() {
        return cash_paid;
    }

    public BigDecimal getOwnership() {
        return ownership;
    }
}

