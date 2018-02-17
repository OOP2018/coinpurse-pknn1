package coinpurse;

public class ThaiMoneyFactory extends MoneyFactory {
    private static long nextSerialNumber = 100_000_000L;

    public ThaiMoneyFactory() {
        super();
    }

    @Override
    public Valuable createMoney(double value) {
        double[] amount = {
                1D, 2D, 5D, 10D, 20D, 50D, 100D, 500D, 1000D
        };
        if (value < 0) throw new IllegalArgumentException("Value can't be less than 0.");
        for (double am : amount) {
            if (value == am) {
                if (value < 20) return new Coin(value, "Baht");
                else {
                    BankNote bankNote = new BankNote(value, "Baht");
                    bankNote.setSerialNumber(nextSerialNumber++);
                    return bankNote;
                }
            }
        }
        throw new IllegalArgumentException("Cannot create " + value + "Baht.");
    }
}
