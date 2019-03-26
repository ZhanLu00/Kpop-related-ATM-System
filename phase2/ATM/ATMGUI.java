package ATM;

import ATM.Users.BankManager;
import ATM.Users.Client;
import ATM.Users.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;

public class ATMGUI {

    NumberFormat df = NumberFormat.getCurrencyInstance(); //currency format

    Atm atm = new Atm("phase1/ATM/BankUsers.txt","phase1/ATM/BankAccounts.txt",
            "phase1/ATM/AtmInfo.txt", "phase1/ATM/alerts.txt");


    /**
     * initialize the form (do not modify this code)
     */
    private JPanel root;
    private JButton newUser;
    private JPanel welcomePage;
    private JPanel newUserPage;
    private JButton returningUser;
    private JLabel welcomeText;
    private JPanel returningUserPage;
    private JTextField usernameText;
    private JPasswordField passwordText;
    private JButton loginButton;
    private JLabel enterUsername;
    private JButton returnToTheMainButton;
    private JLabel enterPassword;
    private JButton createUser;


    public ATMGUI() throws IOException {

        newUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomePage.setVisible(false);
                newUserPage.setVisible(true);
            }
        });
        returningUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomePage.setVisible(false);
                returningUserPage.setVisible(true);
            }
        });
        returnToTheMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returningUserPage.setVisible(false);
                welcomePage.setVisible(true);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = atm.getUser(usernameText.getText(), passwordText.getText());
                if (user instanceof BankManager){
                    // TODO
                }else if (user instanceof Client){
                    // TODO
                }else{
                    JOptionPane.showMessageDialog(null, "Incorrect username/password. " +
                            "Please try again.");
                }
            }
        });
    }

    public static void main(String[] args) throws IOException {
        final ATMGUI atm = new ATMGUI();
        JFrame frame = new JFrame("ATM");
        frame.setResizable(false);
        frame.setSize(500, 500);
        frame.setContentPane(atm.root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        atm.welcomePage.setVisible(true);
    }
}
