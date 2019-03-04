import BankAccounts.BankAccount;

public abstract class User {
    /** This is an user interface
     * serve as abstract class purpose **/

    public static String loginName;
    public static String createDate;
    public String password;
    public BankAccount account;
    public String history;

    /** setters **/
    void setInfo(String name, String date){
    }

    void setPassword(String password){this.password = password;}

    /** getters **/
    abstract String getName();

    abstract String getDate();

    abstract String getPassword();

    abstract BankAccount getBankAccount();

    abstract String getHistory();

    

}
