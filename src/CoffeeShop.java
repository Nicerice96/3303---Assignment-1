import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CoffeeShop {
    private static final BlockingQueue<String> ingredientBuffer = new LinkedBlockingQueue<>();

    public static void main (String [] args) throws InterruptedException {

        System.out.println("PROGRAM STARTING...");



        for (int iteration = 0; iteration < 5; iteration++) {

            ingredientBuffer.clear();


            Agent agent = new Agent();
            agent.addIngredients("COFFEEBEANS", "WATER", "SUGAR");
            agent.start();
            agent.join();

            for (String item : agent.getIngredient()) {
                ingredientBuffer.add(item);
            }

            System.out.println("--------------------------AGENT HAS FINISHED PRODUCING----------------------------");
            System.out.println("Placing items on counter...");


            Barista baristaCofeeBeans = new Barista("COFFEEBEANS", ingredientBuffer);
            Barista baristaSugar = new Barista("SUGAR", ingredientBuffer);
            Barista baristaWater = new Barista("WATER", ingredientBuffer);

            baristaCofeeBeans.setName("baristaCoffeeBeans");
            baristaCofeeBeans.start();
            baristaSugar.setName("baristaSugar");
            baristaSugar.start();
            baristaWater.setName("baristaWater");
            baristaWater.start();

            baristaCofeeBeans.join();
            baristaSugar.join();
            baristaWater.join();

            System.out.println("--------------------------BARISTA'S HAVE FINISHED----------------------------");
            System.out.println("***************************************************************************************************");

        }
    }





    }
