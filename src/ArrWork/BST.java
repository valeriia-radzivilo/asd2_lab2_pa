package ArrWork;

// Java program to demonstrate
// insert operation in binary
// search tree
public class BST {

    /* Class containing left
       and right child of current node
     * and key value*/
    public class Node {
        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    // Root of BST
    public Node root;

    // Constructor
    public BST() {
        root = null;
    }



    // This method mainly calls insertRec()
    public void insert(int key) {
        root = insertRec(root, key);
    }

    /* A recursive function to
       insert a new key in BST */
    Node insertRec(Node root, int key) {

        /* If the tree is empty,
           return a new node */
        if (root == null) {
            root = new Node(key);
            return root;
        }

        /* Otherwise, recur down the tree */
        else if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key >= root.key)
            root.right = insertRec(root.right, key);

        /* return the (unchanged) node pointer */
        return root;
    }
    int size() { return size(root); }
    int size(Node node)
    {
        if (node == null)
            return 0;
        else
            return (size(node.left) + 1 + size(node.right));
    }

    public void deleteTree(Node node)
    {
        // In Java automatic garbage collection
        // happens, so we can simply make root
        // null to delete the tree
        root = null;
    }
}