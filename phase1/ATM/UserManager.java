package ATM;

import ATM.Users.User;

import java.util.ArrayList;

public class UserManager {

    public ArrayList <User> users;

    public UserManager(){
        this.users = new ArrayList<User>();
    }

    public UserManager(ArrayList<User> users){
        this.users = users;
    }


    public void addUser(User user){
        users.add(user);
    }

    public void removeUser(User user){
        users.remove(user);
    }

    public User getUser(String username, String password){
        for (User u : users){
            if (u.getUsername().equals(username) && u.getPassword().equals(password)){
                return u;
            }
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}