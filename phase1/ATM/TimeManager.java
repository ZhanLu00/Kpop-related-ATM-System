package ATM;

import java.util.Date;

public class TimeManager {
    private String month;
    private int year, day, monthIndex;

    private static final String[] months = new String[] {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
    private static final int[] daysInMonths = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public TimeManager(int year, String month, int day, boolean goForwardOneDay) {
        this.year = year;

        monthIndex = -1;
        for (int i = 0; i < months.length; i++) {
            if (month.equals(months[i])) {
                monthIndex = i;
                break;
            }
        }

        if (monthIndex == -1) {
            throw new IllegalArgumentException(String.format("Invalid Date: %d %s %d",year,month,day));
        }

        if (day < 0 || day > daysInMonths[monthIndex]) {
            throw new IllegalArgumentException(String.format("Invalid Date: %d %s %d",year,month,day));
        }

        if (goForwardOneDay) {
            goForwardOneDay();
        }

    }

    private void goForwardOneDay() {
        if (month.equals("dec")) {
            month = "jan";
            year += 1;
            day = 1;
            monthIndex = 0;
            return;
        }

        day += 1;
        if (day > daysInMonths[monthIndex]) {
            day = 1;
            monthIndex += 1;
            month = months[monthIndex];
        }
    }

    public static Date dateFromString(String dateString){
        String[] split = dateString.split(" ");
        int year = Integer.parseInt(split[0]);
        int day = Integer.parseInt(split[2]);
        String month = split[1].replace(" ","");
        int montHIndex = -1;

        for (int i = 0; i < months.length; i++) {
            if (month.equals(months[i])) {
                montHIndex = i + 1;
                break;
            }
        }

        return new Date(year,montHIndex,day);
    }

    public Date getDate() {
        return new Date(year,monthIndex+1,day);
    }

    public String getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getDay() {
        return day;
    }
}
