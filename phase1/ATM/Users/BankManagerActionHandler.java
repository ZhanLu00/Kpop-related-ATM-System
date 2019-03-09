package ATM.Users;
import ATM.AccountManager;
import ATM.Atm;
import ATM.BankAccounts.BankAccount;
import ATM.BillManager;
import ATM.UserManager;

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

    public BankManagerActionHandler(BankManager bankManager, Atm atm){
        this.bankManager = bankManager;
        this.atm = atm;
    }


    private int getIntFromUser(String display) throws IOException {
        System.out.print(display);
        return Integer.parseInt(kbd.readLine());
    }

    private String getStringFromUser(String display) throws IOException {
        System.out.println(display);
        return kbd.readLine();
    }

    public void updateCommandLineInterface() throws IOException {

        System.out.println("Please select an option:");
        System.out.println("Type 1 to Create Client");
        System.out.println("Type 2 to Restock Machine");
        System.out.println("Type 3 to Undo Transaction");
        System.out.println("Type 4 to view account creation requests");

        int input = Integer.parseInt(kbd.readLine());

        if (input == 1){
            String username = getStringFromUser("Username for new client: ");
            String[] userInfo = addClient(username);

            System.out.println("New client created.");
            System.out.println("Login: " + userInfo[0]);
            System.out.println("Password: " + userInfo[1]);
        }
        else if (input == 2) {
            int numFives = getIntFromUser("Number of fives: ");
            int numTens = getIntFromUser("Number of tens: ");
            int numTwenties = getIntFromUser("Number of twenties: ");
            int numFifties = getIntFromUser("Number of fifties: ");

            restockBills(numFives, numTens, numTwenties, numFifties);
        }
        else if (input == 3) {

        }
        else if (input == 4) {
            int c = 0;
            for (String[] accountRequest : atm.getAccountManager().getAccountRequests()) {
                System.out.println(String.format("%d) Username: %s,  Account TypeL %s", c, accountRequest[0], accountRequest[1]));
            }

            int requestNum = getIntFromUser("which request do you want to fulfill (-1 for all): ");
            Boolean result;
            if (requestNum == -1) {
                result = fulfillAllAccountRequests();
            }
            else {
                result = fulfillAccountRequest(requestNum);
            }
            System.out.println( result ? ("Account(s) created") : "Invalid. Check request number / user existence");
        }
        else{
            System.out.print("Invalid Input. ");
        }
    }

    private boolean fulfillAccountRequest(int requestNum) {
        if (requestNum >= atm.getAccountManager().getAccountRequests().size() || requestNum < 0) {
            return false;
        }

        String[] request = atm.getAccountManager().getAccountRequests().get(requestNum);
        String username = request[0];
        String accountType = request[1];

        User accountuser = atm.getUserManager().getUser(username);

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

    private boolean fulfillAllAccountRequests() {
        boolean allCompleted = true;
       while (atm.getAccountManager().getAccountRequests().size() > 0) {
           if (!fulfillAccountRequest(0)) {
               allCompleted = false;
           }
       }
       return allCompleted;
    }

    private void restockBills(int numFives, int numTens, int numTwenties, int numFifties) {
        atm.getBillManager().deposit(numFives,numTens,numTwenties,numFifties);
    }

    public String[] addClient(String username){
        if (atm.getUserManager().userExists(username)) {
            return new String[] {"USER NOT CREATED. USERNAME ALREADY EXISTS"};
        }

        Random r = new Random();
        String randomPassword = Integer.toString(r.nextInt(89999) + 10000);

        Client client = new Client(username, randomPassword);
        atm.getUserManager().addUser(client);

        return new String[] {username, randomPassword};
    }



}
