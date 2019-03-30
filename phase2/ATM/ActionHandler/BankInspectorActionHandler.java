package ATM.ActionHandler;

import ATM.Atm;
import ATM.BankAccounts.BankAccount;
import ATM.Managers.TimeManager;
import ATM.Transaction;
import ATM.Users.BankInspector;
import ATM.Users.Client;

import java.io.*;
import java.util.ArrayList;

public class BankInspectorActionHandler extends ClientActionHandler {
    private BankInspector bankInspector;
    private Atm atm;
    private BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));

    private Client selectedClient;

    private String messageFile;

    public BankInspectorActionHandler(BankInspector bankInspector, Atm atm, String messageFile) {
        super(bankInspector,atm);
        this.bankInspector = bankInspector;
        this.atm = atm;
        this.messageFile = messageFile;
    }

    public void selectClient(String clientName) {
        selectedClient = (Client) atm.getUserManager().getUser(clientName);
    }

    public ArrayList<String> getClientAccountSummary() {
        if (selectedClient == null) {
            return null;
        }

        ArrayList<String> result = new ArrayList<>();
        for (Integer accountNumber : selectedClient.getAccounts()) {
            BankAccount account = atm.getAccountManager().getAccount(accountNumber);

            result.add(String.format("Type: %s, Balance: %f, Date Created: %s", BankAccount.getAccountType(account), account.getBalance(), TimeManager.dateToString(account.getDATE_CREATED())));
        }

        return result;
    }

    public ArrayList<String> getClientOutgoingTransactions() {
        if (selectedClient == null) {
            return null;
        }

        ArrayList<String> result = new ArrayList<>();
        for (Integer account : selectedClient.getAccounts()) {
            for (Transaction transaction : atm.getTransactionManager().getTransactionsBySender(account)) {
                result.add("Outgoing: " + transaction.toString());
            }
        }

        return result;
    }

    public ArrayList<String> getClientIncomingTransactions() {
        if (selectedClient == null) {
            return null;
        }

        ArrayList<String> result = new ArrayList<>();
        for (Integer account : selectedClient.getAccounts()) {
            for (Transaction transaction : atm.getTransactionManager().getTransactionsByReceiver(account)) {
                result.add("Incoming: " + transaction.toString());
            }
        }
        return result;
    }

    public ArrayList<String> getAllTransactions() {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> incoming = getClientIncomingTransactions();
        ArrayList<String> outgoing = getClientOutgoingTransactions();

        if (incoming != null) {
            result.addAll(incoming);
        }

        if (outgoing != null) {
            result.addAll(outgoing);
        }

        return result;
    }

    public void sendMessageToManager(String alert) throws IOException {
        File file = new File(messageFile);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String message;
        StringBuilder fileOut = new StringBuilder();
        while((message = br.readLine()) != null){
            fileOut.append(message);
        }
        br.close();

        fileOut.append(alert).append("\n");
        BufferedWriter writer = new BufferedWriter(new FileWriter(messageFile));
        writer.write(fileOut.toString());

        writer.close();
    }

}
