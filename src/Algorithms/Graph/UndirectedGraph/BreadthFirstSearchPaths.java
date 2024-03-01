package Algorithms.Graph.UndirectedGraph;

import DataStructures.Graph.UndirectedGraphAPI.Graph;
import DataStructures.Queue.LinkedQueue;

public class BreadthFirstSearchPaths {
    private int[] edgeTo;
    private boolean[] marked;
    private int[] distTo;
    private int largestDist;
    private int s;

    public BreadthFirstSearchPaths(Graph G, int s) {
        edgeTo = new int[G.v()];
        marked = new boolean[G.v()];
        distTo = new int[G.v()];
        largestDist = 0;
        this.s = s;
        bfs(G, s);
    }

    public boolean hasPathTo(int v) { return marked[v]; }

    private void bfs(Graph G, int s) {
        LinkedQueue<Integer> q = new LinkedQueue<Integer>(); // i wrote it
        q.enqueue(s);
        marked[s] = true;
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    distTo[w] = largestDist;
                    edgeTo[w] = v;
                    q.enqueue(w);
                }
            }
            largestDist++;
        }
    }
}
