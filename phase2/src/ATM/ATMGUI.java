package ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ATMGUI {

    /**
     * initialize the form (do not modify this code)
     */
    //panels
    public JPanel root;

    // WELCOME PAGE
    public JPanel welcomePage;
    public JButton newUser;
    public JButton returningUser;
    public JButton exitButton;

    // NEW USER PAGE
    public JPanel newUserPage;
    public JButton goBackNew;
    public JFormattedTextField userDesiredName;
    public JButton accRequestButton;
    public JButton requestStatus;
    public JComboBox newAccType;

    // RETURNING USER PAGE
    public JPanel returningUserPage;
    public JTextField usernameText;
    public JPasswordField passwordText;
    public JButton loginButton;
    public JButton goBackReturn;

    // CLIENT OPTIONS
    public JPanel clientOptions;
    public JButton viewAccountSummaryButton;
    public JButton withdrawMoneyButton;
    public JButton transferMoneyButton;
    public JButton payABillButton;
    public JButton depositMoneyButton;
    public JButton goBackClient;
    public JButton changePasswordButton;
    public JButton setPrimaryChequingAccountButton;

    // MANAGER OPTIONS
    public JPanel managerOptions;
    public JButton createNewClientButton;
    public JButton undoTransactionButton;
    public JButton restockMachineButton;
    public JButton viewAccountCreationRequestsButton;
    public JButton showAlertsButton;
    public JButton viewUserCreationRequestsButton;
    public JButton joinAccountsButton;
    public JButton logOutManager;


    // DEPOSIT OPTION
    public JPanel depositOption;
    public JSpinner numFives;
    public JSpinner numTens;
    public JSpinner numTwenty;
    public JSpinner numFifty;
    public JFormattedTextField depositAccNum;
    public JFormattedTextField chequeAmt;
    public JButton depositButton;
    public JButton goBackDeposit;

    // SUMMARY OF ACCOUNTS OPTION
    public JPanel summaryOfAccounts;
    public JTextArea accountSummaries;
    public JButton goBackSummary;
    public JButton makeANewAccountButton;
    public JTextArea accountInfo;
    public JFormattedTextField summaryAccNum;
    public JButton seeMostRecentTransactionButton;
    public JButton checkAccountCreationDateButton;

    // TRANSFER OPTION
    public JPanel transferOption;
    public JButton goBackTransfer;
    public JFormattedTextField transOutAccNum;
    public JFormattedTextField transInAccNum;
    public JFormattedTextField transAmt;
    public JButton transferButton;

    // PAY BILL OPTION
    public JPanel payBill;
    public JFormattedTextField billAccNum;
    public JFormattedTextField billAmt;
    public JFormattedTextField billPayee;
    public JButton goBackBill;
    public JButton payBillButton;

    // WITHDRAW OPTION
    public JPanel withdrawOption;
    public JFormattedTextField accNumWithdraw;
    public JFormattedTextField withdrawAmt;
    public JButton goBackWithdraw;
    public JButton withdrawButton;


    // CHANGE PASS OPTION
    public JPanel changePassword;
    public JPasswordField newPassword;
    public JButton goBackPassword;
    public JButton changePswd;

    // INSPECTOR OPTIONS
    public JPanel inspectorOptions;
    public JButton sendMessageToManagerButton;
    public JButton seeAllTransactionsButton;
    public JButton checkClientsAccountButton;
    public JButton logOutInspector;
    public JButton moreOptionsButton;

    // SEND MANAGER MESSAGE OPTION
    public JPanel sendManagerMsg;
    public JButton goBackMsg;
    public JButton sendMessageButton;
    public JTextArea inspectorMsg;

    // INSPECTOR CLIENT SUMMARY
    public JPanel clientSummary;
    public JFormattedTextField clientUsername;
    public JButton seeClientAccountSummaryButton;
    public JButton seeClientIncomingTransactionsButton;
    public JButton seeClientOutgoingTransactionsButton;
    public JTextArea clientSummaryOrTransaction;
    public JButton goBackClientSummary;

    // ALL TRANSACTIONS PAGE
    public JPanel allTransactions;
    public JTextArea allTransactionText;
    public JButton goBackTransactions;

    // NEW BANK ACCOUNT PAGE
    public JPanel newAccount;
    public JComboBox accType;
    public JTextArea accRequestStatus;
    public JButton goBackNewAcc;
    public JButton createAccountButton;

    // BANK MANAGER CREATING A NEW CLIENT PAGE
    public JPanel newClient;
    public JTextField createUserManager;
    public JButton goBackCreateUserManager;
    public JButton createUserNew;
    public JComboBox accTypeNew;

    // RESTOCK MACHINE PAGE
    public JPanel restockMachine;
    public JButton goBackRestock;
    public JButton restockATM;
    public JSpinner restockFives;
    public JSpinner restockTens;
    public JSpinner restockTwenty;
    public JSpinner restockFifty;

    // UNDO TRANSACTION PAGE
    public JPanel undoTransaction;
    public JButton goBackUndo;
    public JButton undoButton;
    public JList recentTrans;

    // VIEW NEW USER CREATION REQUESTS PAGE
    public JPanel viewUserRequests;
    public JButton goBackUserRequest;
    public JButton acceptUserRequestButton;
    public JButton declineUserRequestButton;
    public JList userRequestsList;

    // VIEW ALERTS/MESSAGES PAGE
    public JPanel viewAlerts;
    public JButton goBackAlert;
    public JButton clearAlertsButton;
    public JTextArea alertText;
    public JTextArea messageText;
    public JButton clearMessages;

    // VIEW NEW ACCOUNT CREATION REQUESTS PAGE
    public JPanel viewAccountRequests;
    public JButton goBackAccRequest;
    public JButton declineAccountRequestButton;
    public JButton acceptAccountRequestButton;
    public JList accountRequestsList;

    // JOIN TWO CLIENT ACCOUNTS
    public JPanel joinAccounts;
    public JTextField jointUser1;
    public JTextField jointUser2;
    public JButton goBackJoinAcc;
    public JButton joinButton;
    public JFormattedTextField joinAccNum;

    // SET PRIMARY CHEQUING ACCOUNT
    public JPanel setPrimary;
    public JButton goBackPrimary;
    public JButton setPrimaryButton;
    public JFormattedTextField primaryAccNum;


    // COMMUNISM
    public JPanel communismPage;
    public JButton communismButton;
    public JButton backButton;
    public JButton communism;

    public void changePage(JPanel currentPage, JPanel newPage) {
        currentPage.setVisible(false);
        newPage.setVisible(true);
    }

    public void popUp(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    /**
     * This button will remove all the action listener from a button
     */
    public void removeAL(JButton b){
        for (ActionListener acc:b.getActionListeners()){
            b.removeActionListener(acc);
        }
    }

    public ATMGUI() {

    }

    public void init() {
        // to set the communism button transparent
        communism.setOpaque(false);
        communism.setContentAreaFilled(false);
        communism.setBorderPainted(false);
    }

    private void createUIComponents() {
        // ALLOW CLIENTS TO DEPOSIT AT LEAST 0 OF EACH NUMBER OF BILLS
        numFives = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        numTens = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        numTwenty = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        numFifty = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));

        // ALLOW BANK MANAGERS TO RESTOCK AT LEAST 0 OF EACH NUMBER OF BILLS
        restockFives = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        restockTens = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        restockTwenty = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        restockFifty = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));

//        accountRequestsList = new JList();
//        userRequestsList = new JList();
//        recentTrans = new JList();
//
//        accountRequestsList.setSelectedIndex(0);
//        userRequestsList.setSelectedIndex(0);
//        recentTrans.setSelectedIndex(0);
    }

}