package ATM;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

public class AtmUserInterface {
    UserManager userManager = new UserManager();
    BankManager bankManager = new BankManager();
    TimeManager timeManager = new TimeManager();

    Atm atm = new Atm(userManager, bankManager, timeManager);

    public static void main(String[] args) {
        BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter username: ");
        String username = kbd.readLine();
        System.out.println("Enter password: ");
        String password = kbd.readLine();
        System.out.println(atm.getStartingText(username, password));
    }




}
