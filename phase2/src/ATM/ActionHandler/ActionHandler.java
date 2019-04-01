package ATM.ActionHandler;

import ATM.Atm;
import ATM.BankAccounts.BankAccount;
import ATM.Managers.AccountManager;
import ATM.Managers.BillManager;
import ATM.Managers.TransactionManager;
import ATM.Managers.UserManager;
import ATM.Managers.RequestManager;
import ATM.Users.BankManager;
import ATM.Users.Client;
import ATM.Users.User;
import ATM.ATMGUI;
import ATM.Transaction;

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


    // attributes for execution
    private String userType;

    private User currentUser;

    private int runStage;



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
                newUser();
            }
        });
        viewer.returningUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.changePage(viewer.welcomePage, viewer.returningUserPage);
                runStage = 102;
                login();
            }
        });

    }

    /**
     * New User Page
     */
    public void newUser(){
        viewer.accRequestButton.addActionListener(e->{
            // get input
            String username = viewer.userDesiredName.getText();
            String type = viewer.newAccType.getSelectedItem().toString();
            // check if the username existed
            if (!userManager.userExists(username)){
                // store the alert
                requestManager.addRequest("newUser", username, type);
                viewer.popUp("Request submitted, please come back to check" +
                        "your status");
            }else{
                viewer.popUp("Your username is already token");
            }
        });
        viewer.requestStatus.addActionListener(e->{
            // get input
            String username = viewer.userDesiredName.getText();
            String type = viewer.newAccType.getSelectedItem().toString();
            // check if the request exist
            if (requestManager.requestExist("newUser", username, null)){
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
        });
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
                        bankInspectorOption();
                    }
                }else{
                    viewer.usernameText.setText("");
                    viewer.passwordText.setText("");
                    viewer.popUp("Incorrect username/password. Please try again.");
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
     * Client Action Handler
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
        viewer.setPrimaryChequingAccountButton.addActionListener(e->{
            viewer.changePage(viewer.clientOptions, viewer.setPrimary);
            setPrimary();
        });
        viewer.goBackClient.addActionListener(e->{
            viewer.changePage(viewer.clientOptions, viewer.welcomePage);
            currentUser = null;
        });

    }

    public void accountSummary(){
        Map accountBalance = clientActionHandler.checkBalance();
        StringBuilder summary = new StringBuilder("Bank Accounts and Balances: \n");
        for (Object accountNumber : accountBalance.keySet()) {
            summary.append(accountNumber + ": " + accountBalance.get(accountNumber));
        }
        summary.append("Your net total is: " + clientActionHandler.netTotal(accountBalance));
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
        viewer.goBackClientSummary.addActionListener(e -> {
            viewer.changePage(viewer.summaryOfAccounts, viewer.clientOptions);
        });

    }

    public void createNewAccount(){
        String accType = viewer.accType.getSelectedItem().toString();
        if (requestManager.requestExist("newAccount", currentUser.username, accType))
        // TODO CHECK IF THIS IS RIGHT
        viewer.createAccountButton.addActionListener(e -> {
            accountManager.requestNewAccount(currentUser.username, accType);
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
            int numFives, numTens, numTwenty, numFifty, account;
            double numCheque;
            try{
                account = (int) viewer.depositAccNum.getValue();
                numFives = (int) viewer.numFives.getValue();
                numTens = (int) viewer.numTens.getValue();
                numTwenty = (int) viewer.numTwenty.getValue();
                numFifty = (int) viewer.numFifty.getValue();
                numCheque = (double) viewer.chequeAmt.getValue();
                boolean succeedCheque = clientActionHandler.deposit(account, numCheque);
                boolean succeedCash = clientActionHandler.deposit(account, numFives, numTens, numTwenty, numFifty);
                if (succeedCheque || succeedCash){
                    viewer.popUp("Deposit successful.");
                }else{
                    viewer.popUp("Please check your input.");
                }
            }catch (Exception exp){
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

    public void setPrimary(){
        viewer.setPrimaryButton.addActionListener(e->{
            try{
                // get input
                int accNum = (int) viewer.selectPrimary.getSelectedItem();
                // do action
                if (clientActionHandler.setPrimary(accNum)){
                    viewer.popUp("You have successfully set a new " +
                            "primary account.");
                }else{
                    viewer.popUp("Please select a chequing account");
                }
            }catch (Exception exp){
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
            try {
                showAlerts();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        viewer.viewUserCreationRequestsButton.addActionListener(e->{
            viewer.changePage(viewer.managerOptions, viewer.viewUserRequests);
            viewUserCreationRequests();
        });
        viewer.joinAccountsButton.addActionListener(e -> {
            viewer.changePage(viewer.managerOptions, viewer.joinAccounts);
            joinAccounts();
        });
        viewer.logOutManager.addActionListener(e->{
            viewer.changePage(viewer.managerOptions, viewer.welcomePage);
            currentUser = null;
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
        int selectedIndex = viewer.recentTrans.getSelectedIndex();
        viewer.undoButton.addActionListener(e -> {
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
            int numFives, numTens, numTwenty, numFifty;
            try{
                numFives = (int) viewer.restockFives.getValue();
                numTens = (int) viewer.restockTens.getValue();
                numTwenty = (int) viewer.restockTwenty.getValue();
                numFifty = (int) viewer.restockFifty.getValue();
                bankManagerActionHandler.restockBills(numFives, numTens, numTwenty, numFifty);
                viewer.popUp("ATM has been restocked.");
            }catch (Exception exp){
                viewer.popUp("Please check your input.");
            }
        });
        viewer.goBackRestock.addActionListener(e -> {
            viewer.changePage(viewer.restockMachine, viewer.managerOptions);
        });
    }

    public void viewAccountCreationRequests(){
        ArrayList<String[]> accRequests = accountManager.getAccountRequests();
        Object[] accRequestObject = accRequests.toArray();
        viewer.accountRequestsList.setListData(accRequestObject);
        int selectedIndex = viewer.accountRequestsList.getSelectedIndex();

        // TODO UPDATE REQUEST MANAGER STATUS -- ACCEPTED
        viewer.acceptAccountRequestButton.addActionListener(e -> {
            String username = accRequests.get(selectedIndex)[0];
            String accType = accRequests.get(selectedIndex)[1];
            User user = userManager.getUser(username);
            BankAccount acc = accountManager.createAccount(accType);
            ((Client) user).addAccounts(acc.getId());
        });
        // TODO UPDATE REQUEST MANAGER STATUS -- DECLINED
        viewer.declineAccountRequestButton.addActionListener(e -> {

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
        ArrayList<String> userRequests = userManager.getClientRequests();
        Object[] userRequestObject = userRequests.toArray();
        viewer.userRequestsList.setListData(userRequestObject);
        int selectedIndex = viewer.accountRequestsList.getSelectedIndex();

        // TODO UPDATE THE REQUEST STATUS OF USER
        viewer.acceptUserRequestButton.addActionListener(e -> {
            String username = userRequests.get(selectedIndex);
            String[] user = bankManagerActionHandler.addClient(username);
            // TODO DELIVER THE USERNAME AND PASSWORD TO THE USER?
        });

        // TODO UPDATE THE REQUEST STATUS OF USER
        viewer.declineUserRequestButton.addActionListener(e -> {

        });
        viewer.goBackUserRequest.addActionListener(e -> {
            viewer.changePage(viewer.viewUserRequests, viewer.managerOptions);
        });
    }

    public void joinAccounts(){
        // FIXME NOT DONE
        viewer.joinButton.addActionListener(e -> {
            try{
                Client user1 = userManager.getUser(viewer.jointUser1.getText());
                Client user2 = userManager.getUser(viewer.jointUser2.getText());
                BankAccount joinAcc = accountManager.getAccount(Integer.parseInt(viewer.joinAccNum.getText()));
                if (user1 == null || user2 == null){
                    viewer.popUp("Please enter valid usernames.");
                }else if (bankManagerActionHandler.joinAccount(user1.getUsername(), user2.getUsername(), joinAcc.getId())){
                    viewer.popUp("Accounts has been jointed");
                }else{
                    viewer.popUp("Something is wrong with your input");
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
            sendMessageToManager();
        });
        viewer.seeAllTransactionsButton.addActionListener(e -> {
            viewer.changePage(viewer.inspectorOptions, viewer.allTransactions);
            seeAllTransactions();

        });
        viewer.checkClientsAccountButton.addActionListener(e -> {
            viewer.changePage(viewer.inspectorOptions, viewer.clientSummary);
            checkClientsAccount();
        });
        viewer.moreOptionsButton.addActionListener(e -> {
            viewer.changePage(viewer.inspectorOptions, viewer.summaryOfAccounts);
        });
        viewer.logOutInspector.addActionListener(e -> {
            viewer.changePage(viewer.inspectorOptions, viewer.welcomePage);
            currentUser = null;
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
