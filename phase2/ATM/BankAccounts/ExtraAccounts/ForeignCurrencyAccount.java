package ATM.BankAccounts.ExtraAccounts;

import ATM.BankAccounts.BankAccount;

import java.util.Date;

public class ForeignCurrencyAccount extends BankAccount {
    double exchangeRate;
    double foreignCurrencyBalance;
    public ForeignCurrencyAccount(Date date, double balance, double exchangeRate){
        super(date, balance);
        this.exchangeRate = exchangeRate;
        this.foreignCurrencyBalance = exchangeRate * this.balance;
    }

    public double getBalance() {
        return this.balance;
    }
    /**
     * Deposits the given amount into an account, storing its CAD and foreign currency values.
     */
    public boolean deposit(double amount) {
        this.balance += amount;
        this.foreignCurrencyBalance += amount*exchangeRate;
        return true;
    }

    /**
     * Withdraws the given amount out of an account.
     * Withdrawal is successful if balance >= amount.
     * Returns true if withdrawal is successful, false otherwise.
     */
    public boolean withdraw(double amount) {
        if (this.balance >= amount) {
            this.balance += -amount;
            this.foreignCurrencyBalance += -amount*exchangeRate;
            return true;
        } else {
            return false;
        }
    }


}
