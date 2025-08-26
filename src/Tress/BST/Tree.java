package Tress.BST;

public class Tree {

    public static class Node{
        int data;
        Node left;
        Node right;
        Node parent;

        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }

        Node(int data, Node left, Node right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
        Node(int data, Node left, Node right, Node parent){
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

}
