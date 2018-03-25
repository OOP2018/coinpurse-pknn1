package coinpurse.strategy;

import coinpurse.Valuable;

import java.util.Collections;
import java.util.List;

/**
 * Strategy of withdraw which always return empty list.
 *
 * @author Pakanon Pantisawat
 */
public class NeverWithdrawStrategy implements WithdrawStrategy {
    /**
     * Never withdraw anything and return an empty list.
     * @param amount is the amount of money to withdraw, with currency.
     * @param money  is the contents that are available for possible withdraw.
     *               Must not be null, but may be an empty list.
     *               This list is not modified.
     * @return null
     */
    @Override
    public List<Valuable> withdraw(Valuable amount, List<Valuable> money) {
        return null;
    }
}
