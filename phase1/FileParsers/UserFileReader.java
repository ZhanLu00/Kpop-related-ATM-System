package FileParsers;

import Users.BankManager;
import Users.Client;
import Users.User;

import java.io.*;
import java.util.ArrayList;

public class UserFileReader {
    private ArrayList<User> users;

    public UserFileReader(String fileName) throws IOException {

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
                client.addAccount(Integer.parseInt(seperated[i].replace(",","")));
            }
        }
        else if (userType.equals("manager")) {
            BankManager bankManager = new BankManager(username,password);
            for (int i = 3; i < seperated.length; i++) {
                bankManager.addAccount(Integer.parseInt(seperated[i].replace(",","")));
            }
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
