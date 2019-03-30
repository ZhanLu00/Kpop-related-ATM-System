package ATM.ActionHandler;

import ATM.*;
import ATM.BankAccounts.AssetAccounts.ChequingAccount;
import ATM.BankAccounts.BankAccount;
import ATM.Users.BankManager;
import ATM.Users.Client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Random;

public class BankManagerActionHandler {
    // this class will be handle actions from users
    // acting as an operation class here
    // two types of actions come from two types of user
    // this class will need to access and mutate the Accounts from AccountManager
    // bankManager
    // Client

    // Bank manager's info is nessesary or not?
//    private BankManager bankManager;
    private Atm atm;
    private BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));

    public BankManagerActionHandler(Atm atm) {
//        this.bankManager = bankManager;
        this.atm = atm;
    }

    /**
     * Creates a new bank account for user.
     * Returns true if account was successfully created, false otherwise.
     */
    public boolean createAccountForUser(String username, String accountType){
        Client accountUser = ((Client) atm.getUserManager().getUser(username));

        if (accountUser == null) {
            return false;
        }

        BankAccount newAccount = atm.getAccountManager().createAccount(accountType);

        if (newAccount != null) {
            // This loops through list of account the client owns if the account created is a chequing account.
            // If this is the first chequing Account the client owns, primary will be set to true.
            if (newAccount instanceof ChequingAccount) {
                boolean primary = false;
                for (int id : accountUser.getAccounts()) {
                    BankAccount account = atm.getAccountManager().getAccount(id);
                    if(account instanceof ChequingAccount && ((ChequingAccount) account).getPrimary()) {
                        primary = true;
                    }
                }
                ((ChequingAccount) newAccount).setPrimary(!primary);
            }

            accountUser.addAccounts(newAccount.getId());
            return true;
        }
        return false;
    }

    public boolean fulfillAccountRequest(int requestNum) {
        if (requestNum >= atm.getAccountManager().getAccountRequests().size() || requestNum < 0) {
            return false;
        }

        String[] request = atm.getAccountManager().getAccountRequests().remove(requestNum);
        String username = request[0];
        String accountType = request[1];
        return createAccountForUser(username, accountType);
    }

    public boolean fulfillAllAccountRequests() {
        boolean allCompleted = true;
        while (atm.getAccountManager().getAccountRequests().size() > 0) {
            if (!fulfillAccountRequest(0)) {
                allCompleted = false;
            }
        }
        return allCompleted;
    }

    public void restockBills(int numFives, int numTens, int numTwenties, int numFifties) {
        atm.getBillManager().deposit(numFives, numTens, numTwenties, numFifties);
    }

    public String[] addClient(String username) {
        if (atm.getUserManager().userExists(username)) {
            return new String[]{"USER NOT CREATED. USERNAME ALREADY EXISTS", ""};
        }

        Random r = new Random();
        String randomPassword = Integer.toString(r.nextInt(89999) + 10000);

        Client client = new Client(username, randomPassword);
        atm.getUserManager().addUser(client);

        return new String[]{username, randomPassword};
    }

    public ArrayList<String> getAlerts() throws IOException {
        return atm.readAlerts();
    }

    public void clearAccounts() throws IOException {
        atm.clearAlerts();
    }

    private void setAtmDate(int day, int month, int year) {
        atm.getTimeManager().getDate().setDate(day);
        atm.getTimeManager().getDate().setMonth(month);
        atm.getTimeManager().getDate().setYear(year);
    }

    private boolean undoTransaction(int id) {
        return atm.getTransactionManager().undoTransaction(id, atm.getAccountManager());
    }

    private int getIntFromUser(String display) throws IOException {
        System.out.print(display);
        return Integer.parseInt(kbd.readLine());
    }

    private String getStringFromUser(String display) throws IOException {
        System.out.print(display);
        return kbd.readLine();
    }

    public boolean addAccountToUser(Client client, int accountId) {
        if (client == null || (accountId < 0 || accountId >= atm.getAccountManager().getAccounts().size())) {
            return false;
        }

        client.addAccounts(accountId);

        return true;
    }

    public boolean addAccountToUser(String username, int accountId) {
        Client client = ((Client) atm.getUserManager().getUser(username));


        return addAccountToUser(client, accountId);
    }

    /**
     * Transfers all existing non-debt account balances to a specified receiver account.
     * Returns true if receiver account is valid.
     */
    public boolean transferAllToAccount(int receiverAccountId){
        if (atm.getAccountManager().getAccount(receiverAccountId) == null){
            return false;
        }
        for (BankAccount account:atm.getAccountManager().getAccounts()) {
            atm.getAccountManager().transfer(account.getBalance(), account.getId(), receiverAccountId);
        }
        return true;
    }

    /**
     * Creates new client and chequing account (username has prefix "communist leader").
     * Transfers all existing non-debt account balances to the new account.
     * Returns username and password for new client.
     */
    public String[] createCommunismAccount(String username) {
        String[] login = addClient("communist leader " + username);
        createAccountForUser(login[0], BankAccount.CHEQUING);
        int account = ((Client) atm.getUser(login[0], login[1])).getAccounts().get(0);
        transferAllToAccount(account);
        return login;
    }

    public void displayCommandLineInterface() throws IOException {
        while (true) {
            System.out.println("Please select an option:");
            System.out.println("Type 1 to Create Client");
            System.out.println("Type 2 to Restock Machine");
            System.out.println("Type 3 to Undo Transaction");
            System.out.println("Type 4 to view account creation requests");
            System.out.println("Type 5 to set time");
            System.out.println("Type 6 to show alerts");
            System.out.println("Type 7 to clear alerts");
            System.out.println("Type 8 to exit");

            int input = Integer.parseInt(kbd.readLine());

            if (input == 1) {
                inputOne();
            } else if (input == 2) {
                inputTwo();
            } else if (input == 3) {
                inputThree();
            } else if (input == 4) {
                inputFour();
            } else if (input == 5) {
                inputFive();
            } else if (input == 6) {
                inputSix();
            } else if (input == 7) {
                atm.clearAlerts();
                System.out.println("Cleared Alerts");
            } else if (input == 8) {
                break;
            } else {
                System.out.print("Invalid Input. ");
            }
            System.out.println();
            System.out.println();
        }
    }

    public void inputOne() throws IOException {
        String username = getStringFromUser("Username for new client: ");
        String[] userInfo = addClient(username);

        System.out.println("New client created.");
        System.out.println("Login: " + userInfo[0]);
        System.out.println("Password: " + userInfo[1]);
    }

    public void inputTwo() throws IOException {
        int numFives = getIntFromUser("Number of fives: ");
        int numTens = getIntFromUser("Number of tens: ");
        int numTwenties = getIntFromUser("Number of twenties: ");
        int numFifties = getIntFromUser("Number of fifties: ");

        restockBills(numFives, numTens, numTwenties, numFifties);
    }

    public void inputThree() throws IOException {
        int c = 0;

        for (Transaction transaction : atm.getTransactionManager().getTransactions()) {
            System.out.println(String.format("%d)  Sender: %d,  Receiver: %d, Amount %f", c,
                    transaction.getSender(), transaction.getReceiver(), transaction.getAmount()));
            c+=1;
        }
        int transaction = getIntFromUser("Which transaction would you like to undo: ");
        System.out.println(undoTransaction(transaction) ? ("Transaction Undone") : ("Error undoing transaction"));
    }

    public void inputFour() throws IOException {
        int c = 0;
        for (String[] accountRequest : atm.getAccountManager().getAccountRequests()) {
            System.out.println(String.format("%d) Username: %s,  Account Type %s", c, accountRequest[0], accountRequest[1]));
            c += 1;
        }

        if (atm.getAccountManager().getAccountRequests().size() == 0) {
            System.out.println("No Requests");
        } else {
            int requestNum = getIntFromUser("which request do you want to fulfill (-1 for all): ");
            boolean result;
            if (requestNum == -1) {
                result = fulfillAllAccountRequests();
            } else {
                result = fulfillAccountRequest(requestNum);
            }
            System.out.println(result ? ("Account(s) created") : "Invalid. Check request number / user existence");
        }
    }

    public void inputFive() throws IOException {
        int day = getIntFromUser("day: ");
        int month = getIntFromUser("month(as number 0-11): ");
        int year = getIntFromUser("year: ");

        setAtmDate(day, month, year);
    }

    public void inputSix() throws IOException {
        System.out.println("ALERTS:");
        for (String alert : getAlerts()) {
            System.out.println(alert);
        }
    }

}
