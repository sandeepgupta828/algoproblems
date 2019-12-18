package carta.api;

import java.math.BigDecimal;
import java.util.Date;

/*
#INVESTMENT DATE, SHARES PURCHASED, CASH PAID, INVESTOR
2016-04-03,1000,10000.00,Sandy Lerner
 */
public class ShareData {
    private final Date investmentDate;
    private final Long sharesPurchased;
    private final Double cashPaid;
    private final String investor;

    public ShareData(Date investmentDate, Long sharesPurchased, Double cashPaid, String investor) {
        this.investmentDate = investmentDate;
        this.sharesPurchased = sharesPurchased;
        this.cashPaid = cashPaid;
        this.investor = investor;
    }

    public Date getInvestmentDate() {
        return investmentDate;
    }

    public Long getSharesPurchased() {
        return sharesPurchased;
    }

    public Double getCashPaid() {
        return cashPaid;
    }

    public String getInvestor() {
        return investor;
    }

    @Override
    public String toString() {
        return "ShareData{" +
                "investmentDate=" + investmentDate +
                ", sharesPurchased=" + sharesPurchased +
                ", cashPaid=" + cashPaid +
                ", investor='" + investor + '\'' +
                '}';
    }
}
