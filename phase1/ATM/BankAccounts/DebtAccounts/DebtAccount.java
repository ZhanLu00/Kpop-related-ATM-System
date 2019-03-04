package ATM.BankAccounts.DebtAccounts;
import ATM.BankAccounts.BankAccount;


/**
 * An abstract DebtAccount class.
 */
public abstract class DebtAccount extends BankAccount {

    protected DebtAccount(ATM.Users.Client client) {
        super(client);
    }

    public double getBalance() {
        return -this.balance;
    }

}
