package ATM.Users;
import ATM.AccountManager;
import ATM.Atm;
import ATM.BankAccounts.AssetAccounts.AssetAccount;
import ATM.BankAccounts.BankAccount;
import ATM.BankAccounts.DebtAccounts.DebtAccount;
import ATM.BillManager;
import ATM.UserManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ClientActionHandler {
    // this class will be handle actions from users
    // acting as an operation class here
    // two types of actions come from two types of user
    // this class will need to access and mutate the Accounts from AccountManager
    // bankManager
    // Client

    private Client client;
    private AccountManager accountManager;

    public ClientActionHandler(Client client, AccountManager manager){
        this.client = client;
        this.accountManager = manager;
    }

    public void getText(Atm atm){

    }

    // check balance
    public Map<Integer, Double> checkBalance(){
        Map balance = new HashMap();

        // get account numbers first
        for (int accountNumber: this.client.accountNumbers){
            // get balance from each of the bank number
            balance.put(accountNumber, this.accountManager.getAccount(accountNumber).getBalance());
        }
        return balance;
    }

    // check transaction history (of itself)

    // withdraw
    public Boolean withdraw(BankAccount account, Double amount){
        /*
        @ TODO add the function below in the user interface part
        the function only returns true or false
         */
        // will return a new balance if request complete, return false if declined
        return account.withdraw(amount);
    }

    // transfer
    public Boolean transfer(double amount, int senderId, int receiverId ){
        /*
        this method might be useless
         */
        return this.accountManager.transfer(amount, senderId, receiverId);
    }

    // pay bills
    public Boolean payBills(){
        /*

         */
        return true;
    }

    // request creation of an account
    public void accountCreation(String type){
        this.accountManager.requestNewAccount(this.client.getUsername(), type);
    }

    /*
    Check if the username matches password
     */
    public Boolean loginCheck(String password){
        if (client.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    /*
    Calculate the net total of all accounts of an user
     */
    public Double netTotal(Map<Integer, Double> accounts){
        // The total of their debt account balances subtracted from the total of their asset account balances.

        double total = 0;

        for (int accountNumber : accounts.keySet()){
            BankAccount account = accountManager.getAccount(accountNumber);
            if (account instanceof DebtAccount){
                total -= account.getBalance();
            }else if (account instanceof AssetAccount){
                total += account.getBalance();
            }
        }
        return total;
    }

    public void displayCommandLineInterface() throws IOException {
        while (true){
            // show user name
            System.out.println("User: "+client.getUsername());
            // show user account's balance
            System.out.println("Bank Accounts and Balance:");
            Map accountBalance = checkBalance();
            for (Object accountNumber:accountBalance.keySet()){
                System.out.println(accountNumber+": "+ accountBalance.get(accountNumber));
            }
            // show transaction history
            System.out.println(accountManager);
            // the date of creation of one of their accounts
            System.out.println(accountManager.getAccount(client.accountNumbers.get(0)).getDATE_CREATED());
            // their net total
            System.out.println("Your net total is: "+ netTotal(accountBalance));

            // Display options
            System.out.println("Please select an option: ");
            System.out.println("Enter 1 to view a summary of all your account balance");
            System.out.println("Enter 2 to view the most recent transaction on a selected account");
            System.out.println("Enter 3 to check the date of creation of a selected account");
            System.out.println("Enter 4 to check your net total");
            System.out.println("Enter 5 to Transfer Money");
            System.out.println("Enter 6 to withdraw");
            System.out.println("Enter 7 to pay a bill");
            System.out.println("Enter 8 to make a deposit");
            System.out.println("Enter 9 to request a creation of an account");



        }
    }
}
