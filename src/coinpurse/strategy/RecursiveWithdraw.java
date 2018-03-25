package coinpurse.strategy;

import coinpurse.Money;
import coinpurse.MoneyUtil;
import coinpurse.Valuable;

import java.util.ArrayList;
import java.util.List;

/**
 * Strategy to withdraw with recursion method.
 *
 * @author Pakanon Pantisawat
 */
public class RecursiveWithdraw implements WithdrawStrategy {
    /**
     * Withdraw with recursion
     *
     * @param amount is the amount of money to withdraw, with currency.
     * @param money  is the contents that are available for possible withdraw.
     *               Must not be null, but may be an empty list.
     *               This list is not modified.
     * @return A list of possible way to withdraw money or return null if doesn't exists.
     */
    @Override
    public List<Valuable> withdraw(Valuable amount, List<Valuable> money) {
        if (amount.getValue() == 0 || money.isEmpty()) return null;
        List<Valuable> filtered = MoneyUtil.filterByCurrency(money, amount.getCurrency());
        return possibleWithdraw(amount, filtered);
    }

    /**
     * Get a possible way to withdraw.
     * @param amount is an amount to withdraw.
     * @param money is a list of valuable to withdraw.
     * @return A possible way to withdraw.
     */
    private List<Valuable> possibleWithdraw(Valuable amount, List<Valuable> money) {
        if (amount.getValue() == 0) {
            System.out.println("K");
            return new ArrayList<>();
        }
        if (money.size() == 0 || amount.getValue() < 0) {
            System.out.println("A");
            return null;
        }
        Valuable fromFirst = new Money(amount.getValue() - money.get(0).getValue(), amount.getCurrency());
        List<Valuable> temp = possibleWithdraw(fromFirst, money.subList(1, money.size()));
        if (temp == null)
            return possibleWithdraw(amount, money.subList(1, money.size()));
        else {
            temp.add(money.get(0));
            return temp;
        }

    }


}
