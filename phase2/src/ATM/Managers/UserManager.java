package ATM.Managers;

import ATM.Users.User;
import ATM.Users.Client;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;


/**
 * A UserManager class that stores all the users.
 */

public class UserManager implements Iterable<User> {

    private ArrayList <User> users;
    private ArrayList <String> clientRequests;
    private Date date;

    public UserManager(ArrayList<User> users, ArrayList<String> clientRequests, Date date){
        this.users = users;
        this.clientRequests = clientRequests;
        this.date = date;
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
        User o = null;
        return o;
    }
    public Client getUser(String username){
        for (User u : users){
            if (u.getUsername().equals(username)){
                return (Client)u;
            }
        }
        return null;
    }

    /**
     * Returns a list of all the users requests (represented by usernames).
     */
    public ArrayList<String> getClientRequests() {return clientRequests;}

    /**
     * Returns a list of all the users.
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    public boolean userExists(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }



    /**
     * Returns a list of all the users login
     */
    public ArrayList<String> getUsersLogin(){
        ArrayList<String> login = new ArrayList<String>(0);
        for (User user : this.users){
            login.add(user.getUsername());
        }
        return login;
    }

    private class UserManagerIterator implements Iterator<User> {
        private ArrayList<User> users;
        int i;
        public UserManagerIterator(ArrayList<User> users) {
            this.users = users;
            this.i = 0;
        }

        @Override
        public boolean hasNext() {
            return i < users.size();
        }

        @Override
        public User next() {
            i+=1;
            return users.get(i-1);
        }
    }

    @Override
    public Iterator<User> iterator() {
        return new UserManagerIterator(users);
    }

    @Override
    public void forEach(Consumer<? super User> action) {

    }

    @Override
    public Spliterator<User> spliterator() {
        return null;
    }
}