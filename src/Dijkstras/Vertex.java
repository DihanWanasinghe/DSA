package Dijkstras;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

    private List<Edge> edges ;
    private int id;
    private boolean  explored;


    public Vertex(int id) {
        this.id = id;
        this.edges = new ArrayList<Edge>();
        this.explored = false;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  void addEdge(Edge edge){
        this.edges.add(edge);
    }
    public boolean isExplored() {
        return explored;
    }
    public void  setExplored(boolean explored) {
        this.explored = explored;
    }

}
