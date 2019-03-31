package ATM.Users;
import java.util.*;

public class Client extends ATM.Users.User {


    private ArrayList<Integer> accountNumbers;
    private int primaryAccount = -1;

    public Client(String username, String password) {
        super(username, password);
        this.accountNumbers = new ArrayList<>(1);
    }


    // add account
    public void addAccounts(int accountNumber){
        this.accountNumbers.add(accountNumber);
    }

    public ArrayList<Integer> getAccounts(){
        return this.accountNumbers;
    }

    public int getPrimaryAccount(){ return this.primaryAccount; }

    public void setPrimaryAccount(int primaryAccount) { this.primaryAccount = primaryAccount; }
}
