package ATM;

import java.util.ArrayList;

public class UserManager {

    public ArrayList <User> users;

    public UserManager(){
        this.users = new ArrayList<User>;
    }

    public void addUser(User user){
        users.add(user);
    }

    public void removeUser(User user){
        users.remove(user);
    }

    private boolean userExists(String username, String password){
        for (User u : users){
            if (u.getUsername().equals(username)){
                if (u.getPassword().equals(password)){
                    return true;
                }
            }
        }
        return false;
    }

    public User getUser(String username, String password){
        if (userExists(username, password)){
            for (User u : users){
                if (u.getUsername().equals(username)){
                    return u;
                }
            }
        }
        return null;
    }

}