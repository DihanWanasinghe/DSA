package Tress.BST;

import static javax.management.Query.in;

public class BSTOperations {

    public static void main(String[] args) {

    }

    private static Tree.Node findValue(Tree.Node root, int value) {
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

    private static Tree.Node insertNode(Tree.Node root, int value) {
        Tree.Node node = root;

        if (node == null) {
            node = new Tree.Node(value);
            return node;
        }

        // if(root==null){
        // root = new Tree.Node(value);
        // return;
        // }
        // while (node != null) {
        // parent = node;
        if (value < node.data) {
            node.left = insertNode(node.left, value);

        } else if (value > node.data) {
            node.right = insertNode(node.right, value);

        } else {
            System.out.println("Value already exists");
            return node;
        }
        updateHeight(node);
        Tree.Node balancedNode = rebalance(node);

        return balancedNode;
        // }
        // Tree.Node newNode = new Tree.Node(value, null, null, parent);
        // if (parent.data > value) {
        // parent.left = newNode;
        // } else {
        // parent.right = newNode;
        // }
    }

    private static void updateHeight(Tree.Node node) {
        node.height = Math.max(findHeight(node.left), findHeight(node.right)) + 1;
        node.balanceFactor = findHeight(node.left) - findHeight(node.right);
    }

    private static void deleteNode(Tree.Node root, int value) {
        Tree.Node node = findValue(root, value);
        Tree.Node parent = null;
        if (node != null && node.parent != null) {
            parent = node.parent;
        }

        if (node == null) {
            System.out.println("Value not found");
            return;
        }
        if (node.left == null && node.right == null) {

            if (parent == null) {
                root = null;
            } else if (parent.left == node) {
                parent.left = null;

            } else {
                parent.right = null;
            }
        } else if (node.left == null || node.right == null) {

            Tree.Node child = node.left != null ? node.left : node.right;
            if (parent != null) {
                child.parent = parent;
                if (parent.data > value) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            } else {

                root = child;
            }
            node = null;

        } else {

            Tree.Node leftBranchMax = node.left;
            while (leftBranchMax.right != null) {
                leftBranchMax = leftBranchMax.right;
            }
            if (node.right.left != null) {
                leftBranchMax.right = node.right.left;
                node.right.left.parent = leftBranchMax;
                node.right.left = node.left;
                node.left.parent = node.right;
            } else {
                node.right.left = node.left;
                node.left.parent = node.right;
            }
            if (parent != null) {
                if (parent.data > value) {
                    parent.left = node.right;
                } else if (parent.data < value) {
                    parent.right = node.right;
                }
                node.right.parent = parent;
            } else {
                root = node.right;
            }
            node = null;

        }

    }

    private static void printTreeIncreasing(Tree.Node root) {
        if (root == null) {
            return;
        }

        printTreeIncreasing(root.left);
        System.out.println(root.data);
        printTreeIncreasing(root.right);

    }

    private static void printTreeDecreasing(Tree.Node root) {
        if (root == null) {
            return;
        }

        printTreeDecreasing(root.right);
        System.out.println(root.data);
        printTreeDecreasing(root.left);

    }

    private static int findHeight(Tree.Node node) {
        if (node == null) {
            return 0; // base case: empty tree has height 0
        }
        int leftHeight = findHeight(node.left);
        int rightHeight = findHeight(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    private static void leftRotation(Tree.Node unbalancedNegativeParent) {

        if (unbalancedNegativeParent == null || unbalancedNegativeParent.right == null) {
            return;
        }

        unbalancedNegativeParent.right.parent = unbalancedNegativeParent.parent;
        if (unbalancedNegativeParent.parent != null) {
            if (unbalancedNegativeParent == unbalancedNegativeParent.parent.left) {
                unbalancedNegativeParent.parent.left = unbalancedNegativeParent.right;
            } else {
                unbalancedNegativeParent.parent.right = unbalancedNegativeParent.right;
            }
        }

        unbalancedNegativeParent.parent = unbalancedNegativeParent.right;
        Tree.Node temp = unbalancedNegativeParent.right.left;
        if (unbalancedNegativeParent.right.left != null) {
            unbalancedNegativeParent.right.left.parent = unbalancedNegativeParent;
        } // Safe because of the if check
        unbalancedNegativeParent.right.left = unbalancedNegativeParent;
        unbalancedNegativeParent.right = temp;

    }

    private static void rightRotation(Tree.Node unbalancedPositiveParent) {

        if (unbalancedPositiveParent == null || unbalancedPositiveParent.left == null) {
            return;
        }
        Tree.Node positiveChild = unbalancedPositiveParent.left;
        positiveChild.parent = unbalancedPositiveParent.parent;

        if (unbalancedPositiveParent.parent != null) {
            if (unbalancedPositiveParent == unbalancedPositiveParent.parent.left) {
                unbalancedPositiveParent.parent.left = positiveChild;
            } else {
                unbalancedPositiveParent.parent.right = positiveChild;
            }
        }

        unbalancedPositiveParent.parent = positiveChild;
        unbalancedPositiveParent.left = positiveChild.right;

        if (positiveChild.right != null) {
            positiveChild.right.parent = unbalancedPositiveParent;
        }
        positiveChild.right = unbalancedPositiveParent;
    }

    private static void rightLeftRotation(Tree.Node unbalancedNegativeParent) {
        if (unbalancedNegativeParent == null || unbalancedNegativeParent.right == null
                || unbalancedNegativeParent.right.left == null) {
            return;
        }
        rightRotation(unbalancedNegativeParent.right);
        leftRotation(unbalancedNegativeParent);
    }

    private static void leftRightRotation(Tree.Node unbalancedPositiveParent) {
        if (unbalancedPositiveParent == null || unbalancedPositiveParent.left == null
                || unbalancedPositiveParent.left.right == null) {
            return;
        }
        leftRotation(unbalancedPositiveParent.left);
        rightRotation(unbalancedPositiveParent);
    }

    private static Tree.Node rebalance(Tree.Node node) {

        if (node == null) {
            return null;
        }

        if (node.balanceFactor > 1) {
            if (node.left.balanceFactor < 0) {
                node.left = leftRotationOptimized(node.left);

            }
            node = rightRotationOptimized(node);
        } else if (node.balanceFactor < -1) {

            if (node.right.balanceFactor > 0) {
                node.right = rightRotationOptimized(node.right);

            }
            node = leftRotationOptimized(node);
        }
        return node;

    }

    private static Tree.Node leftRotationOptimized(Tree.Node node) {
        Tree.Node parent = node.parent;
        Tree.Node x = node.right;
        Tree.Node T2 = x.left;

        // Perform rotation
        x.left = node;
        node.right = T2;

        x.parent = parent;
        node.parent = x;

        if (parent != null) {
            if (parent.left == node) {
                parent.left = x;
            } else {
                parent.right = x;
            }

        }

        if (T2 != null) {
            T2.parent = node;
        }

        // Update heights (BOTTOM-UP)
        updateHeight(node);
        updateHeight(x);

        // Return new root of this subtree
        return x;

    }

    private static Tree.Node rightRotationOptimized(Tree.Node node) {

        Tree.Node x = node.left;
        Tree.Node T2 = x.right;
        Tree.Node parent = node.parent;

        x.right = node;
        node.left = T2;
        
        x.parent = parent;
        node.parent =x;

        if(T2 !=null){
            T2.parent = node;
        }

        if(parent !=null){
            if(parent.left == node){
                parent.left = x;
            }else{
                parent.right = x;
            }
        }



        updateHeight(node);
        updateHeight(x);

        return x;
    }

}
