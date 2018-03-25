package coinpurse.strategy;

import coinpurse.Valuable;

import java.util.List;

/**
 * Strategy for withdrawing money.
 *
 * @author Pakanon Pantisawat
 */
public interface WithdrawStrategy {
    /**
     * Find and return items from a collection whose total value equals
     * the requested amount.
     *
     * @param amount is the amount of money to withdraw, with currency.
     * @param money  is the contents that are available for possible withdraw.
     *               Must not be null, but may be an empty list.
     *               This list is not modified.
     * @return A list containing references from the money parameter (List) whose sum equals the amount, if a solution is found.
     *         If a solution isn't found, returns null.
     */
    public List<Valuable> withdraw(Valuable amount, List<Valuable> money);
}
