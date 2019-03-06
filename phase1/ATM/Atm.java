package ATM;
import ATM.Users.BankManager;
import ATM.Users.User;
import FileParsers.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

public class Atm {
    private UserManager userManager;
    private TimeManager timeManager;
    private AccountManager accountManager;

    private String userFileName, accountFileName, atmFileName;

    public Atm (String userFileName,  String accountFileName, String atmFileName) throws IOException {
        UserFileReader userFileReader = new UserFileReader(userFileName);
        AccountFileReader accountFileReader = new AccountFileReader(accountFileName);
        AtmFileReader atmFileReader = new AtmFileReader(atmFileName);
        atmFileReader.read();

        this.userManager = new UserManager(userFileReader.getUsers());
        this.accountManager = new AccountManager(accountFileReader.getAccounts());
        this.timeManager = new TimeManager(atmFileReader.getDate(), true);

        this.userFileName = userFileName;
        this.accountFileName = accountFileName;
        this.atmFileName = atmFileName;
    }
    public User getUser(String username, String password){
        return userManager.getUser(username, password);
    }

    public TimeManager getTimeManager(){
        return this.timeManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void printText() throws IOException {

        BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter username: ");
        String username = kbd.readLine();
        System.out.println("Enter password: ");
        String password = kbd.readLine();
        User user = getUser(username, password);
        if (user != null) {
//            user.printText(this);
        } else{
            System.out.println("Incorrect username or password.");
        }
        printText();
    }

    public void save() throws IOException {
        AccountFileWriter accountFileWriter = new AccountFileWriter(accountFileName,accountManager.getAccounts());
        UserFileWriter userFileWriter = new UserFileWriter(userFileName, userManager.getUsers());
        AtmFileWriter atmFileWriter = new AtmFileWriter(atmFileName, timeManager.getDate());

        accountFileWriter.write();
        userFileWriter.write();
        atmFileWriter.write();
    }
}
