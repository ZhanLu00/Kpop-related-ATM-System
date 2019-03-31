package ATM.BankAccounts.DebtAccounts;
import java.util.Date;


/**
 * A CreditCardAccount class.
 */
public class CreditCardsAccount extends DebtAccount {

    public CreditCardsAccount(Date dateCreated, double initialBalance) {
        super(dateCreated, initialBalance);
    }

    /**
     * Attempts to withdraw *amount* from account.
     * Since it's impossible to withdraw from a credit card account, this always returns false.
     */
    public boolean withdraw(double amount) {
        return false;
    }

}
