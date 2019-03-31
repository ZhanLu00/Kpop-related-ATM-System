package ATM.BankAccounts.ExtraAccounts;

import ATM.BankAccounts.BankAccount;

import java.util.Date;

public class ForeignCurrencyAccount extends BankAccount {
    double exchangeRate;
    double foreignCurrencyBalance;
    String currencyType;
    public ForeignCurrencyAccount(Date date, double initialBalance, String currencyType, double exchangeRate){
        super(date, initialBalance);
        this.exchangeRate = exchangeRate;
        this.currencyType = currencyType;
        this.foreignCurrencyBalance = exchangeRate * this.balance;
    }

    public String getCurrencyType() { return this.currencyType; }
    public double getBalance() {
        return this.balance;
    }
    /**
     * Deposits the given CAD amount into an account, storing its CAD and foreign currency values.
     */
    public boolean deposit(double amount) {
        this.balance += amount;
        this.foreignCurrencyBalance += amount*exchangeRate;
        return true;
    }

    public double getForeignCurrencyBalance() {
        return foreignCurrencyBalance;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    /**
     * Sets exchange rate and updates balances accordingly.
     */
    public boolean setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
        this.balance = this.foreignCurrencyBalance/exchangeRate;
        return true;
    }
    /**
     * Withdraws the given amount out of an account as CAD.
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
