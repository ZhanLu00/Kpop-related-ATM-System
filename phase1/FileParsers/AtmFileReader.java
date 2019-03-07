package FileParsers;

import ATM.TimeManager;

import java.io.*;
import java.util.Date;

public class AtmFileReader {
    private String fileName;
    private Date date;

    public AtmFileReader(String fileName) {
        this.fileName = fileName;
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
        }
    }

    public Date getDate() {
        return date;
    }
}
