package FileParsers;

import ATM.BankAccounts.AssetAccounts.ChequingAccount;
import ATM.BankAccounts.AssetAccounts.SavingsAccount;
import ATM.BankAccounts.BankAccount;
import ATM.BankAccounts.DebtAccounts.CreditCardsAccount;
import ATM.BankAccounts.DebtAccounts.LineOfCreditAccount;
import ATM.TimeManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AccountFileReader {
    public ArrayList<BankAccount> accounts;

    public AccountFileReader(String fileName) throws IOException {

        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String accountInfo;
        while((accountInfo = br.readLine()) != null){
            createAccount(accountInfo);
        }
    }

    private void createAccount(String accountInfo) {
        String[] seperated = accountInfo.split(",");
        String type = seperated[0].replace(",","");
        int balence = Integer.parseInt(seperated[1].replace(",",""));
        Date date = TimeManager.dateFromString(seperated[2].replace(",",""));

        if (type.equals("chequing")) {
            accounts.add(new ChequingAccount(null,date));
        }
        else if (type.equals("savings")) {
            accounts.add(new SavingsAccount(null,date));
        }
        else if (type.equals("creditcard")) {
            accounts.add(new CreditCardsAccount(null, date));
        }
        else if (type.equals("lineofcredit")) {
            accounts.add(new LineOfCreditAccount(null,date));
        }

    }
}
