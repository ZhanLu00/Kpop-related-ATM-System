package ATM.BankAccounts;
import ATM.BankAccounts.AssetAccounts.ChequingAccount;
import ATM.BankAccounts.ExtraAccounts.ForeignCurrencyAccount;
import ATM.BankAccounts.ExtraAccounts.LotteryAccount;
import ATM.BankAccounts.AssetAccounts.SavingsAccount;
import ATM.BankAccounts.DebtAccounts.CreditCardsAccount;
import ATM.BankAccounts.DebtAccounts.LineOfCreditAccount;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


/**
 * An abstract BankAccount class.
 */
public abstract class BankAccount {

    public static final String LINE_OF_CREDIT = "lineofcredit";
    public static final String CREDIT_CARD = "creditcard";
    public static final String SAVINGS = "savings";
    public static final String CHEQUING ="chequing" ;
    public static final String LOTTERY ="lottery";
    public static final String FOREIGN_CURRENCY = "foreigncurrency";


    protected static int nextId = 0;

    protected int id;
    protected final Date DATE_CREATED;

    protected double balance = 0;


    public BankAccount(Date date, double balance) {

        this.id = nextId;
        nextId += 1;

        this.DATE_CREATED = date;

        this.balance = balance;

    }


    /** Getters **/

    public abstract double getBalance();


    public int getId() {
        return id;
    }

    /**
     * Returns the type of an account in a String format.
     */
    public static String getAccountType(BankAccount account) {
        if (account instanceof ChequingAccount) {
            return BankAccount.CHEQUING;
        }
        else if (account instanceof SavingsAccount) {
            return BankAccount.SAVINGS;
        }
        else if (account instanceof LotteryAccount) {
            return BankAccount.LOTTERY;
        }
        else if (account instanceof CreditCardsAccount) {
            return BankAccount.CREDIT_CARD;
        }
        else if (account instanceof LineOfCreditAccount) {
            return BankAccount.LINE_OF_CREDIT;
        }
        else if (account instanceof ForeignCurrencyAccount) {
            return BankAccount.FOREIGN_CURRENCY;
        }
        return "account";
    }

    /**
     * Deposits the given amount into an account.
     */
    public boolean deposit(double amount) {
        this.balance += amount;
        return true;
    }

    /**
     * Withdraws the given amount out of an account.
     */
    public abstract boolean withdraw(double amount);


    public Date getDATE_CREATED() {
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
                BufferedWriter writer = new BufferedWriter(new FileWriter("outgoing.txt"));
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