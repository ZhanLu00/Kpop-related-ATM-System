package ATM.BankAccounts.DebtAccounts;
import java.util.Date;


/**
 * A LineOfCreditAccount class.
 */
public class LineOfCreditAccount extends DebtAccount {

    public LineOfCreditAccount(Date dateCreated, double initialBalance) {
        super(dateCreated, initialBalance);
    }

    /**
     * Withdraws the given amount out of an account.
     * If the amount after withdrawal is less than the maximum debt that can incur, the withdraw is unsuccessful.
     * The method returns true if withdraw is successful, false otherwise.
     */
    public boolean withdraw(double amount) {
        if (this.balance + amount <= this.getMaxDebt()) {
            this.balance += amount;
            return true;
        } else {
            return false;
        }
    }

}
