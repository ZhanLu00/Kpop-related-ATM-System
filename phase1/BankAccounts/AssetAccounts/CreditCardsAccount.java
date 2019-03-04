package BankAccounts.AssetAccounts;

public class CreditCardsAccount extends AssetAccount {
    public CreditCardsAccount (String password, Users.Client client) {
        super(password, client);
    }

    public boolean withdraw(double amount) {
        System.out.println("It is not possible to withdraw from a credit card account");
        return false;
    }

}
