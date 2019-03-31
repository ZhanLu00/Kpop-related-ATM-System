package src.ATM.Users;
import java.util.*;

public class Client extends User {


    private ArrayList<Integer> accountNumbers;

    public Client(String username, String password) {
        super(username, password);
        this.accountNumbers = new ArrayList<Integer>(1);
    }


    // add account
    public void addAccounts(int accountNumber){
        this.accountNumbers.add(accountNumber);
    }

    public ArrayList<Integer> getAccounts(){
        return this.accountNumbers;
    }


}
