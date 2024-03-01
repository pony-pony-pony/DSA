package DataStructures.SymbolTables;

import java.util.LinkedList;
import java.util.Queue;

// Geometric applications of BST
// (see image)


// Binary Search Tree
// parent has two children, right child is bigger than parent, left child is smaller than parent;
// because bst can be completely unbalanced:
// search - O(N)
// insert - O(N)

// delete - O(N)

public class BinarySearchTree <Key extends Comparable<Key>, Value> {
    public static void main(String[] args) {
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();
        bst.put(1, "n");
        bst.put(2, "m");
        bst.put(-1, "min");
        bst.put(5, "mn");
        bst.put(10, "ui");
        bst.print();
        bst.keys();
    }

    private Node root;

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
        if (cmp > 0) return size(x.left) + 1 + rank(x.right, key);
        if (cmp < 0) return rank(x.left, key);
        else return size(x.left);
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
        root = put(key, val, root);
    }
    private Node put(Key key, Value val, Node x) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp > 0) x.right = put(key, val, x.right);
        else if (cmp < 0) x.left = put(key, val, x.left);
        else x.val = val;

        x.size = size(x.left) + size(x.right) + 1;
        return x;
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

    private class Node  {
        private Key key;
        private Value val;
        private Node left, right;
        private int size;

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }

        @Override
        public String toString() {
            return "(" + key + ";" + val + ')';
        }
    }

}
