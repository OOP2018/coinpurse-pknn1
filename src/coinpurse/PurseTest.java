package coinpurse;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Test the Purse using JUnit.
 * This is a JUnit 4 test suite.  
 * 
 * IDEs (Eclipse, Netbeans, IntelliJ, BlueJ) include JUnit 4,
 * but you have to tell the IDE to add it to your project as a "Library".
 * To run these tests, right click on this file (in Project panel)
 * and choose Run As -> JUnit test
 * 
 * @author  Resident Evil
 * @version 2018.01.19
 */
public class PurseTest {
	/** tolerance for comparing two double values */
	private static final double TOL = 1.0E-6;
    private static final String CURRENCY = "Baht";
	
    /**
     * Sets up the test fixture.
     * Called before every test method.
     */
    @Before
    public void setUp() {
    	// nothing to initialize
    }

    /**
     * Make a valuable with the default currency. To save typing "new Coin(...)"
     */
    private Valuable makeValuable(double value) {
        if (value > 10) return new BankNote(value, CURRENCY);
        return new Coin(value, CURRENCY);
	}

    /** Easy test that the Purse constructor is working. */
    @Test
    public void testConstructor()
    {
        Purse purse = new Purse(3);
        assertEquals(3, purse.getCapacity());
        assertEquals(false, purse.isFull());
        assertEquals(0, purse.count());
    }


    /** Insert some valuables. Easy test. */
    @Test
    public void testInsert()
    {
        Purse purse = new Purse(3);
        Valuable banknote = makeValuable(20);
        Valuable coin1 = makeValuable(5);
        Valuable banknote2 = makeValuable(100);
        assertTrue(purse.insert(banknote));
        assertTrue( purse.insert(coin1));
        assertTrue(purse.insert(banknote2));
        assertEquals( 3, purse.count() );
        // purse is full so insert should fail
        assertFalse(purse.insert(makeValuable(10)) );
    }


	/** Insert should reject coin with no value. */
    @Test
    public void testInsertNoValue()
    {
        Purse purse = new Purse(3);
        Valuable fakeCoin = new Coin(0, CURRENCY);
        assertFalse( purse.insert(fakeCoin) );
    }


    @Test(timeout=1000)
    public void testIsFull()
    {   // borderline case (capacity 1)
        Purse purse = new Purse(1);
        assertFalse( purse.isFull());
        purse.insert(makeValuable(1) );
        assertTrue( purse.isFull() );
        // real test
        int capacity = 4;
        purse = new Purse(capacity);
        for(int k=1; k<=capacity; k++) {
            assertFalse(purse.isFull());
            purse.insert(makeValuable(k) );
        }
        // should be full now
        assertTrue( purse.isFull());
        assertFalse(purse.insert(makeValuable(5) ) );
    }

	/** Should be able to insert same coin many times,
	 *  since spec doesn't say anything about this.
	 */
	@Test(timeout=1000)
	public void testInsertSameCoin()
	{
		int capacity = 5;
		double value = 10.0;
		Purse purse = new Purse(capacity);
		Coin coin = new Coin(value, "THB");
		assertTrue( purse.insert(coin) );
		assertTrue( purse.insert(coin) ); // should be allowed
		assertTrue( purse.insert(coin) ); // should be allowed
		assertTrue( purse.insert(coin) ); // should be allowed
		assertTrue( purse.insert(coin) ); // should be allowed
		assertEquals( purse.getBalance(), 5*value, TOL);
	}

	/** Add one coin and remove it. */
	@Test(timeout=1000)
	public void testEasyWithdraw() {
		Purse purse = new Purse(10);
		double [] values = {1, 20, 0.5, 10}; // values of coins we will insert
		
		for(double value : values) {
            Valuable coin = makeValuable(value);
			assertTrue(purse.insert(coin));
			assertEquals(value,  purse.getBalance(), TOL);
            Valuable[] result = purse.withdraw(value);
			assertTrue( result != null );
			assertEquals( 1, result.length );
			assertSame(  coin, result[0] ); // should be same object
			assertEquals( 0, purse.getBalance(), TOL );
		}
	}
	

	/** Add 4 coins and then withdraw in pairs, but not in same order. */
	@Test(timeout=1000)
	public void testMultiWithdraw() {
		Purse purse = new Purse(10);
        Valuable[] Valuables = {makeValuable(5.0), makeValuable(10.0), makeValuable(1.0), makeValuable(5.0) };
		// insert them all
        for (Valuable Valuable : Valuables) assertTrue(purse.insert(Valuable));

        double amount1 = Valuables[1].getValue() + Valuables[3].getValue();
        double amount2 = Valuables[0].getValue() + Valuables[2].getValue();
		assertEquals(amount1+amount2, purse.getBalance(), TOL);

        Valuable[] wd1 = purse.withdraw(amount1);
		assertEquals(amount1, sum(wd1), TOL );
		
		assertEquals(amount2, purse.getBalance(), TOL);
        Valuable[] wd2 = purse.withdraw(amount2);
		
		// should be empty now
		assertEquals(0, purse.getBalance(), TOL );
	}
	

	/** Withdraw full amount in purse, using varying numbers of objects. */
	@Test(timeout=1000)
	public void testWithdrawEverything() {
		Purse purse = new Purse(10);
        // Valuables we want to insert and then withdraw.
		// Use values such that greedy will succeed, but not monotonic
        List<Valuable> Valuables = Arrays.asList(
                makeValuable(1.0), makeValuable(0.5), makeValuable(10.0), makeValuable(0.25), makeValuable(5.0)
        );
        // num = number of Valuables to insert and then withdraw
        for (int num = 1; num <= Valuables.size(); num++) {
			double amount = 0.0;
            List<Valuable> subList = Valuables.subList(0, num);
            for (Valuable c: subList) {
				purse.insert(c);
				amount += c.getValue();
			}
			// balance should be exactly what we just inserted
			assertEquals( amount, purse.getBalance(), TOL);
			// can we withdraw it all?
            Valuable[] result = purse.withdraw(amount);
			String errmsg = String.format("couldn't withdraw %.2f but purse has %s",
					amount, Arrays.toString(subList.toArray()) );
			assertNotNull( errmsg, result );
			// is the amount correct?
			assertEquals("Withdraw wrong amount", amount, sum(result), TOL);
			// should not be anything left in the purse
			assertEquals( 0.0, purse.getBalance(), TOL);
		}
	}


	@Test(timeout=1000)
	public void testImpossibleWithdraw() {
		Purse purse = new Purse(10);
		assertNull( purse.withdraw(1));
        purse.insert(makeValuable(20) );
		assertNull( purse.withdraw(1) );
		assertNull( purse.withdraw(19) );
		assertNull( purse.withdraw(21));
        purse.insert(makeValuable(20)); // now it has 20 + 20
		assertNull( purse.withdraw(30) );
    }

    /**
     * Sum the value of some Valuables.
     * @param Valuables array of Valuables
     * @return sum of values of the Valuables
     */
    private double sum(Valuable[] Valuables) {
        if (Valuables == null) return 0.0;
		double sum = 0;
        for (Valuable c : Valuables) if (c != null) sum += c.getValue();
		return sum;
	}
}


