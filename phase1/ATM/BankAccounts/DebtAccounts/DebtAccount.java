package ATM.BankAccounts.DebtAccounts;
import ATM.BankAccounts.BankAccount;

import java.util.Date;


/**
 * An abstract DebtAccount class.
 */
public abstract class DebtAccount extends BankAccount {

    protected DebtAccount(Date dateCreated, double balance) {
        super(dateCreated, balance);
    }

    public double getBalance() {
        return -this.balance;
    }

}
