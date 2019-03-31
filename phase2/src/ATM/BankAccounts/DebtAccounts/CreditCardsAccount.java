package src.ATM.BankAccounts.DebtAccounts;
import java.util.Date;


/**
 * A CreditCardAccount class.
 */
public class CreditCardsAccount extends DebtAccount {

    public CreditCardsAccount(Date dateCreated, double initialBalance) {
        super(dateCreated, initialBalance);
    }

    /**
     * Withdraws the given amount out of an account.
     * Since it is impossible to withdraw from a credit card account, the method always returns false.
     */
    public boolean withdraw(double amount) {
        return false;
    }

}
