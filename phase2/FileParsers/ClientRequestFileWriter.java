package FileParsers;

import ATM.BankAccounts.BankAccount;
import ATM.TimeManager;
import ATM.Users.BankInspector;
import ATM.Users.BankManager;
import ATM.Users.Client;
import ATM.Users.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class ClientRequestFileWriter {

    private String fileName;
    private ArrayList<String> usernames;

    public ClientRequestFileWriter(String fileName, ArrayList<String> usernames) {
        this.fileName = fileName;
        this.usernames = usernames;
    }

    /**
     * Writes the client requests file.
     * Each line contains the username for a client request.
     */
    public void write() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write("");
        StringBuilder fileOut = new StringBuilder();

        for (String username:usernames) {
            fileOut.append(String.format("%s\n",username));
        }

        writer.append(fileOut);
        writer.close();
    }

}
