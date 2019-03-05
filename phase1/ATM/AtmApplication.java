package ATM;

public class AtmApplication {
    UserManager userManager = new UserManager();
    BankManager bankManager = new BankManager();
    TimeManager timeManager = new TimeManager();

    Atm atm = new Atm(userManager, bankManager, timeManager);

    public static void main(String[] args) {
        atm.printText();

    }




}
