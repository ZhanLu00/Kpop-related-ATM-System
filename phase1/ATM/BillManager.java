package ATM;

public class BillManager {
    public int fives;
    public int tens;
    public int twenties;
    public int fifties;

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
