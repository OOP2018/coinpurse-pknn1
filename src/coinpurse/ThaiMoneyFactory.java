package coinpurse;

public class ThaiMoneyFactory extends MoneyFactory {

    public ThaiMoneyFactory() {
        super();
    }

    @Override
    public Valuable createMoney(double value) {
        if (value < 1) throw new IllegalArgumentException("Value can't be less than 1.");
        if (value >= 20) return new BankNote(value, "Baht");
        return new Coin(value, "Baht");
    }
}
