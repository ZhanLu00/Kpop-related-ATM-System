package ATM.BankAccounts.DebtAccounts;
import ATM.BankAccounts.BankAccount;
import ATM.Transaction;

import java.util.Date;


/**
 * An abstract DebtAccount class.
 */
public abstract class DebtAccount extends BankAccount {

    protected DebtAccount(Date dateCreated, double balance) {
        super(dateCreated, balance);
    }

    protected DebtAccount(Date dateCreated, double balance, Transaction lastTransaction) {
        super(dateCreated, balance,lastTransaction);
    }

    public double getBalance() {
        return -this.balance;
    }

}
