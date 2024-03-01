package Algorithms.Graph.UndirectedGraph;

import DataStructures.Graph.UndirectedGraphAPI.Graph;

public class ConnectedComponents {
    private boolean[] marked;
    private int[] id;
    private int count;


    public ConnectedComponents(Graph G) {
        id = new int[G.v()];
        marked = new boolean[G.v()];
        count = 0;

        for (int v = 0; v < G.v(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    public int id(int v) { return id[v]; }

    public int count() { return count; }


    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v))
            if (!marked[w])
                dfs(G, w);


    }
}
