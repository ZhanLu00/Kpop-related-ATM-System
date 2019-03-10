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

    /**
     * Withdraws the amount of money specified and decreases the number of bills as needed.
     * If the amount of denomination goes below 20, an alert is written.
     */
    public void withdraw(int amount) throws IOException {
        if (amount < 0) {

        }

        if (amount >= 50 && fifties >= 1){
            int take = Math.min(fifties, (int)(amount/50));
            fifties -= take;
            amount -= take * 50;
        }
        if (amount >= 20 && twenties >= 1){
            int take = Math.min(twenties, (int)(amount/20));
            twenties -= take;
            amount -= take * 20;
        }
        if (amount >= 10 && tens >= 1){
            int take = Math.min(tens, (int)(amount/10));
            tens -= take;
            amount -= take * 10;
        }
        if (amount >= 5 && fives >= 1){
            int take = Math.min(fives, (int)(amount/5));
            fives -= take;
            amount -= take * 5;
        }
        if (fifties < 20 || twenties < 20 || tens < 20 || fives < 20) {
            writeAlerts();
        }
    }

    /**
     * Returns whether or not there is enough money in the ATM for the amount the user requested to withdraw.
     */
    public boolean withdrawable(int amount){
        if (amount % 5 == 0){
            if (amount >= 50 && fifties >= 1){
                amount -= Math.min(fifties, (int)(amount/50)) * 50;
            }
            if (amount >= 20 && twenties >= 1){
                amount -= Math.min(twenties, (int)(amount/20)) * 20;
            }
            if (amount >= 10 && tens >= 1){
                amount -= Math.min(tens, (int)(amount/10)) * 10;
            }
            if (amount >= 5 && fives >= 1){
                amount -= Math.min(fives, (int)(amount/5)) * 5;
            }
            return amount == 0;
        }else{
            System.out.println("Please choose an amount divisible by 5.");
            return false;
        }
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
