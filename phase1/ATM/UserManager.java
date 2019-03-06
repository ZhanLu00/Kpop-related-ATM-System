package ATM;

import ATM.Users.User;

import java.util.ArrayList;

/**
 * A UserManager class that stores all the users.
 */

public class UserManager {

    public ArrayList <User> users;

    public UserManager(){
        this.users = new ArrayList<User>();
    }

    public UserManager(ArrayList<User> users){
        this.users = users;
    }

    /**
     * Adds a user to the list of users.
     */
    public void addUser(User user){
        users.add(user);
    }

    /**
     * Removes a user from the list of users.
     */
    public void removeUser(User user){
        users.remove(user);
    }

    /**
     * Returns a user with the corresponding username and password.
     * If no user user with the username or password is found, returns null.
     */
    public User getUser(String username, String password){
        for (User u : users){
            if (u.getUsername().equals(username) && u.getPassword().equals(password)){
                return u;
            }
        }
        return null;
    }

    /**
     * Returns a list of all the users.
     */
    public ArrayList<User> getUsers() {
        return users;
    }
}