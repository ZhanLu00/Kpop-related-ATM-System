package FileParsers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Read Account creation requests from a text file
 */
public class AccountRequestFileWriter {
    private String fileName;
    private ArrayList<String[]> accountRequests;

    public AccountRequestFileWriter(String fileName, ArrayList<String[]> accountRequests) {
        this.fileName = fileName;
        this.accountRequests = accountRequests;
    }

    /**
     * Writes the account requests file.
     * Each line is an account request.
     * On each line, first String is username and second String is account type.
     */
    public void write() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write("");
        StringBuilder fileOut = new StringBuilder();
        for (String[] account : accountRequests) {
            fileOut.append(String.format("%s,%s,%s\n", account[0], account[1], account[2]));
        }
        writer.append(fileOut);
        writer.close();
    }

    /**
     * Sets the file name for account requests file.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Sets account requests by taking arraylist of String arrays.
     * First element of array is the username.
     * Second element of array is the account type.
     */
    public void setAccountRequests(ArrayList<String[]> accounts) {
        this.accountRequests = accounts;
    }

    /**
     * Returns the account requests in an arraylist of String arrays.
     * First element of array is the username.
     * Second element of array is the account type.
     */
    public ArrayList<String[]> getAccountRequests() {
        return accountRequests;
    }

    /**
     * Gets the file name for account requests file.
     */
    public String getFileName() {
        return fileName;
    }
}