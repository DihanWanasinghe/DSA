/**
 * -------------------------------------------------------------
 * Name        : Dihan Wanasinghe
 * IIT ID      : 20232101
 * UOW ID      : w2054987
 * -------------------------------------------------------------
 */

package MaxFlow;
import java.io.*;
import java.util.*;
import MaxFlow.Vertex;
import MaxFlow.Edge;
import MaxFlow.edmond_carp.EdmondCarp;

public class MaxFlowRunner {
    public static void main(String[] args) {

        // Specify the  input files directory "benchmarks"
        //comment the following line to test the recursive method
        File file =  new File("src/MaxFlow/benchmarks/iterative_benchmarks");

        //uncomment the following line to test the recursive method
        // File file =  new File("benchmarks/recursive_benchmarks");
        File [] benchmarks = file.listFiles();

         // read each file one by one
        for(File benchmarkFile : benchmarks) {
            try(BufferedReader bufferedReader = new BufferedReader(new FileReader(benchmarkFile)))         {
                List<Vertex> vertexList = new ArrayList<Vertex>();

               int vertexCount =    Integer.parseInt(bufferedReader.readLine())   ;
                System.out.println(benchmarkFile.getName());
                System.out.println();
                System.out.println("Total Vertices: "+vertexCount);

               String line;
               while ( ( line = bufferedReader.readLine() ) != null){
                //    int count =0;
                   String[] stringNumbers = line.trim().split("\\s+");
                   int edgeStartId = Integer.parseInt(stringNumbers[0]);
                   int edgeEndId = Integer.parseInt(stringNumbers[1]);
                   int weight = Integer.parseInt(stringNumbers[2]);

                   Vertex edgeStartingVertex = findOrCreateVertex(vertexList, edgeStartId);
                   Vertex edgeEndingVertex = findOrCreateVertex(vertexList, edgeEndId);

                   //created the forward and reverse edges
                   //change back the paramter to weight for other methods , currentlt this is for edmond carp
                  // Edge reverseEdge = new Edge(edgeStartingVertex,weight);
                   Edge reverseEdge = new Edge(edgeStartingVertex,0);

                   Edge forwardEdge= new Edge(edgeEndingVertex,weight);
                   //adds the forward edge to the edgeList of the starting vertex
                   edgeStartingVertex.addNeighbor(forwardEdge);

                   //adds the reverse edge to the edgeList of the ending vertex
                   //additionally it was added to reverseList , indicating that the added list is a reverse edge
                     edgeEndingVertex.addNeighbor(reverseEdge);
                     edgeEndingVertex.addReverseNeighbor(reverseEdge);
               }

               Vertex source= vertexList.getFirst();
               int targetId = vertexCount -1 ;
               Vertex target = null;

               for(Vertex v : vertexList) {

                   if(v.getId() == targetId) {
                      target = v;
                      break;
                   }
               }
                System.out.println("Source: " + source.getId() + " Target: " + target.getId());
                LinkedHashMap<Vertex,Integer> pathVertices = new LinkedHashMap<>();
                //comment the following two lines to test the recursive method
                int maxFLowIterative = EdmondCarp.edmondCarpAlgorithm(source,target);
                System.out.println("Max Flow Iterative : " + maxFLowIterative);

                // Uncomment the following lines to test the recursive method
                // int maxFlowRecursive = findMaxFlowRecursive(source,target,0,pathVertices);
                // System.out.println("Max Flow Recursive: " + maxFlowRecursive);
                System.out.println();

            }catch (IOException ioException){
                System.out.println("Error Reading the FIle");
            }
        }
    }

    // creating a Vertex object based on information passed from the file
    private static Vertex findOrCreateVertex(List<Vertex> vertexList, int id) {
        for (Vertex v : vertexList) {
            if (v.getId() == id) {
                return v;
            }
        }
        Vertex newVertex = new Vertex(id);
        vertexList.add(newVertex);
        return newVertex;
    }

    private  static int findMaxFlowRecursive(Vertex source, Vertex target, int maxFlow, LinkedHashMap<Vertex,Integer> pathVertices) {

        if(source == target){

            int maxWeight = Integer.MAX_VALUE;

            // find the maxium weight on the path
            for(Vertex vertex1: pathVertices.keySet()) {
                int existingEdgeWeight  =  vertex1.getEdges().get(pathVertices.get(vertex1)).getWeight();
                if(existingEdgeWeight < maxWeight)
                    maxWeight = existingEdgeWeight;
            }
            //recalculates the weights of the edges on the path
            //prints the path
          if(maxWeight>0) {
              System.out.print("Path :");
              for (Vertex vertex1 : pathVertices.keySet()) {
                  int existingEdgeWeight = vertex1.getEdges().get(pathVertices.get(vertex1)).getWeight();
                  vertex1.getEdges().get(pathVertices.get(vertex1)).setWeight(existingEdgeWeight - maxWeight);
                  System.out.print(""+vertex1.getId()+ " -> " );
              }
              System.out.print(target.getId());
              System.out.print(" Maximum Flow on the Path : " + maxWeight);
              System.out.println();
          }
          //return the  maxFlow
            maxFlow = maxFlow+maxWeight;
            return maxFlow;
        }
        Collections.sort(source.getEdges());
        //if the source vertex has a neighbor with weight >0
        //explore that vertex with recursion

        for(int i =0;i<source.getEdges().size();i++) {

            Edge edge = source.getEdges().get(i);
            int currentWeight = edge.getWeight();
            if (currentWeight > 0) {
                pathVertices.put(source, i);
                Vertex neighbour = edge.getNeighbour();
                maxFlow =   findMaxFlowRecursive(neighbour, target, maxFlow,pathVertices);
            }
        }

        // when fully explored , remove the source from the path
        Vertex lastVertex = null;
        for(Vertex vertex2: pathVertices.keySet()) {

            lastVertex = vertex2;
        }
        pathVertices.remove(lastVertex);
        return  maxFlow;
    }

    private static int findMaxFlowIterative(Vertex source , Vertex target){

        //Stack for DFS
        Stack<Vertex> stack = new Stack<>();

        //Map for storing the visited vertices
        // key = Vertex , value = visited neighboring vertices from the key
        Map<Vertex,ArrayList<Vertex>> visitedList = new HashMap<>();

        //Map for storing path  - from source to the target
        // key = Vertex , value = Edge index ,each which was used for the path from that vertex
        Map<Vertex,Integer> pathWithWeight = new LinkedHashMap<>();
        int maxFLow =0;

        while( source != null){

            //If the source and target are the same, we have found a path
            if (source.equals(target)) {

                // Calculate the maximum weight on the path and update the weights of the edges on the path
                int maxWeight = Integer.MAX_VALUE;

                for(Vertex vertex: pathWithWeight.keySet()) {

                   int edgeIndex= pathWithWeight.get(vertex);
                   // calculate the weight if the edge is not a reverse edge
                    if(!vertex.getReverseEdges().contains(vertex.getEdges().get(edgeIndex))) {
                        int currentWeight = vertex.getEdges().get(pathWithWeight.get(vertex)).getWeight();
                        if (currentWeight < maxWeight) {
                            maxWeight = currentWeight;
                        }
                    // calculate the weight if the edge is a reverse edge based on the maxCapacity on the reverse edge
                    }else {
                        int maxCap = vertex.getEdges().get(edgeIndex).getMaxCap();
                        if (maxCap < maxWeight) {
                            maxWeight = maxCap;
                        }
                    }
                }

                // when source = target, the pathWithWeight will be empty
                if(pathWithWeight.isEmpty()){
                    maxWeight =0;
                }

                //recalculates the weights of the edges on the path
                if(maxWeight>0) {
                    System.out.print("Path :");
                    for (Vertex vertex : pathWithWeight.keySet()) {
                        int edgeIndex = pathWithWeight.get(vertex);
                        int existingEdgeWeight = vertex.getEdges().get(edgeIndex).getWeight();
                        //the maximum weight on the path should be removed from the current capacity of a forward edge
                        if(!vertex.getReverseEdges().contains(vertex.getEdges().get(edgeIndex))) {
                            vertex.getEdges().get(edgeIndex).setWeight(existingEdgeWeight - maxWeight);
                            Vertex neighbor = vertex.getEdges().get(edgeIndex).getNeighbour();

                            // changes the weight of the forward edge , which corresponds to this reverse edge
                            for (Edge edge : neighbor.getEdges()) {
                               if( edge.getNeighbour().equals(vertex)) {
                                   edge.setWeight(existingEdgeWeight - maxWeight);
                                      break;
                               }

                            }
                            //the maximum weight on the path should be added to  the current capacity(0) of a reverse edge
                        }else {
                            vertex.getEdges().get(edgeIndex).setWeight(existingEdgeWeight + maxWeight);
                            Vertex neighbor = vertex.getEdges().get(edgeIndex).getNeighbour();

                            // changes the weight of the forward edge , which corresponds to this reverse edge
                            for (Edge edge : neighbor.getEdges()) {
                                if( edge.getNeighbour().equals(vertex)) {
                                    edge.setWeight(existingEdgeWeight + maxWeight);
                                    break;
                                }

                            }

                        }
                        System.out.print(""+vertex.getId()+ " -> " );
                    }
                    //calculate the maxFlow
                   maxFLow = maxFLow + maxWeight;
                    System.out.print(target.getId());
                    System.out.print(" Maximum Flow on the Path : " + maxWeight);
                  System.out.println();
                }
                if(!stack.isEmpty()){
                    //gets the last vertex in the path (vertex before the target)
                    source = stack.peek();
                    continue;
                }else{
                    //when the source and target are the same, and the stack is empty
                    break;
                }
            }

            // source's edge list - we start with index 0
            int edgeListIndex=0;
            //gets the edgeList for the source vertex
            List<Edge> edges = source.getEdges();
            //If the source vertex is not in the visited list, add it
            if(!visitedList.containsKey(source)){
                //sorts the edges in descending order
                // create a List for the source vertex , so it can store the vertices visited from the source
                ArrayList<Vertex> visited = new ArrayList<>();
                visitedList.put(source, visited);
            }

            if(!stack.contains(source)) {
                stack.push(source);
            }
            //If the source vertex is not in the pathWithWeight map, add it
            //we use index 0 for the first edge
            // will be replaced with another  index if the edge weight is 0, or that path is previously visited
            pathWithWeight.put(source, edgeListIndex);


            boolean sourcePoppedFromStack = false;
            Vertex neighbor;

            while (true) {


                Edge edge= edges.get(edgeListIndex);

                //gets the neighbor vertex corresponding to the edgeListIndex
                neighbor = edges.get(edgeListIndex).getNeighbour();
                //if the edge weight is 0, or the neighbor vertex is already visited

                //neighbor node is already visited from the current vertex
                boolean isAlreadyVisited = visitedList.get(source).contains(neighbor);
                // edge capacity is fully expended
                boolean isSaturated = source.getEdges().get(edgeListIndex).getWeight() ==0;
                boolean isReverseEdge=   source.getReverseEdges().contains(edge);
                //neighbor is already within the current path
                boolean isInPath = pathWithWeight.containsKey(neighbor);

                if(isAlreadyVisited  || (isSaturated && !isReverseEdge)
                || ( isReverseEdge && !isSaturated) || isInPath ) {
                    //increase the edge listIndex to get another edge
                    edgeListIndex++;
                    //if every edge is visited on that vertex , or weight is 0
                    //pop the vertex from the stack
                    //and remove it from the pathWithWeight map because we dont go on path containing that vertex - it is already full explored
                    if(edgeListIndex>= edges.size()) {
                        edgeListIndex = 0;

                        stack.pop();
                        visitedList.get(source).clear();

                        //  goes to the parent of the source as the current vertex -source is already fully explored
                        // if no parent available , outputs maxFlow - graph is fully explored
                        if(stack.isEmpty()){
                            return maxFLow;

                        }
                        source = stack.peek();

                        sourcePoppedFromStack = true;
                        Vertex lastVertex = null;

                        //removes the current source from the pathWithWeight map
                        //as we go back to the parent of source
                        for(Vertex vertex: pathWithWeight.keySet()) {

                            lastVertex = vertex;
                        }
                        pathWithWeight.remove(lastVertex);

                        break;
                    }
                }else{
                    // when the edgeListIndex got change from 0
                    pathWithWeight.replace(source, edgeListIndex);
                    break;
                }
            }
            if(!sourcePoppedFromStack) {

                //if the source still has a neighbor vertex to explore
                //adds it to the visited list as we are going to explore it next

                visitedList.get(source).add(neighbor);

                //loops with the neighbor vertex
                source = neighbor;

            }


        }

        return  maxFLow;
    }




}