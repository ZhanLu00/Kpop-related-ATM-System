package FileParsers;

import ATM.TimeManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class AtmFileReader {
    private String fileName;
    private Date date;
    private int fives, tens, twenties, fifties;
    private ArrayList<String[]> accountCreationRequests;

    public AtmFileReader(String fileName) {
        this.fileName = fileName;
        this.accountCreationRequests = new ArrayList<>();
    }

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
                String[] seperated = line.split(",");
                this.fives = Integer.parseInt(seperated[0]);
                this.tens = Integer.parseInt(seperated[1]);
                this.twenties = Integer.parseInt(seperated[2]);
                this.fifties = Integer.parseInt(seperated[3]);
            }
            else {
                String[] seperated = line.split(",");
                String clientName = seperated[0].replace(",","");
                String accountType = seperated[1].replace(",","");
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
