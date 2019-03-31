package src.FileParsers;

import src.ATM.Transaction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TransactionFileWriter {
    private ArrayList<Transaction> transactions;
    private String fileName;

    public TransactionFileWriter(ArrayList<Transaction> transactions, String fileName) {
        this.transactions = transactions;
        this.fileName = fileName;
    }

    public void write() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        for (Transaction transaction : transactions) {
            double amount = transaction.getAmount();
            int sender = transaction.getSender();
            int receiver = transaction.getReceiver();
            String type = transaction.getType();

            writer.write(String.format("%f,%d,%d,%s\n",amount,sender,receiver,type));
        }

        writer.close();
    }
}
