package ATM.BankAccounts.ExtraAccounts;
import ATM.BankAccounts.DebtAccounts.LineOfCreditAccount;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;


public class LotteryAccount extends LineOfCreditAccount {

    public LotteryAccount(Date dateCreated, double initialBalance) {
        super(dateCreated, initialBalance);
    }

    /**
     * Multiplies balance by a factor between 0.5 and 1.5 (inclusive)..
     */
    public void collectInterest() {
        this.balance *= ThreadLocalRandom.current().nextDouble(0.5, 1.5);
    }


}