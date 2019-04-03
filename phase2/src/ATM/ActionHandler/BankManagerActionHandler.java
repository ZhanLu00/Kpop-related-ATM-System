package ATM.ActionHandler;

import ATM.*;
import ATM.BankAccounts.AssetAccounts.ChequingAccount;
import ATM.BankAccounts.BankAccount;
import ATM.Users.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

/**
 * A BankManagerActionHandler class.
 * This class handles actions from BankManager, acting as an operation class.
 * This class will need to access and modify fields from AccountManager, BankManager and Client.
 */
public class BankManagerActionHandler {
    private Atm atm;

    public BankManagerActionHandler(Atm atm) {
        this.atm = atm;
    }

    /**
     * Creates a new bank account for user.
     * Returns account id if account was successfully created, -1 otherwise.
     */
    public int createAccountForUser(String username, String accountType) {
        Client accountUser = (Client)atm.getUserManager().getUser(username);

        // the false condition:
        if (accountUser == null) {
            return -1;
        }

        // create a new account that is processed by the account manager
        BankAccount newAccount = atm.getAccountManager().createAccount(accountType);

        if (newAccount != null) {
            if (newAccount instanceof ChequingAccount && accountUser.getPrimaryAccount() == -1) {
                accountUser.setPrimaryAccount(newAccount.getId());
            }
            // add an account to the AccountManager
            atm.getAccountManager().addAccount(newAccount);
            // add an account to the accountUser
            accountUser.addAccounts(newAccount.getId());
            return newAccount.getId();
        } else {
            return -1;
        }
    }


    /**
     * Increase the amount of each cash by the given numbers.
     */
    void restockBills(int numFives, int numTens, int numTwenties, int numFifties) {
        atm.getBillManager().deposit(numFives, numTens, numTwenties, numFifties);
    }

    /**
     * To return an array of String contains formatted requests
     */
    public Vector formatRequest(ArrayList<String[]> requests){
        Vector formatted = new Vector();
        for (String[] rec:requests){
            String info = "Username: " +rec[0] + "  Account: "+ rec[1];
            formatted.addElement(info);
        }

        return formatted;
    }
    /**
     * Add a new client if it not currently in UserManager.
     * Assign a random password to the new client.
     * Returns a string array of {username, password}.
     */
    public String[] addClient(String username) {
        if (atm.getUserManager().userExists(username)) {
            System.out.println("nope");
            return new String[]{null, null};
        }
        System.out.println("created");
        Random r = new Random();
        String randomPassword = Integer.toString(r.nextInt(89999) + 10000);

        Client client = new Client(username, randomPassword);
        atm.getUserManager().addUser(client);
        return new String[]{username, randomPassword};
    }

    /**
     * Return an arraylist of alerts from ATM alert.
     */
    ArrayList<String> getAlerts() throws IOException {
        return atm.readAlerts();
    }

    /**
     * Clear all alerts in the atm alert
     */
    void clearAlerts() throws IOException {
        atm.clearAlerts();
    }

    /**
     * Return an arraylist of messages from the inspector
     */
    ArrayList<String> getMessages() throws IOException {
        return atm.readMessages();
    }

    /**
     * Clear all messages in the ATM
     */
    void clearMessages() throws IOException {
        atm.clearMessages();
    }

    private void setAtmDate(int day, int month, int year) {
        atm.getTimeManager().getDate().setDate(day);
        atm.getTimeManager().getDate().setMonth(month);
        atm.getTimeManager().getDate().setYear(year);
    }

    /**
     * Undoes a transaction by id.
     * Returns true of the transaction is undone, false otherwise.
     */
    boolean undoTransaction(int id) {
        return atm.getTransactionManager().undoTransaction(id, atm.getAccountManager());
    }

    /**
     * join account for user
     */
    boolean joinAccount(String user1, String user2, int acc){
        // find users
        if (atm.getUserManager().userExists(user1) && atm.getUserManager().userExists(user2)){
            // add acc in
            if (((Client)atm.getUserManager().getUser(user1)).getAccounts().contains(acc)){
                if (((Client)atm.getUserManager().getUser(user2)).getAccounts().contains(acc)){
                }else{
                    ((Client)atm.getUserManager().getUser(user2)).addAccounts(acc);
                }
            }else{
                if (((Client)atm.getUserManager().getUser(user2)).getAccounts().contains(acc)){
                }else{
                    ((Client)atm.getUserManager().getUser(user2)).addAccounts(acc);
                }
            }
            return true;
        }else{
            return false;
        }
    }

    /**
     * Adds an account to client.accounts by client and account id.
     */
    private boolean addAccountToUser(Client client, int accountId) {
        if (client == null || (accountId < 0 || accountId >= atm.getAccountManager().getAccounts().size())) {
            return false;
        }

        client.addAccounts(accountId);

        return true;
    }

    /**
     * Adds an account to client.accounts by username and account id.
     */
    boolean addAccountToUser(String username, int accountId) {
        Client client = (Client)atm.getUserManager().getUser(username);
        return addAccountToUser(client, accountId);
    }

    /**
     * Transfers all existing non-debt account balances to a specified receiver account.
     * Returns true if receiver account is valid.
     */
    public boolean transferAllToAccount(int receiverAccountId) {
        if (atm.getAccountManager().getAccount(receiverAccountId) == null) {
            return false;
        }
        for (BankAccount account : atm.getAccountManager().getAccounts()) {
            atm.getAccountManager().transfer(account.getBalance(), account.getId(), receiverAccountId);
        }
        return true;
    }

    /**
     * Creates new client and chequing account (username "communist leader").
     * Transfers all existing non-debt account balances to the new account.
     * Returns username and password for new client.
     */
    public String[] createCommunismAccount() {
        String[] login = addClient("communist_leader");


        createAccountForUser(login[0], "CHEQUING_ACCOUNT");
        int account = ((Client) atm.getUser(login[0], login[1])).getAccounts().get(0);
        transferAllToAccount(account);
        return login;
    }


}
