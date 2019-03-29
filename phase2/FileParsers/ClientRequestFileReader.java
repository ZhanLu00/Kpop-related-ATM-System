package FileParsers;

import java.io.*;
import java.util.ArrayList;

public class UserRequestFileReader {
    private ArrayList<String> userRequests;

    public UserRequestFileReader(String fileName) throws IOException {
        userRequests = new ArrayList<>();
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String username;
        while((username = br.readLine()) != null){
            userRequests.add(username);
        }
    }
    public ArrayList<String> getUserRequests() {
        return userRequests;
    }
}
