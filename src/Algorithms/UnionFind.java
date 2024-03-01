package Algorithms;

import java.util.Random;

// the elements are stored in tree-like structure
public class UnionFind {
    private int[] id; // parent of i is id[i]
    private int[] sz; // size of tree where i is the root

    public UnionFind(int n) {
        id = new int[n];
        sz = new int[n];
        for(int i = 0; i < n; i++) {
            id[i] = i; // parent of a root is the root itself
            sz[i] = 1;
        }
    }

    // if both elements have the same root they are connected
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    // compares sizes of two trees that q and p belong to and
    // connects root of a smaller tree to the root of a bigger one;
    // that way tree is more flat
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return; // if elements have the same root they are already connected
        if (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }
        else { id[j] = i; sz[j] += sz[i];  }
    }

    // while the root is not reached, i becomes its parent
    public int root(int i) {
        while (id[i] != i) {
            id[i] = id[id[i]]; // connecting element to its grandparent to flatten the tree
            i = id[i];
        }
        return i;
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(100_000_000);
        Random r = new Random();
        for(int i = 0; i < 1000; i++) {
            uf.union(r.nextInt(1000), r.nextInt(100_000_0000));
        }

    }
}


