package ATM.testfiles;

import FileParsers.*;

import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
       AccountFileReader fileReader = new AccountFileReader("phase2/ATM/BankAccounts.txt");
       AccountRequestFileWriter fileWriter = new AccountRequestFileWriter("phase2/ATM/BankAccounts.txt",fileReader.getAccounts());
       fileWriter.write();
    }
}
