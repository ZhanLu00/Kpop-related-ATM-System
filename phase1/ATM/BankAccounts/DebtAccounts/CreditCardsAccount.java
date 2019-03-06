package ATM.BankAccounts.DebtAccounts;
import ATM.BankAccounts.AssetAccounts.AssetAccount;

import java.util.Date;


/**
 * A CreditCardAccount class.
 */
public class CreditCardsAccount extends DebtAccount {

    public CreditCardsAccount(ATM.Users.Client client, Date dateCreated, double balance) {
        super(client,dateCreated, balance);
    }

    /**
     * Withdraws the given amount out of an account.
     * Since it is impossible to withdraw from a credit card account, the method always returns false.
     */
    public boolean withdraw(double amount) {
        return false;
    }

}
