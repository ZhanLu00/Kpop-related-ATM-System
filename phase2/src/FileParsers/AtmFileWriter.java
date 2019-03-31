package FileParsers;

import ATM.Managers.AccountManager;
import ATM.Managers.BillManager;
import ATM.Managers.TimeManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Write the current date for the atm and the number of bills to a file
 */
public class AtmFileWriter {
    private String fileName;
    private Date date;
    private BillManager billManager;
    private AccountManager accountManager;

    public AtmFileWriter(String fileName, Date date, BillManager billManager, AccountManager accountManager) {
        this.fileName = fileName;
        this.date = date;
        this.billManager = billManager;
        this.accountManager = accountManager;
    }

    /**
     * @throws IOException
     * Write the information to the text file at this.fileName
     */
    public void write() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(TimeManager.dateToString(date) + "\n");
        writer.write(String.format("%d,%d,%d,%d", billManager.getFives(), billManager.getTens(), billManager.getTwenties(), billManager.getFifties())+ "\n");

        for (String[] accountRequest : accountManager.getAccountRequests()) {
            writer.write(String.format("%s,%s\n", accountRequest[0], accountRequest[1]));
        }

        writer.close();
    }
}
