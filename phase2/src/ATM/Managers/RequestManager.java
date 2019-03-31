package ATM.Managers;

import java.util.ArrayList;

public class RequestManager {
    private ArrayList<String[]> accountRequests;
    private ArrayList<String> clientRequests;

    public RequestManager(ArrayList<String[]> accountRequests, ArrayList<String> clientRequests) {
        this.accountRequests = accountRequests;
        this.clientRequests = clientRequests;
    }

    public void addAccountRequest(String username, String type) {
        accountRequests.add(new String[]{username,type});
    }

    public void addClientRequest(String username) {
        clientRequests.add(username);
    }

    public ArrayList<String[]> getAccountRequests() {
        return accountRequests;
    }

    public ArrayList<String> getClientRequests() {
        return clientRequests;
    }


    public void addRequest(String requestType, String username, String type) {
        if (requestType.equals("newUser")) {
            addClientRequest(username);
        }
        else if (requestType.equals("newAccount")) {
            addAccountRequest(username,type);
        }
    }

    public boolean requestExist(String requestType, String username, String type) {
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

    public String getStatus(String requestType, String username) {
        return "accepted";
    }
}
