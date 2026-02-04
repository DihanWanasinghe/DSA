package Graphs;

public class Edge {
   private int weight;
    private Node neighbor;

    Edge(int weight, Node neighbor){
        this.weight = weight;
        this.neighbor = neighbor;
    }

    public int getWeight() {
        return weight;
    }
    public Node getNeighbor() {
        return neighbor;
    }
}
