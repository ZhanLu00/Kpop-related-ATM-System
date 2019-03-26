package ATM;

import ATM.Users.BankManager;
import ATM.Users.Client;
import ATM.Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;

public class ATMGUI {

    NumberFormat df = NumberFormat.getCurrencyInstance(); //currency format

    Atm atm = new Atm("phase1/ATM/BankUsers.txt","phase1/ATM/BankAccounts.txt",
            "phase1/ATM/AtmInfo.txt", "phase1/ATM/alerts.txt");


    private JPanel welcomePage;
    private JButton newUserButton;
    private JButton returningUserButton;
    private JLabel welcomeText;
    private JLabel newOrReturningUser;
    private JPanel panel1;
    private JPanel newUserPage;
    private JPanel returningUserPage;
    private JTextField username;
    private JPasswordField password;
    private JButton loginButton;
    private JLabel usernameText;
    private JLabel passwordText;


    public ATMGUI() throws IOException {
        newUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomePage.setVisible(false);
                newUserPage.setVisible(true);
            }
        });
        returningUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomePage.setVisible(false);
                returningUserPage.setVisible(true);
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
                }
            }
        });
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("ATM");
        frame.setResizable(false);
        frame.setSize(500, 500);
        frame.setContentPane(new ATMGUI().welcomePage);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
