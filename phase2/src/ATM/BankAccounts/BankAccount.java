package src.ATM.BankAccounts;

import src.ATM.BankAccounts.AssetAccounts.ChequingAccount;
import src.ATM.BankAccounts.AssetAccounts.SavingsAccount;
import src.ATM.BankAccounts.DebtAccounts.CreditCardsAccount;
import src.ATM.BankAccounts.DebtAccounts.LineOfCreditAccount;
import src.ATM.BankAccounts.ExtraAccounts.ForeignCurrencyAccount;
import src.ATM.BankAccounts.ExtraAccounts.LotteryAccount;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


/**
 * An abstract BankAccount class.
 */
public abstract class BankAccount {

    protected static int nextId = 0;
    protected final int ID;
    protected final Date DATE_CREATED;
    protected double balance = 0;


    public BankAccount(Date date, double initialBalance) {
        this.ID = nextId;
        BankAccount.nextId += 1;
        this.DATE_CREATED = date;
        this.balance = initialBalance;
    }

    public int getId() {
        return this.ID;
    }

    public String getType() {
        if (this instanceof ChequingAccount) {
            return "CHEQUING_ACCOUNT";
        } else if (this instanceof SavingsAccount) {
            return "SAVINGS_ACCOUNT";
        } else if (this instanceof CreditCardsAccount) {
            return "CREDIT_CARD_ACCOUNT";
        } else if (this instanceof LotteryAccount) {
            return "LOTTERY_ACCOUNT";
        } else if (this instanceof LineOfCreditAccount) {
            return "LINE_OF_CREDIT_ACCOUNT";
        } else if (this instanceof ForeignCurrencyAccount) {
            return "FOREIGN_CURRENCY_ACCOUNT";
        } else {
            return "UNKNOWN_ACCOUNT";
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public abstract boolean deposit(double amount);
    public abstract boolean withdraw(double amount);

    public Date getDateCreated() {
        return DATE_CREATED;
    }

    /**
     * Pays a bill by transferring money out to a non-user's account.
     * Writes the transaction in outgoing.txt.
     * Updates this.lastTransaction.
     * Returns True if paying is successful, false otherwise.
     */
    public boolean payBill(double amount, int receiver) {
        if(this.withdraw(amount)) {
            try {
                String bill = amount + ", " + receiver;
                BufferedWriter writer = new BufferedWriter(new FileWriter("/outgoing.txt"));
                writer.append(bill);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }
}