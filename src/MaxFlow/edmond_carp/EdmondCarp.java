package MaxFlow.edmond_carp;

import MaxFlow.Edge;
import MaxFlow.Vertex;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EdmondCarp {



    public static int edmondCarpAlgorithm(Vertex source, Vertex sink){

        List<Vertex> visitedList = new ArrayList<>();
         Queue<Vertex> queue = new LinkedList<>() ;
         int maxFlow =0 ;

         while(true) {
             while (true) {

                 if (source.equals(sink)) {
                     Vertex initialSource = source;
                     int maxWeight = Integer.MAX_VALUE;


                     while (!source.getParent().isEmpty()) {

                         Vertex parent = source.getParent().keySet().iterator().next();
                         int endgeIndex = source.getParent().get(parent);
                         int currentWeight = parent.getEdges().get(endgeIndex).getWeight();
                         if (currentWeight < maxWeight) {
                             maxWeight = currentWeight;
                         }
                         source = parent;
                     }
                     maxFlow += maxWeight;

                     while (!initialSource.getParent().isEmpty()) {

                         Vertex parent = initialSource.getParent().keySet().iterator().next();
                         int endgeIndex = initialSource.getParent().get(parent);
                         int currentWeight = parent.getEdges().get(endgeIndex).getWeight();
                         Edge reverseEdge = null;

                         for (int i = 0; i < initialSource.getReverseEdges().size(); i++) {
                             if (initialSource.getReverseEdges().get(i).getNeighbour().equals(parent)) {
                                 reverseEdge = initialSource.getReverseEdges().get(i);
                                 break;
                             }
                         }
                         if (reverseEdge != null) {
                             int reverseEdgeCurrentWeight = reverseEdge.getWeight();
                             reverseEdge.setWeight(reverseEdgeCurrentWeight + maxWeight);

                         }
                         parent.getEdges().get(endgeIndex).setWeight(currentWeight - maxWeight);
                         initialSource.getParent().clear();
                         initialSource = parent;

                     }
                     queue.clear();
                     visitedList.clear();
                     break;
                 }
                 visitedList.add(source);
                 List<Edge> edges = source.getEdges();
                 for (int i = 0; i < edges.size(); i++) {
                     Edge edge = edges.get(i);
                     if (edge.getWeight() == 0  ){
                         continue;
                     }else if(!source.getParent().isEmpty() && (edge.getNeighbour() .equals(source.getParent().keySet().iterator().next()) )){
                         continue;
                     }else if(visitedList.contains(edge.getNeighbour())) {
                         continue;
                     }
                     if(edge.getNeighbour().getParent().isEmpty()) {
                         edge.getNeighbour().getParent().put(source, i);
                     }
                     if (edge.getNeighbour().equals(sink)) {
                         queue.clear();
                         queue.add(edge.getNeighbour());
                         break;
                     }
                     queue.add(edge.getNeighbour());
                 }
                 if (queue.isEmpty()) {
                     return maxFlow;
                 }
                 source = queue.poll();
             }
         }
    }
}
