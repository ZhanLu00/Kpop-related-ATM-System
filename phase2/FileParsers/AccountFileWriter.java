package FileParsers;

import ATM.BankAccounts.AssetAccounts.ChequingAccount;
import ATM.BankAccounts.AssetAccounts.SavingsAccount;
import ATM.BankAccounts.BankAccount;
import ATM.BankAccounts.DebtAccounts.CreditCardsAccount;
import ATM.BankAccounts.DebtAccounts.DebtAccount;
import ATM.BankAccounts.DebtAccounts.LineOfCreditAccount;
import ATM.TimeManager;
import ATM.Transaction;

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
            String type = BankAccount.getAccountType(account);

            double balance;
            if (account instanceof DebtAccount) {
                balance = -account.getBalance();
            }
            else {
                balance = account.getBalance();
            }

            Date date = account.getDATE_CREATED();
            String dateString = TimeManager.dateToString(date);

            fileOut.append(String.format("%s,%f,%s\n",type,balance,dateString));

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