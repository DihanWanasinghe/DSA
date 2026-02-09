package Dijkstras;

public class WrappedVertex {
    private int distance;
    private Vertex vertex;


    public WrappedVertex(Vertex vertex) {
        this.vertex = vertex;
        this.distance = Integer.MAX_VALUE;
    }


    public WrappedVertex(Vertex vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }
}
