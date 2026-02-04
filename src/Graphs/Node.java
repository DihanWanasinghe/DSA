package Graphs;

import java.util.ArrayList;
import java.util.List;

public class Node {
    int data;
    List<Edge> edges;

   public Node(int data, List<Edge> edges){
        this.data = data;
        this.edges = edges;
    }

   public  Node(int data){
        this.data = data;
        this.edges = new ArrayList<Edge>();
    }

    public void addEdge(Edge edge){
        this.edges.add(edge);
    }

    public  void removeEdge(Edge edge){
        this.edges.remove(edge);
    }

    public  int getData(){
        return data;
    }

    public List<Edge> getEdges(){
        return edges;
    }

}
