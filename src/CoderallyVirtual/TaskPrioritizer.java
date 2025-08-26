package CoderallyVirtual;



import java.util.PriorityQueue;
import java.util.Scanner;

public class TaskPrioritizer {

    static class  Task {
        String name;
        int priority;
        int order;

        public  Task(String name, int priority, int order) {
            this.name = name;
            this.priority = priority;
            this.order = order;
        }
    }

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = Integer.parseInt(sc.nextLine());

            PriorityQueue<Task> priorityQueue = new PriorityQueue<>(
                    (a, b) -> {
                        if (a.priority != b.priority) {
                            return b.priority - a.priority;
                        }
                        return Integer.compare(a.order, b.order);
                    }
            );



            for (int i = 0; i < n; i++) {

                String[] parts = sc.nextLine().split(" ");
                String command = parts[0];
                if(command.equals("ADD")) {
                    String taskName = parts[1];
                    int priority = Integer.parseInt(parts[2]);
                    int order = i;
                    Task task = new Task(taskName, priority, order);
                    priorityQueue.add(task);
                }else if(command.equals("RETRIEVE")) {
                    if(!priorityQueue.isEmpty()) {
                        Task task = priorityQueue.poll();
                        System.out.println(task.name);
                    }
                }

            }

        }

}
