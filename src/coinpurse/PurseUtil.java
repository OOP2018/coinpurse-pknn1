package coinpurse;

/**
 * Class for testing purse functional.
 * Contain method for testing purse with certain test case.
 *
 * @author Pakanon Pantisawat
 */

public class PurseUtil {

    public static void purseTest() {
        Purse purse = new Purse(6);
        System.out.println("Before adding any coins in the purse");
        System.out.printf("There is %.2f in the purse and purse isFull() : %s%n", purse.getBalance(), purse.isFull());
        System.out.printf("Adding %d coins, adding success: %s%n", 5, purse.insert(new Coin(5, "THB")));
        System.out.printf("Adding %d coins, adding success: %s%n", 10, purse.insert(new Coin(10, "THB")));
        System.out.printf("Adding %d coins, adding success: %s%n", 2, purse.insert(new Coin(2, "THB")));
        System.out.printf("Adding %d coins, adding success: %s%n", 0, purse.insert(new Coin(0, "THB")));
        System.out.printf("Adding %d coins, adding success: %s%n", 1, purse.insert(new Coin(1, "THB")));
        System.out.printf("Adding %d coins, adding success: %s%n", 5, purse.insert(new Coin(5, "THB")));
        System.out.printf("Adding %d coins, adding success: %s%n", 10, purse.insert(new Coin(10, "THB")));
        System.out.printf("Adding %d coins, adding success: %s%n", 1, purse.insert(new Coin(1, "THB")));
        System.out.printf("Adding %d coins, adding success: %s%n", 10, purse.insert(new Coin(10, "THB")));


        System.out.println("After added the coins in the purse");

        System.out.printf("You have %d coin(s) in the purse, isFull: %s, total balance: %.2f%n", purse.count(), purse.isFull(), purse.getBalance());
        System.out.printf("toString returns: %s%n", purse.toString());

        System.out.println("Test withdraw");

        double amount = 10;
        Coin[] withdrew = purse.withdraw(amount);
        System.out.printf("Withdraw %.2f Baht Test. is null?: %s%n", amount, withdrew == null);
        if (withdrew != null) {
            System.out.printf("Withdrew consists of [");
            for (Coin aCoin : withdrew) {
                System.out.print(aCoin.toString() + " ");
            }
            System.out.print("]");
            System.out.println();
        }

        System.out.printf("You have %d coin(s) in the purse, isFull: %s, total balance: %.2f%n", purse.count(), purse.isFull(), purse.getBalance());

        amount = 19;
        withdrew = purse.withdraw(amount);
        System.out.printf("Withdraw %.2f Baht Test. is null?: %s%n", amount, withdrew == null);
        if (withdrew != null) {
            System.out.printf("Withdrew consists of [");
            for (Coin aCoin : withdrew) {
                System.out.print(aCoin.toString() + " ");
            }
            System.out.print("]");
            System.out.println();
        }

        System.out.printf("You have %d coin(s) in the purse, isFull: %s, total balance: %.2f%n", purse.count(), purse.isFull(), purse.getBalance());

    }

    public static void main(String[] args) {
        purseTest();
    }

}
