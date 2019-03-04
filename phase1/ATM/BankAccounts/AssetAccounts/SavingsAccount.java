package ATM.BankAccounts.AssetAccounts;



public class SavingsAccount extends AssetAccount {

    public SavingsAccount(ATM.Users.Client client) {
        super(client);
    }

    public boolean withdraw(double amount) {

        if (this.balance >= amount) {
            this.balance += -amount;
            return true;
        } else {
            return false;
        }

    }

}
