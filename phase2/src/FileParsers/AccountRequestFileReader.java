package src.FileParsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AccountRequestFileReader {
    private ArrayList<String[]> accountRequests;

    public AccountRequestFileReader(String fileName) throws IOException {

        accountRequests = new ArrayList<>();

        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String accountInfo;
        while((accountInfo = br.readLine()) != null){
            accountRequests.add(accountInfo.split(","));
        }
    }
    /**
     * Returns the account requests in an arraylist of String arrays.
     * First element of array is the username.
     * Second element of array is the account type.
     */
    public ArrayList<String[]> getAccountRequests() {
        return accountRequests;
    }
}
