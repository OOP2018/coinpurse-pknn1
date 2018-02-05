package coinpurse;

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

    /**
     * Create a purse with a specified capacity.
     *
     * @param capacity is maximum number of Valuables you can put in purse.
     */
    public Purse(int capacity) {
        money = new ArrayList<>();
        this.capacity = capacity;
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
        // if the purse is already full then can't insert anything.
        return !isFull() && !(valuable.getValue() <= 0) && money.add(valuable);
    }

    /**
     * Withdraw the requested amount of money.
     * Return an array of Valuables withdrawn from purse,
     * or return null if cannot withdraw the amount requested.
     *
     * @param amount is the amount to withdraw
     * @return array of Valuable objects for money withdrawn,
     * or null if cannot withdraw requested amount.
     */
    public Valuable[] withdraw(double amount) {

        /*
         * See lab sheet for outline of a solution,
         * or devise your own solution.
         * The idea is to be greedy.
         * Try to withdraw the largest Valuables possible.
         * Each time you choose a Valuable as a candidate for
         * withdraw, add it to a temporary list and
         * decrease the amount (remainder) to withdraw.
         *
         * If you reach a point where amountNeededToWithdraw == 0
         * then you found a solution!
         * Now, use the temporary list to remove Valuables
         * from the money list, and return the temporary
         * list (as an array).
         */

        // Did we get the full amount?
        // This code assumes you decrease amount each time you remove a Valuable.
        // Your code might use some other variable for the remaining amount to withdraw.
		/*if ( amountNeededToWithdraw != 0 )
		{	
			// failed. Don't change the contents of the purse.
			
		}*/

        // Success.
        // Remove the Valuables you want to withdraw from purse,
        // and return them as an array.
        // Use list.toArray( array[] ) to copy a list into an array.
        // toArray returns a reference to the array itself.
        if (amount < 0) {
            System.out.println("Withdraw amount that is negative is not allowed");
            return null;
        }

        money.sort(comparator);
        List<Valuable> withdrawing = new ArrayList<>();
        double amountNeedToWithdraw = amount;
        for (int i = money.size() - 1; i >= 0; i--) {
            if (amountNeedToWithdraw - money.get(i).getValue() >= 0) {
                amountNeedToWithdraw -= money.get(i).getValue();
                withdrawing.add(money.get(i));
                money.remove(i);
            }
        }

        if (amountNeedToWithdraw > 0) {
            money.addAll(withdrawing);
            return null;
        }
        return withdrawing.toArray(new Valuable[withdrawing.size()]);
    }

    /**
     * toString returns a string description of the purse contents.
     * It can return whatever is a useful description.
     */
    public String toString() {
        return String.format("This purse have %d valuables with %d max capacity and total balance %.2f", money.size(), capacity, getBalance());
    }

}