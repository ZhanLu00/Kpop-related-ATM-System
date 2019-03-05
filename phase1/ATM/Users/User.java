package ATM.Users;

import ATM.BankAccounts.BankAccount;
import java.io.*;
import java.util.*;

public abstract class User {
    /** This use interface
     *  Store, getter login name, account created date
     *  Store, getter, setter password
     *  Store, getter, setter Accounts
     */

    public static String username;
    public String password;
    public ArrayList<Integer> accountNumbers;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.accountNumbers = new ArrayList<Integer>(1);
    }

    /** getters **/

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public ArrayList getAccounts(){
        return this.accountNumbers;
    }

    /** setter **/

    public void setPassword(String pswd){
        this.password = pswd;
    }

    // add account
    public void addAccounts(int accountNumber){
        this.accountNumbers.add(accountNumber);
    }



    /** update account **/

    abstract public void updateAccount();


}