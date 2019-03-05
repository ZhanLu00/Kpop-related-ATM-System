package ATM.BankAccounts.DebtAccounts;
import ATM.BankAccounts.AssetAccounts.AssetAccount;

import java.util.Date;


/**
 * A CreditCardAccount class.
 */
public class CreditCardsAccount extends DebtAccount {

    public CreditCardsAccount(ATM.Users.Client client, Date dateCreated) {
        super(client,dateCreated);
    }

    public boolean withdraw(double amount) {
        return false;
    }

}
