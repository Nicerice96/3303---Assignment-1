import java.util.ArrayList;

public class Barista extends Thread {

    private String baristaIngredient;

    private ArrayList<String> totalIngredients = new ArrayList<>();

    Barista(String baristaIngredient) {
        this.baristaIngredient = baristaIngredient;
        totalIngredients.add(baristaIngredient);
    }

    public enum MandatoryIngredients {
        WATER, SUGAR, COFFEEBEANS
    }

    public synchronized void add(ArrayList<String> ingredient) {
        if (totalIngredients.size() <= 3) {
            totalIngredients.addAll(ingredient);
        } else {
            totalIngredients.clear();
            totalIngredients.add(baristaIngredient);
        }
    }

    public synchronized boolean brewCoffee() {
        if (!totalIngredients.contains(baristaIngredient)) {
            System.out.println(Thread.currentThread() + " is waiting for " + baristaIngredient);
            return false;
        }

        for (MandatoryIngredients item : MandatoryIngredients.values()) {
            if (!totalIngredients.contains(item.name())) {
                System.out.println("NOT ALL ITEMS FOUND: " + totalIngredients + " MISSING: " + item);
                return false;
            }
        }
        System.out.println("ALL ITEMS FOUND!: " + totalIngredients + "SUCCESS");
        return true;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Something went horribly wrong");
        }

        synchronized (this) {
            if (brewCoffee()) {
                System.out.println(Thread.currentThread().getName() + " SUCCESS :: " + Thread.currentThread().getName() +" BREWED A COFFEE!!!");
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
