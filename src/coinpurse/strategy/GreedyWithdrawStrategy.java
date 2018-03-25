package coinpurse.strategy;

import coinpurse.MoneyUtil;
import coinpurse.Valuable;
import coinpurse.ValueComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Strategy of withdrawing in descending ordered from the most value valuable.
 *
 * @author Pakanon Pantisawat
 */
public class GreedyWithdrawStrategy implements WithdrawStrategy {

    private Comparator<Valuable> comparator = new ValueComparator();
    /**
     * Withdraw from valuable with the most value in descending order.
     *
     * @param amount is the amount of money to withdraw, with currency.
     * @param money  is the contents that are available for possible withdraw.
     *               Must not be null, but may be an empty list.
     *               This list is not modified.
     * @return A list of valuable to withdraw.
     */
    @Override
    public List<Valuable> withdraw(Valuable amount, List<Valuable> money) {
        List<Valuable> sortedMoney = MoneyUtil.filterByCurrency(money, amount.getCurrency());
        sortedMoney.sort(comparator);

        List<Valuable> withdrawing = new ArrayList<>();
        double amountNeedToWithdraw = amount.getValue();
        for (int i = sortedMoney.size() - 1; i >= 0; i--) {
            if (amountNeedToWithdraw - sortedMoney.get(i).getValue() >= 0) {
                amountNeedToWithdraw -= sortedMoney.get(i).getValue();
                withdrawing.add(sortedMoney.get(i));
                sortedMoney.remove(i);
            }
        }

        if (amountNeedToWithdraw > 0) return null;
        return withdrawing;
    }
}
