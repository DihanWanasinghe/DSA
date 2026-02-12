package A_Star;

import A_Star.Vertex;

public class WrappedVertex {
    private int totalCost;
    private Vertex vertex;
    private int edgeCost;


    public WrappedVertex(Vertex vertex) {
        this.vertex = vertex;
    }


    public WrappedVertex(Vertex vertex, int totalCost  , int edgeCost) {
        this.vertex = vertex;
        this.totalCost = totalCost;
        this.edgeCost = edgeCost;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }

    public int getEdgeCost() {
        return edgeCost;
    }

    public void setEdgeCost(int edgeCost) {
        this.edgeCost = edgeCost;
    }
}
