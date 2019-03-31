package ATM.Managers;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;


public class BillManagerTest {
    BillManager billManager;
    @Before
    public void setUp(){
        billManager = new BillManager(20, 20, 20, 20, "alerts.txt");
    }

    @Test
    public void testDepositBills(){
        assertEquals(billManager.getFives(), 20);
        assertEquals(billManager.getTens(), 20);
        assertEquals(billManager.getTwenties(), 20);
        assertEquals(billManager.getFifties(), 20);
        billManager.deposit(1, 2, 3, 4);
        assertEquals(billManager.getFives(), 21);
        assertEquals(billManager.getTens(), 22);
        assertEquals(billManager.getTwenties(), 23);
        assertEquals(billManager.getFifties(), 24);
    }

    @Test
    public void testDepositAmount(){
        billManager.deposit(45);
        assertEquals(billManager.getFifties(), 20);
        assertEquals(billManager.getTwenties(), 22);
        assertEquals(billManager.getTens(), 20);
        assertEquals(billManager.getFives(), 21);
    }

    @Test
    public void testWithdrawable(){
        assertTrue(billManager.withdrawable(1000));
        assertFalse(billManager.withdrawable(1005));
        assertFalse(billManager.withdrawable(999));
        BillManager billManager1 = new BillManager(1, 2, 0, 1, "alerts.txt");
        assertFalse(billManager1.withdrawable(40));
        assertTrue(billManager1.withdrawable(20));
    }

}
