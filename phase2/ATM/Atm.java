package ATM;
import ATM.Users.User;
import FileParsers.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Atm {
    private UserManager userManager;
    private TimeManager timeManager;
    private AccountManager accountManager;
    private BillManager billManager;
    private TransactionManager transactionManager;

    private String userFileName, accountFileName, atmFileName, alertsFileName, transactionsFileName, messagesFileName;

    public Atm (String userFileName,  String accountFileName, String atmFileName, String alertsFileName, String transactionsFileName, String messagesFileName) throws IOException {
        UserFileReader userFileReader = new UserFileReader(userFileName);
        AccountFileReader accountFileReader = new AccountFileReader(accountFileName);
        AtmFileReader atmFileReader = new AtmFileReader(atmFileName);
        TransactionFileReader transactionFileReader = new TransactionFileReader(transactionsFileName);

        transactionFileReader.read();
        atmFileReader.read();

        this.timeManager = new TimeManager(atmFileReader.getDate(), true);
        Date date = timeManager.getDate();
        this.userManager = new UserManager(userFileReader.getUsers(), date);
        this.accountManager = new AccountManager(accountFileReader.getAccounts(), date);
        this.billManager = new BillManager(atmFileReader.getFives(), atmFileReader.getTens(), atmFileReader.getTwenties(), atmFileReader.getFifties(),alertsFileName);

        for (String[] accountCreationRequest : atmFileReader.getAccountCreationRequests()) {
            accountManager.requestNewAccount(accountCreationRequest[0],accountCreationRequest[1]);
        }

        if (date.getDate() == 1) {
            accountManager.updateInterestAccounts();
        }

        this.transactionManager = new TransactionManager(transactionFileReader.getTransactions());

        this.userFileName = userFileName;
        this.accountFileName = accountFileName;
        this.atmFileName = atmFileName;
        this.alertsFileName = alertsFileName;
        this.transactionsFileName = transactionsFileName;
        this.messagesFileName = messagesFileName;
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

    public String getAlertsFileName() {
        return alertsFileName;
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

    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    public ArrayList<String> readAlerts() throws IOException {
        File file = new File(alertsFileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        ArrayList<String> alerts = new ArrayList<>();

        String line;
        while((line = br.readLine()) != null) {
            alerts.add(line);
        }
        return alerts;
    }

    public void clearAlerts() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(alertsFileName));
        writer.write("");
        writer.close();
    }

    public void save() throws IOException {
        AccountFileWriter accountFileWriter = new AccountFileWriter(accountFileName,accountManager.getAccounts());
        UserFileWriter userFileWriter = new UserFileWriter(userFileName, userManager.getUsers());
        AtmFileWriter atmFileWriter = new AtmFileWriter(atmFileName, timeManager.getDate(), billManager, accountManager);
        TransactionFileWriter transactionFileWriter = new TransactionFileWriter(transactionManager.getTransactions(), transactionsFileName);

        accountFileWriter.write();
        userFileWriter.write();
        atmFileWriter.write();
        transactionFileWriter.write();
    }
}
