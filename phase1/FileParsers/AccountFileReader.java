package src.FileParsers;

import src.ATM.BankAccounts.AssetAccounts.ChequingAccount;
import src.ATM.BankAccounts.AssetAccounts.SavingsAccount;
import src.ATM.BankAccounts.BankAccount;
import src.ATM.BankAccounts.DebtAccounts.CreditCardsAccount;
import src.ATM.BankAccounts.DebtAccounts.LineOfCreditAccount;
import src.ATM.TimeManager;
import src.ATM.Transaction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AccountFileReader {
    private ArrayList<BankAccount> accounts;

    public AccountFileReader(String fileName) throws IOException {

        accounts = new ArrayList<>();

        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String accountInfo;
        while((accountInfo = br.readLine()) != null){
            createAccount(accountInfo);
        }
    }

    private void createAccount(String accountInfo) {
        String[] separated = accountInfo.split(",");
        String type = separated[0].replace(",","");
        double balance = Double.parseDouble(separated[1].replace(",",""));
        Date date = TimeManager.dateFromString(separated[2].replace(",",""));


        Transaction transaction;
        if (separated.length < 4) {
            transaction = null;
        }
        else {
            Date transactionDate = TimeManager.dateFromString(separated[3].replace(",",""));
            int sender = Integer.parseInt(separated[4].replace(",",""));
            int receiver = Integer.parseInt(separated[5].replace(",",""));
            double amount = Double.parseDouble(separated[6].replace(",",""));
            transaction = new Transaction(amount, sender, receiver, transactionDate);
        }

        if (type.equals(BankAccount.CHEQUING)) {
            accounts.add(new ChequingAccount(date,balance,transaction));
        }
        else if (type.equals(BankAccount.SAVINGS)) {
            accounts.add(new SavingsAccount(date,balance,transaction));
        }
        else if (type.equals(BankAccount.CREDIT_CARD)) {
            accounts.add(new CreditCardsAccount(date,balance,transaction));
        }
        else if (type.equals(BankAccount.LINE_OF_CREDIT)) {
            accounts.add(new LineOfCreditAccount(date,balance,transaction));
        }

    }

    public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }
}
