package ATM;


import ATM.ActionHandler.ActionHandler;
import ATM.Managers.*;
import ATM.ATMGUI;
import ATM.BankAccounts.BankAccount;
import ATM.Users.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AtmTesting {

    // viewer
    public static ATMGUI atmgui = new ATMGUI();

    //TODO: txt file for currency rates
    private ArrayList<Object[]> rates = new ArrayList<>();
    private CurrencyManager currencyManager = new CurrencyManager(rates);

    // model
    private AccountManager accountManager = new AccountManager(new ArrayList<BankAccount>(), new ArrayList<String[]>(),
            currencyManager, new Date());

    private TransactionManager transactionManager = new TransactionManager();

    private UserManager userManager = new UserManager(new ArrayList<User>(), new ArrayList<String>(), new Date());

    private BillManager billManager = new BillManager();



    static String userFileName = "/testfiles/userfiles.txt";
    static String accountFileName = "/testfiles/accounts.txt";
    static String atmFileName = "/testfiles/Atminfo.txt";
    static String alertsFileName = "/testfiles/alerts.txt";
    static String transactionsFileName = "/testfiles/transactions.txt";
    static String messagesFileName = "/testfiles/messages.txt";
    static String accountRequestFileName = "/testfiles/accountRequests.txt";
    static String clientRequestFileName = "/testfiles/clientRequest.txt";


    // controller
    static public ActionHandler actionHandler = new ActionHandler(atmgui);

    public static void main(String[] args) throws IOException {
        Atm atm = new Atm (userFileName,  accountFileName, atmFileName, alertsFileName, transactionsFileName,
                messagesFileName, accountRequestFileName, clientRequestFileName);
        actionHandler.initViewer();
        actionHandler.initOperator();
    }

    public void loadData(String fileName){

    }




}
