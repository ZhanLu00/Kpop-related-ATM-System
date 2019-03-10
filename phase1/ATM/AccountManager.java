package ATM;

import ATM.BankAccounts.AssetAccounts.ChequingAccount;
import ATM.BankAccounts.AssetAccounts.SavingsAccount;
import ATM.BankAccounts.BankAccount;
import ATM.BankAccounts.DebtAccounts.CreditCardsAccount;
import ATM.BankAccounts.DebtAccounts.LineOfCreditAccount;

import java.util.*;
import java.util.function.Consumer;

/**
 * An AccountManager class that stores all accounts.
 */
public class AccountManager implements Iterable<BankAccount> {
    private ArrayList<BankAccount> accounts;
    private ArrayList<String[]> accountRequests;
    private Date date;

    public AccountManager(ArrayList<BankAccount> accounts, Date date) {
        this.accounts = accounts;
        this.accountRequests = new ArrayList<>();
        this.date = date;
    }

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
    public boolean transfer(double amount, int senderId, int receiverId) {
        BankAccount sender = this.getAccount(senderId);
        BankAccount receiver = this.getAccount(receiverId);
        if(sender.withdraw(amount) && receiver.deposit(amount)) {
            // TODO: 2019-03-05 add transaction date
            Transaction transaction = new Transaction(amount, sender, receiver, date);
            sender.setLastTransaction(transaction);
            return true;
        } else {
            return false;
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
        if (accountType.equals(BankAccount.CHEQUING)) {
            return new ChequingAccount(date,0);
        }
        else if (accountType.equals(BankAccount.SAVINGS)) {
            return new SavingsAccount(date,0);
        }
        else if (accountType.equals(BankAccount.CREDIT_CARD)) {
            return new CreditCardsAccount(date,0);
        }
        else if (accountType.equals(BankAccount.LINE_OF_CREDIT)) {
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
            return bankAccounts.get(i);
        }
    }

    @Override
    public Iterator<BankAccount> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super BankAccount> action) {

    }

    @Override
    public Spliterator<BankAccount> spliterator() {
        return null;
    }
}
