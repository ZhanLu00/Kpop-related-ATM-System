package src.ATM.Managers;

import java.util.Date;

public class TimeManager {
    private Date date;

    private static final String[] months = new String[] {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};

    public TimeManager(int year, int month, int date, boolean goForwardOneDay) {
        this.date = new Date(year,month,date);

        if (goForwardOneDay) {
            this.date.setDate(this.date.getDate()+1);
        }
    }

    public TimeManager(Date date, boolean goForwardOneDay) {
        this.date = date;

        if (goForwardOneDay) {
            this.date.setDate(this.date.getDate()+1);
        }
    }

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

        Date date = new Date(year,monthIndex,day);
        return date;
    }

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
