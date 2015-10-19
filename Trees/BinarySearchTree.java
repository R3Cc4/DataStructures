
import java.util.NoSuchElementException;

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
        if (value == null) {
            delete(key);
            return;
        }
        root = put(root, key, value);
        assert check();
    }

    /*
    Private method to put the node at its designated place. This will insert a new node if the passed
    node value is null. Else compares the key element with the current node and inserts in the left subtree
    if the key is less or inserts in the right sub tree if the key is more. if the keys are equal then it
    will replace with the value provided.
     */
    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    /*
    Removes the smallest key and the associated value with it from the tree.
     */
    public void deleteMin() {

        if (isEmpty()) throw new NoSuchElementException("Tree is Empty");
        root = deleteMin(root);
        assert check();
    }

    /*
    Private method to perform the operation of deletion of the minimum node.
     */
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * Removes the largest key and associated value from the symbol table.
     *
     * @throws NoSuchElementException if the Tree is empty
     */
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMax(root);
        assert check();
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * Removes the key and associated value from the Tree
     * (if the key is in the symbol table).
     *
     * @param key the key
     * @throws NullPointerException if key is null
     */
    public void delete(Key key) {
        root = delete(root, key);
        assert check();
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) delete(x.left, key);
        else if (cmp > 0) delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    /*
    returns the smallest key in the Tree.
    * @throws NoSuchElementException if the Tree is empty
     */

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException(" Called Min() while the Binary Search Tree is Empty");
        return min(root).key;
    }
    /*
    Returns the node with minimum key.
     */

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);

    }

    /**
     * Returns the largest key in the symbol table.
     *
     * @return the largest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty Binary Search Tree");
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else return max(x.right);
    }

    /**
     * Returns the largest key in the Binary Search Tree less than or equal to key.
     *
     * @param key the key
     * @return the largest key in the Binary Search Tree less than or equal to key
     * @throws NoSuchElementException if there is no such key
     * @throws NullPointerException   if key is null
     */
    public Key floor(Key key) {
        if (isEmpty()) throw new NoSuchElementException("called floor() with empty symbol table");
        Node x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    /**
     * Returns the smallest key in the Binary Search Tree greater than or equal to <tt>key</tt>.
     *
     * @param key the key
     * @return the smallest key in the symbol table greater than or equal to <tt>key</tt>
     * @throws NoSuchElementException if there is no such key
     * @throws NullPointerException   if key  is <tt>null</tt>
     */
    public Key ceiling(Key key) {
        if (isEmpty()) throw new NoSuchElementException("called ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) {
            Node t = ceiling(x.left, key);
            if (t != null) return t;
            else return x;
        }
        return ceiling(x.right, key);
    }

    /**
     * Return the kth smallest key in the Binary search Tree.
     *
     * @param k the order statistic
     * @return the kth smallest key in the symbol table
     * @throws IllegalArgumentException unless <tt>k</tt> is between 0 and
     */
    public Key select(int k) {
        if (k < 0 || k >= size()) throw new IllegalArgumentException();
        Node x = select(root, k);
        return x.key;
    }

    // Return key of rank k.
    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    /**
     * Return the number of keys in the symbol table strictly less than <tt>key</tt>.
     *
     * @param key the key
     * @return the number of keys in the symbol table strictly less than <tt>key</tt>
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public int rank(Key key) {
        return rank(key, root);
    }

    // Number of keys in the subtree less than key.
    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }


    /**
     * Returns all keys in the symbol table as an <tt>Iterable</tt>.
     * To iterate over all of the keys in the symbol table named <tt>st</tt>,
     * use the foreach notation: <tt>for (Key key : st.keys())</tt>.
     *
     * @return all keys in the symbol table
     */
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    /**
     * Returns all keys in the symbol table in the given range,
     * as an <tt>Iterable</tt>.
     *
     * @return all keys in the sybol table between <tt>lo</tt>
     * (inclusive) and <tt>hi</tt> (exclusive)
     * @throws NullPointerException if either <tt>lo</tt> or <tt>hi</tt>
     *                              is <tt>null</tt>
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    /**
     * Returns the number of keys in the symbol table in the given range.
     *
     * @return the number of keys in the sybol table between <tt>lo</tt>
     * (inclusive) and <tt>hi</tt> (exclusive)
     * @throws NullPointerException if either <tt>lo</tt> or <tt>hi</tt>
     *                              is <tt>null</tt>
     */
    public int size(Key lo, Key hi) {
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    /**
     * Returns the height of the BST (for debugging).
     *
     * @return the height of the BST (a 1-node tree has height 0)
     */
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    /**
     * Returns the keys in the BST in level order (for debugging).
     *
     * @return the keys in the BST in level order traversal
     */
    public Iterable<Key> levelOrder() {
        Queue<Key> keys = new Queue<Key>();
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) continue;
            keys.enqueue(x.key);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return keys;
    }

    /*************************************************************************
     * Check integrity of BST data structure.
     ***************************************************************************/
    private boolean check() {
        if (!isBST()) StdOut.println("Not in symmetric order");
        if (!isSizeConsistent()) StdOut.println("Subtree counts not consistent");
        if (!isRankConsistent()) StdOut.println("Ranks not consistent");
        return isBST() && isSizeConsistent() && isRankConsistent();
    }

    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure is a binary tree since order is strict
    private boolean isBST() {
        return isBST(root, null, null);
    }

    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    // are the size fields correct?
    private boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        if (x.N != size(x.left) + size(x.right) + 1) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    // check that ranks are consistent
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
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