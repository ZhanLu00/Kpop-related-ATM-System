package FileParsers;

import ATM.BankAccounts.BankAccount;
import ATM.BillManager;
import ATM.TimeManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.util.Date;

public class AtmFileWriter {
    private String fileName;
    private Date date;
    private BillManager billManager;

    public AtmFileWriter(String fileName, Date date, BillManager billManager) {
        this.fileName = fileName;
        this.date = date;
        this.billManager = billManager;
    }

    public void write() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(TimeManager.dateToString(date) + "\n");
        writer.write(String.format("%d,%d,%d,%d", billManager.getFives(), billManager.getTens(), billManager.getTwenties(), billManager.getFifties()));
        writer.close();
    }
}
