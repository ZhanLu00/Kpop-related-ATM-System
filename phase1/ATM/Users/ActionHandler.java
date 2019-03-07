package ATM.Users;
import ATM.BillManager;
import ATM.UserManager;

import java.util.ArrayList;
import java.util.Random;

public class ActionHandler {
    // this class will be handle actions from users
    // acting as an operation class here
    // two types of actions come from two types of user
    // this class will need to access and mutate the Accounts from AccountManager
    // bankManager
    // Client

    private User user;

    public void ActionHandler(User user){
        this.user = user;
    }



    // for bank manager
    // add account
    public int[] addUser(UserManager allUsers){
        // create a random login (8 letters) and password (four number)
        Random r = new Random();
        int[] userInfo = new int[2];

        // get all login number
        ArrayList<Integer> allUser = allUsers.getUsersLogin();

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

    // for user
    // check balance
    // check transaction history (of itself)
    // withdraw
    // transfer
    // pay bills
    // request creation of an account

}
