package ATM.Users;

public abstract class User {
    /** This use interface
     *  Store, getter login name, account created date
     *  Store, getter, setter password
     *  Store, getter, setter Accounts
     */
    public String username;
    public String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /** getters **/

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }


    /** setter **/

    public void setPassword(String pswd){
        this.password = pswd;
    }





}