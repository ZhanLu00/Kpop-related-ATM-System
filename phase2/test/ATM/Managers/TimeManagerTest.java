package ATM.Managers;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;


public class TimeManagerTest {
    TimeManager timeManager;

    @Before
    public void setUp(){
        timeManager = new TimeManager(2019, 3, 25, false);
    }

    @Test
    public void testGoForwardOneDay(){
        assertEquals(timeManager.getDate(), new Date(2019, 3, 25));
        TimeManager timeManager1 = new TimeManager(2019, 3, 25, true);
        assertEquals(timeManager1.getDate(), new Date(2019, 3, 26));
    }

    @Test
    public void testDateStringConversion(){
        assertEquals(TimeManager.dateFromString("2019 mar 25"), new Date(2019, 3, 25));
        assertEquals(TimeManager.dateToString(new Date(2019, 3, 25)), "2019 mar 25");
    }

}
