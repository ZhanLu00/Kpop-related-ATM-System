package ATM.Users;
import java.util.*;

public class Client extends User {



    public Client(String username, String password) {
        super(username, password);
    }

    // TODO: 2019-03-05 implement interface
    @Override
    public void updateAccount() {

    }


    // get balance
    public ArrayList getBalance(){
        ArrayList<String[]> balance = new ArrayList<>(0);
        for (int account:this.accountNumbers) {
            //balance.add({String(account), });
        }
        return balance;

    }

    // transfer money

    // withdraw cash

    // pay a bill


}
