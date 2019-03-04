package ATM;

public class Atm {
    UserManager userManager;
    BankManager bankManager;
    TimeManager timeManager;
    public Atm (UserManager userManager, BankManager bankManager, TimeManager timeManager){
        this.userManager = userManager;
        this.bankManager = bankManager;
        this.timeManager = timeManager;
        this.actionManager = actionManager;
    }
    public User getUser(String username, String password){
        return userManager.getUser(username, password);
    }
    public void setUser(User user){
        userManager.addUser(user);
    }

    public BankManager getBankManager() {
        return bankManager;
    }

    public void setBankManager(bankManager bankManager){
        this.bankManager = bankManager;
    }

    public String getStartingText(String username, String password){
        String text;
        User user = userManager.getAccount(username, password);
        for (Integer i: accounts) {
            BankAccount account = Arraylist AccountManager.getAccount(user.getAccounts());
        }
        return text;
    }
}
