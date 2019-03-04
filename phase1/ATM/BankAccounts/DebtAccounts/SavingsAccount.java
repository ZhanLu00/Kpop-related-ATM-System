package ATM.BankAccounts.DebtAccounts;

public class SavingsAccount extends DebtAccount {
    public SavingsAccount(String password, ATM.Users.Client client) {
        super(password, client);
    }

    public boolean withdraw(double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    // TODO: increaseBalance()
}
