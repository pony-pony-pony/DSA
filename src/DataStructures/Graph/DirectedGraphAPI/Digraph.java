package DataStructures.Graph.DirectedGraphAPI;

import DataStructures.Bag;

public class Digraph {
    private final int V;
    private Bag<Integer>[] adj; // adjacency lists

    public Digraph(int V) {
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    public Digraph reverse() {
        Digraph reversed = new Digraph(V);
        for (int v = 0; v < V; v++)
            for (int w : adj[v])
                reversed.addEdge(w, v);

        return reversed;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    public int v() { return V; }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}
