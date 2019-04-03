package ATM.ActionHandler;

import ATM.*;
import ATM.BankAccounts.AssetAccounts.AssetAccount;
import ATM.BankAccounts.AssetAccounts.ChequingAccount;
import ATM.BankAccounts.BankAccount;
import ATM.BankAccounts.DebtAccounts.DebtAccount;
import ATM.BankAccounts.DebtAccounts.CreditCardsAccount;
import ATM.Managers.AccountManager;
import ATM.Managers.BillManager;
import ATM.Users.Client;
import ATM.Atm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A ClientActionHandler class.
 * This class handles actions from Client, acting as an operation class.
 * This class will need to access and modify fields from AccountManager, BankManager and Client.
 */
public class ClientActionHandler {
    // this class will be handle actions from users
    // acting as an operation class here
    // two types of actions come from two types of user
    // this class will need to access and mutate the Accounts from AccountManager
    // bankManager
    // Client

    private Client client;
    private AccountManager accountManager;
    private BillManager billManager;
    private BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
    private Atm atm;


    public ClientActionHandler(Client client, Atm atm) {
        this.atm = atm;
        this.client = client;
        this.accountManager = atm.getAccountManager();
        this.billManager = atm.getBillManager();
    }

    // check balance
    public Map<Integer, Double> checkBalance() {
        Map balance = new HashMap<Integer, Double>();

        // get account numbers first
        for (int accountNumber : this.client.getAccounts()) {
            // get balance from each of the bank number
            balance.put(accountNumber, this.accountManager.getAccount(accountNumber).getBalance());
        }
        return balance;
    }

    // check transaction history (of itself)

    // withdraw
    public Boolean withdraw(BankAccount account, int amount) {
    /*
    @ TODO add the function below in the user interface part
    the function only returns true or false
     */
        // will return a new balance if request complete, return false if declined

        // if
        if (account instanceof CreditCardsAccount) {
            return false;
        } else {

            if (account.withdraw(amount)) {
                if (billManager.withdrawable(amount)) {
                    return true;
                } else {
                    account.deposit(amount);
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    // transfer
    public Boolean transfer(double amount, int senderId, int receiverId) {

        // check if both in and out acc are in the system
        // also check if the out is from the owner's account
        if (client.getAccounts().contains(senderId)){
            if (accountManager.accountExist(receiverId)){
                // transfer and check if theres enough balance
                Transaction transaction = this.accountManager.transfer(amount, senderId, receiverId);
                // check if it this transaction is transferable
                if (transaction!=null) {
                    // add transaction history
                    atm.getTransactionManager().addTransaction(transaction);
                    return true;
                }
            }
        }
        return false;
    }

    /*
    Pay a bill
     */
    public Boolean payBill(int transOut, double amount, int transIn) {
        BankAccount account = accountManager.getAccount(transOut);
        // check if there are enough balance
        if (account.getBalance() < amount) {
            return false;
        } else {
            // withdraw money from transOut account
            if (account.payBill(amount, transIn)) {
                // save the transaction history
                Transaction transaction = new Transaction(amount, transOut, transIn, "bill");
                atm.getTransactionManager().addTransaction(transaction);
                return true;
            } else {
                return false;
            }
        }

    }

    /*
    Calculate the net total of all accounts of an user
     */
    double netTotal(Map<Integer, Double> accounts) {
        // The total of their debt account balances subtracted from the total of their asset account balances.

        double total = 0;

        for (int accountNumber : accounts.keySet()) {
            BankAccount account = accountManager.getAccount(accountNumber);
            if (account instanceof DebtAccount) {
                total -= account.getBalance();
            } else {
                total += account.getBalance();
            }
        }
        return total;
    }


    /**
     * Deposits the give amount into an account by cash.
     * Both the balance of the account and the cash storage of ATM increases.
     * This method always returns true.
     */
    public boolean deposit(int id, int fives, int tens, int twenties, int fifties) {
        BankAccount account = accountManager.getAccount(id);
        account.deposit(fives * 5 + tens * 10 + twenties * 20 + fifties * 50);
        billManager.deposit(fives, tens, twenties, fifties);
        return true;
    }

    /**
     * Deposits the given amount into an account by cheque.
     * Only the balance of the account increases. The cash storage of ATM remains the same.
     * This method always returns true.
     */
    public boolean deposit(int id, double amount) {
        BankAccount account = accountManager.getAccount(id);
        account.deposit(amount);
        return true;
    }

    /**
     * Changes the password of this client.
     * 6 < pswd.length < 10
     * Returns true if password is reset, false otherwise.
     */
    boolean changepswd(char[] pswd) {
        if (pswd.length < 5 || pswd.length >= 10) {
            return false;
        } else {
            this.client.setPassword(String.valueOf(pswd));
            return true;
        }
    }

    /**
     * Sets a chequing account as primary account.
     * Returns true if this action is successful, false otherwise.
     */
    public boolean setPrimary(int accNum) {

       BankAccount account = accountManager.getAccount(accNum);
       if (account instanceof ChequingAccount) {
           if (client.getAccounts().contains(accNum)) {
               client.setPrimaryAccount(accNum);
               return true;
           }
       }
       return false;
    }


}
