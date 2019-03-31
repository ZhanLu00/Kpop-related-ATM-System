package src.ATM.testfiles;

import src.FileParsers.*;

import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
       AccountFileReader fileReader = new AccountFileReader("phase2/src.ATM/BankAccounts.txt");
       AccountRequestFileWriter fileWriter = new AccountRequestFileWriter("phase2/src.ATM/BankAccounts.txt",fileReader.getAccounts());
       fileWriter.write();
    }
}
