package ATM.BankAccounts.AssetAccounts;

import java.util.Date;


/**
 * A ChequingAccount class.
 */
public class ChequingAccount extends AssetAccount {

    public ChequingAccount(ATM.Users.Client client, Date date) {
        super(client, date);
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
