package ATM;
import java.util.Date;


/**
 * A Date class.
 */
public class Date {

    public static final String[] MONTHS =
            {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

    private int year, month, day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String toString() {
        return MONTHS[this.month - 1] + " " + this.day + ", " + this.year;
    }

    public void nextDay() {

        Date old = new Date(this.year, this.month, this.day);

    }

}
