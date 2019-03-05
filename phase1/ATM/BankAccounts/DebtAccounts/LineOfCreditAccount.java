package ATM.BankAccounts.DebtAccounts;
import ATM.BankAccounts.AssetAccounts.AssetAccount;


/**
 * A LineOfCreditAccount class.
 */
public class LineOfCreditAccount extends AssetAccount {

    protected LineOfCreditAccount (ATM.Users.Client client) {
        super(client);
    }

    public boolean withdraw(double amount) {
        this.balance += -amount;
        return true;
    }

}
