package ATM;

import ATM.Managers.AccountManager;
import ATM.Managers.BillManager;
import ATM.Users.Client;
import ATM.ActionHandler.ClientActionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Map;

public class ATMGUI {

    NumberFormat df = NumberFormat.getCurrencyInstance(); //currency format

    // FIXME ahhhh
    public AccountManager accountManager;
    public BillManager billManager;
    public Client client;

    // FIXME what's messages file?
    Atm atm = new Atm("phase2/ATM/testfiles/BankUsers.txt","phase2/ATM/testfiles/BankAccounts.txt",
            "phase2/ATM/testfiles/AtmInfo.txt", "phase2/ATM/testfiles/alerts.txt", "phase2/ATM/testfiles", "");


    /**
     * initialize the form (do not modify this code)
     */
    //panels
    public JPanel root;

    // WELCOME PAGE
    public JPanel welcomePage;
    public JButton newUser;
    public JButton returningUser;
    public JLabel welcomeText;


    // TODO NEW USER PAGE
    public JPanel newUserPage;
    public JComboBox accTypes;
    public JButton goBackNew;
    public JFormattedTextField userPhone;
    public JButton accRequestButton;

    // RETURNING USER PAGE
    public JPanel returningUserPage;
    public JLabel enterUsername;
    public JLabel enterPassword;
    public JTextField usernameText;
    public JPasswordField passwordText;
    public JButton loginButton;
    public JButton goBackReturn;

    // CLIENT OPTIONS
    public JPanel clientOptions;
    public JLabel clientAsk;
    public JButton viewAccountSummaryButton;
    public JButton withdrawMoneyButton;
    public JButton transferMoneyButton;
    public JButton payABillButton;
    public JButton depositMoneyButton;
    public JButton goBackClient;
    public JButton changePasswordButton;

    // MANAGER OPTIONS
    public JPanel managerOptions;
    public JLabel managerAsk;
    public JButton createNewClientButton;
    public JButton undoTransactionButton;
    public JButton restockMachineButton;
    public JButton viewAccountCreationRequestsButton;
    public JButton showAlertsButton;
    public JButton logOutManager;

    // DEPOSIT OPTION
    public JPanel depositOption;
    public JLabel numFiveLabel;
    public JLabel numTenLabel;
    public JLabel numTwentyLabel;
    public JLabel depositAmtLabel;
    public JLabel numFiftyLabel;
    public JLabel depositInAccLabel;
    public JSpinner numFives;
    public JSpinner numTens;
    public JSpinner numTwenty;
    public JSpinner numFifty;
    public JFormattedTextField depositAccNum;
    public JButton depositButton;
    public JButton goBackDeposit;

    // TODO SUMMARY OF ACCOUNTS OPTION
    public JPanel summaryOfAccounts;
    public JLabel summaryText;
    public JTextArea accountSummaries;
    public JButton goBackSummary;
    public JButton makeANewAccountButton;
    public JTextArea mostRecentTransaction;

    // TRANSFER OPTION
    public JPanel transferOption;
    public JButton goBackTransfer;
    public JFormattedTextField transOutAccNum;
    public JFormattedTextField transInAccNum;
    public JFormattedTextField transAmt;
    public JButton transferButton;
    public JLabel transOutLabel;
    public JLabel transInLabel;
    public JLabel transAmtLabel;

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


    // TODO CHANGE PASS OPTION
    public JPanel changePassword;
    public JPasswordField newPassword;
    public JButton goBackPassword;
    public JButton changePswd;

    // INSPECTOR OPTIONS
    public JPanel inspectorOptions;
    public JLabel inspectorAsk;
    public JButton sendMessageToManagerButton;
    public JButton seeAllTransactionsButton;
    public JButton checkClientSAccountButton;
    public JButton logOutInspector;

    // SEND MANAGER MESSAGE OPTION
    public JPanel sendManagerMsg;
    public JButton goBackMsg;
    public JButton sendMessageButton;
    public JTextArea inspectorMsg;

    // INSPECTOR CLIENT SUMMARY
    public JPanel clientSummary;
    public JPanel allTransactions;
    public JFormattedTextField clientUsername;
    public JButton seeClientAccountSummaryButton;
    public JButton seeClientIncomingTransactionsButton;
    public JButton seeClientOutgoingTransactionsButton;
    public JTextArea clientSummaryOrTransaction;
    public JButton goBackClientSummary;


    public void changePage(JPanel currentPage, JPanel newPage){
        currentPage.setVisible(false);
        newPage.setVisible(true);
    }


    public ATMGUI() throws IOException {

        // TODO separate methods into helper methods to avoid long method code smell?

        goBackClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(clientOptions, welcomePage);
            }
        });
        logOutManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(managerOptions, welcomePage);
            }
        });
        depositMoneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(clientOptions, depositOption);
            }
        });
        // only allows number input
        // TODO prevent pasted text from allowing text?
        depositAccNum.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))){
                    e.consume();
                }
            }
        });
        // TODO -- does this work if acc num is correct
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object accountNum = depositAccNum.getValue();
                if (client.accountNumbers.contains(accountNum)){
                    int fives = (int) numFives.getValue();
                    int tens = (int) numTens.getValue();
                    int twenties = (int) numTwenty.getValue();
                    int fifties = (int) numFifty.getValue();
                    accountManager.getAccount((int)accountNum).deposit(fives*5+tens*10+twenties*20+fifties*50);
                    billManager.deposit(fives, tens, twenties, fifties);
                    JOptionPane.showMessageDialog(null, "Deposit complete, here is your new " +
                            "balance: " + accountManager.getAccount((int)accountNum).getBalance());
                }else{
                    JOptionPane.showMessageDialog(null, "Account number does not exist. " +
                            "Please try again.");
                    depositAccNum.setText("");
                }

            }
        });
        goBackDeposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(accCreation, clientOptions);
            }
        });
        goBackTransfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(transferOption, clientOptions);
            }
        });
        goBackBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(payBill, clientOptions);
            }
        });
    }

    public void init()throws IOException{
        final ATMGUI atm = new ATMGUI();
        JFrame frame = new JFrame("ATM");
        frame.setResizable(false);
        frame.setLocation(500,300);
        frame.setSize(600, 500);
        frame.setContentPane(atm.root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        atm.welcomePage.setVisible(true);
    }

    private void createUIComponents() {
        numFives = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        numTens = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        numTwenty = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        numFifty = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));


        // TODO put this stuff in the button action for "view acc summary"
        StringBuilder accountSummaries = new StringBuilder();
        Map accountBalance = ClientActionHandler.checkBalance();
        for (Object accountNumber:accountBalance.keySet()){
            accountSummaries.append(accountNumber+": "+ accountBalance.get(accountNumber) + "\n");
        }
        summaryText.setText(String.valueOf(accountSummaries));
    }
}
