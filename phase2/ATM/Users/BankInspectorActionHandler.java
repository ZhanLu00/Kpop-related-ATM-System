package ATM.Users;

import ATM.Atm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BankInspectorActionHandler {
    private BankInspector bankInspector;
    private Atm atm;
    private BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));

    private Client selectedClient;

    public BankInspectorActionHandler(BankInspector bankInspector, Atm atm) {
        this.bankInspector = bankInspector;
        this.atm = atm;
    }

    

}
