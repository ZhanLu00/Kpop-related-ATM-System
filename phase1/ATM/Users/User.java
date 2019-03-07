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

    public static int username;
    public int password;
    public ArrayList<Integer> accountNumbers;

    public User(int username, int password) {
        this.username = username;
        this.password = password;
        this.accountNumbers = new ArrayList<Integer>(1);
    }

    /** getters **/

    public int getUsername(){
        return this.username;
    }

    public int getPassword(){
        return this.password;
    }

    public ArrayList<Integer> getAccounts(){
        return this.accountNumbers;
    }

    /** setter **/

    public void setPassword(int pswd){
        this.password = pswd;
    }

    // add account
    public void addAccounts(int accountNumber){
        this.accountNumbers.add(accountNumber);
    }



    /** update account **/

    abstract public void updateAccount();


}