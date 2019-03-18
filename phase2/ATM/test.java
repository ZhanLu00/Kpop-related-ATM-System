package ATM;

import ATM.BankAccounts.BankAccount;
import FileParsers.*;

import java.io.IOException;
import java.util.Date;

public class test {
    public static void main(String[] args) throws IOException {
       AccountFileReader fileReader = new AccountFileReader("phase1/ATM/BankAccounts.txt");
       AccountFileWriter fileWriter = new AccountFileWriter("phase1/ATM/BankAccounts.txt",fileReader.getAccounts());
       fileWriter.write();
    }
}
