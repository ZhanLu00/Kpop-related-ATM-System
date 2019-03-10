package FileParsers;

import ATM.BankAccounts.AssetAccounts.ChequingAccount;
import ATM.BankAccounts.AssetAccounts.SavingsAccount;
import ATM.BankAccounts.BankAccount;
import ATM.BankAccounts.DebtAccounts.CreditCardsAccount;
import ATM.BankAccounts.DebtAccounts.LineOfCreditAccount;
import ATM.TimeManager;

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
            String type = getAccountType(account);
            double balance = account.getBalance();
            Date date = account.getDATE_CREATED();
            String dateString = TimeManager.dateToString(date);
            fileOut.append(String.format("%s,%f,%s\n",type,balence,dateString));
        }
        writer.append(fileOut);
        writer.close();
    }

    private String getAccountType(BankAccount account) {
        if (account instanceof ChequingAccount) {
            return BankAccount.CHEQUING;
        }
        else if (account instanceof SavingsAccount) {
            return BankAccount.SAVINGS;
        }
        else if (account instanceof CreditCardsAccount) {
            return BankAccount.CREDIT_CARD;
        }
        else if (account instanceof LineOfCreditAccount) {
            return BankAccount.LINE_OF_CREDIT;
        }
        return "account";
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