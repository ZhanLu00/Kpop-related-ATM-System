package ATM.Users;
import ATM.Atm;
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

    public BankManagerActionHandler(BankManager bankManager){
        this.bankManager = bankManager;
    }

    public void printText(Atm atm){
        BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please select an option:");
        System.out.println("Type 1 to Create User");
        System.out.println("Type 2 to Restock Machine");
        System.out.println("Type 3 to Undo Transaction");
        System.out.println("You have " + + " account creation requests. Type 4 to View" +
                " Requests");
        String input = kbd.readLine();
        if (input == 1){
            int[] userInfo = addUser(atm.getUserManager);
            System.out.println("New user created.");
            System.out.println("Login: " + userInfo[0]);
            System.out.println("Password: " + userInfo[1]);
        }
        else{
            System.out.print("Invalid Input. ");
            printText(atm);
        }
    }

    // for bank manager
    // add account
    public int[] addUser(UserManager allUsers){
        // create a random login (8 letters) and password (four number)
        Random r = new Random();
        int[] userInfo = new int[2];

        // get all login number
        ArrayList<String> allUser = allUsers.getUsersLogin();

        // generate use info
        userInfo[0] = 99999999 - r.nextInt(89999999);
        userInfo[1] = 9999 - r.nextInt(8999);

        // loop if the login is exist already
        while (allUser.indexOf(userInfo[0]) != -1){
            userInfo[0] = 99999999 - r.nextInt(89999999);
        }

        return userInfo;

    }

    // check transaction history (of all the accounts)
    // manage cash
    public BillManager manageCash(BillManager cash){
        return cash;
    }
    // cancle transaction

}
