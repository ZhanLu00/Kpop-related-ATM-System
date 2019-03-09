package ATM;

public class BillManager {
    private int fives;
    private int tens;
    private int twenties;
    private int fifties;

    public BillManager(){
        this.fives = 0;
        this.tens = 0;
        this.twenties = 0;
        this.fifties = 0;
    }

    public BillManager(int fives, int tens, int twenties, int fifties){
        this.fives = fives;
        this.tens = tens;
        this.twenties = twenties;
        this.fifties = fifties;
    }

    public int withdraw(int amount) {
        if (amount < 0) {
            return 0;
        }

        int initalAmount = amount;

        int fiftiesTaken = Math.min(amount / 50, fifties);
        amount -= 50 * fiftiesTaken;
        fifties -= fiftiesTaken;

        int twentiesTaken = Math.min(amount / 20, twenties);
        amount -=  20 * twentiesTaken;
        twenties -= twentiesTaken;

        int tensTaken = Math.min(amount / 10, tens);
        amount -=  10 * tensTaken;
        tens -= tensTaken;

        int fivesTaken = Math.min(amount / 5, fives);
        amount -=  5 * fivesTaken;
        fives -= fivesTaken;

        return initalAmount - amount;
    }

    public void deposit(int fives, int tens, int twenties, int fifties) {
        this.fives += Math.max(fives,0);
        this.tens += Math.max(tens,0);
        this.twenties += Math.max(twenties,0);
        this.fifties += Math.max(fifties,0);
    }

    public int getFives(){
        return this.fives;
    }
    public int getTens(){
        return this.tens;
    }
    public int getTwenties(){
        return this.twenties;
    }
    public int getFifties(){
        return this.fifties;
    }

}
