package ATM.ActionHandler;

import ATM.*;
import ATM.Users.BankManager;
import ATM.Users.Client;
import ATM.Users.User;

public class ActionHandler {

    /**
     * Private attributes
     */

    // data
    private BankManager bankManager;

    private AccountManager accountManager;

    private TransactionManager transactionManager;

    private UserManager userManager;

    // viwer/display
    private ATMGUI viewer;

    // attributes for execution
    private String userType;

    private User currentUser;


    /**
     * Initialize attributes
     */
    public ActionHandler(TransactionManager transactionManager, AccountManager accountManager, BankManager bankManager, UserManager userManager, ATMGUI atmgui){
        this.bankManager = bankManager;
        this.accountManager = accountManager;
        this.bankManager = bankManager;
        this.userManager = userManager;
        this.viewer = atmgui;
    }

    /**
     * Initialize viewer
     */
    public void initViewer(){
        // this will be the title page
    }

    /**
     * Initialize operator
     */
    public void initOperator(){

        // basic on the current page, add listener

        // start from the main page
        // add listener to buttons, make sure to set the event as defined

        // log in page
        // add listener to buttons and input field

        // based on



    }

    /**
     * User Log in
     */
    public boolean login(String userId, String pswd){
        if (userManager.userExists(userId)){
            if (userManager.getUser(userId).getPassword().equals(pswd)){
                currentUser = userManager.getUser(userId);
                // use type
                if (currentUser instanceof Client){
                    userType = "client";
                }else if (currentUser instanceof BankManager){
                    userType = "bankManager";
                }else{
                    userType = "bankInspector";
                }
                return true;
            }
        }
        return false;
    }

}
