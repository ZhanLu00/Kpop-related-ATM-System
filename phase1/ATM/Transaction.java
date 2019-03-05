package ATM;
import java.util.Date;


public class Transaction {

    private final double AMOUNT;
    private final Date DATE;

    public Transaction(double amount, Date date) {
        this.AMOUNT = amount;
        this.DATE = date;
    }

    public double getAMOUNT() {
        return AMOUNT;
    }

    public Date getDATE() {
        return DATE;
    }

}
