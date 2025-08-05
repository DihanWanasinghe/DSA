package MaxFlow;
public class Edge implements  Comparable<Edge> {
    Vertex neighbour;
    int weight;
    int maxCap;

    public Edge(Vertex neighbour, int weight)  {
        this.neighbour = neighbour;
        this.weight = weight;
        this.maxCap= weight;

    }

    public Vertex getNeighbour() {
        return neighbour;
    }

    public void setNeighbour(Vertex neighbour) {
        this.neighbour = neighbour;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(other.weight, this.weight);
    }

    public int getMaxCap() {
        return maxCap;
    }

    public void setMaxCap(int maxCap) {
        this.maxCap = maxCap;
    }
}
