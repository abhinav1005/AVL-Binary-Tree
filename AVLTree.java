import java.util.*;

public class AVLTree<T extends Comparable<T>, V> {

    private class Node {
        private T key;
        private V value;
        private int height;
        //private int size;
        private Node leftNode;
        private Node rightNode;

        /**
         * Constructor for nested Node class
         */

        public Node(T key, V value) {
            this.key = key;
            this.value = value;
            this.leftNode = null;
            this.rightNode = null;
            this.height = 0;
            //this.size = size;
        }

        /**
         * getter method for the key
         * @return
         */
        public T getKey() {
            return this.key;
        }

        /**
         * setter method for the key
         * @param newKey
         */
        public void setKey(T newKey) {
            this.key = newKey;
        }

        /**
         * getter method for the value
         * @return
         */
        public V getValue() {
            return this.value;
        }

        /**
         * setter method for the value
         * @param newValue
         */
        public void setValue(V newValue) {
            this.value = newValue;
        }

        /**
         * getter method for the height
         * @return
         */
        public int getHeight(){
            return this.height;
        }

        /**
         * setter method for the height
         * @param height
         */
        public void setHeight(int height){
            this.height = height;
        }

        /**
         * getter method for the left node
         * 
         * @return Node
         */
        public Node getLeftNode() {
            return this.leftNode;
        }

        /**
         * getter method for the right method
         * 
         * @return Node
         */
        public Node getRightNode() {
            return this.rightNode;
        }

        /**
         * setter method for left node
         * 
         * @param newLeftNode
         */
        public void setLeftNode(Node newLeftNode) {
            this.leftNode = newLeftNode;
        }

        /**
         * setter method for the right node
         * 
         * @param newRightNode
         */
        public void setRightNode(Node newRightNode) {
            this.rightNode = newRightNode;
        }

    }

    /**
     * root node
     */
    private Node root;


    /**
     * insert method that inserts a node into the tree
     * @param key
     * @param value
     */
    public void insert(T key, V value) {

        if(key == null){
            ;
        }
        root = insertHelper(root, key, value);
    }

    /**
     * insert helper method
     * @param node
     * @param key
     * @param value
     * @return a Node
     */
    private Node insertHelper(Node node, T key, V value){
        if(node == null){
            return new Node(key, value);
        }
        if(key.compareTo(node.getKey()) < 0){
            node.setLeftNode(insertHelper(node.getLeftNode(), key, value));
        }
        else if(key.compareTo(node.getKey()) > 0){
            node.setRightNode(insertHelper(node.getRightNode(), key, value));
        }
        else{
            return node;
        }

        node.setHeight(Math.max(height(node.getLeftNode()), height(node.getRightNode())) + 1);
        int balanceFactor = balanceFactor(node);
        if(balanceFactor > 1){
            if(key.compareTo(node.getLeftNode().getKey())<0){
                return rotateRight(node);
            }
            else if(key.compareTo(node.getLeftNode().getKey()) > 0){
                node.setLeftNode(rotateLeft(node.getLeftNode()));
                return rotateRight(node);
            }
        }

        if(balanceFactor < -1){
            if(key.compareTo(node.getRightNode().getKey())>0){
                return rotateLeft(node);
            }
            else if(key.compareTo(node.getRightNode().getKey()) < 0){
                node.setRightNode(rotateRight(node.getRightNode()));
                return rotateLeft(node);
            }
            
        }
        return node;
    }

    private Node balance(Node root){
        if (balanceFactor(root) < -1){
            if(balanceFactor(root.getRightNode()) > 0){
                root.setRightNode(rotateRight(root.getRightNode()));
            }
            root = rotateLeft(root);
        }
        else if(balanceFactor(root) > 1){
            if(balanceFactor(root.getLeftNode()) < 0){
                root.setLeftNode(rotateLeft(root.getLeftNode()));
            }
            root = rotateRight(root);
        }
        return root;
    }

    /**
     * method that returns the balance factor of the subtree
     * @param node
     * @return
     */
    private int balanceFactor(Node node){
        return height(root.getLeftNode()) - height(root.getRightNode());
    }

    /**
     * method that performs right rotate on the nodes
     * @param node
     * @return
     */
    private Node rotateRight(Node node){
        Node y = node.getLeftNode();
        Node c = y.getRightNode();
        y.setRightNode(node);
        node.setLeftNode(c);
        node.setHeight(Math.max(height(node.getLeftNode()), height(node.getRightNode())) + 1);
        y.setHeight(Math.max(height(y.getLeftNode()), height(y.getRightNode())) + 1);
        return y;
    }

    /**
     * method that performs left rotation
     * @param node
     * @return
     */
    private Node rotateLeft(Node node){
        Node y = node.getRightNode();
        Node c = y.getLeftNode();
        y.setLeftNode(node);
        node.setRightNode(c);
        node.setHeight(Math.max(height(node.getLeftNode()), height(node.getRightNode())) + 1);
        y.setHeight(Math.max(height(y.getLeftNode()), height(y.getRightNode())) + 1);
        return y;
    }

    /**
     * Returns the height of the node or -1 if null
     * @param node
     * @return
     */
    private int height(Node node){
        return node == null ? -1 : node.getHeight();
    }


    /**
     * method that looks for the key in the tree and returns the value
     * @param key
     * @return
     */
    public V search(T key) {
        if(root == null){
            //throw exception here
            return null;
        }
        else {
        Node pointer = root;

        while (pointer != null) {
            if (key.compareTo(pointer.getKey()) == 0) {
                return pointer.getValue();
            } else if (key.compareTo(pointer.getKey()) < 0) {
                pointer = pointer.getLeftNode();
            } else {
                pointer = pointer.getRightNode();
            }
        }
        return null;
    }

    }
 /**
     * method to delete a node with specific key from the tree
     * 
     * @param key
     */
    public void delete(T key) {

        if (root == null || key == null) {
            throw new NoSuchElementException();
        } else {
          // calls the helper method with the root node and the key to be deleted
            deleteHelper(root, key);
        }

    }

    /**
     * Helper method for the delete method which uses recursion
     */
    private Node deleteHelper(Node node, T key) {
        if (node == null) {
            return node;
        }
        if (key.compareTo(node.getKey()) < 0) {
            node.setLeftNode(deleteHelper(node.getLeftNode(), key));
        } else if (key.compareTo(node.getKey()) > 0) {
            node.setRightNode(deleteHelper(node.getRightNode(), key));
        } else {
          // if both the child nodes of the node to be deleted is null then simply delete it
            if (node.getLeftNode() == null && node.getRightNode() == null) {
                return null;
                
                // if left child is null then replace with the right child node
            } else if (node.getLeftNode() == null) {
                return node.getRightNode();
                
                // if right child is null then replace with left child node
            } else if (node.getRightNode() == null) {
                return node.getLeftNode();
                
                // when both the child nodes are present
            } else {
              // replaces the node with the min from the right subtree
                Node temp = getMin(node.getRightNode());
                node.setKey(temp.getKey());
                node.setValue(temp.getValue());
                node.setRightNode(deleteHelper(node.getRightNode(), temp.getKey()));
                // return node;
            }
        }
        return node;
    }

  /**
     * returns the minimum from the left subtree
     * used for delete method
     * 
     * @param node
     * @return
     */
    private Node getMin(Node node) {
        if (node.getLeftNode() == null) {
            return node;
        } else {
            getMin(node.getLeftNode());
        }
        return null;
    }

    /**
     * method to return a list with inorder traversal
     * @return
     */
    public List<V> inorderRec() {
        if (root == null) {
            return null;
        } else {
            //getArrayList().clear();

            ArrayList<V> ar = new ArrayList<V>();
            return (inorderTree(root, ar));
            // return ar;
        }
    }

    /**
     * Helper method for inorder traversal of the tree using recursion
     * 
     * @param node
     */
    private List<V> inorderTree(Node node, List<V> list) {

        if (node.getLeftNode() != null) {
            inorderTree(node.getLeftNode(), list);
        }

        list.add(node.getValue());
        //getArrayList().add(node.getValue());

        if (node.getRightNode() != null) {
            inorderTree(node.getRightNode(), list);
        }
        return list;
    }
    
    /**
     * method to return the kthSmallest Value
     * @return
     */
    public V kthSmallest(int k) {
        
        if (root == null || k < 0) {
            return null;
        } else {
            ArrayList<V> arr = (ArrayList<V>)inorderRec();
            return arr.get(k - 1);
            // return getArrayList().get(k - 1);
        }
        //return null;
    }

    public static void main(String[] args) {
        AVLTree<Integer, Integer> a = new AVLTree<Integer, Integer>();
        a.insert(2,2);
        a.insert(13,13);
        a.insert(53,53);
        a.insert(9,9);
        a.insert(21,21);
        a.insert(61,61);
        a.insert(8,8);
        a.insert(11,11);
        System.out.println(a.inorderRec());
        
        
        System.out.println("**************Demonstration***************");
        System.out.println(" 1. Construct a BinarySearchTree");
        AVLTree<Integer, Integer> tree = new AVLTree<Integer, Integer>();

        System.out.println(" 2. insert keys");
        tree.insert(2, 2);
        tree.insert(1, 1);
        tree.insert(4, 4);
        tree.insert(5, 5);
        tree.insert(9, 9);
        //tree.insert(3, 3);
        //tree.insert(6, 6);
        //tree.insert(7, 7);
        //tree.insert(10, 10);
        //tree.insert(12, 12);
        //tree.insert(11, 11);

        System.out.println("Inorder after insertion: ");
        System.out.println(tree.inorderRec());

        /*
        System.out.println(" 3. Delete 4 and then delete 9");
        tree.delete(4);
        tree.delete(9);
        */
        
        System.out.println(" 4. Print the keys using Inorder Traversal");
        System.out.println(tree.inorderRec());

        System.out.println(" 5. Search 12 and then search 4");
        System.out.println(tree.search(12));
        System.out.println(tree.search(4));

        System.out.println(" 6. Find the third smallest element in the tree");
        System.out.println(tree.kthSmallest(3));

        System.out.println(" 7. Show that the tree is generic");
        System.out.println("I have inserted (Double, String) in the following order: ");
        System.out.println("First insert: 10.0, A");
        System.out.println("Second insert: 50.0, D");
        System.out.println("Third insert: 5.0, C");
        AVLTree<Double, String> tree2 = new AVLTree<Double, String>();
        tree2.insert(10.0, "A");
        tree2.insert(50.0, "D");
        tree2.insert(5.0, "C");
        System.out.println("The inorder traversal results in: ");
        System.out.println(tree2.inorderRec());

        System.out.println(" 8. Also include an example using key-value pairs");
        System.out.println("Making a tree with key-value as integer, integer and adding (1,1) (2,2) and (3,3)");
        AVLTree<Integer, Integer> t = new AVLTree<Integer, Integer>();

        t.insert(1, 1);
        t.insert(2, 2);
        t.insert(3, 3);
        System.out.println("The inorder traversal is: ");
        System.out.println(t.inorderRec());

        System.out.println("Now another example with key value as Integer, string with insertions (5,A), (2,B), (10,C)");
        AVLTree<Integer, String> d = new AVLTree<Integer, String>();
        d.insert(5, "A");
        d.insert(2, "B");
        d.insert(10, "C");
        System.out.println("The inorder traversal is: ");
        System.out.println(d.inorderRec());

        AVLTree<Integer, Integer> b = new AVLTree<Integer, Integer>();
        b.insert(20, 20);
        b.insert(10, 10);
        b.insert(1, 1);
        b.insert(15, 15);
        b.insert(50, 50);
        b.inorderRec();

    }

}
