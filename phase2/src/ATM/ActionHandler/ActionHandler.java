package ATM.ActionHandler;

import ATM.Atm;
import ATM.BankAccounts.BankAccount;
import ATM.Managers.AccountManager;
import ATM.Managers.BillManager;
import ATM.Managers.TransactionManager;
import ATM.Managers.UserManager;
import ATM.Managers.RequestManager;
import ATM.Users.BankInspector;
import ATM.Users.BankManager;
import ATM.Users.Client;
import ATM.Users.User;
import ATM.ATMGUI;
import ATM.Transaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ActionHandler {

    /**
     * Private attributes
     */

    // data/model
    private AccountManager accountManager;

    private TransactionManager transactionManager;

    private UserManager userManager;

    private BillManager billManager;

    private RequestManager requestManager;

    // viewer/display
    private ATMGUI viewer;

    // sub-action handler
    private BankInspectorActionHandler bankInspectorActionHandler;

    private ATM.ActionHandler.BankManagerActionHandler bankManagerActionHandler;

    private ClientActionHandler clientActionHandler;

    private Atm atm;


    // attributes for execution
    private String userType;

    private User currentUser;



    /**
     * Initialize attributes
     */
    public ActionHandler(Atm atm, ATMGUI atmgui){
        this.accountManager = atm.getAccountManager();
        this.userManager = atm.getUserManager();
        this.billManager = atm.getBillManager();
        this.transactionManager = atm.getTransactionManager();
        this.billManager = atm.getBillManager();
        this.requestManager = atm.getRequestManager();
        this.viewer = atmgui;
        this.atm = atm;
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
        // initialize all the action listener
        viewer.newUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.changePage(viewer.welcomePage, viewer.newUserPage);
                newUser();
            }
        });
        newUser();
        login();
        viewer.returningUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.changePage(viewer.welcomePage, viewer.returningUserPage);
                login();
            }
        });

        viewer.exitButton.addActionListener(e -> {
            try {
                atm.save();
                System.exit(0);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

    }

    /**
     * New User Page
     */
    private void newUser(){
        viewer.accRequestButton.addActionListener(e->{
            // get input
            String username = viewer.userDesiredName.getText();
            String acc = viewer.newAccType.getSelectedItem().toString();
            // check if the username existed
            if (!userManager.userExists(username)){
                // store the alert
                if (requestManager.requestExist("newUser", username, acc)){
                    viewer.popUp("Only check status for this username is allowed");
                }else{
                    requestManager.addRequest("newUser", username, acc);
                    viewer.popUp("Request submitted, please come back to check" +
                            "your status");
                }

            }else{
                viewer.popUp("Your username is already token");
            }
        });
        viewer.requestStatus.addActionListener(e->{
            // get input
            String username = viewer.userDesiredName.getText();
            String type = viewer.newAccType.getSelectedItem().toString();
            // check if the request exist
            if (requestManager.requestExist("newUser", username, type)){
                // then show the status
                String status = requestManager.getStatus("newUser", username);
                if (status.equals("accepted")){
                    String pswd = userManager.getUser(username).getPassword();
                    viewer.popUp("Request accepted, here is your initial password: \n " + pswd);
                }else if (status.equals("pending")) {
                    viewer.popUp("Request pending.");
                }else{
                    viewer.popUp("Request declined.");
                }
            }else{
                viewer.popUp("Please request to make an account before you check your status.");
            }
        });
        viewer.goBackNew.addActionListener(e->{
            viewer.changePage(viewer.newUserPage, viewer.welcomePage);
            viewer.userDesiredName.setText("");
        });
    }


    /**
     * User Log in
     */
    private void login(){
        viewer.goBackReturn.addActionListener(e->{
            viewer.changePage(viewer.returningUserPage, viewer.welcomePage);

        });
        viewer.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean loginSuccess = login(viewer.usernameText.getText(), String.valueOf(viewer.passwordText.getPassword()));
                if (loginSuccess){
                    if (userType.equals("client")){
                        viewer.changePage(viewer.returningUserPage, viewer.clientOptions);
                        currentUser = userManager.getUser(viewer.usernameText.getText());
                        viewer.usernameText.setText("");
                        viewer.passwordText.setText("");
                        clientActionHandler = new ClientActionHandler((Client)currentUser, atm);

                    }else if(userType.equals("bankManager")){
                        viewer.changePage(viewer.returningUserPage, viewer.managerOptions);
                        bankManagerActionHandler = new BankManagerActionHandler(atm);
                        viewer.usernameText.setText("");
                        viewer.passwordText.setText("");
                    }else{
                        viewer.changePage(viewer.returningUserPage, viewer.inspectorOptions);
                        viewer.usernameText.setText("");
                        viewer.passwordText.setText("");
                        currentUser = userManager.getUser(viewer.usernameText.getText());
                        bankInspectorActionHandler = new BankInspectorActionHandler((BankInspector) currentUser,atm,"messages.txt");
                    }
                }else{
                    viewer.usernameText.setText("");
                    viewer.passwordText.setText("");
                    viewer.popUp("Incorrect username/password. Please try again.");
                }
            }
        });
    }
    private boolean login(String userId, String pswd){
        if (userManager.userExists(userId)){
            if (userManager.getUser(userId).getPassword().equals(pswd)){
                currentUser = userManager.getUser(userId);
                // use type
                if (currentUser instanceof BankInspector){
                    userType = "bankInspector";
                }else if (currentUser instanceof BankManager){
                    userType = "bankManager";
                }else{
                    userType = "client";
                }
                return true;
            }
        }
        return false;
    }


    /**
     * Client Action Handler
     */
    public void clientOption(){
        // add listener
        viewer.viewAccountSummaryButton.addActionListener(e->{
            viewer.changePage(viewer.clientOptions, viewer.summaryOfAccounts);
        });
        accountSummary();
        viewer.withdrawMoneyButton.addActionListener(e->{
            viewer.changePage(viewer.clientOptions, viewer.withdrawOption);
        });
        withdraw();
        viewer.transferMoneyButton.addActionListener(e->{
            viewer.changePage(viewer.clientOptions, viewer.transferOption);
        });
        transfer();
        viewer.payABillButton.addActionListener(e->{
            viewer.changePage(viewer.clientOptions, viewer.payBill);
        });
        payBill();
        viewer.depositMoneyButton.addActionListener(e->{
            viewer.changePage(viewer.clientOptions, viewer.depositOption);
        });
        deposit();
        viewer.changePasswordButton.addActionListener(e->{
            viewer.changePage(viewer.clientOptions, viewer.changePassword);
        });
        changePswd();
        viewer.setPrimaryChequingAccountButton.addActionListener(e->{
            viewer.changePage(viewer.clientOptions, viewer.setPrimary);
        });
        setPrimary();
        viewer.goBackClient.addActionListener(e->{
            // todo clean all the action listener
            viewer.changePage(viewer.clientOptions, viewer.welcomePage);
        });

    }

    public void accountSummary(){
        Map accountBalance = clientActionHandler.checkBalance();
        StringBuilder summary = new StringBuilder("Bank Accounts and Balances: \n");
        for (Object accountNumber : accountBalance.keySet()) {
            summary.append(accountNumber + ": " + accountBalance.get(accountNumber)+ "\n");
        }
        summary.append("Your net total is: " + clientActionHandler.netTotal(accountBalance) + "\n");
        viewer.accountSummaries.setText(summary.toString());

        viewer.seeMostRecentTransactionButton.addActionListener(e -> {
            viewer.accountInfo.setText("");
            int accountNum;
            try{
                accountNum = Integer.parseInt(viewer.summaryAccNum.getText());
                ArrayList<Transaction> transactions = transactionManager.getTransactionsBySender(accountNum);
                if(transactions.size() == 0 || transactions.get(transactions.size() - 1) == null) {
                    viewer.popUp("The latest transaction is not viewable on this account");
                }else{
                    Transaction transaction = transactions.get(transactions.size() - 1);
                    viewer.accountInfo.setText(transaction.toString());
                }
            }catch (Exception exp){
                viewer.popUp("Please check your input.");
            }
        });
        // see account creation date
        viewer.checkAccountCreationDateButton.addActionListener(e -> {
            viewer.accountInfo.setText("");
            int accountNum;
            try{
                accountNum = Integer.parseInt(viewer.summaryAccNum.getText());
                viewer.accountInfo.setText(accountManager.getAccount(accountNum).getDateCreated().toString());
            }catch (Exception exp){
                viewer.popUp("Please check your input.");
            }
        });
        // request to create a new account
        viewer.makeANewAccountButton.addActionListener(e -> {
            viewer.changePage(viewer.summaryOfAccounts, viewer.newAccount);
            createNewAccount();
        });

        // go back
        viewer.goBackSummary.addActionListener(e -> {
            viewer.changePage(viewer.summaryOfAccounts, viewer.clientOptions);
        });

    }

    public void createNewAccount(){
        viewer.createAccountButton.addActionListener(e -> {
            String accType = viewer.accType.getSelectedItem().toString();
            if (!requestManager.requestExist("newAccount", currentUser.username, accType)){
                accountManager.requestNewAccount(currentUser.username, accType);
                viewer.popUp("request submitted");
            }else{
                viewer.popUp("something is wrong");
            }
        });


        viewer.goBackNewAcc.addActionListener(e -> {
            viewer.changePage(viewer.newAccount, viewer.summaryOfAccounts);
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
            int billAccNum, billPayee;
            double billAmt;
            try{
                billAccNum = Integer.parseInt(viewer.billAccNum.getText());
                billPayee = Integer.parseInt(viewer.billPayee.getText());
                billAmt = Double.parseDouble(viewer.billAmt.getText());
                boolean succeed = clientActionHandler.payBill(billAccNum, billAmt, billPayee);
                if (succeed){
                    viewer.popUp("You have successfully paid your bill.");
                }else{
                    viewer.popUp("You don't have enough money");
                }
            }catch (Exception exp){
                viewer.popUp("Please check your input.");
            }
        });

        viewer.goBackBill.addActionListener(e->{
            viewer.changePage(viewer.payBill, viewer.clientOptions);
        });

    }

    public void deposit(){

        viewer.depositButton.addActionListener(e->{
            int numFives, numTens, numTwenty, numFifty, account;
            double numCheque;
            try{
                // get input
                account = Integer.parseInt(viewer.depositAccNum.getText());
                numFives = (int) viewer.numFives.getValue();
                numTens = (int) viewer.numTens.getValue();
                numTwenty = (int) viewer.numTwenty.getValue();
                numFifty = (int) viewer.numFifty.getValue();
                numCheque = Double.parseDouble(viewer.chequeAmt.getText());
                // check if
                boolean succeedCheque = clientActionHandler.deposit(account, numCheque);
                boolean succeedCash = clientActionHandler.deposit(account, numFives, numTens, numTwenty, numFifty);
                if (succeedCheque || succeedCash){
                    viewer.popUp("Deposit successful.");
                }else{
                    viewer.popUp("Something is wrong");
                }
            }catch (Exception exp){
                viewer.popUp("Please check your input. (put 0 in cheque if it is currently empty");
            }
        });

        viewer.goBackDeposit.addActionListener(e->{
            viewer.changePage(viewer.depositOption, viewer.clientOptions);
        });
    }

    public void changePswd(){
        viewer.changePswd.addActionListener(e->{
            char[] pswd = viewer.newPassword.getPassword();
            if (pswd.length != 0) {
                boolean succeed = clientActionHandler.changepswd(pswd);
                if (succeed) {
                    viewer.popUp("You have successfully changed your password. Don't forget it!");
                } else {
                    viewer.popUp("Please enter a password between 5 to 10 characters.");
                }
            }else{
                viewer.popUp("Please check your input.");
            }
        });
        viewer.goBackPassword.addActionListener(e->{
            viewer.changePage(viewer.changePassword, viewer.clientOptions);
            viewer.newPassword.setText("");
        });
    }

    public void setPrimary(){
        viewer.setPrimaryButton.addActionListener(e->{
            Object accNum = viewer.primaryAccNum.getValue();
            if (accNum instanceof Integer){
                if (clientActionHandler.setPrimary((int)accNum)){
                    viewer.popUp("You have successfully set a new " +
                            "primary account.");
                }else{
                    viewer.popUp("Please select a chequing account");
                }
            }else{
                viewer.popUp("Please check your input.");
            }
        });
        viewer.goBackPrimary.addActionListener(e->{
            viewer.changePage(viewer.setPrimary, viewer.clientOptions);
        });

    }

    /**
     * Bank Manager Action Handler
     */

    public void bankManagerOption(){
        viewer.createNewClientButton.addActionListener(e->{
            viewer.changePage(viewer.managerOptions, viewer.newClient);
        });
        createNewClient();
        viewer.undoTransactionButton.addActionListener(e->{
            viewer.changePage(viewer.managerOptions, viewer.undoTransaction);
        });
        undoTransaction();
        viewer.restockMachineButton.addActionListener(e->{
            viewer.changePage(viewer.managerOptions, viewer.restockMachine);
        });
        restockMachine();
        viewer.viewAccountCreationRequestsButton.addActionListener(e->{
            viewer.changePage(viewer.managerOptions, viewer.viewAccountRequests);
        });
        viewAccountCreationRequests();
        // todo what is this
        viewer.showAlertsButton.addActionListener(e->{
            viewer.changePage(viewer.managerOptions, viewer.viewAlerts);
            try {
                showAlerts();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        viewer.viewUserCreationRequestsButton.addActionListener(e->{
            viewer.changePage(viewer.managerOptions, viewer.viewUserRequests);
        });
        viewUserCreationRequests();
        viewer.joinAccountsButton.addActionListener(e -> {
            viewer.changePage(viewer.managerOptions, viewer.joinAccounts);
        });
        joinAccounts();
        viewer.logOutManager.addActionListener(e->{
            // to do clean all the action listener
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
            int selectedIndex = viewer.recentTrans.getSelectedIndex();
            // TODO CHECK IF THIS WORKS
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
            Object numFives, numTens, numTwenty, numFifty;
            numFives = viewer.restockFives.getValue();
            numTens = viewer.restockTens.getValue();
            numTwenty = viewer.restockTwenty.getValue();
            numFifty = viewer.restockFifty.getValue();
            if (numFives instanceof Integer && numTens instanceof Integer && numTwenty instanceof Integer && numFifty instanceof Integer){
                bankManagerActionHandler.restockBills((int)numFives, (int)numTens, (int)numTwenty, (int)numFifty);
                viewer.popUp("ATM has been restocked.");
            }else{
                viewer.popUp("Please check your input.");
            }
        });
        viewer.goBackRestock.addActionListener(e -> {
            viewer.changePage(viewer.restockMachine, viewer.managerOptions);
        });
    }

    public void viewAccountCreationRequests(){

        ArrayList<String[]> accRequests = requestManager.getClientRequestsByStatus("newAccount","pending");
        viewer.accountRequestsList.setListData(bankManagerActionHandler.formatRequest(accRequests));

        viewer.acceptAccountRequestButton.addActionListener(e -> {
            int selectedIndex = viewer.accountRequestsList.getSelectedIndex();

            String username = accRequests.get(selectedIndex+1)[0];
            String accType = accRequests.get(selectedIndex+1)[1];
            User user = userManager.getUser(username);
            BankAccount acc = accountManager.createAccount(accType);
            ((Client) user).addAccounts(acc.getId());
            atm.getRequestManager().updateStatus("newAccount", currentUser.username, "accepted");
            ArrayList<String[]> accRequests1 = requestManager.getClientRequestsByStatus("newAccount","pending");
            viewer.accountRequestsList.setListData(bankManagerActionHandler.formatRequest(accRequests1));

        });
        viewer.declineAccountRequestButton.addActionListener(e -> {
            atm.getRequestManager().updateStatus("newAccount", currentUser.username, "declined");


        });
        viewer.goBackAccRequest.addActionListener(e -> {
            viewer.changePage(viewer.viewAccountRequests, viewer.managerOptions);
        });
    }

    public void showAlerts() throws IOException {
        StringBuilder alerts = new StringBuilder();
        ArrayList<String> alertsText = bankManagerActionHandler.getAlerts();
        for (String alert : alertsText) {
            alerts.append(alert);
        }
        viewer.alertText.setText(alerts.toString());

        viewer.clearAlertsButton.addActionListener(e -> {
            try {
                bankManagerActionHandler.clearAlerts();
                viewer.alertText.setText("");
                viewer.popUp("The alerts have been cleared.");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        viewer.goBackAlert.addActionListener(e -> {
            viewer.changePage(viewer.viewAlerts, viewer.managerOptions);
        });
    }

    public void viewUserCreationRequests(){
        ArrayList<String[]> userRequests = requestManager.getClientRequestsByStatus("newUser", "pending");
        viewer.userRequestsList.setListData(bankManagerActionHandler.formatRequest(userRequests));

        viewer.acceptUserRequestButton.addActionListener(e -> {
            // get input
            int selectedIndex = viewer.accountRequestsList.getSelectedIndex();
            String username = userRequests.get(selectedIndex+1)[0];
            String accType = userRequests.get(selectedIndex+1)[1];
            // add user, add initial account, change status
            // add user
            bankManagerActionHandler.addClient(username);
            // initialize account
            BankAccount acc = accountManager.createAccount(accType);
            // add account to user's account
            ((Client)userManager.getUser(username)).addAccounts(acc.getId());
            accountManager.addAccount(acc);
            // update status
            requestManager.updateStatus("newUser", username, "accepted");
            viewer.popUp("This request has been accept");
            // update the window
            ArrayList<String[]> userRequests1 = requestManager.getClientRequestsByStatus("newUser", "pending");
            viewer.userRequestsList.setListData(bankManagerActionHandler.formatRequest(userRequests1));

        });

        viewer.declineUserRequestButton.addActionListener(e -> {
            // get input
            int selectedIndex = viewer.accountRequestsList.getSelectedIndex();
            String username = userRequests.get(selectedIndex)[0];
            String accType = userRequests.get(selectedIndex)[1];

            // update status
            requestManager.updateStatus("newUser", username, "declined");
            viewer.popUp("This request has been declined");
            // update the window
//            ArrayList<String[]> userRequests1 = requestManager.getClientRequestsByStatus("newUser", "pending");
//            viewer.userRequestsList.setListData(bankManagerActionHandler.formatRequest(userRequests1));

        });
        viewer.goBackUserRequest.addActionListener(e -> {
            viewer.changePage(viewer.viewUserRequests, viewer.managerOptions);
        });
    }

    public void joinAccounts(){
        viewer.joinButton.addActionListener(e -> {
            try{
                Client user1 = (Client)userManager.getUser(viewer.jointUser1.getText());
                Client user2 = (Client)userManager.getUser(viewer.jointUser2.getText());
                BankAccount joinAcc = accountManager.getAccount(Integer.parseInt(viewer.joinAccNum.getText()));
                if (user1 == null || user2 == null){
                    viewer.popUp("Please enter valid usernames.");
                } else if (!user1.getAccounts().contains(joinAcc) || !user2.getAccounts().contains(joinAcc)){
                    viewer.popUp("Please choose an account that at least one of the users currently own");
                }else{
                    boolean successful = bankManagerActionHandler.joinAccount(user1.getUsername(), user2.getUsername(), joinAcc.getId());
                    if (successful){
                        viewer.popUp("Account joined.");
                    }else{
                        viewer.popUp("Please check your input and try again.");
                    }
                }
            }catch (Exception exp){
                viewer.popUp("Please check your input.");
            }
        });
        viewer.goBackJoinAcc.addActionListener(e -> {
            viewer.changePage(viewer.joinAccounts, viewer.managerOptions);
        });
    }

    /**
     * Bank Inspector Action Handler
     */

    public void bankInspectorOption(){
        viewer.sendMessageToManagerButton.addActionListener(e -> {
            viewer.changePage(viewer.inspectorOptions, viewer.sendManagerMsg);
        });
        sendMessageToManager();
        viewer.seeAllTransactionsButton.addActionListener(e -> {
            viewer.changePage(viewer.inspectorOptions, viewer.allTransactions);
        });
        seeAllTransactions();
        viewer.checkClientsAccountButton.addActionListener(e -> {
            viewer.changePage(viewer.inspectorOptions, viewer.clientSummary);
        });
        checkClientsAccount();
        viewer.moreOptionsButton.addActionListener(e -> {
            viewer.changePage(viewer.inspectorOptions, viewer.summaryOfAccounts);
        });
        viewer.logOutInspector.addActionListener(e -> {
            // todo clean all the action listener
            viewer.changePage(viewer.inspectorOptions, viewer.welcomePage);
        });
    }


    public void sendMessageToManager() {
        viewer.sendMessageToManagerButton.addActionListener(e -> {
            if (viewer.inspectorMsg.getText().isEmpty()){
                viewer.popUp("Please enter a message.");
            }else{
                try {
                    bankInspectorActionHandler.sendMessageToManager(viewer.inspectorMsg.getText());
                    viewer.popUp("Your message has been sent.");
                    viewer.inspectorMsg.setText("");
                } catch (IOException e1) {
                    viewer.popUp("Error! Please try again.");
                    e1.printStackTrace();
                }
            }

        });
        viewer.goBackMsg.addActionListener(e -> {
            viewer.changePage(viewer.sendManagerMsg, viewer.inspectorOptions);
        });
    }

    public void seeAllTransactions() {
        StringBuilder transactions = new StringBuilder();
        ArrayList<String> transactionText = bankInspectorActionHandler.getAllTransactions();
        for (String transaction : transactionText) {
            transactions.append(transaction);
        }
        viewer.allTransactionText.setText(transactions.toString());

        viewer.goBackTransactions.addActionListener(e -> {
            viewer.changePage(viewer.allTransactions, viewer.inspectorOptions);
        });
    }

    public void checkClientsAccount(){
        bankInspectorActionHandler.selectClient(viewer.clientUsername.getText());
        StringBuilder text = new StringBuilder();
        viewer.seeClientAccountSummaryButton.addActionListener(e -> {
            viewer.clientSummaryOrTransaction.setText("");
            ArrayList<String> clientSummary = bankInspectorActionHandler.getClientAccountSummary();
            for (String summary : clientSummary) {
                text.append(summary);
            }
            viewer.clientSummaryOrTransaction.setText(text.toString());
        });

        viewer.seeClientIncomingTransactionsButton.addActionListener(e -> {
            viewer.clientSummaryOrTransaction.setText("");
            ArrayList<String> incomingTransactions = bankInspectorActionHandler.getClientIncomingTransactions();
            for (String incoming : incomingTransactions) {
                text.append(incoming);
            }
            viewer.clientSummaryOrTransaction.setText(text.toString());
        });

        viewer.seeClientOutgoingTransactionsButton.addActionListener(e -> {
            viewer.clientSummaryOrTransaction.setText("");
            ArrayList<String> outgoingTransactions = bankInspectorActionHandler.getClientOutgoingTransactions();
            for (String outgoing : outgoingTransactions) {
                text.append(outgoing);
            }
            viewer.clientSummaryOrTransaction.setText(text.toString());
        });

        viewer.goBackClientSummary.addActionListener(e -> {
            viewer.changePage(viewer.clientSummary, viewer.inspectorOptions);
        });
    }
}
