package Algorithms.Graph.UndirectedGraph;

import DataStructures.Graph.UndirectedGraphAPI.Graph;

import java.util.Stack;

// Same for directed graph
public class DepthFirstSearchPaths {
    private int[] edgeTo;
    private boolean[] marked;
    private int s;

    public DepthFirstSearchPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.v()];
        marked = new boolean[G.v()];
        dfs(G, s);
    }

    public boolean hasPathTo(int v) { return marked[v]; }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path  = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
                edgeTo[w] = v;
            }
        }
    }
}
