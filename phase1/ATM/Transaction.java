package ATM;
import java.util.Date;


public class Transaction {

    private final double AMOUNT;
    private final String TYPE;
    private final Date DATE;

    public Transaction(double amount, String type, Date date) {
        this.AMOUNT = amount;
        this.TYPE = type;
        this.DATE = date;
    }

    public double getAMOUNT() {
        return AMOUNT;
    }

    public String getTYPE() {
        return TYPE;
    }

    public Date getDATE() {
        return DATE;
    }

}
