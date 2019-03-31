package src.ATM;


import src.ATM.ActionHandler.ActionHandler;
import src.ATM.Managers.AccountManager;
import src.ATM.Managers.BillManager;
import src.ATM.Managers.TransactionManager;
import src.ATM.Managers.UserManager;

public class AtmTesting {

    // viewer
    public ATMGUI atmgui = new ATMGUI();

    // model
    private AccountManager accountManager = new AccountManager(new ArrayList<BankAccount>, new ArrayList<String[]>, new Date);

    private TransactionManager transactionManager = new TransactionManager();

    private UserManager userManager = new UserManager(new ArrayList<User>, new ArrayList<String>, new Date());

    private BillManager billManager = new BillManager();

    String userFileName = "/testfiles/userfiles.txt";
    String accountFileName = "/testfiles/accounts.txt";
    String atmFileName = "/testfiles/Atminfo.txt";
    String alertsFileName = "/testfiles/alerts.txt";
    String transactionsFileName = "/testfiles/transactions.txt";
    String messagesFileName = "/testfiles/messages.txt";
    String accountRequestFileName = "/testfiles/accountRequests.txt";
    String clientRequestFileName = "/testfiles/clientRequest.txt";


    // controller
    public ActionHandler actionHandler = new ActionHandler(atmgui);

    public void main(String[] args) throws IOException {
        public Atm atm = new Atm (userFileName,  accountFileName, atmFileName, alertsFileName, transactionsFileName,
                messagesFileName, accountRequestFileName, clientRequestFileName);
        actionHandler.initViewer();
        actionHandler.initOperator();
    }

    public void loadData(String fileName){

    }




}
