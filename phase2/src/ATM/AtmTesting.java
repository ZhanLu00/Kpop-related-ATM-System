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

    public static void main(String[] args) throws IOException{
        String userFileName = "/ATM/testfiles/userfiles.txt";
        String accountFileName = "/testfiles/accounts.txt";
        String atmFileName = "/testfiles/Atminfo.txt";
        String alertsFileName = "/testfiles/alerts.txt";
        String transactionsFileName = "/testfiles/transactions.txt";
        String messagesFileName = "/testfiles/messages.txt";
        String accountRequestFileName = "/testfiles/accountRequests.txt";
        String clientRequestFileName = "/testfiles/clientRequest.txt";

        Atm atm = new Atm (userFileName,  accountFileName, atmFileName, alertsFileName, transactionsFileName,
                messagesFileName, accountRequestFileName, clientRequestFileName);
        ATMGUI gui = new ATMGUI();
        ActionHandler actionHandler = new ActionHandler(atm, gui);
        actionHandler.initViewer();
        actionHandler.initOperator();
    }

    public void loadData(String fileName){

    }




}
