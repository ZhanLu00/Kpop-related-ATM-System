package ATM.Currency;
import java.util.*;



public class CurrencyManager {

    /**
     * A list of exchange rates.
     */
    private ArrayList<Object[]> rates = new ArrayList<>();


    /**
     * Gets the exchange rate from USD to the target currency.
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
