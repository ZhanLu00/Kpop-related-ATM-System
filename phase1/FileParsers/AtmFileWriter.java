package FileParsers;

import ATM.BankAccounts.BankAccount;
import ATM.TimeManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.util.Date;

public class AtmFileWriter {
    private String fileName;
    private Date date;

    public AtmFileWriter(String fileName, Date date) {
        this.fileName = fileName;
        this.date = date;
    }

    public void write() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(TimeManager.dateToString(date));
        writer.close();
    }
}
