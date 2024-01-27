import java.util.ArrayList;

/**
 * A class to represent an individual barista
 *
 * @author Zarif
 * @version 2.0
 */

public class Barista extends Thread {

    private String baristaIngredient;

    private ArrayList<String> inventory = new ArrayList<>();

    /**
     * Barista Constructor; sets default Barista ingredient
     *
     * @param baristaIngredient
     */
    Barista(String baristaIngredient) {
        this.baristaIngredient = baristaIngredient;
        inventory.add(baristaIngredient);
    }

    /**
     * An enum to represent the required elements needed to brew a Coffee
     */
    public enum MandatoryIngredients {
        WATER, SUGAR, COFFEEBEANS
    }

    /**
     * Allow for the addition of an ingredient into the current Barista's inventory
     *
     * @param ingredient
     */
    public synchronized void add(ArrayList<String> ingredient) {
        if (inventory.size() <= 3) {
            inventory.addAll(ingredient);
        } else {
            inventory.clear();
            inventory.add(baristaIngredient);
        }
    }

    /**
     * Determines whether all ingredients needed to brew a coffee are within the current barista's inventory or not
     *
     * @return boolean
     */

    public synchronized boolean brewCoffee() {
        if (!inventory.contains(baristaIngredient)) {
            System.out.println(Thread.currentThread() + " is waiting for " + baristaIngredient);
            return false;
        }

        for (MandatoryIngredients item : MandatoryIngredients.values()) {
            if (!inventory.contains(item.name())) {
                System.out.println("NOT ALL ITEMS FOUND: " + inventory + " MISSING: " + item);
                return false;
            }
        }
        System.out.println("ALL ITEMS FOUND!: " + inventory);
        return true;
    }

    /**
     * runs the current barista thread and determines whether the current thread was able to brew a coffee or not
     */
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Something went horribly wrong");
        }

        synchronized (this) {
            if (brewCoffee()) {
                System.out.println(Thread.currentThread().getName() + " SUCCESS :: " + Thread.currentThread().getName() + " BREWED A COFFEE!!!");
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    System.out.println("Something went horribly wrong");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " ERROR :: An item seems to be missing! - either waiting or failed");
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    System.out.println("Something went horribly wrong");
                }
            }
        }
    }
}
