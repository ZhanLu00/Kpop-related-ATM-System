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

    public void getText() {

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
    public Boolean withdraw(BankAccount account, int amount) throws IOException {
    /*
    @ TODO add the function below in the user interface part
    the function only returns true or false
     */
        // will return a new balance if request complete, return false if declined

        // if
        if (account instanceof CreditCardsAccount) {
            System.out.println("Credit card account does not support withdraw");
            return false;
        } else {

            if (account.withdraw(amount)) {
                boolean withdrawable = billManager.withdrawable(amount);
                if (withdrawable) {
                    return true;
                } else {
                    System.out.println("There are not enough money in this src.ATM, please try another one");
                    account.deposit(amount);
                    return false;
                }
            } else {
                System.out.println("Withdraw Declined, please check your balance");
                return false;
            }
        }
    }

    // transfer
    public Boolean transfer(double amount, int senderId, int receiverId) {

        Transaction transaction = this.accountManager.transfer(amount, senderId, receiverId);

        if (transaction != null) {
            atm.getTransactionManager().addTransaction(transaction);
            return true;
        }

        return false;
    }


    // request creation of an account
    public void accountCreation(String type) {
        this.accountManager.requestNewAccount(this.client.getUsername(), type);
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
    public double netTotal(Map<Integer, Double> accounts) {
        // The total of their debt account balances subtracted from the total of their asset account balances.

        double total = 0;

        for (int accountNumber : accounts.keySet()) {
            BankAccount account = accountManager.getAccount(accountNumber);
            if (account instanceof DebtAccount) {
                total -= account.getBalance();
            } else if (account instanceof AssetAccount) {
                total += account.getBalance();
            }
        }
        return total;
    }


    public boolean deposit(int id, int fives, int tens, int twenties, int fifties) {
        BankAccount account = accountManager.getAccount(id);
        account.deposit(fives * 5 + tens * 10 + twenties * 20 + fifties * 50);
        billManager.deposit(fives, tens, twenties, fifties);
        return true;
    }

    public boolean deposit(int id, double amount){
        BankAccount account = accountManager.getAccount(id);
        account.deposit(amount);
        return true;
    }

    public boolean changepswd(char[] pswd) {
        if (pswd.length <= 6 || pswd.length >= 15) {
            return false;
        } else {
            this.client.setPassword(String.valueOf(pswd));
            return true;
        }
    }


    public void displayCommandLineInterface() throws IOException {
        // basic info
        String userName = client.getUsername();
        ArrayList<Integer> accountNumbers = client.getAccounts();

        while (true) {
            // show user name
            System.out.println("User: " + userName);
            Map accountBalance = checkBalance();

            // Display options
            System.out.println("Please select an option: ");
            System.out.println("Enter 1 to view a summary of all your account balance");
            System.out.println("Enter 2 to view the most recent transaction on a selected account");
            System.out.println("Enter 3 to check the date of creation of a selected account");
            System.out.println("Enter 4 to check your net total");
            System.out.println("Enter 5 to Transfer Money (between own accounts)");
            System.out.println("Enter 6 to Transfer Money (out to other user's)");
            System.out.println("Enter 7 to withdraw");
            System.out.println("Enter 8 to pay a bill");
            System.out.println("Enter 9 to make a deposit");
            System.out.println("Enter 10 to request a creation of an account");
            System.out.println("Enter 11 to set your primary chequing account");

            int input = Integer.parseInt(kbd.readLine());

            if (input == 1) {
                inputOne(accountBalance);
            } else if (input == 2) {
                inputTwo(accountNumbers);
            } else if (input == 3) {
                inputThree(accountNumbers);
            } else if (input == 4) {
                // check your net total
                System.out.println("Your net total is: " + netTotal(accountBalance));
            } else if (input == 5 || input == 6) {
                inputFiveSix(input);
            } else if (input == 7) {
                inputSeven(accountNumbers);
            } else if (input == 8) {
                inputEight();
            } else if (input == 9) {
                inputNine();
            } else if (input == 10) {
                inputTen();
            } else if (input == 11) {
                inputEleven();
            }
        }
    }

    public void inputOne(Map accountBalance) {
        // show user account's balance
        System.out.println("Bank Accounts and Balance:");
        for (Object accountNumber : accountBalance.keySet()) {
            System.out.println(accountNumber + ": " + accountBalance.get(accountNumber));
        }
    }

    /**
     * Helper function for input 2 and 3
     */
    private int accountNum(ArrayList<Integer> accountNumbers) throws IOException {
        System.out.println("Enter the account that you want to check");
        int accountNumber = Integer.parseInt(kbd.readLine());
        while (!accountNumbers.contains(accountNumber)) {
            accountNumber = Integer.parseInt(kbd.readLine());
        }
        return accountNumber;
    }

    public void inputTwo(ArrayList<Integer> accountNumbers) throws IOException {
        // view wth most recent transaction
        int accountNumber = accountNum(accountNumbers);
        BankAccount account = accountManager.getAccount(accountNumber);
        // Transaction transaction = account.getLastTransaction();

        //System.out.println(String.format("Sender: %d,  Receiver:  %d,  Amount: %f", transaction.getSender(), transaction.getReceiver(), transaction.getAmount()));
    }

    public void inputThree(ArrayList<Integer> accountNumbers) throws IOException {
        // check the date of creation
        int accountNumber = accountNum(accountNumbers);
        // the date of creation of one of their accounts
        System.out.println(accountManager.getAccount(accountNumber).getDateCreated());
    }

    public void inputFiveSix(int input) throws IOException {
        System.out.println("Enter the account that you want to transfer out");
        int transOut = Integer.parseInt(kbd.readLine());
        System.out.println("Enter the account that you want to transfer in");
        int transIn = Integer.parseInt(kbd.readLine());
        System.out.println("Enter the amount that you want to transfer in between");
        double amount = Double.parseDouble(kbd.readLine());


        if (transfer(amount, transIn, transOut)) {
            System.out.println("Your new balance is : ");
            System.out.println(accountManager.getAccount(transIn).getBalance());
            if (input == 5) {
                System.out.println(accountManager.getAccount(transOut).getBalance());
            }
        } else {
            System.out.println("Request Declined, there is an error in your transIn/Out/amount");
        }
    }

    public void inputSeven(ArrayList<Integer> accountNumbers) throws IOException {
        // withdraw
        System.out.println("Enter the account that you want to withdraw from");
        int accountNumber = Integer.parseInt(kbd.readLine());
        while (accountNumbers.contains(accountNumber)) {
            System.out.println("Please enter a right account number");
            System.out.println(accountNumber);
        }
        // amount
        int amount = Integer.parseInt(kbd.readLine());
        while (amount < 0) {
            System.out.println("Please enter a positive number");
            amount = Integer.parseInt(kbd.readLine());
        }
        BankAccount account = accountManager.getAccount(accountNumber);
        if (withdraw(account, amount)) {
            System.out.println("Withdraw Succeed");
        } else {
            ;
        }
    }

    public void inputEight() throws IOException {
        // pay a bill
        System.out.println("Enter the account number that you pay from");
        int accountN = Integer.parseInt(kbd.readLine());
        System.out.println("Enter the amount you pay");
        double amount = Double.parseDouble(kbd.readLine());
        System.out.println("Enter the payee account number");
        int payee = Integer.parseInt(kbd.readLine());

        if (payBill(accountN, amount, payee)) {
            System.out.println("Request has been done");
        } else {
            System.out.println("Request declined");
        }
    }

    private int positiveNum(int amount) throws IOException {
        while (amount < 0) {
            System.out.println("Please enter a positive number");
            amount = Integer.parseInt(kbd.readLine());
        }
        return amount;
    }

    private int[] numCash() throws IOException {
        System.out.println("Enter the number of fives you want to deposit");
        int fives = Integer.parseInt(kbd.readLine());
        fives = positiveNum(fives);
        System.out.println("Enter the number of tens you want to deposit");
        int tens = Integer.parseInt(kbd.readLine());
        tens = positiveNum(tens);
        System.out.println("Enter the number of twenties you want to deposit");
        int twenties = Integer.parseInt(kbd.readLine());
        twenties = positiveNum((twenties));
        System.out.println("Enter the number of fifties you want to deposit");
        int fifties = Integer.parseInt(kbd.readLine());
        fifties = positiveNum(fifties);
        return new int[] {fives, tens, twenties, fifties};
    }

    public void inputNine() throws IOException {
        // checks for id to deposit
        int id;
        if (client.getPrimaryAccount() != -1) {
            id = client.getPrimaryAccount();
        } else {
            System.out.println("Enter the account number you want to deposit into");
            id = Integer.parseInt(kbd.readLine());
        }
        // checks for ways to deposit
        System.out.println("Enter 1 to deposit with cheque");
        System.out.println("Enter 2 to deposit with cash");
        int way = Integer.parseInt(kbd.readLine());
        while (!(way == 1 || way == 2)) {
            System.out.println("Please enter a number between 1 and 2");
            way = Integer.parseInt(kbd.readLine());
        }

        //deposit with cheques
        if (way == 1) {
            System.out.println("Enter the amount you want to deposit by cheque");
            double amount = Double.parseDouble(kbd.readLine());
            while (amount < 0) {
                System.out.println("Please enter a positive number");
                amount = Double.parseDouble(kbd.readLine());
            }
            deposit(id, amount);
        } else { // deposit with cash
            int[] cash = numCash();
            deposit(id, cash[0], cash[1], cash[2], cash[3]);
        }
        System.out.print("Request has been done");
    }

    public void inputTen() throws IOException {
        // request a creation of an account
        System.out.println("Account type that you want to create");
        String type = kbd.readLine();
        try {
            accountManager.requestNewAccount(client.getUsername(), type);
            System.out.println("Request send");
        } catch (Exception e) {
            System.out.println("Request declined");
        }
    }

    /**
     * Sets an chequing account as primary.
     */
    public void inputEleven() throws IOException {
        System.out.println("Enter the account number that you want to select as new primary account");
        int id = Integer.parseInt(kbd.readLine());
        BankAccount newPrimary = accountManager.getAccount(id);

        if (newPrimary instanceof ChequingAccount) {
            client.setPrimaryAccount(id);
            System.out.print("Request has been done");
        } else {
            System.out.println("Request declined. The account you entered is not a chequing account");
        }
    }
}
