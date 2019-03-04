package ATM.BankAccounts.AssetAccounts;

public class LineOfCreditAccount extends AssetAccount {
    public LineOfCreditAccount (String password, ATM.Users.Client client) {
        super(password, client);
    }

    public boolean withdraw(double amount) {
        this.balance += amount;
        return true;
    }
}
