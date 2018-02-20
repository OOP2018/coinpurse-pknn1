package coinpurse;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ResourceBundle;


public class MoneyFactoryTest {
    private MoneyFactory moneyFactory = null;
    private String currency;

    @Before
    public void setUp() {
        // Nothing to setup
    }

    /**
     * Set factory for the MoneyFactory to create instance.
     *
     * @param factoryClass is the name of factory class.
     */
    private void setFactory(String factoryClass) {
        MoneyFactory moneyFactory = null;
        try {
            moneyFactory = (MoneyFactory) Class.forName("coinpurse." + factoryClass).newInstance();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            System.out.println("Cannot create Money Factory.");
            System.exit(0);
        }
        if (moneyFactory == null) System.exit(0);
        MoneyFactory.setFactory(moneyFactory);
        this.moneyFactory = MoneyFactory.getInstance();
        if (moneyFactory.getClass().equals(ThaiMoneyFactory.class)) currency = "Baht";
        else if (moneyFactory.getClass().equals(MalayMoneyFactory.class)) currency = "Ringgit";
    }

    /**
     * Make valuable based on value and currency.
     * @param value value of the money in the specific currency.
     * @return new Money object.
     */
    private Valuable makeValuable(double value) {
        if (currency.equals("Baht")) {
            if (value == 1 || value == 2 || value == 5 || value == 10)
                return new Coin(value, currency);
            else if (value == 20 || value == 50 || value == 100 || value == 500 || value == 1000) {
                return new BankNote(value, currency);
            }
        } else if (currency.equals("Ringgit")) {
            if (value == 0.05 || value == 0.1 || value == 0.2 || value == 0.5)
                return new Coin(value, currency);
            else if (value == 1 || value == 2 || value == 5 || value == 10 || value == 20 || value == 50 || value == 100)
                return new BankNote(value, currency);
        }
        throw new IllegalArgumentException("Cannot create " + value + " " + currency);
    }

    /**
     * Test creating factory by checking class of the instance.
     */
    @Test
    public void testCreateFactory() {
        setFactory("ThaiMoneyFactory");
        assertEquals(ThaiMoneyFactory.class, moneyFactory.getClass());
        assertNotEquals(MalayMoneyFactory.class, moneyFactory.getClass());
        assertEquals(currency, "Baht");
        setFactory("MalayMoneyFactory");
        assertEquals(MalayMoneyFactory.class, moneyFactory.getClass());
        assertNotEquals(ThaiMoneyFactory.class, moneyFactory.getClass());
        assertEquals(currency, "Ringgit");
    }

    /**
     * Test creating money that has valid value.
     */
    @Test
    public void testCreateLegalMoney() {
        setFactory("ThaiMoneyFactory");
        double[] thaiValue = {1, 2, 5, 10, 20, 50, 100, 500, 1000};
        for (double val : thaiValue) {
            assertEquals(makeValuable(val), moneyFactory.createMoney(val));
        }
        setFactory("MalayMoneyFactory");
        double[] malayValue = {0.05, 0.10, 0.20, 0.50, 1, 2, 5, 10, 20, 50, 100};
        for (double val : malayValue) {
            assertEquals(makeValuable(val), moneyFactory.createMoney(val));
        }
    }


    /**
     * Test creating money that has invalid value in Thai Baht.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateIllegalThaiMoney() {
        setFactory("ThaiMoneyFactory");
        moneyFactory.createMoney(0.9);
    }

    /**
     * Test creating money that has invalid value in Malaysia Ringgit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateIllegalMalayMoney() {
        setFactory("MalayMoneyFactory");
        moneyFactory.createMoney(0.9);
    }

    /**
     * Test serial number of the factory to be unique.
     */
    @Test
    public void testBankNoteSerialNumber() {
        double value = 20;
        setFactory("ThaiMoneyFactory");
        BankNote bankNote = (BankNote) moneyFactory.createMoney(value);
        long prevSerialNumber = bankNote.getSerial();
        for (int round = 0; round < 5; round++) {
            bankNote = (BankNote) moneyFactory.createMoney(value);
            assertNotEquals(bankNote.getSerial(), prevSerialNumber);
            prevSerialNumber = bankNote.getSerial();
        }
        setFactory("MalayMoneyFactory");
        bankNote = (BankNote) moneyFactory.createMoney(value);
        prevSerialNumber = bankNote.getSerial();
        for (int round = 0; round < 5; round++) {
            bankNote = (BankNote) moneyFactory.createMoney(value);
            assertNotEquals(bankNote.getSerial(), prevSerialNumber);
            prevSerialNumber = bankNote.getSerial();
        }
    }
}

