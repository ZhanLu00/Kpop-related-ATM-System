package ATM.BankAccounts.DebtAccounts;
import ATM.BankAccounts.BankAccount;

import java.util.Date;


/**
 * An abstract DebtAccount class.
 */
public abstract class DebtAccount extends BankAccount {

    protected DebtAccount(ATM.Users.Client client, Date dateCreated, double balance) {
        super(client, dateCreated, balance);
    }

    public double getBalance() {
        return -this.balance;
    }

}
