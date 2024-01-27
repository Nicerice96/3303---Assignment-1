import java.util.ArrayList;

/**
 * Class that simulates the CoffeeShop; acts as the main class
 *
 * @author Zarif
 * @version 1.0 beta
 */

public class CoffeeShop {
    static Box box = new Box();


    /**
     * Begin's the simulated CoffeeShop environment
     *
     * @param args
     * @throws InterruptedException
     */

    public static void main(String[] args) throws InterruptedException {
        System.out.println("PROGRAM STARTING...");

        for (int iteration = 0; iteration < 20; iteration++) {

            Barista baristaCoffeeBeans = new Barista("COFFEEBEANS");
            Barista baristaSugar = new Barista("SUGAR");
            Barista baristaWater = new Barista("WATER");


            Agent agent = new Agent();
            agent.addIngredients("COFFEEBEANS", "WATER", "SUGAR");
            agent.start();
            agent.join();

            box.put(agent.getIngredients());
            System.out.println("Agent placed " + agent.getIngredients() + " on the counter");

            ArrayList<String> boxIngredient = (ArrayList<String>) box.get();

            System.out.println("Barista's retrieving ingredients...");

            baristaCoffeeBeans.add(boxIngredient);
            baristaWater.add(boxIngredient);
            baristaSugar.add(boxIngredient);

            baristaCoffeeBeans.setName("baristaCoffeeBeans");
            baristaCoffeeBeans.start();
            baristaSugar.setName("baristaSugar");
            baristaSugar.start();
            baristaWater.setName("baristaWater");
            baristaWater.start();

            baristaCoffeeBeans.join();
            baristaSugar.join();
            baristaWater.join();

            System.out.println("--------------------------BARISTAS HAVE FINISHED----------------------------");
            System.out.println("***************************************************************************************************");
        }
    }
}
