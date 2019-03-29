package ATM.BankAccounts.ExtraAccounts;

import ATM.BankAccounts.BankAccount;

import java.util.Date;

public class ForeignCurrencyAccount extends BankAccount {
    double exchangeRate;
    public ForeignCurrencyAccount(Date date, double balance, double exchangeRate){
        super(date, balance*exchangeRate);
        this.exchangeRate = exchangeRate;
    }

    public double getBalance() {
        return this.balance;
    }
    /**
     * Deposits the given amount into an account, storing in its foreign currency value.
     */
    public boolean deposit(double amount) {
        this.balance += amount*exchangeRate;
        return true;
    }

    /**
     * Withdraws the given amount out of an account.
     * Withdrawal is successful if balance >= amount after converted to CAD.
     * Returns true if withdrawal is successful, false otherwise.
     */
    public boolean withdraw(double amount) {
        if (this.balance >= amount*exchangeRate) {
            this.balance += -amount*exchangeRate;
            return true;
        } else {
            return false;
        }
    }


}
