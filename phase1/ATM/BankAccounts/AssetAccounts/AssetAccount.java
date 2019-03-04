package ATM.BankAccounts.AssetAccounts;
import ATM.BankAccounts.BankAccount;



/**
 * An abstract AssetAccount class.
 */
public abstract class AssetAccount extends BankAccount {

    AssetAccount(ATM.Users.Client client) {
        super(client);
    }

    public boolean deposit(double amount) {
        this.balance += amount;
        return true;
    }

}
