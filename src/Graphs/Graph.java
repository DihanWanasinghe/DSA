package Graphs;

import java.util.*;

public class Graph {

    Node root;

    Graph(Node root){
        this.root = root;
    }

    public Node bfs(int target){
        if(root == null){
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> visited = new HashSet<>();
        queue.add(root);
        visited.add(root);
        while(!queue.isEmpty()){
            Node current = queue.poll();

            if(current.getData() == target){
                return current;
            }
            for(Edge edge : current.getEdges()){
                if(visited.contains(edge.getNeighbor())){
                    continue;
                }
                queue.add(edge.getNeighbor());
                visited.add(edge.getNeighbor());
            }
        }
        return null;
    }

    public Node  dfs (int target){
        if(root == null){
            return null;
        }
        Stack<Node> stack = new Stack<>();
        Set<Node> visitedSet = new HashSet<>();

        stack.push(root);
        visitedSet.add(root);
        while(!stack.isEmpty()){
            Node current = stack.peek();
            if(current.getData() == target){
                return current;
            }
            List<Edge> edges = current.getEdges();
            List<Node> neighbors = new ArrayList<>();
            for(Edge edge : edges){
                neighbors.add(edge.getNeighbor());
            }
            for( Node neighbor : neighbors){
                if(!visitedSet.contains(neighbor) ){
                    stack.push(neighbor);
                    visitedSet.add(neighbor);
                    break;
                }

            }
            if(current == stack.peek()){
                stack.pop();
            }

        }

        return  null;
    }
}
