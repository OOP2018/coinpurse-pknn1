package coinpurse;

import coinpurse.strategy.NeverWithdrawStrategy;
import coinpurse.strategy.WithdrawStrategy;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

/**
 * A purse contains Valuables.
 * You can insert Valuables, withdraw money, check the balance,
 * and check if the purse is full.
 *
 * @author Pakanon Pantisawat
 */
public class Purse {

    private static Comparator<Valuable> comparator = new ValueComparator();
    /**
     * Capacity is maximum number of items the purse can hold.
     * Capacity is set when the purse is created and cannot be changed.
     */
    private final int capacity;
    /**
     * Collection of objects in the purse.
     */
    private List<Valuable> money;

    private WithdrawStrategy strategy;

    /**
     * Create a purse with a specified capacity.
     *
     * @param capacity is maximum number of Valuables you can put in purse.
     */
    public Purse(int capacity) {
        money = new ArrayList<>();
        this.capacity = capacity;
        strategy = new NeverWithdrawStrategy();
    }

    /**
     * Count and return the number of Valuables in the purse.
     * This is the number of valuable in the purse, not their value.
     *
     * @return the number of valuable items in the purse.
     */
    public int count() {
        return money.size();
    }

    /**
     * Get the total value of all items in the purse.
     *
     * @return the total value of items in the purse.
     */
    public double getBalance() {
        double total = 0;
        for (Valuable valuable : money)
            total += valuable.getValue();

        return total;
    }


    /**
     * Return the capacity of the purse.
     *
     * @return the capacity
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Test whether the purse is full.
     * The purse is full if number of items in purse equals
     * or greater than the purse capacity.
     *
     * @return true if purse is full.
     */
    public boolean isFull() {
        return count() >= this.capacity;
    }

    /**
     * Insert a Valuable into the purse.
     * The Valuable is only inserted if the purse has space for it
     * and the Valuable has positive value.  No worthless Valuables!
     *
     * @param valuable is a Valuable object to insert into purse
     * @return true if valuable can be inserted, false if can't insert
     */
    public boolean insert(Valuable valuable) {
        return !isFull() && !(valuable.getValue() <= 0) && money.add(valuable);
    }

    /**
     * Withdraw the requested amount if possible in strategy set.
     * @param amount is the amount to withdraw.
     * @return A list of valuable withdrew from the purse.
     */
    public Valuable[] withdraw(Valuable amount) {
        if (amount == null) return null;
        if (amount.getValue() <= 0) {
            System.out.println("Withdraw amount that is less than 0 is not allowed.");
            return null;
        }

        List<Valuable> withdrawing = strategy.withdraw(amount, money);
        money.removeAll(withdrawing);
        return withdrawing.toArray(new Valuable[withdrawing.size()]);
    }

    /**
     * Withdraw the requested amount of money with currency of "Baht".
     * Return an array of Valuables withdrawn from purse,
     * or return an empty list if cannot withdraw the amount requested.
     *
     * @param amount is the amount to withdraw
     * @return array of Valuable objects for money withdrawn,
     * or an empty list if cannot withdraw requested amount.
     */
    public Valuable[] withdraw(double amount) {
        Valuable valuableAmount = new Money(amount, "Baht");
        return withdraw(valuableAmount);
    }

    /**
     * toString returns a string description of the purse contents.
     * It can return whatever is a useful description.
     */
    public String toString() {
        return String.format("This purse have %d valuables with %d max capacity and total balance %.2f", money.size(), capacity, getBalance());
    }

    public void setStrategy(WithdrawStrategy strategy) {
        this.strategy = strategy;
    }
}