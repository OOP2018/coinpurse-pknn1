package coinpurse;

public class MoneyFactoryDemo {

    private static void setFactory(String factoryClass) {
        MoneyFactory factory = null;
        try {
            factory = (MoneyFactory) Class.forName(factoryClass).newInstance();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            throw new IllegalArgumentException("Factory name is not valid.");
        }
        MoneyFactory.setFactory(factory);
    }

    private static Money makeThaiBaht(double value) {
        if (value == 1 || value == 2 || value == 5 || value == 10) return new Coin(value, "Baht");
        else if (value == 20 || value == 50 || value == 100 || value == 500 || value == 1000)
            return new BankNote(value, "Baht");
        throw new IllegalArgumentException("Cannot create " + value + " Baht.");
    }

    private static Money makeMalayRinggit(double value) {
        if (value == 0.05 || value == 0.1 || value == 0.2 || value == 0.5) return new Coin(value, "Ringgit");
        else if (value == 1 || value == 2 || value == 5 || value == 20 || value == 10 || value == 50 || value == 100)
            return new BankNote(value, "Ringgit");
        throw new IllegalArgumentException("Cannot create " + value + " Ringgit.");
    }

    private static void testCase(String testPrompt, Object expected, Object actual) {
        System.out.println(testPrompt + " test");
        System.out.printf("Expected: %s, Actual: %s, Result: %s%n", expected, actual, expected.equals(actual));
    }

    private static void testMoney(double[] amounts, String currency) {
        MoneyFactory moneyFactory = MoneyFactory.getInstance();
        System.out.println("Test creating money.");
        for (double amount : amounts) {
            Money money = null;
            if (currency.equalsIgnoreCase("Baht")) money = makeThaiBaht(amount);
            else if (currency.equalsIgnoreCase("Ringgit")) money = makeMalayRinggit(amount);
            testCase("Creating " + amount + " Baht", moneyFactory.createMoney(amount), money);
        }
    }

    public static void main(String[] args) {
        double[] amounts = {
                1, 2, 5, 10, 20, 50, 100, 500, 1000
        };
        setFactory("coinpurse.ThaiMoneyFactory");
        testMoney(amounts, "Baht");

        double[] amounts2 = {
                0.05, 0.10, 0.20, 0.50, 1, 2, 5, 10, 20, 50, 100
        };
        setFactory("coinpurse.MalayMoneyFactory");
        testMoney(amounts2, "Ringgit");
    }

}