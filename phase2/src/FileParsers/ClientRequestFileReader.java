package FileParsers;

import java.io.*;
import java.util.ArrayList;

public class ClientRequestFileReader {
    private ArrayList<String> clientRequests;

    public ClientRequestFileReader(String fileName) throws IOException {
        clientRequests = new ArrayList<>();
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String username;
        while((username = br.readLine()) != null){
            clientRequests.add(username);
        }
    }
    public ArrayList<String> getClientRequests() {
        return clientRequests;
    }
}
