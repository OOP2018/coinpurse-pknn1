package coinpurse;

public abstract class MoneyFactory {
    private static MoneyFactory ourInstance = null;

    protected MoneyFactory() {

    }

    public static MoneyFactory getInstance() {
        return ourInstance;
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
