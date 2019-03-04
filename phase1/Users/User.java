package Users;

import BankAccounts.BankAccount;
import java.io.*;
import java.util.*;

public abstract class User {
    /** This use interface
     *  Store, getter login name, account created date
     *  Store, getter, setter password
     *  Store, getter, setter Accounts
     */

    private static String username;
    private String password;
    private ArrayList<BankAccount> accounts;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.accounts = new ArrayList<BankAccount>(1);
    }

    /** getters **/

    abstract public String getUsername();

    abstract public String getPassword();

    /** setter **/

    abstract public void setPassword();

    abstract public void setAccounts();



}