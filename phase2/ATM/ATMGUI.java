package ATM;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class ATMGUI {

    NumberFormat df = NumberFormat.getCurrencyInstance(); //currency format

    private JPanel welcomePage;
    private JButton newUserButton;
    private JButton returningUserButton;
    private JLabel welcomeText;
    private JLabel newOrReturningUser;

    public ATMGUI(){
        newUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomePage.setVisible(false);
                // JOptionPane.showMessageDialog(null, "you pressed the new user button");
            }
        });
        returningUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomePage.setVisible(false);
                // JOptionPane.showMessageDialog(null, "you pressed the new returning button");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ATM");
        frame.setResizable(false);
        frame.setSize(500,500);
        frame.setContentPane(new ATMGUI().welcomePage);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.pack();
        frame.setVisible(true);
    }
}
