package ATM.BankAccounts.DebtAccounts;
import ATM.BankAccounts.AssetAccounts.AssetAccount;


/**
 * A CreditCardAccount class.
 */
public class CreditCardsAccount extends AssetAccount {

    protected CreditCardsAccount (ATM.Users.Client client) {
        super(client);
    }

    public boolean withdraw(double amount) {
        return false;
    }

}
