package ATM.BankAccounts.AssetAccounts;
import ATM.BankAccounts.BankAccount;

import java.util.Date;


/**
 * An abstract AssetAccount class.
 */
public abstract class AssetAccount extends BankAccount {

    protected AssetAccount(ATM.Users.Client client, Date date) {
        super(client, date);
    }

    public double getBalance() {
        return this.balance;
    }

}
