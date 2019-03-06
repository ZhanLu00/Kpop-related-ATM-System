package ATM;

import ATM.BankAccounts.BankAccount;
import FileParsers.*;

import java.io.IOException;
import java.util.Date;

public class test {
    public static void main(String[] args) throws IOException {
//        AccountFileReader fileReader5 = new AccountFileReader("phase1/ATM/BankAccounts.txt");
//        System.out.println(fileReader5.getAccounts());
//
//
//        AccountFileWriter fileWriter = new AccountFileWriter("phase1/ATM/BankAccounts2.txt",fileReader5.getAccounts());
//        fileWriter.write();
//
//        AtmFileReader fileReader = new AtmFileReader("phase1/ATM/AtmInfo.txt");
//        fileReader.read();
//        System.out.println(fileReader.getDate());
//
//        UserFileReader fileReader1 = new UserFileReader("phase1/ATM/BankUsers.txt");
//        System.out.println(fileReader1.getUsers());
//
//        UserFileReader fileReader2 = new UserFileReader("phase1/ATM/BankUsers.txt");
//
//        UserFileWriter fileWriter7 = new UserFileWriter("phase1/ATM/BankUsers1.txt",fileReader2.getUsers());
//        fileWriter7.write();

        AtmFileReader atmFileReader = new AtmFileReader("phase1/ATM/AtmInfo.txt");
        atmFileReader.read();
        AtmFileWriter atmFileWriter = new AtmFileWriter("phase1/ATM/info.txt",atmFileReader.getDate());
        atmFileWriter.write();
    }
}
