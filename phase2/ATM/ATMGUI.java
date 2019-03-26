package ATM;

import javax.swing.*;
import java.awt.*;
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
    private JPanel newUserPage;
    private JPanel returningUserPage;

    public ATMGUI() {
        $$$setupUI$$$();
        newUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomePage.setVisible(false);
                welcomePage.add(newUserPage);
                // JOptionPane.showMessageDialog(null, "you pressed the new user button");
                newUserPage.setVisible(true);
            }
        });
        returningUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomePage.setVisible(false);
                // JOptionPane.showMessageDialog(null, "you pressed the new returning button");
                returningUserPage.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ATM");
        frame.setResizable(false);
        frame.setSize(500, 500);
        frame.setContentPane(new ATMGUI().welcomePage);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        welcomePage = new JPanel();
        newUserPage = new JPanel();
        returningUserPage = new JPanel();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        welcomePage.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        welcomePage.setBackground(new Color(-6898203));
        returningUserButton = new JButton();
        returningUserButton.setText("Returning User");
        welcomePage.add(returningUserButton, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        newUserButton = new JButton();
        newUserButton.setText("New User");
        welcomePage.add(newUserButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        welcomeText = new JLabel();
        Font welcomeTextFont = this.$$$getFont$$$("Comic Sans MS", -1, 36, welcomeText.getFont());
        if (welcomeTextFont != null) welcomeText.setFont(welcomeTextFont);
        welcomeText.setForeground(new Color(-16777216));
        welcomeText.setHorizontalAlignment(0);
        welcomeText.setHorizontalTextPosition(0);
        welcomeText.setText("Welcome to --Bank Name--");
        welcomePage.add(welcomeText, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        newOrReturningUser = new JLabel();
        Font newOrReturningUserFont = this.$$$getFont$$$("Comic Sans MS", -1, 26, newOrReturningUser.getFont());
        if (newOrReturningUserFont != null) newOrReturningUser.setFont(newOrReturningUserFont);
        newOrReturningUser.setForeground(new Color(-16777216));
        newOrReturningUser.setText("Are you a new or returning user?");
        welcomePage.add(newOrReturningUser, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return welcomePage;
    }
}
