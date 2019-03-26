package FileParsers;

import ATM.Transaction;

import java.io.*;
import java.util.ArrayList;

public class TransactionFileReader {
    private ArrayList<Transaction> transactions;
    private String fileName;

    public TransactionFileReader(String fileName) {
        this.fileName = fileName;
        this.transactions = new ArrayList<>();
    }

    public void read() throws IOException {
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String transactionInfo;

        while((transactionInfo = br.readLine()) != null){
            String[] splitInfo = transactionInfo.split(",");
            double amount = Double.parseDouble(splitInfo[0].replace(",",""));
            int sender = Integer.parseInt(splitInfo[1].replace(",",""));
            int receiver = Integer.parseInt(splitInfo[2].replace(",",""));
            String type = splitInfo[3].replace(",","");

            transactions.add(new Transaction(amount,sender,receiver,type));
        }

        br.close();
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
