package FileParsers;

import java.io.*;
import java.util.ArrayList;

/**
 * Read the client creation requests for the atm from a file
 */
public class ClientRequestFileReader {
    private ArrayList<String[]> clientRequests;

    public ClientRequestFileReader(String fileName) throws IOException {
        clientRequests = new ArrayList<>();
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String user;
        while((user = br.readLine()) != null){
            clientRequests.add(user.split(","));
        }
    }
    public ArrayList<String[]> getClientRequests() {
        return clientRequests;
    }
}
