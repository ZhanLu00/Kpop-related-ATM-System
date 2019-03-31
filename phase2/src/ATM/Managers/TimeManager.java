package ATM.Managers;

import java.util.Date;

/**
 * Class to manage time in the atm. Allows for converting between different representations and going forward a day
 * to simulate the passage of time
 */
public class TimeManager {
    private Date date;

    private static final String[] months = new String[] {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};

    /**
     * @param year the year of the date
     * @param month The index of the month from 0-11
     * @param date the current date within the month
     * @param goForwardOneDay Indicates whether to go forward a day to simulate time passing
     */
    public TimeManager(int year, int month, int date, boolean goForwardOneDay) {
        this.date = new Date(year,month,date);

        if (goForwardOneDay) {
            this.date.setDate(this.date.getDate()+1);
        }
    }

    /**
     * @param date The current date.
     * @param goForwardOneDay Indicates whether to go forward a day to simulate time passing
     */
    public TimeManager(Date date, boolean goForwardOneDay) {
        this.date = date;

        if (goForwardOneDay) {
            this.date.setDate(this.date.getDate()+1);
        }
    }

    /**
<<<<<<< HEAD
     * Takes string in form "2000 jan 1" and returns corresponding Date type object.
=======
     * @param dateString in format yyyy month dd
     * @return Date object representing the date from the string
>>>>>>> 2fe8093a78367ef129ff0f8be31e4f9b92f9b911
     */
    public static Date dateFromString(String dateString){
        String[] split = dateString.split(" ");
        int year = Integer.parseInt(split[0]);
        int day = Integer.parseInt(split[2]);
        String month = split[1].replace(" ","");
        int monthIndex = -1;

        for (int i = 0; i < months.length; i++) {
            if (month.equals(months[i])) {
                monthIndex = i;
                break;
            }
        }
        return new Date(year,monthIndex,day);
    }

    /**
     * @param date A date object representing the date
     * @return A string with the date in yyyy month dd format
     */
    public static String dateToString(Date date) {
        int year = date.getYear();
        String month = months[date.getMonth()];
        int day = date.getDate();

        return String.format("%d %s %d", year, month, day);
    }

    public Date getDate() {
        return date;
    }

}
