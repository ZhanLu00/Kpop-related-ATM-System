package ATM;

import ATM.BankAccounts.BankAccount;
import FileParsers.AccountFileReader;
import FileParsers.AccountFileWriter;
import FileParsers.UserFileReader;

import java.io.IOException;
import java.util.Date;

public class test {
    public static void main(String[] args) throws IOException {
        AccountFileReader fileReader = new AccountFileReader("phase1/ATM/BankAccounts.txt");
        System.out.println(fileReader.getAccounts());


        AccountFileWriter fileWriter = new AccountFileWriter("phase1/ATM/BankAccounts2.txt",fileReader.getAccounts());
        fileWriter.write();

//        UserFileReader fileReader1 = new UserFileReader("phase1/ATM/BankUsers.txt");
//        System.out.println(fileReader1.getUsers());

    }
}
