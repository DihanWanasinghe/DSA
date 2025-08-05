package MaxFlow;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Vertex  {
    private int id;
    private List<Edge> edges = new ArrayList<>();
    private List<Edge> reverseEdges = new ArrayList<>();
    private Map<Vertex,Integer> parent = new LinkedHashMap<>();

    public Map<Vertex, Integer> getParent() {
        return parent;
    }

    public void setParent(Map<Vertex, Integer> parent) {
        this.parent = parent;
    }

    public List<Edge> getReverseEdges() {
        return reverseEdges;
    }

    public void setReverseEdges(List<Edge> reverseEdges) {
        this.reverseEdges = reverseEdges;
    }

    public Vertex(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void addNeighbor(Vertex neighbor, int weight) {
        edges.add(new Edge(neighbor, weight));
    }

    public  void addNeighbor(Edge edge) {
        edges.add(edge);
    }


    public void addReverseNeighbor(Vertex neighbor, int weight) {
        reverseEdges.add(new Edge(neighbor, weight));
    }

    public void addReverseNeighbor(Edge edge) {
        reverseEdges.add(edge);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }



}
