package A_Star;

import A_Star.Vertex;

public class Edge {
    private int weight;
    private Vertex neighbor;

    Edge(int weight, Vertex neighbor){
        this.weight = weight;
        this.neighbor = neighbor;
    }

    public int getWeight() {
        return weight;
    }
    public Vertex getNeighbor() {
        return neighbor;
    }
}
