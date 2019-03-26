package ATM.Users;

import ATM.*;
import ATM.BankAccounts.BankAccount;

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

    private BankManager bankManager;
    private Atm atm;
    private BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));

    public BankManagerActionHandler(BankManager bankManager, Atm atm) {
        this.bankManager = bankManager;
        this.atm = atm;
    }

    public boolean fulfillAccountRequest(int requestNum) {
        if (requestNum >= atm.getAccountManager().getAccountRequests().size() || requestNum < 0) {
            return false;
        }

        String[] request = atm.getAccountManager().getAccountRequests().remove(requestNum);
        String username = request[0];
        String accountType = request[1];

        Client accountuser = ((Client) atm.getUserManager().getUser(username));

        if (accountuser == null) {
            return false;
        }

        BankAccount newAccount = atm.getAccountManager().createAccount(accountType);

        if (newAccount != null) {
            accountuser.addAccounts(newAccount.getId());
            return true;
        }
        return false;
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

    private boolean undoTransaction(int transaction) {
        if (transaction < 0 || transaction >= atm.getAccountManager().getNumAccounts()) {
            return false;
        }

        return atm.getAccountManager().getAccount(transaction).undoTransaction(atm.getAccountManager());
    }

    private int getIntFromUser(String display) throws IOException {
        System.out.print(display);
        return Integer.parseInt(kbd.readLine());
    }

    private String getStringFromUser(String display) throws IOException {
        System.out.print(display);
        return kbd.readLine();
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
        for (BankAccount bankAccount : atm.getAccountManager()) {
            Transaction transaction = bankAccount.getLastTransaction();
            if (transaction != null) {
                System.out.println(String.format("%d)  Sender: %d,  Receiver: %d, Amount %f", c,
                        transaction.getSender(), transaction.getReceiver(), transaction.getAmount()));
            } else {
                System.out.println(String.format("%d) None", c));
            }
            c += 1;
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
