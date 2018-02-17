package coinpurse;


public class MalayMoneyFactory extends MoneyFactory {

    public MalayMoneyFactory() {
        super();
    }

    @Override
    public Valuable createMoney(double value) {
        double[] amount = {
                0.05, 0.10, 0.20, 0.50, 1, 2, 5, 10, 20, 50, 100
        };
        if (value < 0) throw new IllegalArgumentException("Value cannot be less than 0.");
        for (double am : amount) {
            if (value == am) {
                if (value < 1) return new Coin(value, "Ringgit");
                else return new BankNote(value, "Ringgit");
            }
        }
        throw new IllegalArgumentException("Cannot create " + value + " Ringgit money.");
    }
}
