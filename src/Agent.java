import java.util.ArrayList;
import java.util.Random;

/**
 * Agent acts as the "supplier" and generates ingredients for the baristas to use
 *
 * @author Zarif
 * @version 2.0
 */


public class Agent extends Thread {

    private ArrayList<String> ingredients = new ArrayList<>();
    private ArrayList<String> agentProducedIngredients = new ArrayList<>();

    /**
     * Adds items to the agent's inventory to select from later
     *
     * @param itemOne
     * @param itemTwo
     * @param itemThree
     */

    public void addIngredients(String itemOne, String itemTwo, String itemThree) {
        agentProducedIngredients = new ArrayList<>();
        ingredients.add(itemOne);
        ingredients.add(itemTwo);
        ingredients.add(itemThree);
    }

    /**
     * returns the randomly selected ingredients in the form of an arraylist
     *
     * @return ArrayList<String>
     */
    public ArrayList<String> getIngredients() {
        return agentProducedIngredients;

    }

    /**
     * runs the current Agent thread and randomly selects 2 ingredients from the agent's inventory
     */
    @Override
    public void run() {
        Random random = new Random();

        for (int i = 0; i < ingredients.size() - 1; i++) {
            int randIndex = random.nextInt(ingredients.size());
            String producedIngredient = ingredients.get(randIndex);

            agentProducedIngredients.add(producedIngredient);
            System.out.println("Agent produced " + producedIngredient);


            try {
                Thread.sleep(2000);

            } catch (Exception e) {
                System.out.println("Something went horribly wrong");
            }

        }

    }


}