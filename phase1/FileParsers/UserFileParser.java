package FileParsers;

import Users.User;

import java.io.*;
import java.util.ArrayList;

public class UserFileParser {
    private ArrayList<User> users;


    public UserFileParser(String fileName) throws IOException {

        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null){
            System.out.println(line);
        }
    }
    
}
