package ATM;

import ATM.Users.BankManager;
import ATM.Users.Client;
import ATM.Users.ClientActionHandler;
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
    private AccountManager accountManager;
    private BillManager billManager;
    private Client client;


    Atm atm = new Atm("phase1/ATM/BankUsers.txt","phase1/ATM/BankAccounts.txt",
            "phase1/ATM/AtmInfo.txt", "phase1/ATM/alerts.txt");


    /**
     * initialize the form (do not modify this code)
     */
    //panels
    private JPanel root;
    private JPanel welcomePage;
    private JPanel newUserPage;
    private JPanel returningUserPage;
    private JPanel clientOptions;
    private JPanel managerOptions;
    private JPanel depositOption;
    private JPanel summaryOfAccounts;
    private JPanel accCreation;
    private JPanel transferOption;
    private JPanel payBill;
    private JPanel recentTransaction;
    private JPanel netTotal;
    private JPanel withdrawOption;

    private JButton newUser;

    private JButton returningUser;
    private JLabel welcomeText;

    private JTextField usernameText;
    private JPasswordField passwordText;
    private JButton loginButton;
    private JLabel enterUsername;
    private JButton returnToTheMainButton;
    private JLabel enterPassword;
    private JButton createUser;
    private JButton viewSummaryOfAllButton;
    private JButton viewAccountCreationDateButton;
    private JButton withdrawMoneyButton;
    private JButton transferMoneyButton;
    private JButton payABillButton;
    private JButton depositMoneyButton;
    private JButton viewMostRecentTransactionButton;
    private JButton checkNetTotalButton;
    private JButton returnToTheMainButton1;
    private JButton createNewClientButton;
    private JButton undoTransactionButton;
    private JButton setTimeButton;
    private JButton clearAlertsButton;
    private JButton restockMachineButton;
    private JButton viewAccountCreationRequestsButton;
    private JButton showAlertsButton;
    private JButton returnToTheMainButton2;
    private JSpinner numFives;
    private JSpinner numTens;
    private JSpinner numTwenty;
    private JSpinner numFifty;
    private JFormattedTextField depositAccNum;
    private JButton depositButton;
    private JLabel summaryText;
    private JTextArea accountSummaries;

    private JFormattedTextField accNum;
    private JButton checkAccountCreationDateButton;
    private JButton goBackButton;
    private JFormattedTextField accCreationDate;
    private JLabel enterAccNumDate;
    private JButton goBackButton1;
    private JFormattedTextField formattedTextField1;
    private JFormattedTextField formattedTextField2;
    private JFormattedTextField formattedTextField3;
    private JButton transferButton;
    private JTextField depositAccount;

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
        returningUser.addActionListener(new ActionListener() {
        });
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
        atm.welcomePage.setVisible(true);
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
