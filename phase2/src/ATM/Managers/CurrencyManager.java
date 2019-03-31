package ATM.Managers;
import java.util.*;


public class CurrencyManager {

    private ArrayList<Object[]> rates = new ArrayList<>();

    public CurrencyManager(ArrayList<Object[]> rates){
        this.rates = rates;
    }

    /**
     * Gets the exchange rate from CAD to the target currency.
     */
    public double getRate(String targetPrefix){
        for (Object[] exchangeRate : rates) {
            String prefix = (String) exchangeRate[0];
            if (prefix.equals(targetPrefix)) {
                return (double) exchangeRate[1];
            }
        }
        return -1;
    }

    /**
     * Creates a new exchange rate, or replaces an existing entry.
     *
     * Prefix must be exactly 3 characters, and rate must be positive.
     */
    public boolean setRate(String prefix, double rate){
        if (prefix.length() == 3 && rate > 0) {
            Object[] newRate = {prefix, rate};
            rates.add(newRate);
            return true;
        }
        return false;
    }

}
