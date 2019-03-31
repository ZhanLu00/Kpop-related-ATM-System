package src.ATM.Managers;

import src.ATM.BankAccounts.AssetAccounts.ChequingAccount;
import src.ATM.BankAccounts.ExtraAccounts.ForeignCurrencyAccount;
import src.ATM.BankAccounts.ExtraAccounts.LotteryAccount;
import src.ATM.BankAccounts.AssetAccounts.SavingsAccount;
import src.ATM.BankAccounts.BankAccount;
import src.ATM.BankAccounts.DebtAccounts.CreditCardsAccount;
import src.ATM.BankAccounts.DebtAccounts.LineOfCreditAccount;
import src.ATM.BankAccounts.DebtAccounts.DebtAccount;
import src.ATM.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * An AccountManager class that stores all accounts.
 */
public class AccountManager implements Iterable<BankAccount> {
    private ArrayList<BankAccount> accounts;
    private ArrayList<String[]> accountRequests;
    private Date date;

    public AccountManager(ArrayList<BankAccount> accounts, ArrayList<String[]> accountRequests, Date date) {
        this.accounts = accounts;
        this.accountRequests = accountRequests;
        this.date = date;
    }

    public void updateInterestAccounts() {
        for (BankAccount account : accounts) {
            if (account instanceof SavingsAccount) {
                ((SavingsAccount) account).collectInterest();
            }
            else if (account instanceof LotteryAccount) {
                ((LotteryAccount) account).collectInterest();
            }
        }
    }

//    public static void setMaxDebt(int newDebt) {
//        DebtAccount.MAX_DEBT = newDebt;
//    }

    /**
     * Adds an account to the list of accounts.
     */
    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    /**
     * Takes an id number and returns the BankAccount with corresponding id.
     * If id > accounts.size(), method returns null.
     */
    public BankAccount getAccount(int id) {
        if (id < accounts.size()) {
            return accounts.get(id);
        } else {
            return null;
        }
    }

    /**
     * Transfers money between two accounts.
     * Returns true if the transaction is successful, false otherwise.
     */
    public Transaction transfer(double amount, int senderId, int receiverId) {
        BankAccount sender = this.getAccount(senderId);
        BankAccount receiver = this.getAccount(receiverId);
        if(sender.withdraw(amount) && receiver.deposit(amount)) {
            Transaction transaction = new Transaction(amount, senderId, receiverId, "transfer");
            return transaction;
        } else {
            return null;
        }
    }

    public void requestNewAccount(String clientName, String accountType) {
        accountRequests.add(new String[] {clientName, accountType});
    }

    public ArrayList<String[]> getAccountRequests() {
        return accountRequests;
    }

    public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }

    public int getNumAccounts() {
        return accounts.size();
    }

    public BankAccount createAccount(String accountType) {
        if (accountType.equals("CHEQUING_ACCOUNT")) {
            // All new ChequingAccount has its primary attribute set to false.
            return new ChequingAccount(date,0);
        }
        else if (accountType.equals("SAVINGS_ACCOUNT")) {
            return new SavingsAccount(date,0);
        }
        else if (accountType.equals("LOTTERY_ACCOUNT")) {
            return new LotteryAccount(date,0);
        }
        else if (accountType.equals("FOREIGN_CURRENCY_ACCOUNT")){
            return new ForeignCurrencyAccount(date, 0, 0.74);
        }
        else if (accountType.equals("CREDIT_CARD_ACCOUNT")) {
            return new CreditCardsAccount(date,0);
        }
        else if (accountType.equals("LINE_OF_CREDIT_ACCOUNT")) {
            return new LineOfCreditAccount(date,0);
        }
        return null;
    }

    private class AccountManagerIterator implements Iterator<BankAccount> {
        private ArrayList<BankAccount> bankAccounts;
        int i;

        public AccountManagerIterator(ArrayList<BankAccount> bankAccounts) {
            this.bankAccounts = bankAccounts;
            this.i = 0;
        }

        @Override
        public boolean hasNext() {
            return i < bankAccounts.size();
        }

        @Override
        public BankAccount next() {
            i+=1;
            return bankAccounts.get(i-1);
        }
    }

    @Override
    public Iterator<BankAccount> iterator() {
        return new AccountManagerIterator(accounts);
    }

    @Override
    public void forEach(Consumer<? super BankAccount> action) {

    }

    @Override
    public Spliterator<BankAccount> spliterator() {
        return null;
    }
}
