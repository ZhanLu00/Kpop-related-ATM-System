package FileParsers;

import ATM.BankAccounts.BankAccount;
import ATM.TimeManager;
import ATM.Users.BankManager;
import ATM.Users.Client;
import ATM.Users.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class UserFileWriter {

    private String fileName;
    private ArrayList<User> users;

    public UserFileWriter(String fileName, ArrayList<User> users) {
        this.fileName = fileName;
        this.users = users;
    }

    public void write() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write("");
        StringBuilder fileOut = new StringBuilder();

        for (User user : users) {
            String type = getType(user);
            String username = user.getUsername();
            String password = user.getPassword();
            StringBuilder accounts = new StringBuilder();
            for (Integer accountNum : user.getAccounts()) {
                accounts.append(accountNum.toString()).append(",");
            }
            String accountsString = accounts.toString().substring(0,accounts.length()-1);
            fileOut.append(String.format("%s,%s,%s,%s",type,username,password,accountsString));
        }

        writer.append(fileOut);
        writer.close();
    }

    private String getType(User user) {
        if (user instanceof Client) {
            return "client";
        }
        else if (user instanceof BankManager) {
            return "manager";
        }

        return "user";
    }

}
