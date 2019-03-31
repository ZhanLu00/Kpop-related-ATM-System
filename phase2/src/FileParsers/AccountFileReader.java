package FileParsers;

import ATM.BankAccounts.AssetAccounts.ChequingAccount;
import ATM.BankAccounts.AssetAccounts.SavingsAccount;
import ATM.BankAccounts.BankAccount;
import ATM.BankAccounts.DebtAccounts.CreditCardsAccount;
import ATM.BankAccounts.DebtAccounts.LineOfCreditAccount;
import ATM.BankAccounts.ExtraAccounts.ForeignCurrencyAccount;
import ATM.BankAccounts.ExtraAccounts.LotteryAccount;
import ATM.Managers.TimeManager;

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


        if (type.equals("CHEQUING_ACCOUNT")) {
            accounts.add(new ChequingAccount(date,balance));
        }
        else if (type.equals("SAVINGS_ACCOUNT")) {
            accounts.add(new SavingsAccount(date,balance));
        }
        else if (type.equals("CREDIT_CARD_ACCOUNT")) {
            accounts.add(new CreditCardsAccount(date,balance));
        }
        else if (type.equals("LINE_OF_CREDIT_ACCOUNT")) {
            accounts.add(new LineOfCreditAccount(date,balance));
        }
        else if (type.equals("LOTTERY_ACCOUNT")) {
            accounts.add(new LotteryAccount(date,balance));
        }
        else if (type.equals("FOREIGN_CURRENCY_ACCOUNT")) {
            double oldExchangeRate = Double.parseDouble(separated[4].replace(",",""));
            String currencyType = separated[3].replace(",","");
            accounts.add(new ForeignCurrencyAccount(date, balance*oldExchangeRate, currencyType, oldExchangeRate));
        }

    }

    public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }
}
