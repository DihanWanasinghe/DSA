package CoderallyVirtual;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class EmergencyDispatch {

    private static List<List<Edge>> graph;
    private static int n, m, startTime, targetNode;
    static class Edge {
        int to;
        int travelTime;
        int closureStart;
        int closureEnd;

        public Edge(int to, int travelTime, int closureStart, int closureEnd) {
            this.to = to;
            this.travelTime = travelTime;
            this.closureStart = closureStart;
            this.closureEnd = closureEnd;
        }

        public boolean canTraverse(int startTime) {
            if (closureStart == -1 && closureEnd == -1) {
                return true;
            }
            int endTime = startTime + travelTime - 1;
            return endTime < closureStart || startTime > closureEnd;
        }
    }

    static class State implements Comparable<State> {
        int node;
        int arrivalTime;

        public State(int node, int arrivalTime) {
            this.node = node;
            this.arrivalTime = arrivalTime;
        }

        @Override
        public int compareTo(State other) {
            return Integer.compare(this.arrivalTime, other.arrivalTime);
        }
    }




    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        n = Integer.parseInt(firstLine[0]);
        m = Integer.parseInt(firstLine[1]);
        startTime = Integer.parseInt(firstLine[2]);
        targetNode = Integer.parseInt(firstLine[3]);

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String[] edgeData = br.readLine().split(" ");
            int u = Integer.parseInt(edgeData[0]);
            int v = Integer.parseInt(edgeData[1]);
            int travelTime = Integer.parseInt(edgeData[2]);
            int closureStart = Integer.parseInt(edgeData[3]);
            int closureEnd = Integer.parseInt(edgeData[4]);
            graph.get(u).add(new Edge(v, travelTime, closureStart, closureEnd));
            graph.get(v).add(new Edge(u, travelTime, closureStart, closureEnd));
        }

        int result = findMinimumTime();
        System.out.println(result);
    }

    private static int findMinimumTime() {
        PriorityQueue<State> pq = new PriorityQueue<>();
        int[] minTime = new int[n + 1];
        boolean[] visited = new boolean[n + 1];

        Arrays.fill(minTime, Integer.MAX_VALUE);
        pq.offer(new State(1, startTime));
        minTime[1] = startTime;

        while (!pq.isEmpty()) {
            State current = pq.poll();
            int currentNode = current.node;
            int currentTime = current.arrivalTime;

            if (visited[currentNode]) {
                continue;
            }

            visited[currentNode] = true;

            if (currentNode == targetNode) {
                return currentTime - startTime;
            }

            for (Edge edge : graph.get(currentNode)) {
                int nextNode = edge.to;
                if (visited[nextNode]) {
                    continue;
                }
                if (edge.canTraverse(currentTime)) {
                    int newArrivalTime = currentTime + edge.travelTime;
                    if (newArrivalTime < minTime[nextNode]) {
                        minTime[nextNode] = newArrivalTime;
                        pq.offer(new State(nextNode, newArrivalTime));
                    }
                }
            }
        }
        return -1;
    }




}
