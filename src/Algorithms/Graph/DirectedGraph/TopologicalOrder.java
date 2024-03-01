package Algorithms.Graph.DirectedGraph;

import DataStructures.Graph.DirectedGraphAPI.DirectedEdge;
import DataStructures.Graph.DirectedGraphAPI.EdgeWeightedDigraph;

import java.util.Stack;

// Reverse postordering produces a topological sorting.
// works only on DAG(Directed Acyclic Graph)
public class TopologicalOrder {
    private boolean[] marked;
    private Stack<Integer> reversePost;

    public TopologicalOrder(EdgeWeightedDigraph G)
    {
        marked = new boolean[G.v()];
        reversePost = new Stack<>();

        for (int v = 0; v < G.v(); v++) {
            if (!marked[v]) dfs(G, v);
        }

    }

    public Iterable<Integer> order() {
        return reversePost;
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        marked[v] = true;
        for (DirectedEdge e : G.adj(v))
            if (!marked[e.to()])
                dfs(G, e.to());
        reversePost.add(v);
    }

}
