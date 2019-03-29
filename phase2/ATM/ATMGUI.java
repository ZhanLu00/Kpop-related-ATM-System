package ATM;

import ATM.Users.BankManager;
import ATM.Users.Client;
import ATM.ActionHandler.ClientActionHandler;
import ATM.Users.User;

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
    Atm atm = new Atm("phase2/ATM/BankUsers.txt","phase2/ATM/BankAccounts.txt",
            "phase2/ATM/AtmInfo.txt", "phase2/ATM/alerts.txt", "phase2/", "");


    /**
     * initialize the form (do not modify this code)
     */
    //panels
    public JPanel root;
    public JPanel welcomePage;
    public JPanel newUserPage;
    public JPanel returningUserPage;
    public JPanel clientOptions;
    public JPanel managerOptions;
    public JPanel depositOption;
    public JPanel summaryOfAccounts;
    public JPanel accCreation;
    public JPanel transferOption;
    public JPanel payBill;
    public JPanel recentTransaction;
    public JPanel netTotal;
    public JPanel withdrawOption;

    public JButton newUser;

    public JButton returningUser;
    public JLabel welcomeText;

    public JTextField usernameText;
    public JPasswordField passwordText;
    public JButton loginButton;
    public JLabel enterUsername;
    public JButton returnToTheMainButton;
    public JLabel enterPassword;
    public JButton createUser;
    public JButton viewSummaryOfAllButton;
    public JButton viewAccountCreationDateButton;
    public JButton withdrawMoneyButton;
    public JButton transferMoneyButton;
    public JButton payABillButton;
    public JButton depositMoneyButton;
    public JButton viewMostRecentTransactionButton;
    public JButton checkNetTotalButton;
    public JButton returnToTheMainButton1;
    public JButton createNewClientButton;
    public JButton undoTransactionButton;
    public JButton setTimeButton;
    public JButton clearAlertsButton;
    public JButton restockMachineButton;
    public JButton viewAccountCreationRequestsButton;
    public JButton showAlertsButton;
    public JButton returnToTheMainButton2;
    public JSpinner numFives;
    public JSpinner numTens;
    public JSpinner numTwenty;
    public JSpinner numFifty;
    public JFormattedTextField depositAccNum;
    public JButton depositButton;
    public JLabel summaryText;
    public JTextArea accountSummaries;

    public JFormattedTextField accNum;
    public JButton checkAccountCreationDateButton;
    public JButton goBackButton;
    public JFormattedTextField accCreationDate;
    public JLabel enterAccNumDate;
    public JButton goBackButton1;
    public JFormattedTextField transOutAccNum;
    public JFormattedTextField transInAccNum;
    public JFormattedTextField transAmt;
    public JButton transferButton;
    public JFormattedTextField billAccNum;
    public JFormattedTextField billAmt;
    public JFormattedTextField billPayee;
    public JButton goBackButton2;
    public JButton payBillButton;
    public JTextField depositAccount;

    public void changePage(JPanel currentPage, JPanel newPage){
        currentPage.setVisible(false);
        newPage.setVisible(true);
    }


    public ATMGUI() throws IOException {

        // TODO separate methods into helper methods to avoid long method code smell?

        newUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(welcomePage, newUserPage);
            }
        });
        returningUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(welcomePage, returningUserPage);
            }
        });
        returnToTheMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(returningUserPage, welcomePage);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = atm.getUser(usernameText.getText(), passwordText.getText());
                if (user instanceof BankManager){
                    changePage(returningUserPage, managerOptions);
                }else if (user instanceof Client){
                    changePage(returningUserPage, clientOptions);
                    client = new Client(usernameText.getText(), passwordText.getText());
                }else{
                    usernameText.setText("");
                    passwordText.setText("");
                    JOptionPane.showMessageDialog(null, "Incorrect username/password. " +
                            "Please try again.");
                }
            }
        });
        returnToTheMainButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(clientOptions, welcomePage);
            }
        });
        returnToTheMainButton2.addActionListener(new ActionListener() {
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
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(accCreation, clientOptions);
            }
        });
        goBackButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(transferOption, clientOptions);
            }
        });
        goBackButton2.addActionListener(new ActionListener() {
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
//        atm.welcomePage.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        final ATMGUI atm = new ATMGUI();
        JFrame frame = new JFrame("ATM");
        frame.setResizable(false);
        frame.setLocation(500,300);
        frame.setSize(600, 500);
        frame.setContentPane(atm.root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.pack();
        frame.setVisible(true);
//        atm.welcomePage.setVisible(true);
    }

    private void createUIComponents() {
        numFives = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        numTens = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        numTwenty = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        numFifty = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));

        StringBuilder accountSummaries = new StringBuilder();
        Map accountBalance = ClientActionHandler.checkBalance();
        for (Object accountNumber:accountBalance.keySet()){
            accountSummaries.append(accountNumber+": "+ accountBalance.get(accountNumber) + "\n");
        }
        summaryText.setText(String.valueOf(accountSummaries));
    }
}
