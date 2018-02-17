package coinpurse;

public abstract class MoneyFactory {
    private static MoneyFactory instance = null;


    protected MoneyFactory() {

    }

    public static MoneyFactory getInstance() {
        return instance;
    }

    public static void setFactory(MoneyFactory factory) {
        instance = factory;
    }

    public abstract Valuable createMoney(double value);

    public Valuable createMoney(String value) {
        try {
            return createMoney(Double.parseDouble(value));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Can't pass the value as a number.");
        }
    }
}
