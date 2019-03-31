package FileParsers;

import ATM.Users.BankInspector;
import ATM.Users.BankManager;
import ATM.Users.Client;
import ATM.Users.User;

import java.io.*;
import java.util.ArrayList;

/**
 * Read the users for the program from the file
 */
public class UserFileReader {
    private ArrayList<User> users;

    public UserFileReader(String fileName) throws IOException {
        users = new ArrayList<>();
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String userInfo;
        while((userInfo = br.readLine()) != null){
            createUser(userInfo);
        }
    }

    /**
     * @param userInfo
     * Create a user from the line of information from the file
     */
    private void createUser(String userInfo) {
        String[] separated = userInfo.split(",");
        String userType = separated[0].replace(",","");
        String username = separated[1].replace(",","");
        String password = separated[2].replace(",","");

        if (userType.equals("client")) {
            Client client = new Client(username,password);
            for (int i = 3; i < separated.length; i++) {
                client.addAccounts(Integer.parseInt(separated[i].replace(",","")));
            }
            users.add(client);
        }
        else if (userType.equals("manager")) {
            BankManager bankManager = new BankManager(username,password);
            users.add(bankManager);
        }
        else if (userType.equals("inspector")) {
            BankInspector bankInspector = new BankInspector(username,password);
            for (int i = 3; i < separated.length; i++) {
                bankInspector.addAccounts(Integer.parseInt(separated[i].replace(",","")));
            }
            users.add(bankInspector);
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
