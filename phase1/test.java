import FileParsers.UserFileParser;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws IOException {
        UserFileParser userFileParser = new UserFileParser("phase1/BankUsers.txt");
    }
}
