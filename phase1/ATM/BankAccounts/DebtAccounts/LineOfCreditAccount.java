package ATM.BankAccounts.DebtAccounts;
import ATM.BankAccounts.AssetAccounts.AssetAccount;

import java.util.Date;


/**
 * A LineOfCreditAccount class.
 */
public class LineOfCreditAccount extends DebtAccount {

    public LineOfCreditAccount(ATM.Users.Client client, Date dateCreated) {
        super(client,dateCreated);
    }

    public boolean withdraw(double amount) {
        this.balance += -amount;
        return true;
    }

}
