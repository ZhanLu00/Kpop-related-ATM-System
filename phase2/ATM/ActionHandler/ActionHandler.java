package ATM.ActionHandler;

import ATM.*;
import ATM.Users.BankManager;
import ATM.Users.Client;
import ATM.Users.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ActionHandler {

    /**
     * Private attributes
     */

    // data/model
    private AccountManager accountManager;

    private TransactionManager transactionManager;

    private UserManager userManager;

    private BillManager billManager;

    // viwer/display
    private ATMGUI viewer;

    // sub-action handler
    private BankInspectorActionHandler bankInspectorActionHandler;

    private BankManagerActionHandler bankManagerActionHandler;

    private ClientActionHandler clientActionHandler;


    // attributes for execution
    private String userType;

    private User currentUser;

    private int runStage;



    /**
     * Initialize attributes
     */
    public ActionHandler(Atm atm,  ATMGUI atmgui){
        this.accountManager = atm.getAccountManager();
        this.userManager = atm.getUserManager();
        this.billManager = atm.getBillManager();
        this.transactionManager = atm.getTransactionManager();
        this.viewer = atmgui;
        // those will be initialized when log in happens
//        this.bankInspectorActionHandler = new BankInspectorActionHandler(bank)
//        this.bankManagerActionHandler = new BankManagerActionHandler(atm);
//        this.clientActionHandler = new ClientActionHandler()
    }

    /**
     * Initialize viewer
     */
    public void initViewer() throws IOException {
        // this will be the title page
        viewer.init();
    }

    /**
     * Initialize operator
     */
    public void initOperator(){
        this.runStage = 100;
        // basic on the current page, add listener
        viewer.newUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.changePage(viewer.welcomePage, viewer.newUserPage);
                runStage = 101;
            }
        });
        viewer.returningUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.changePage(viewer.welcomePage, viewer.returningUserPage);
                runStage = 102;
            }
        });

        // start from the main page


        // add listener to buttons, make sure to set the event as defined

        // log in page
        // add listener to buttons and input field

        // based on the user type, call different method for windows



    }


    /**
     * User Log in
     */
    public void login(){
        viewer.returnToTheMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.changePage(viewer.returningUserPage, viewer.welcomePage);
            }
        });
        viewer.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean loginSuccess = login(viewer.usernameText.getText(), viewer.passwordText.getText());
                if (loginSuccess){
                    if (userType == "client"){
                        viewer.changePage(viewer.returningUserPage, viewer.clientOptions);
                    }else if(userType.equals("bankManager")){
                        viewer.changePage(viewer.returningUserPage, viewer.managerOptions);
                    }else{
                        viewer.changePage(viewer.returningUserPage, viewer.inspectorOptions);
                    }
                }else{
                    viewer.usernameText.setText("");
                    viewer.passwordText.setText("");
                    JOptionPane.showMessageDialog(null, "Incorrect username/password. " +
                            "Please try again.");
                }
            }
        });
    }
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

    /**
     * client action handler
     */
    public void clientOption(){
        // add listener
    }

    public void bankManagerOption(){

    }

}
