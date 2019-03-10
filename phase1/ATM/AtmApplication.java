package ATM;

import ATM.Users.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AtmApplication {
    private static BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
//        Atm atm = new Atm("BankUsers.txt", "BankAccounts.txt", "AtmInfo.txt");
//        atm.printText();
//        atm.save();

        Atm atm = new Atm("phase1/ATM/BankUsers.txt","phase1/ATM/BankAccounts.txt","phase1/ATM/AtmInfo.txt", "phase1/ATM/alerts.txt");

        while(true) {
            String username = getStringFromUser("Username (-1 to exit): ");

            if (username.equals("-1")) {
                break;
            }

            String password = getStringFromUser("Password: ");

            User user = atm.getUser(username, password);

            if (user instanceof BankManager) {
                BankManagerActionHandler actionHandler = new BankManagerActionHandler((BankManager) user, atm);
                actionHandler.displayCommandLineInterface();
            } else if (user instanceof  Client) {
                ClientActionHandler actionHandler = new ClientActionHandler((Client)user, atm.getAccountManager(), atm.getBillManager());
                actionHandler.displayCommandLineInterface();
            }
        }

        atm.save();
    }

    private static int getIntFromUser(String display) throws IOException {
        System.out.print(display);
        return Integer.parseInt(kbd.readLine());
    }

    private static String getStringFromUser(String display) throws IOException {
        System.out.print(display);
        return kbd.readLine();
    }


}
