package ATM.ActionHandler;

import ATM.*;
import ATM.Managers.AccountManager;
import ATM.Managers.BillManager;
import ATM.Managers.TransactionManager;
import ATM.Managers.UserManager;
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
        viewer.goBackReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.changePage(viewer.returningUserPage, viewer.welcomePage);
            }
        });
        viewer.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean loginSuccess = login(viewer.usernameText.getText(), viewer.passwordText.getPassword().toString());
                if (loginSuccess){
                    if (userType == "client"){
                        viewer.changePage(viewer.returningUserPage, viewer.clientOptions);
                        currentUser = userManager.getUser(viewer.usernameText.getText());
                        clientOption();
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


    /******************************************
     * client action handler
     */
    public void clientOption(){
        // add listener
        viewer.viewAccountSummaryButton.addActionListener(e->{
            viewer.changePage(viewer.clientOptions, viewer.summaryOfAccounts);
            accountSummary();
        });
        viewer.withdrawButton.addActionListener(e->{
            viewer.changePage(viewer.clientOptions, viewer.withdrawOption);
            withdraw();
        });
        viewer.transferButton.addActionListener(e->{
            viewer.changePage(viewer.clientOptions, viewer.transferOption);
            transfer();
        });
        viewer.payABillButton.addActionListener(e->{
            viewer.changePage(viewer.clientOptions, viewer.payBill);
            payBill();
        });
        viewer.depositMoneyButton.addActionListener(e->{
            viewer.changePage(viewer.clientOptions, viewer.depositOption);
            deposit();
        });
        viewer.changePasswordButton.addActionListener(e->{
            viewer.changePage(viewer.clientOptions, viewer.changePassword);
            changePswd();
        });
        viewer.goBackClient.addActionListener(e->{
            viewer.changePage(viewer.clientOptions, viewer.welcomePage);
            currentUser = null;
        });
    }

    public void accountSummary(){
        // a field for accounts sum
        // include creation date


        // net total

        // a field for transaction history

    }

    public void withdraw(){

        viewer.withdrawButton.addActionListener(e->{
            boolean inputOk = false;
            int withdrawAmount, accountNum;
            try{
                withdrawAmount = Integer.parseInt(viewer.withdrawAmt.getText());
                accountNum = Integer.parseInt(viewer.accNumWithdraw.getText());
                boolean succeed = clientActionHandler.withdraw(accountManager.getAccount(accountNum), withdrawAmount);
                if (succeed){
                    JOptionPane.showMessageDialog(null, "withdraw succeed");
                }else{
                    JOptionPane.showMessageDialog(null, "You don't have that much money");
                }
            }catch (Exception exp){
                JOptionPane.showMessageDialog(null, "Please check your input");
            }
        });

        viewer.goBackWithdraw.addActionListener(e->{
            viewer.changePage(viewer.withdrawOption, viewer.clientOptions);
        });
    }

    public void transfer(){

        viewer.transferButton.addActionListener(e->{
            boolean inputOk = false;
            int outAccNum, inAccNum;
            double transAmt;
            try{
                outAccNum = Integer.parseInt(viewer.transOutAccNum.getText());
                inAccNum = Integer.parseInt(viewer.transInAccNum.getText());
                transAmt = Double.parseDouble(viewer.transInAccNum.getText());
                boolean succeed = clientActionHandler.transfer(transAmt, outAccNum, inAccNum);
                if (succeed){
                    JOptionPane.showMessageDialog(null, "transfer succeed");
                }else{
                    JOptionPane.showMessageDialog(null, "Error: check your input and your balance");
                }
            }catch (Exception exp){
                JOptionPane.showMessageDialog(null, "Please check your input");
            }


        });

        viewer.goBackTransfer.addActionListener(e->{
            viewer.changePage(viewer.transferOption, viewer.clientOptions);
        });
    }

    public void payBill(){
        viewer.payBillButton.addActionListener(e->{
            boolean inputOk = false;
            int billAccNum, billPayee;
            double billAmt;
            try{
                billAccNum = Integer.parseInt(viewer.billAccNum.getText());
                billPayee = Integer.parseInt(viewer.billPayee.getText());
                billAmt = Double.parseDouble(viewer.billAmt.getText());
                boolean succeed = clientActionHandler.transfer(billAmt, billAccNum, billPayee);
                if (succeed){
                    JOptionPane.showMessageDialog(null, "Pay bill succeed");
                }else{
                    JOptionPane.showMessageDialog(null, "You don't have that much money");
                }
            }catch (Exception exp){
                JOptionPane.showMessageDialog(null, "Please check your input");
            }
        });

        viewer.goBackWithdraw.addActionListener(e->{
            viewer.changePage(viewer.payBill, viewer.clientOptions);
        });

    }

    public void deposit(){

        viewer.depositButton.addActionListener(e->{
            int numFives, numTens, numTwenty, numFifty;

            numFives = Integer.parseInt(viewer.numFives.getToolTipText());
            numTens = Integer.parseInt(viewer.numTens.getToolTipText());
            numTwenty = Integer.parseInt(viewer.numTwenty.getToolTipText());
            numFifty = Integer.parseInt(viewer.numFifty.getToolTipText());
            boolean succeed = clientActionHandler.deposit(numFives, numTens, numTwenty, numFifty);
            if (succeed){
                JOptionPane.showMessageDialog(null, "deposit succeed");
            }else{
                JOptionPane.showMessageDialog(null, "something is wrong");
            }
        });

        viewer.goBackDeposit.addActionListener(e->{
            viewer.changePage(viewer.depositOption, viewer.clientOptions);
        });
    }

    public void changePswd(){
        viewer.changePswd.addActionListener(e->{
            // todo why this returns String
            char[] pswd = viewer.newPassword.getPassword();
            if (pswd.length == 0) {
                boolean succeed = clientActionHandler.changepswd(pswd);
                if (succeed) {
                    JOptionPane.showMessageDialog(null, "password changed, please remember it!");
                } else {
                    JOptionPane.showMessageDialog(null, "please enter a password in between 6 to 15 character");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Please check your input");
            }
        });
        viewer.goBackPassword.addActionListener(e->{
            viewer.changePage(viewer.changePassword, viewer.clientOptions);
        });
    }


    /**
     * bank manager action handler
     */

    public void bankManagerOption(){
        viewer.createNewClientButton.addActionListener(e->{
            viewer.changePage(viewer.managerOptions, viewer.thepage);
            createNewClient();
        });
        viewer.undoTransactionButton.addActionListener(e->{
            viewer.changePage(viewer.managerOptions, viewer.thepage);
        });
        viewer.restockMachineButton.addActionListener(e->{
            viewer.changePage(viewer.managerOptions, viewer.thepage);
        });
        viewer.viewAccountCreationRequestsButton.addActionListener(e->{
            viewer.changePage(viewer.managerOptions, viewer.thepage);
        });
        viewer.showAlertsButton.addActionListener(e->{
            viewer.changePage(viewer.managerOptions, viewer.thepage);
        });
        viewer.logOutManager.addActionListener(e->{
            viewer.changePage(viewer.managerOptions, viewer.welcomePage);
        });
    }

    public void createNewClient(){
        viewer.button1.addActionListener(e->{


        });

    }



}
