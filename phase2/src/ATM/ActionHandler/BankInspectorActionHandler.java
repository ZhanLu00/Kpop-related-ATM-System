package ATM.ActionHandler;

import ATM.Atm;
import ATM.BankAccounts.BankAccount;
import ATM.Managers.TimeManager;
import ATM.Transaction;
import ATM.Users.BankInspector;
import ATM.Users.Client;

import java.io.*;
import java.util.ArrayList;

public class BankInspectorActionHandler {
    private BankInspector bankInspector;
    private Atm atm;
    private BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));

    private Client selectedClient;

    private String messageFile;

    public BankInspectorActionHandler(BankInspector bankInspector, Atm atm, String messageFile) {
        this.bankInspector = bankInspector;
        this.atm = atm;
        this.messageFile = messageFile;
    }

    public void selectClient(String clientName) {
        selectedClient = (Client) atm.getUserManager().getUser(clientName);
    }

    /**
     * Returns an formatted String ArrayList of accounts selectedClient owns.
     */
    public ArrayList<String> getClientAccountSummary() {
        if (selectedClient == null) {
            return null;
        }

        ArrayList<String> result = new ArrayList<>();
        for (Integer accountNumber : selectedClient.getAccounts()) {
            BankAccount account = atm.getAccountManager().getAccount(accountNumber);

            result.add(String.format("Type: %s, Balance: %f, Date Created: %s", account.getType(),
                    account.getBalance(), TimeManager.dateToString(account.getDateCreated())));
        }

        return result;
    }

    /**
     * Returns an String ArrayList of all the transactions done with selectedClient as the sender.
     */
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

    /**
     * Returns a String arrayList of all the transactions done with selectedClient as the receiver
     */
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

    /**
     * Returns a String ArrayList of all the transactions done with selectedClient
     */
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

    /**
     * Sends a message to the manager.
     */
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
