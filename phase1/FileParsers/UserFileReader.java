package FileParsers;

import ATM.Users.BankManager;
import ATM.Users.Client;
import ATM.Users.User;

import java.io.*;
import java.util.ArrayList;

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

    private void createUser(String userInfo) {
        String[] seperated = userInfo.split(",");
        String userType = seperated[0].replace(",","");
        String username = seperated[1].replace(",","");;
        String password = seperated[2].replace(",","");;

        if (userType.equals("client")) {
            Client client = new Client(username,password);
            for (int i = 3; i < seperated.length; i++) {
                client.addAccounts(Integer.parseInt(seperated[i].replace(",","")));
            }
            users.add(client);
        }
        else if (userType.equals("manager")) {
            BankManager bankManager = new BankManager(username,password);
            for (int i = 3; i < seperated.length; i++) {
                bankManager.addAccounts(Integer.parseInt(seperated[i].replace(",","")));
            }
            users.add(bankManager);
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
