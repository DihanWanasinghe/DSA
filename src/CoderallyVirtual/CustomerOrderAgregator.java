package CoderallyVirtual;

import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class CustomerOrderAgregator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        TreeMap<String, Integer> orderMap = new TreeMap<>();

        for(int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String name = line.split(" ")[0];
            int itemCount = Integer.parseInt( line.split(" ")[1]);

            if(orderMap.containsKey(name)) {
                int currentCount = orderMap.get(name);
                int updatedCount = currentCount + itemCount;
                orderMap.put(name,updatedCount );
            } else {
                orderMap.put(name, itemCount);
            }
        }
        int count = 0;
        int length = orderMap.size();

        System.out.print("[");
        for (String key : orderMap.keySet()) {
            System.out.print("[\"" + key + "\", " + orderMap.get(key) + "]");
            if(count < length) {
                System.out.print(", " );

            }
            count++;

        }
        System.out.println("]");

    }
}
