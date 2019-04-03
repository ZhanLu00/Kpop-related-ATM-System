package ATM.Managers;

import com.sun.istack.internal.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Manage the account and user creation requests of the atm
 */
public class RequestManager {
    /**
     * string[] format: username, requestAccountType, status
     */
    private ArrayList<String[]> accountRequests;
    private ArrayList<String[]> clientRequests;

    public RequestManager(ArrayList<String[]> accountRequests, ArrayList<String[]> clientRequests) {
        this.accountRequests = accountRequests;
        this.clientRequests = clientRequests;
    }


    public ArrayList<String[]> getAccountRequests() {
        return accountRequests;
    }



    public ArrayList<String[]> getClientRequests() {
        return clientRequests;
    }


    /**
     * @param requestType add new client if set to newUser and new account of type 'type' if set to newAccount
     * @param username
     * @param acc
     */
    public void addRequest(String requestType, String username, String acc) {
        if (requestType.equals("newUser")) {
            clientRequests.add(new String[]{username, acc, "pending"});
        }
        else if (requestType.equals("newAccount")) {
            accountRequests.add(new String[]{username,acc, "pending"});
        }
    }

    /**
     * @param requestType if set to newUser check if client creation request exists, if set to newAccount check if
     *                    account creation request exists
     * @param username The username of the person whos account is being created or the username of the new client being
     *                 created
     * @param type The type of the account being created if checking for a account creation request
     * @return if the request exists or not
     */
    public boolean requestExist(String requestType, String username, String type) {
        if (requestType.equals("newUser")) {
            for (String[] clientRequest : clientRequests) {
                if (username.equals(clientRequest[0]) && clientRequest[1].equals(type)) {
                    return true;
                }
            }
        }
        else if (requestType.equals("newAccount")) {
            for (String[] accountRequest : accountRequests) {
                if (accountRequest[0].equals(username) && accountRequest[1].equals(type)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param requestType The type of request being checked for
     * @param username The username of the user who the request pertains to
     * @return a String array where the first item is the type of account, second is the status
     */
    public String[] getStatus(String requestType, String username) {
        if (requestType.equals("newUser")){
            for (String[] info : this.clientRequests){
                if (username.equals(info[0])){
                    return new String[]{info[1], info[2]};
                }
            }
        }else{
            for (String[] info: this.accountRequests){
                if (username.equals(info[0])){
                    return new String[]{info[1], info[2]};
                }
            }
        }
        return new String[]{};
    }

    public ArrayList<String[]> getClientRequestsByStatus(String type, String status){
        ArrayList<String[]> requests = new ArrayList<String[]>();
        if (type.equals("newUser")){
            for (String[] request: this.clientRequests){
                if (request[2].equals(status)){
                    requests.add(request);
                }
            }
        }else{
            for (String[] request: this.accountRequests){
                if (request[2].equals(status)){
                    requests.add(request);
                }
            }
        }
        return requests;
    }

    public boolean updateStatus(String requestType, String username, String status){
        int inde = 0;
        if (requestType.equals("newUser")){
            for (String[] info : this.clientRequests){
                if (username.equals(info[0])){
                    this.clientRequests.set(inde, new String[]{info[0], info[1], status});
                    return true;
                }
                inde +=1;
            }
        }else {
            for (String[] info : this.accountRequests) {
                if (username.equals(info[0])) {
                    this.accountRequests.set(inde, new String[]{info[0], info[1], status});
                    return true;
                }
                inde += 1;
            }
        }
        return false;
    }
}
