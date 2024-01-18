import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Random;

public class Agent extends Thread{

    private ArrayList<String> ingredients = new ArrayList<>();
    private ArrayList<String> agentProducedIngredients = new ArrayList<>();


    public void addIngredients(String itemOne, String itemTwo, String itemThree){
        agentProducedIngredients = new ArrayList<>();
        ingredients.add(itemOne);
        ingredients.add(itemTwo);
        ingredients.add(itemThree);
    }

    @Override
    public void run(){




        Random random = new Random();

        for (int i = 0; i < ingredients.size()-1; i++){
            int randIndex = random.nextInt(ingredients.size());
            String producedIngredient = ingredients.get(randIndex);

            agentProducedIngredients.add(producedIngredient);
            System.out.println("Agent produced " + producedIngredient);


            try{
                Thread.sleep(2000);

            }catch (Exception e){
                System.out.println("Something went horribly wrong");
            }

        }

    }

    public ArrayList<String> getIngredients(){
        return agentProducedIngredients;

    }


}