package ATM.Users;
import ATM.Atm;
import ATM.BillManager;
import ATM.UserManager;

import java.util.ArrayList;
import java.util.Random;

public class ClientActionHandler {
    // this class will be handle actions from users
    // acting as an operation class here
    // two types of actions come from two types of user
    // this class will need to access and mutate the Accounts from AccountManager
    // bankManager
    // Client

    private Client client;

    public ClientActionHandler(Client client){
        this.client = client;
    }

    public void getText(Atm atm){

    }
    // for user
    // check balance
    // check transaction history (of itself)
    // withdraw
    // transfer
    // pay bills
    // request creation of an account

}