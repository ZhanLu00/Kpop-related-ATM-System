package ATM.ActionHandler;

import ATM.*;
import ATM.BankAccounts.AssetAccounts.ChequingAccount;
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
import java.util.ArrayList;

public class ActionHandler {

    /**
     * Private attributes
     */

    // data/model
    private AccountManager accountManager;

    private TransactionManager transactionManager;

    private UserManager userManager;

    private BillManager billManager;

    // viewer/display
    private ATMGUI viewer;

    // sub-action handler
    private BankInspectorActionHandler bankInspectorActionHandler;

    private ATM.ActionHandler.BankManagerActionHandler bankManagerActionHandler;

    private ClientActionHandler clientActionHandler;


    // attributes for execution
    private String userType;

    private User currentUser;

    private int runStage;



    /**
     * Initialize attributes
     */
    public ActionHandler(ATMGUI atmgui){
//        this.accountManager = atm.getAccountManager();
//        this.userManager = atm.getUserManager();
//        this.billManager = atm.getBillManager();
//        this.transactionManager = atm.getTransactionManager();
        this.viewer = atmgui;
    }

    /**
     * Initialize viewer
     */
    public void initViewer(){
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
                boolean loginSuccess = login(viewer.usernameText.getText(), String.valueOf(viewer.passwordText.getPassword()));
                if (loginSuccess){
                    if (userType.equals("client")){
                        viewer.changePage(viewer.returningUserPage, viewer.clientOptions);
                        currentUser = userManager.getUser(viewer.usernameText.getText());
                        clientOption();
                    }else if(userType.equals("bankManager")){
                        viewer.changePage(viewer.returningUserPage, viewer.managerOptions);
                        bankManagerOption();
                    }else{
                        viewer.changePage(viewer.returningUserPage, viewer.inspectorOptions);
                    }
                }else{
                    viewer.usernameText.setText("");
                    viewer.passwordText.setText("");
                    viewer.popUp("Incorrect username/password. " +
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
        StringBuilder summary = new StringBuilder("Bank Accounts and Balances: \n");
        // a field for accounts sum
        // include creation date
        // FIXME GET EACH BANK ACC CREATION DATE, ACC TYPE, ACC NUMBER AND BALANCE
        for (Object accountNumber:accountBalance.keySet()) {
            summary.append(accountNumber + ": " + accountBalance.get(accountNumber));
        }
        // net total
        // FIXME GET USER NET TOTAL
        summary.append("Net total $%s", clientActionHandler.netTotal(currentUser.getAccounts()));


        //set the summary text
        viewer.accountSummaries.setText(summary.toString());

        // a field for transaction history
        // FIXME are the transactions in chronological order? (ie index 0 is most recent)
        //  rn it's looking for account number but we only have current user.....
        Transaction recentTrans = transactionManager.getTransactionsBySender(currentUser.accountNum()).get(0);
        viewer.mostRecentTransaction.setText(recentTrans.toString());

        // request to create a new account
        viewer.makeANewAccountButton.addActionListener(e -> {
            viewer.changePage(viewer.summaryOfAccounts, viewer.newAccount);
        });

        // go back
        viewer.goBackClientSummary.addActionListener(e -> {
            viewer.changePage(viewer.summaryOfAccounts, viewer.clientOptions);
        });

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
                    viewer.popUp("Your withdrawal was successful.");
                }else{
                    viewer.popUp("You don't enough money.");
                }
            }catch (Exception exp){
                viewer.popUp("Please check your input.");
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
                    viewer.popUp("Your transfer was successful.");
                }else{
                    viewer.popUp("Please check your input or balance.");
                }
            }catch (Exception exp){
                viewer.popUp("Please check your input.");
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
                    viewer.popUp("You have successfully paid your bill.");
                }else{
                    viewer.popUp("You don't have enough money");
                }
            }catch (Exception exp){
                viewer.popUp("Please check your input.");
            }
        });

        viewer.goBackWithdraw.addActionListener(e->{
            viewer.changePage(viewer.payBill, viewer.clientOptions);
        });

    }

    public void deposit(){

        viewer.depositButton.addActionListener(e->{
            int numFives, numTens, numTwenty, numFifty;

            numFives = (int) viewer.numFives.getValue();
            numTens = (int) viewer.numTens.getValue();
            numTwenty = (int) viewer.numTwenty.getValue();
            numFifty = (int) viewer.numFifty.getValue();
            boolean succeed = clientActionHandler.deposit(numFives, numTens, numTwenty, numFifty);
            if (succeed){
                viewer.popUp("Deposit successful.");
            }else{
                viewer.popUp("Please check your input.");
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
            if (pswd.length != 0) {
                boolean succeed = clientActionHandler.changepswd(pswd);
                if (succeed) {
                    viewer.popUp("You have successfully changed your password. Don't forget it!");
                } else {
                    viewer.popUp("Please enter a password between 6 to 15 characters.");
                }
            }else{
                viewer.popUp("Please check your input.");
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
            viewer.changePage(viewer.managerOptions, viewer.newClient);
            createNewClient();
        });
        viewer.undoTransactionButton.addActionListener(e->{
            viewer.changePage(viewer.managerOptions, viewer.undoTransaction);
            undoTransaction();
        });
        viewer.restockMachineButton.addActionListener(e->{
            viewer.changePage(viewer.managerOptions, viewer.restockMachine);
            restockMachine();
        });
        viewer.viewAccountCreationRequestsButton.addActionListener(e->{
            viewer.changePage(viewer.managerOptions, viewer.viewAccountRequests);
            viewAccountCreationRequests();
        });
        viewer.showAlertsButton.addActionListener(e->{
            viewer.changePage(viewer.managerOptions, viewer.viewAlerts);
        });
        viewer.viewUserCreationRequestsButton.addActionListener(e->{
            viewer.changePage(viewer.managerOptions, viewer.viewUserRequests);
        });
        viewer.logOutManager.addActionListener(e->{
            viewer.changePage(viewer.managerOptions, viewer.welcomePage);
        });
    }

    public void createNewClient(){
        viewer.createUserNew.addActionListener(e->{
            String[] userCreated = bankManagerActionHandler.addClient(viewer.createUserManager.getText());
            if (userCreated[0] == null){
                viewer.createUserManager.setText("");
                viewer.popUp("Username taken. Please select another username.");
            }else{
                viewer.popUp(String.format("User created. Your password is %s", userCreated[1]));
                int accCreated = bankManagerActionHandler.createAccountForUser(userCreated[0],
                        (String)viewer.accTypeNew.getSelectedItem());
                if (accCreated == -1){
                    viewer.popUp("Account could not be created. Please check your input.");
                }else{
                    bankManagerActionHandler.addAccountToUser(userCreated[0], accCreated);
                }
            }
        });

        viewer.goBackCreateUserManager.addActionListener(e->{
            viewer.changePage(viewer.newClient, viewer.managerOptions);
        });
    }

    public void undoTransaction(){
        Object[] transactions = transactionManager.getTransactions().toArray();
        viewer.recentTrans.setSelectedIndex(0);
        viewer.recentTrans.setListData(transactions);
        viewer.undoButton.addActionListener(e -> {
            // TODO CHECK IF THIS WORKS
            int selectedIndex = viewer.recentTrans.getSelectedIndex()
            boolean undoStatus = bankManagerActionHandler.undoTransaction(selectedIndex);
            if (undoStatus){
                viewer.popUp("Transaction successfully undone.");
                viewer.recentTrans.remove(selectedIndex);
            }else{
                viewer.popUp("Can't undo transaction.");
            }

        });
        viewer.goBackUndo.addActionListener(e -> {
            viewer.changePage(viewer.undoTransaction, viewer.managerOptions);
        });
    }

    public void restockMachine(){
        viewer.restockATM.addActionListener(e -> {
            int numFives, numTens, numTwenty, numFifty;

            numFives = (int) viewer.restockFives.getValue();
            numTens = (int) viewer.restockTens.getValue();
            numTwenty = (int) viewer.restockTwenty.getValue();
            numFifty = (int) viewer.restockFifty.getValue();

            // TODO CHECK IF VALID FIRST?
            bankManagerActionHandler.restockBills(numFives, numTens, numTwenty, numFifty);
            viewer.popUp("ATM has been restocked.");
        });
        viewer.goBackRestock.addActionListener(e -> {
            viewer.changePage(viewer.restockMachine, viewer.managerOptions);
        });
    }

    public void viewAccountCreationRequests(){

    }



}
