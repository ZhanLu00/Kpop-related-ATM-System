package BankAccounts;

public abstract class BankAccount {
    private final int id;

    protected BankAccount(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
