package src.FileParsers;

import src.ATM.BankAccounts.BankAccount;
import src.ATM.BankAccounts.DebtAccounts.DebtAccount;
import src.ATM.BankAccounts.ExtraAccounts.ForeignCurrencyAccount;
import src.ATM.Managers.TimeManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AccountFileWriter {
    private String fileName;
    private ArrayList<BankAccount> accounts;

    public AccountFileWriter(String fileName, ArrayList<BankAccount> accounts) {
        this.fileName = fileName;
        this.accounts = accounts;
    }

    public void write() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write("");
        StringBuilder fileOut = new StringBuilder("");
        for (BankAccount account : accounts) {
            String type = account.getType();

            double balance;
            if (account instanceof DebtAccount) {
                balance = -account.getBalance();
            }
            else {
                balance = account.getBalance();
            }

            Date date = account.getDateCreated();
            String dateString = TimeManager.dateToString(date);

            if (account instanceof ForeignCurrencyAccount) {
                fileOut.append(String.format("%s,%f,%s,%f\n", type, balance, dateString, ((ForeignCurrencyAccount) account).getExchangeRate()));
            }
            else {
                fileOut.append(String.format("%s,%f,%s\n", type, balance, dateString));
            }

        }
        writer.append(fileOut);
        writer.close();
    }


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setAccounts(ArrayList<BankAccount> accounts) {
        this.accounts = accounts;
    }

    public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }

    public String getFileName() {
        return fileName;
    }
}