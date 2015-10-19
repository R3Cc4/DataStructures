public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    private Node root; //Root of binary search tree

    //Initializes empty Tree.
    public BinarySearchTree() {

    }

    public static void main(String[] args) {

        System.out.println("hello World");
    }

    //Returns true if the Tree is empty.
    public Boolean isEmpty() {
        return size() == 0;
    }

    // Returns the number of elements in the Tree.
    public int size() {
        return size(root);
    }

    // Returns the size of a Binary Search Tree rooted at X.
    public int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    /*
    Does this Tree contains given key?

     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /*
    returns the value associated witht he given key
     */

    public Value get(Key key) {

        return get(root, key);
    }

    /*
      recursive method to parse through the tree and return the value for the key passed.
      It compares the current key to the key of the node passed and calls the same method
      with left child of the node if the key passed is less or calls with the right node if
      the key passed is more, or returns the value if the keys are equal.
     */
    private Value get(Node n, Key key) {
        if (n == null) return null;
        int cmp = key.compareTo(n.key);
        if (cmp < 0) return get(n.left, key);
        else if (cmp > 0) return get(n.right, key);
        else return n.val;

    }
    /*
    Inserts the Key Value pair into the tree, Overwriting the old value with the new value if
    the key is already present in the tree.
    If we pass null value then it is same as deleting the key from the tree.
     */

    public void put(Key key, Value value) {

    }

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }


    }


}