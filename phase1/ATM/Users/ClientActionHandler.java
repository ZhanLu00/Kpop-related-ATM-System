package ATM.Users;
import ATM.AccountManager;
import ATM.Atm;
import ATM.BankAccounts.AssetAccounts.AssetAccount;
import ATM.BankAccounts.BankAccount;
import ATM.BankAccounts.DebtAccounts.DebtAccount;
import ATM.BillManager;
import ATM.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    private BillManager billManager;
    private BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));

    public ClientActionHandler(Client client, AccountManager manager, BillManager cashes){
        this.client = client;
        this.accountManager = manager;
        this.billManager = cashes;
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
    public Boolean withdraw(BankAccount account, int amount){
        /*
        @ TODO add the function below in the user interface part
        the function only returns true or false
         */
        // will return a new balance if request complete, return false if declined

        // if
        if (account instanceof DebtAccount){
            System.out.println("DebtAccount does not support withdraw");
            return false;
        }else if (billManager.withdraw(amount))
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
        // basic info
        String userName = client.getUsername();
        ArrayList<Integer> accountNumbers = client.getAccounts();

        while (true){
            // show user name
            System.out.println("User: "+userName);
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

            int input = Integer.parseInt(kbd.readLine());

            if (input == 1){
                // show user account's balance
                System.out.println("Bank Accounts and Balance:");
                for (Object accountNumber:accountBalance.keySet()){
                    System.out.println(accountNumber+": "+ accountBalance.get(accountNumber));
                }
            }else if (input == 2){
                // view wth most recent transaction
                System.out.println("Enter the account that you want to check");
                int accountNumber = Integer.parseInt(kbd.readLine());
                while (!accountNumbers.contains(accountNumber)){
                    accountNumber = Integer.parseInt(kbd.readLine());
                }
                /*
                @ TODO get and display transaction
                 */
            }else if (input == 3){
                // check the date of creation
                System.out.println("Enter the account that you want to check");
                int accountNumber = Integer.parseInt(kbd.readLine());
                while (!accountNumbers.contains(accountNumber)){
                    accountNumber = Integer.parseInt(kbd.readLine());
                }
                // the date of creation of one of their accounts
                System.out.println(accountManager.getAccount(accountNumber).getDATE_CREATED());

            }else if (input ==4){
                // check your net total
                System.out.println("Your net total is: "+ netTotal(accountBalance));

            } else if (input == 5 || input == 6) {
                System.out.println("Enter the account that you want to transfer out");
                int transOut = Integer.parseInt(kbd.readLine());
                System.out.println("Enter the account that you want to transfer in");
                int transIn = Integer.parseInt(kbd.readLine());
                System.out.println("Enter the amount that you want to transfer in between");
                double amount = Double.parseDouble(kbd.readLine());

                if (accountManager.transfer(amount, transIn, transOut)){
                    System.out.println("Your new balance is : ");
                    System.out.println(accountManager.getAccount(transIn).getBalance();
                    if (input == 5){
                        System.out.println(accountManager.getAccount(transOut).getBalance());
                    }
                }else{
                    System.out.println("Request Declined, there is an error in your transIn/Out/amount");
                }
            }else if (input == 7){
                // withdraw
                System.out.println("Enter the account that you want to withdraw from");
                int accountNumber = Integer.parseInt(kbd.readLine());
                while (accountNumbers.contains(accountNumber)){
                    System.out.println("please enter a right account number");
                    System.out.println(accountNumber);
                }
                // amount
                int amount = Integer.parseInt(kbd.readLine());
                while (amount < 0){
                    System.out.println("please enter a positive number");
                    amount = Integer.parseInt(kbd.readLine());
                }
                BankAccount account = accountManager.getAccount(accountNumber);
                withdraw(account, amount);

            }else if (input == 8){
                // pay a bill
            }else if (input == 9){
                // make a deposit
            }else if (input == 10){
                // request a creation of an account
            }



        }
    }
}
