import java.util.*;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<T>, V> {

    /**
     * Nested Node class
     */
    public class Node {

        /**
         * private instance field to store the key of type T
         */
        private T key = null;

        /**
         * private instance field to store the value of type V
         */
        private V value = null;

        /**
         * private instance field to stroe the left node
         */
        private Node leftNode;

        /**
         * private instance field to stroe the right node
         */
        private Node rightNode;

        /**
         * Constructor of the class
         * 
         * @param key
         * @param value
         */
        public Node(T key, V value) {
            this.key = key;
            this.value = value;
            this.leftNode = null;
            this.rightNode = null;
        }

        /**
         * getter method for the key
         * 
         * @return type T
         */
        public T getKey() {
            return this.key;
        }

        /**
         * getter method for the Value
         * 
         * @return V
         */
        public V getValue() {
            return this.value;
        }

        /**
         * setter method for the key
         * 
         * @param newKey
         */
        public void setKey(T newKey) {
            this.key = newKey;
        }

        /**
         * setter method for the value
         * 
         * @param newValue
         */
        public void setValue(V newValue) {
            this.value = newValue;
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
     * private instance field to store the root of the tree
     */
    public Node root;

    /**
     * Arraylist for the inorder method
     */
    private ArrayList<V> arr = new ArrayList<V>();

    /**
     * Constructor of the class
     */
    public BinarySearchTree() {
    }

    /**
     * public getter method of the arraylist
     * 
     * @return
     */
    public List<V> getArrayList() {
        return arr;
    }

    /**
     * Insert method to insert a node into the tree
     * 
     * @param key
     * @param value
     */
    public void insert(T key, V value) {

        // Node<T, V> newNode = new Node<T, V>(key, value);

        // Case when the tree is empty
        if (root == null) {
            root = new Node(key, value);
        }
        // case when the tree is not empty
        else {
            Node pointer = root;
            Node parent = null;
            while (pointer != null) {
                parent = pointer;
                if (key.compareTo(pointer.getKey()) < 0) {
                    pointer = pointer.getLeftNode();
                } else {
                    pointer = pointer.getRightNode();
                }
            }
            
            
            if (key.compareTo(parent.getKey()) < 0) {
                parent.setLeftNode(new Node(key, value));
            } else {
                parent.setRightNode(new Node(key, value));
            }
        }
    }

    /**
     * Method to search the tree for a specific key
     * 
     * @param key
     * @return
     */
    public V search(T key) {
      // case when either root is null or key is null
        if (root == null || key == null) {
            throw new NoSuchElementException();
        } else {
            Node pointer = root;

            while (pointer != null) {
              // when key is found
                if (key.compareTo(pointer.getKey()) == 0) {
                    return pointer.getValue();
                // iterating through the left tree
                } else if (key.compareTo(pointer.getKey()) < 0) {
                    pointer = pointer.getLeftNode();
                } else {
                  // iterating through the right tree
                    pointer = pointer.getRightNode();
                }
            }
            // if not found, return null
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
     * Method that stores the values of the key in a list through inorder traversal
     * 
     * @return
     */
    public List<V> inorderRec() {

        if (root == null) {
            return null;
        } else {
            getArrayList().clear();

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
        getArrayList().add(node.getValue());

        if (node.getRightNode() != null) {
            inorderTree(node.getRightNode(), list);
        }
        return list;
    }

    /**
     * method to find the kth Smallest value in the tree
     * 
     * @param k
     * @return
     */
    public V kthSmallest(int k) {
        if (root == null || k < 0) {
            return null;
        } else {
            ArrayList<V> arr = (ArrayList<V>) inorderRec();
            return arr.get(k - 1);
            // return getArrayList().get(k - 1);
        }
        //return null;
    }

    public static void main(String[] args) {

        System.out.println("**************Demonstration***************");
        System.out.println(" 1. Construct a BinarySearchTree");
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<Integer, Integer>();

        System.out.println(" 2. insert keys");
        tree.insert(2, 2);
        tree.insert(1, 1);
        tree.insert(4, 4);
        tree.insert(5, 5);
        tree.insert(9, 9);
        tree.insert(3, 3);
        tree.insert(6, 6);
        tree.insert(7, 7);
        tree.insert(10, 10);
        tree.insert(12, 12);
        tree.insert(11, 11);

        System.out.println("Inorder after insertion: ");
        System.out.println(tree.inorderRec());

        System.out.println(" 3. Delete 4 and then delete 9");
        tree.delete(4);
        tree.delete(9);

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
        BinarySearchTree<Double, String> tree2 = new BinarySearchTree<Double, String>();
        tree2.insert(10.0, "A");
        tree2.insert(50.0, "D");
        tree2.insert(5.0, "C");
        System.out.println("The inorder traversal results in: ");
        System.out.println(tree2.inorderRec());

        System.out.println(" 8. Also include an example using key-value pairs");
        System.out.println("Making a tree with key-value as integer, integer and adding (1,1) (2,2) and (3,3)");
        BinarySearchTree<Integer, Integer> t = new BinarySearchTree<Integer, Integer>();

        t.insert(1, 1);
        t.insert(2, 2);
        t.insert(3, 3);
        System.out.println("The inorder traversal is: ");
        System.out.println(t.inorderRec());

        System.out.println("Now another example with key value as Integer, string with insertions (5,A), (2,B), (10,C)");
        BinarySearchTree<Integer, String> d = new BinarySearchTree<Integer, String>();
        d.insert(5, "A");
        d.insert(2, "B");
        d.insert(10, "C");
        System.out.println("The inorder traversal is: ");
        System.out.println(d.inorderRec());

        BinarySearchTree<Integer, Integer> a = new BinarySearchTree<Integer, Integer>();
        a.insert(20, 20);
        a.insert(10, 10);
        a.insert(1, 1);
        a.insert(15, 15);
        a.insert(50, 50);
        a.inorderRec();

    }
}
