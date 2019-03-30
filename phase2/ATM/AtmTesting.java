package ATM;


import ATM.ActionHandler.ActionHandler;
import ATM.Managers.AccountManager;
import ATM.Managers.BillManager;
import ATM.Managers.TransactionManager;
import ATM.Managers.UserManager;

public class AtmTesting {

    // viewer
    public ATMGUI atmgui = new ATMGUI();

    // model
    private AccountManager accountManager = new AccountManager();

    private TransactionManager transactionManager = new TransactionManager();

    private UserManager userManager = new UserManager();

    private BillManager billManager = new BillManager();

    // controller
    public ActionHandler actionHandler = new ActionHandler(atmgui);

    public void main(){
        actionHandler.initViewer();
        actionHandler.initOperator();
    }

    public void loadData(String fileName){

    }




}
