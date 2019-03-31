package src.ATM.BankAccounts.DebtAccounts;
import src.ATM.BankAccounts.BankAccount;
import src.ATM.Transaction;

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
