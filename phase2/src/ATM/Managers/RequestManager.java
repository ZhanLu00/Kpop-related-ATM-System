package ATM.Managers;

import com.sun.istack.internal.Nullable;

import java.util.ArrayList;

/**
 * Manage the account and user creation requests of the atm
 */
public class RequestManager {
    private ArrayList<String[]> accountRequests;
    private ArrayList<String> clientRequests;

    public RequestManager(ArrayList<String[]> accountRequests, ArrayList<String> clientRequests) {
        this.accountRequests = accountRequests;
        this.clientRequests = clientRequests;
    }

    /**
     * @param username the username of account user
     * @param type the type of account
     */
    public void addAccountRequest(String username, String type) {
        accountRequests.add(new String[]{username,type});
    }

    /**
     * @param username The username of the new client
     */
    public void addClientRequest(String username) {
        clientRequests.add(username);
    }

    public ArrayList<String[]> getAccountRequests() {
        return accountRequests;
    }

    public ArrayList<String> getClientRequests() {
        return clientRequests;
    }


    /**
     * @param requestType add new client if set to newUser and new account of type 'type' if set to newAccount
     * @param username
     * @param type
     */
    public void addRequest(String requestType, String username, String type) {
        if (requestType.equals("newUser")) {
            addClientRequest(username);
        }
        else if (requestType.equals("newAccount")) {
            addAccountRequest(username,type);
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
    public boolean requestExist(String requestType, String username, @Nullable String type) {
        if (requestType.equals("newUser")) {
            for (String requestUsername : clientRequests) {
                if (username.equals(requestUsername)) {
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
     * @return the status of the request
     */
    public String getStatus(String requestType, String username) {
        if (requestType == "newUser"){
            for (String[] info : this.clientRequests){
                if (username.equals(info[0])){
                    return info[2];
                }
            }
        }else{
            for (String[] info: this.accountRequests){
                if (username == info[0]){
                    return info[1];
                }
            }
        }
        return "";
    }

    public boolean updateStatus(String requestType, String username, String status){
        int inde = 0;
        if (requestType.equals("newUser")){
            for (String[] info : this.clientRequests){
                if (username.equals(info[0])){
                    this.clientRequests.set(inde, new String[]{info[0], info[1], status});
                }
                inde +=1;
            }
        }else {
            for (String[] info : this.accountRequests) {
                if (username.equals(info[0])) {
                    this.accountRequests.set(inde, new String[]{info[0], status});
                    return false;
                }
                inde += 1;
            }
        }
    }
}
