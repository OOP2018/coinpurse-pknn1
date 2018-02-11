package coinpurse;


import java.util.IllegalFormatException;

// TODO: 11/2/18 Create javadoc

public abstract class MoneyFactory {

    private static final MoneyFactory factory = null;

    private MoneyFactory() {
        // TODO: 11/2/18 Fill in the constructor.
    }

    public static MoneyFactory getInstance() {
        // TODO: 11/2/18 Create getInstance() Method.
        return null;
    }

    public abstract Valuable createMoney(double value);

    public Valuable createMoney(String value) {
        try {
            double convertedValue = Double.valueOf(value);
            if (convertedValue < 1) throw new IllegalArgumentException("Value can't be less than 1.");
            return createMoney(convertedValue);
        } catch (IllegalFormatException e) {
            throw new IllegalArgumentException("Value of money is not valid.");
        }
    }

}
