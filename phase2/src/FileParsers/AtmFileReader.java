package FileParsers;

import ATM.Managers.TimeManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Read number of bills and date of the atm from a file
 */
public class AtmFileReader {
    private String fileName;
    private Date date;
    private int fives, tens, twenties, fifties;
    private ArrayList<String[]> accountCreationRequests;

    public AtmFileReader(String fileName) {
        this.fileName = fileName;
        this.accountCreationRequests = new ArrayList<>();
    }

    /**
     * @throws IOException
     * Read the information from the this.fileName text file
     */
    public void read() throws IOException {
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String line;
        int lineNum = 0;
        while((line = br.readLine()) != null){
            if (lineNum==0) {
                this.date = TimeManager.dateFromString(line);
            }
            else if (lineNum == 1) {
                String[] separated = line.split(",");
                this.fives = Integer.parseInt(separated[0]);
                this.tens = Integer.parseInt(separated[1]);
                this.twenties = Integer.parseInt(separated[2]);
                this.fifties = Integer.parseInt(separated[3]);
            }
            else {
                String[] separated = line.split(",");
                String clientName = separated[0].replace(",","");
                String accountType = separated[1].replace(",","");
                accountCreationRequests.add(new String[] {clientName,accountType});
            }
            lineNum += 1;
        }
    }

    public int getFives() {
        return fives;
    }

    public int getTens() {
        return tens;
    }

    public int getTwenties() {
        return twenties;
    }

    public int getFifties() {
        return fifties;
    }

    public String getFileName() {
        return fileName;
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<String[]> getAccountCreationRequests() {
        return accountCreationRequests;
    }
}
