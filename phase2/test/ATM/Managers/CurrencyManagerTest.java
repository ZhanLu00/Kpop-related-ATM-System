package ATM.Managers;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class CurrencyManagerTest {
    ArrayList<Object[]> rates = new ArrayList<>();
    CurrencyManager currencyManager;

    @Before
    public void setUp() {
        rates.add(new Object[]{"USD", 0.75});
        rates.add(new Object[]{"CNY", 5.00});
        currencyManager = new CurrencyManager(rates);
    }

    @Test
    public void testGetSetRate() {
        assertEquals(currencyManager.getRate("USD"), 0.75, 0.01);
        assertEquals(currencyManager.getRate("CNY"), 5.00, 0.01);
        assertFalse(currencyManager.setRate("hello", 3.00));
        assertEquals(currencyManager.getRate("hello"), -1, 0.01);
        assertFalse(currencyManager.setRate("ABC", -3.00));
        assertEquals(currencyManager.getRate("ABC"), -1, 0.01);
        assertTrue(currencyManager.setRate("XYZ", 3));
        assertEquals(currencyManager.getRate("XYZ"), 3, 0.01);
    }

}
