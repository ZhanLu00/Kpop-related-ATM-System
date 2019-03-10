package ATM;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BillManager {
    private int fives;
    private int tens;
    private int twenties;
    private int fifties;
    private String alertsFileName;

    public BillManager(){
        this.fives = 0;
        this.tens = 0;
        this.twenties = 0;
        this.fifties = 0;
    }

    public BillManager(int fives, int tens, int twenties, int fifties, String alertsFileName){
        this.fives = fives;
        this.tens = tens;
        this.twenties = twenties;
        this.fifties = fifties;
        this.alertsFileName = alertsFileName;
    }

    public int withdraw(int amount) throws IOException {

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

        if (fifties < 20 || twenties < 20 || tens < 20 || fives < 20) {
            writeAlerts();
        }

        return initalAmount - amount;
    }

    /*
    @ TODO implement the following method
     */

    public Boolean withdrawable(int amount){
        // return true if there are enough money
        // false otherwise
    }

    private void writeAlerts() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(alertsFileName));
        if (fifties < 20) {
            writer.write(String.format("Restock 50's.  %d remaining", fifties));
        }

        if (twenties < 20) {
            writer.write(String.format("Restock 20's.  %d remaining", twenties));
        }

        if (tens < 20) {
            writer.write(String.format("Restock 10's.  %d remaining", tens));
        }

        if (fives < 20) {
            writer.write(String.format("Restock 5's.  %d remaining", fives));
        }

        writer.close();
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
