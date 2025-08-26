package CoderallyVirtual;

import java.util.HashMap;
import java.util.Scanner;

public class DeliveryDroneOptimizer {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        int  packageCount = Integer.parseInt(line.split(" ")[0]);
        int  maxWeight = Integer.parseInt(line.split(" ")[1]);
        HashMap<Integer,Integer>  itemList= new HashMap<>();
        for(int i = 0; i < packageCount; i++) {

            String line2 = sc.nextLine();
            int itemWeight = Integer.parseInt(line2.split(" ")[0]);
            int itemValue = Integer.parseInt(line2.split(" ")[1]);
            itemList.put(itemWeight,itemValue);

        }
        findMaxValue(itemList);


    }
    private  static void findMaxValue(HashMap<Integer,Integer> arr){
        
    }
}
