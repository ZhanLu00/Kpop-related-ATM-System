package ATM;

import ATM.BankAccounts.BankAccount;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

public class ClientInterface {

    private BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
    private ClientActionHandler actionHandler = new ClientActionHandler();


    public void ClientInterface(){
        ;
    }


    public void displayCommandLineInterface() throws IOException {
        // basic info
        String userName = client.getUsername();
        ArrayList<Integer> accountNumbers = client.getAccounts();
        Boolean running = false;


        while (running) {
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
            System.out.println("Enter 11 to change your password");
            System.out.println("Enter 12 to log out");

            int input = Integer.parseInt(kbd.readLine());

            if (input == 1) {
                // show user account's balance
                System.out.println("Bank Accounts and Balance:");
                for (Object accountNumber : accountBalance.keySet()) {
                    System.out.println(accountNumber + ": " + accountBalance.get(accountNumber));
                }
            } else if (input == 2) {
                // view wth most recent transaction
                System.out.println("Enter the account that you want to check");
                int accountNumber = Integer.parseInt(kbd.readLine());
                while (!accountNumbers.contains(accountNumber)) {
                    accountNumber = Integer.parseInt(kbd.readLine());
                }

                BankAccount account = accountManager.getAccount(accountNumber);
                Transaction transaction = account.getLastTransaction();

                System.out.println(String.format("Sender: %d,  Receiver:  %d,  Amount: %f,  Date: %s", transaction.getSender(), transaction.getReceiver(), transaction.getAmount(), TimeManager.dateToString(transaction.getDate())));


            } else if (input == 3) {
                // check the date of creation
                System.out.println("Enter the account that you want to check");
                int accountNumber = Integer.parseInt(kbd.readLine());
                while (!accountNumbers.contains(accountNumber)) {
                    accountNumber = Integer.parseInt(kbd.readLine());
                }
                // the date of creation of one of their accounts
                System.out.println(accountManager.getAccount(accountNumber).getDATE_CREATED());

            } else if (input == 4) {
                // check your net total
                System.out.println("Your net total is: " + netTotal(accountBalance));

            } else if (input == 5 || input == 6) {
                System.out.println("Enter the account that you want to transfer out");
                int transOut = Integer.parseInt(kbd.readLine());
                System.out.println("Enter the account that you want to transfer in");
                int transIn = Integer.parseInt(kbd.readLine());
                System.out.println("Enter the amount that you want to transfer in between");
                double amount = Double.parseDouble(kbd.readLine());

                if (accountManager.transfer(amount, transIn, transOut)) {
                    System.out.println("Your new balance is : ");
                    System.out.println(accountManager.getAccount(transIn).getBalance());
                    if (input == 5) {
                        System.out.println(accountManager.getAccount(transOut).getBalance());
                    }
                } else {
                    System.out.println("Request Declined, there is an error in your transIn/Out/amount");
                }
            } else if (input == 7) {
                // withdraw
                System.out.println("Enter the account that you want to withdraw from");
                int accountNumber = Integer.parseInt(kbd.readLine());
                while (accountNumbers.contains(accountNumber)) {
                    System.out.println("please enter a right account number");
                    System.out.println(accountNumber);
                }
                // amount
                int amount = Integer.parseInt(kbd.readLine());
                while (amount < 0) {
                    System.out.println("please enter a positive number");
                    amount = Integer.parseInt(kbd.readLine());
                }
                BankAccount account = accountManager.getAccount(accountNumber);
                if (withdraw(account, amount)) {
                    System.out.println("Withdraw Succeed");
                } else {
                    ;
                }

            } else if (input == 8) {
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
            } else if (input == 9) {
                // make a deposit
                System.out.println("Enter your account number, fives, tens, twenties and fifties that you are gonna deposit, press 'enter' in between each info");
                int accountN = Integer.parseInt(kbd.readLine());
                int fives = Integer.parseInt(kbd.readLine());
                int tens = Integer.parseInt(kbd.readLine());
                int twenties = Integer.parseInt(kbd.readLine());
                int fifties = Integer.parseInt(kbd.readLine());

                accountManager.getAccount(accountN).deposit(fives * 5 + tens * 10 + twenties * 20 + fifties * 50);
                billManager.deposit(fives, tens, twenties, fifties);
                System.out.println("Deposite done, here is your new balance:" + accountManager.getAccount(accountN).getBalance());
            } else if (input == 10) {
                // request a creation of an account
                System.out.println("account type that you want to create");
                String type = kbd.readLine();
                try {
                    accountManager.requestNewAccount(client.getUsername(), type);
                    System.out.println("request send");
                } catch (Exception e) {
                    System.out.println("request declined");
                }

            } else if (input == 11) {
                System.out.println("new password");
                String password = kbd.readLine();
                changePassword(password);

            } else if (input == 12) {
                System.out.println("You will be sign out in 3 seconds");
                // how to sign out
                // okay i think i know how to do this
                running = false;
            }


        }
    }
}
