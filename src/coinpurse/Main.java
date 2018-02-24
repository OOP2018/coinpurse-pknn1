package coinpurse;

import java.util.*;

/**
 * A main class to create objects and connect objects together.
 * The user interface needs a reference to coin purse.
 * @author Pakanon Pantisawat
 */
public class Main {

    public static String init() {
        ResourceBundle bundle = ResourceBundle.getBundle("purse");
        String factoryClass = bundle.getString("moneyfactory");
        MoneyFactory factory = null;
        try {
            factory = (MoneyFactory) Class.forName(factoryClass).newInstance();
        } catch (ClassNotFoundException ce) {
            System.out.println("Class " + factoryClass + " was not found.");
        } catch (ClassCastException cce) {
            System.out.println(factoryClass + " is not MoneyFactory type.");
        } catch (Exception e) {
            System.out.println("Cannot create object.");
        }
        if (factory == null) System.exit(1);

        MoneyFactory.setFactory(factory);
        return bundle.getString("currency");
    }
/*
    *//**
     * Configure and start the application.
     * @param args not used
     */
    public static void main( String[] args ) {
        String currency = init();
        // 1. create a Purse
    	Purse purse = new Purse(10);
        // 2. create a ConsoleDialog with a reference to the Purse object
        ConsoleDialog ui = new ConsoleDialog(purse, currency);
        // 3. run the ConsoleDialog
    	ui.run();
    }

}
