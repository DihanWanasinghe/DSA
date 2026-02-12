package A_Star;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Runner {
    public static void main(String[] args) {

    }


    private static int HCaclulation(Vertex vertex){

        // depends on the problem domain,
        // this can be a simple heuristic function that estimates the cost from the current vertex to the target vertex.
        // For example, if we are working with a grid-based pathfinding problem, we can use the Manhattan distance or Euclidean distance as the heuristic.

        return 0;

    }

    private static  void AStart(Vertex source , Vertex target){

        Map<Vertex,Integer> verticesWithTotalEdgeCost = new HashMap<>();
        Map<Vertex,Vertex> parentMap = new HashMap<>();

        Heap heap = new Heap();
        int h = HCaclulation(source);
        heap.insert(new WrappedVertex(source ,h ,0));
        parentMap.put(source, null);
        verticesWithTotalEdgeCost.put(source, 0);
        while(!heap.isEmpty()) {
            WrappedVertex wrappedVertex = heap.minmunExtraction();
            wrappedVertex.getVertex().setExplored(true);
            if (wrappedVertex.getVertex().getId() == target.getId()) {
                ArrayList<Integer> path = new ArrayList<>();
                System.out.println("Target found with total cost: " + verticesWithTotalEdgeCost.get(target));
                Vertex vertex = wrappedVertex.getVertex();
                while (vertex != null) {

                    path.add(0, vertex.getId());
                    vertex = parentMap.get(vertex);

                }
                for(int id : path)
                    System.out.print(id + " -> ");
                return;
            }
            for (Edge edge : wrappedVertex.getVertex().getEdges()) {
                Vertex neighbor = edge.getNeighbor();
                int parentEdgeCost = wrappedVertex.getEdgeCost() + edge.getWeight();
                int hCost = HCaclulation(neighbor);
                int totalCost = parentEdgeCost + hCost;
                if(neighbor.isExplored())
                    continue;

                if(!verticesWithTotalEdgeCost.containsKey(neighbor) ) {
                    WrappedVertex wrappedVertex1 = new WrappedVertex(neighbor, totalCost, parentEdgeCost);
                    heap.insert(wrappedVertex1);
                    verticesWithTotalEdgeCost.put(neighbor, parentEdgeCost);
                    parentMap.put(neighbor, wrappedVertex.getVertex());
                }else if( verticesWithTotalEdgeCost.get(neighbor) > parentEdgeCost) {
                    heap.decreaseKey(neighbor, totalCost);
                    verticesWithTotalEdgeCost.put(neighbor, parentEdgeCost);
                    parentMap.put(neighbor, wrappedVertex.getVertex());
                }


            }
        }

    }
}


