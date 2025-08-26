package Tress.BST;

public class BSTOperations {

    public static void main(String[] args) {


    }
    private static  Tree.Node findValue(Tree.Node root, int value) {
        Tree.Node node = root;
        while (node != null) {
            if (node.data == value)
                return node;
            if (value < node.data) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }
    private static void  insertNode(Tree.Node root, int value){
        Tree.Node node = root;
        Tree.Node parent = null;
        if(root==null){
            root = new Tree.Node(value);
            return;
        }
        while(node!=null){
            parent = node;
            if(value < node.data){
                node = node.left;
            }else if(value > node.data){
                node = node.right;
            }else{
                System.out.println("Value already exists");
                return;
            }
        }
        Tree.Node newNode = new Tree.Node(value,null,null,parent);
        if (parent.data > value) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }


    }
    private static void deleteNode(Tree.Node root, int value){
        Tree.Node node = findValue(root, value);
        Tree.Node parent =null ;
        if(node != null && node.parent !=null) {
            parent = node.parent;
        }

        if(node == null){
            System.out.println("Value not found");
            return;
        }
        if(node.left == null && node.right == null){

            if(parent == null){
                root = null;
            }
            else if(parent.left == node){
                parent.left = null;

            }else {
                parent.right = null;
            }
        }else if(node.left == null || node.right == null) {



            Tree.Node child = node.left !=null ? node.left : node.right;
            if(parent !=null) {
                child.parent = parent;
                if ( parent.data > value) {
                    parent.left = child;
                } else  {
                    parent.right = child;
                }
            }else {

                root = child;
            }
                node = null;


            }else{

            Tree.Node leftBranchMax = node.left;
            while(leftBranchMax.right != null){
                leftBranchMax = leftBranchMax.right;
            }
            if(node.right.left !=null) {
                leftBranchMax.right = node.right.left;
                node.right.left.parent = leftBranchMax;
                node.right.left = node.left;
                node.left.parent = node.right;
            }else{
                node.right.left = node.left;
                node.left.parent = node.right;
            }
            if(parent != null ) {
                if ( parent.data > value) {
                    parent.left = node.right;
                } else if ( parent.data < value) {
                    parent.right = node.right;
                }
                    node.right.parent = parent;
            }else{
                root = node.right;
            }
          node = null;


        }


    }



    private static void printTreeIncreasing(Tree.Node root){
        if(root == null){
            return;
        }

            printTreeIncreasing(root.left);
        System.out.println(root.data);
        printTreeIncreasing(root.right);


    }
    private static  void printTreeDecreasing(Tree.Node root){
        if(root == null){
            return;
        }

            printTreeDecreasing(root.right);
        System.out.println(root.data);
        printTreeDecreasing(root.left);


    }
}


