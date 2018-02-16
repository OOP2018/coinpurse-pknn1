package coinpurse;

public class MalayMoneyFactory extends MoneyFactory {


    public MalayMoneyFactory() {
        super();
    }

    @Override
    public Valuable createMoney(double value) {
        if (value < 1) throw new IllegalArgumentException("Value can't be less than 1.");
        if (value >= 1) return new BankNote(value, "Ringgit");
        return new Coin(value, "Sen");
    }
}
