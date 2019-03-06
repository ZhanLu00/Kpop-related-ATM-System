package ATM.Users;

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
    public String[] addUser(){
        // create a random login (8 letters) and password (four number)
        String[] userInfo = new String[2];
        // generate login


    }
    // check transaction history (of all the accounts)
    // manage cash
    // cancle transaction

    // for user
    // check balance
    // check transaction history (of itself)
    // withdraw
    // transfer
    // pay bills
    // request creation of an account

}
