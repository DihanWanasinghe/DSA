package Dijkstras;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Runner {
    public static void main(String[] args) {

         //create the vertexes
         // add the edges
         // add them to an array
         //get the length of the array
         // select a radom number from 0 to length -1
         // that vertex would be the source vertex
         // wrap each  vertex with srapped vertex
         // source vertex distance should be 0
         // check the source vertex's edges
         // get the neighbor and the weight for each edge
         // calculate the new distance and updated it in the wrapped Vertex

    }

    //lazy implementation of dijkstra's algorithm
    private static  void dijkstras(Vertex[] vertexes, Vertex source) {
        Map<Vertex, Integer> distances = new HashMap<>();



        for (Vertex v : vertexes)
            distances.put(v, Integer.MAX_VALUE);

        WrappedVertex  wrappedSource = new WrappedVertex(source,0);
        // this needs to be changed
        // heap can be larger than the number of vertexes because multiple edges to the same node
            Heap heap = new Heap(vertexes.length);
            heap.insert(wrappedSource);
           distances.put(source, 0);
        while(!heap.isEmpty()) {
                WrappedVertex wrappedVertex = heap.minmunExtraction();
                if(wrappedVertex.getVertex().isExplored()) {
                    continue;
                }
                wrappedVertex.getVertex().setExplored(true);
                List<Edge> edges =  wrappedVertex.getVertex().getEdges() ;
                for(Edge edge : edges) {
                    Vertex neighbor = edge.getNeighbor();
                    if(neighbor.isExplored()) {
                        continue;
                    }
                    int newDistance = wrappedVertex.getDistance() + edge.getWeight();
                    WrappedVertex wrappedNeighbor = new WrappedVertex(neighbor , newDistance);
//                    heap.insert(wrappedNeighbor);
                    if(newDistance < distances.get(neighbor)) {
                        distances.put(neighbor, newDistance);
                        heap.insert(wrappedNeighbor);
                    }
                }
//                distances.put(wrappedVertex.getVertex(), wrappedVertex.getDistance());
            }
            for(Vertex vertex : distances.keySet()) {
                System.out.println("Vertex " + vertex.getId() + " has distance " + distances.get(vertex));
            }
    }
    private static  void resetVertexes(Vertex[] vertexes) {
        for(Vertex vertex : vertexes) {
            vertex.setExplored(false);
        }
    }

    private static  void dijkstrasBetter(Vertex[] vertexes, Vertex source) {
        Map<Vertex, Integer> distances = new HashMap<>();
        Heap heap = new Heap(vertexes.length);
        for (Vertex v : vertexes) {
            distances.put(v, Integer.MAX_VALUE);
            heap.insert(new WrappedVertex(v, Integer.MAX_VALUE));
        }
        distances.put(source, 0);
        heap.decreaseKey(source, 0);

        while(!heap.isEmpty()) {
            WrappedVertex wrappedVertex = heap.minmunExtraction();
            wrappedVertex.getVertex().setExplored(true);
            List<Edge> edges =  wrappedVertex.getVertex().getEdges() ;
            for(Edge edge : edges) {
                Vertex neighbor = edge.getNeighbor();
                if (neighbor.isExplored()) continue;

                int newDistance = wrappedVertex.getDistance() + edge.getWeight();
//                    heap.insert(wrappedNeighbor);
                if(newDistance < distances.get(neighbor)) {


                    distances.put(neighbor, newDistance);
                    heap.decreaseKey(neighbor, newDistance);
//                    heap.insert(wrappedNeighbor);
                }
            }
//                distances.put(wrappedVertex.getVertex(), wrappedVertex.getDistance());
        }
        for(Vertex vertex : distances.keySet()) {
            System.out.println("Vertex " + vertex.getId() + " has distance " + distances.get(vertex));
        }
    }

}
