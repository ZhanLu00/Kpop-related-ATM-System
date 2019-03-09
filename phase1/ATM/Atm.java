package ATM;
import ATM.Users.User;
import FileParsers.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class Atm {
    private UserManager userManager;
    private TimeManager timeManager;
    private AccountManager accountManager;
    private BillManager billManager;

    private String userFileName, accountFileName, atmFileName;

    public Atm (String userFileName,  String accountFileName, String atmFileName) throws IOException {
        UserFileReader userFileReader = new UserFileReader(userFileName);
        AccountFileReader accountFileReader = new AccountFileReader(accountFileName);
        AtmFileReader atmFileReader = new AtmFileReader(atmFileName);
        atmFileReader.read();

        this.timeManager = new TimeManager(atmFileReader.getDate(), true);
        Date date = timeManager.getDate();
        this.userManager = new UserManager(userFileReader.getUsers(), date);
        this.accountManager = new AccountManager(accountFileReader.getAccounts(), date);
        this.billManager = new BillManager(atmFileReader.getFives(), atmFileReader.getTens(), atmFileReader.getTwenties(), atmFileReader.getFifties());

        this.userFileName = userFileName;
        this.accountFileName = accountFileName;
        this.atmFileName = atmFileName;
    }

    public String getUserFileName() {
        return userFileName;
    }

    public String getAccountFileName() {
        return accountFileName;
    }

    public String getAtmFileName() {
        return atmFileName;
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

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public BillManager getBillManager() {
        return billManager;
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
        AtmFileWriter atmFileWriter = new AtmFileWriter(atmFileName, timeManager.getDate(), billManager, accountManager);

        accountFileWriter.write();
        userFileWriter.write();
        atmFileWriter.write();
    }
}
