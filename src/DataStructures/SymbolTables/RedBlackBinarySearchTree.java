package DataStructures.SymbolTables;

import java.util.LinkedList;
import java.util.Queue;


// 2-3 Search Tree
// 2-node has 2 children; 3-node has 3 children;
// 2-node contains 1 key-value; 3-node contains 2 key-value
// 2-node is basically regular node in BST.
// 3-node left child is less than both keys in the node, right is greater than both keys in the node,
// middle child is bigger than first key and smaller than second key in the node.
// Insertion: As in all the BST nodes are added only in the bottom.
// If node is 2-node in the bottom then make it 3-node. If it's a 3-node in the bottom make it temporary 4-node, then
// pass middle in key to the parent. If parent is a 2-node make it 3-node, otherwise do the same as
// with child. So, if this process reaches a root node and root is a 3-node, then after creating temporary 4-node,
// we create new 2-node root with the middle key and left and right keys become 2-node children.
// 2-3 Search Tree guarantees that every path from toot to null link has same length
// search - O(logN)
// insert - O(logN)
// delete - O(logN)

// Left-Leaning Red–Black Tree
// It's a 2-node representation of 2-3 tree. 3-node in LLRBT is:
// larger of two keys in 3-node is a root, smaller is left child and link between these nodes is red.
// All the properties come from 2-3 tree: 1) No node has two red links - this would be a 4-node which is not
// allowed in 2-3 tree. 2) Every path from root to null link has the same number of black links -
// if we put into 3-nodes all the red linked nodes we will get tree with only black links and this tree
// will be a 2-3 tree and 2-3 tree guarantees that every path from toot to null link has same length.
// 3) Red links lean left.
// search - O(logN) - 2logN
// insert - O(logN) - 2logN
// delete - O(logN) - 2logN
public class RedBlackBinarySearchTree<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;


    public static void main(String[] args) {
        RedBlackBinarySearchTree<Integer, Integer> bst = new RedBlackBinarySearchTree<>();
        bst.put(1, 134);
        bst.put(2, 14);
        bst.put(3, 14);
        bst.put(4, 12);
        bst.put(7, 12);
        bst.put(8, 132);
        bst.put(10, 12);
        bst.put(11, 12);
        bst.put(12, 55);

        System.out.println(bst.size(bst.root.right.left));
        System.out.println(bst.size());

        System.out.println(bst.rank(4));
        System.out.println("----------------");
        bst.print();

        System.out.println(bst.keys());
    }



    public void deleteMin() {
        if (root == null) return;
        root = deleteMin(root);
    }
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }
    public void deleteMax() {
        if (root == null) return;
        root = deleteMax(root);
    }
    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.size = size(x.left)  + size(x.right) + 1;
        return x;
    }

    public Key min() {
        return min(root).key;
    }
    private Node min(Node x) {
        while (x.left != null)
            x = x.left;
        return x;
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        while (x.right != null)
            x = x.right;
        return x;
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new LinkedList<>();
        inorder(root, q);
        return q;
    }
    private void inorder(Node x, Queue<Key> q) {
        if (x == null) return;
        inorder(x.left, q);
        q.offer(x.key);
        inorder(x.right, q);
    }

    public int rank(Key key) {
        return rank(root, key);
    }
    private int rank(Node x, Key key) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp > 0)      return 1 + size(x.left) + rank(x.right, key);
        else if (cmp < 0) return rank(x.left, key);
        else              return size(x.left);
    }

    public int size() {
        return size(root);
    }
    private int size(Node x) {
        if(x == null) return 0;
        return x.size;
    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }
    private Node ceiling(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0)  return ceiling(x.right, key);

        Node xx = ceiling(x.left, key);
        if (xx != null) return xx;
        return x;
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }
    private Node floor(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0)  return floor(x.left, key);

        Node xx = floor(x.right, key);
        if (xx != null) return xx;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }
    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;

            Node xx = x;
            x = min(xx.right);
            x.right = deleteMin(xx.right);
            x.left = xx.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null){
            int cmp = key.compareTo(x.key);
            if (cmp > 0) x = x.right;
            else if (cmp < 0) x = x.left;
            else return x.val;
        }
        return null;
    }
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null) return new Node(key, val, RED, 1);
        int cmp = key.compareTo(h.key);
        if (cmp > 0)      h.right = put(h.right, key, val);
        else if (cmp < 0) h.left = put(h.left, key, val);
        else              h.val = val;

        if (isRed(h.right) && !isRed(h.left))    h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    private boolean isRed(Node x){
        if(x == null) return false;
        return x.color == RED;
    }

    private Node rotateLeft(Node h) {
        assert isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateRight(Node h) {
        assert isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node h) {
        assert !isRed(h);
        assert isRed(h.left);
        assert isRed(h.right);
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }




    public void print() {
        print(root, 0);
    }
    private void print(Node h, int l) {
        int i;
        if (h != null) {
            print(h.right, l+1);
            for (i = 1; i <= l; i++) System.out.print("        ");
            System.out.println(h);
            print(h.left, l+1);
        }
    }


    private class Node {
        Key key;
        Value val;
        Node left, right;
        int size;

        boolean color;

        public Node(Key key, Value val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }

        @Override
        public String toString() {
            return "(" + key + ";" + val + ')';
        }
    }
}
