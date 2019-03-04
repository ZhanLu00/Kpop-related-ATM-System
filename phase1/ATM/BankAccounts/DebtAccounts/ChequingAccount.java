package ATM.BankAccounts.DebtAccounts;

public class ChequingAccount extends DebtAccount {
    public ChequingAccount (String password, ATM.Users.Client client) {
        super(password, client);
    }

    public boolean withdraw(double amount) {
        if (this.balance - amount >= -100) {
            this.balance -= amount;
            return true;
        }
        return false;
    }
}
