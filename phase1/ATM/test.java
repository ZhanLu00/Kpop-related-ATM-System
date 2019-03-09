package ATM;

import ATM.BankAccounts.BankAccount;
import FileParsers.*;

import java.io.IOException;
import java.util.Date;

public class test {
    public static void main(String[] args) throws IOException {
        AtmFileReader fileReader = new AtmFileReader("phase1/ATM/AtmInfo.txt");
        fileReader.read();
        BillManager billManager = new BillManager(fileReader.getFives(),fileReader.getTens(),fileReader.getTwenties(),fileReader.getFifties());
        billManager.withdraw(65);
        AtmFileWriter fileWriter = new AtmFileWriter("phase1/ATM/atmtest.txt",fileReader.getDate(),billManager);
        fileWriter.write();
    }
}
