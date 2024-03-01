package DataStructures.Graph.UndirectedGraphAPI;

import DataStructures.Bag;

public class EdgeWeightedGraph {
    private final int V;
    private final Bag<Edge>[] adj; // adjacency lists
    private final Bag<Edge> edges;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<Edge>();
        edges = new Bag<>();
    }

    public void addEdge(Edge e) {
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        edges.add(e);
    }

    public int v() { return V; }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() { return edges; }
}
