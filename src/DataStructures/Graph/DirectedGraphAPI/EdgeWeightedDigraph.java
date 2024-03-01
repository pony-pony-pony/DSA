package DataStructures.Graph.DirectedGraphAPI;

import DataStructures.Bag;

public class EdgeWeightedDigraph {
    private final int V;
    private final Bag<DirectedEdge>[] adj; // adjacency lists
    private final Bag<DirectedEdge> edges;

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<DirectedEdge>();
        edges = new Bag<>();
    }

    public void addEdge(DirectedEdge e) {
        int v = e.from();
        adj[v].add(e);
        edges.add(e);
    }

    public int v() { return V; }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() { return edges; }
}
