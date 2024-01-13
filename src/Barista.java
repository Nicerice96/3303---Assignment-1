import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class Barista extends Thread{


    private String baristaIngredient;

    BlockingQueue<String> queue;



    Barista(String baristaIngredient, BlockingQueue<String> queue){
        this.baristaIngredient = baristaIngredient;
        this.queue = queue;


    }




    public enum MandatoryIngredients{
      WATER, SUGAR, COFFEEBEANS

    };

    public boolean brewCoffee(){

        ArrayList<String> localIngredientList = new ArrayList<>(queue);
        localIngredientList.add(baristaIngredient);


        if (!localIngredientList.contains(baristaIngredient)) {
            System.out.println(Thread.currentThread() + " is waiting for " + baristaIngredient);
            return false;
        }

        for (MandatoryIngredients item : MandatoryIngredients.values()){

            if (!localIngredientList.contains(item.name())){
                System.out.println("NOT ALL ITEMS FOUND: " + localIngredientList + " MISSING: " + item);
                return false;

            }
        }
        System.out.println("ALL ITEMS FOUND!: " + localIngredientList);

        return true;


    }


    @Override
    public void run(){


        System.out.println("Barista's retrieving ingredients...");
        try{
            Thread.sleep(2000);

        }catch (Exception e){
            System.out.println("Something went horribly wrong");
        }

        if (brewCoffee()) {
            System.out.println(Thread.currentThread() + " SUCCESS :: Brewed a Coffee!");

            try{
                Thread.sleep(2000);

            }catch (Exception e){
                System.out.println("Something went horribly wrong");
            }


        } else {
            System.out.println(Thread.currentThread() + " ERROR :: An item seems to be missing! - either waiting or failed");
            try{
                Thread.sleep(2000);

            }catch (Exception e){
                System.out.println("Something went horribly wrong");
            }

        }





    }
}
