package ATM.BankAccounts.AssetAccounts;


/**
 * A ChequingAccount class.
 */
public class ChequingAccount extends AssetAccount {

    public ChequingAccount(ATM.Users.Client client) {
        super(client);
    }

    public boolean withdraw(double amount) {

        if (this.balance - amount >= -100) {
            this.balance += -amount;
            return true;
        } else {
            return false;
        }

    }

}
