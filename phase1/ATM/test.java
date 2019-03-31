package src.ATM;

import src.ATM.BankAccounts.BankAccount;
import src.FileParsers.*;

import java.io.IOException;
import java.util.Date;

public class test {
    public static void main(String[] args) throws IOException {
       AccountFileReader fileReader = new AccountFileReader("phase1/src.ATM/BankAccounts.txt");
       AccountFileWriter fileWriter = new AccountFileWriter("phase1/src.ATM/BankAccounts.txt",fileReader.getAccounts());
       fileWriter.write();
    }
}
