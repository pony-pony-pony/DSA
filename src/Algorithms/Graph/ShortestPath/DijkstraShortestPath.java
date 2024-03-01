package Algorithms.Graph.ShortestPath;

import DataStructures.Graph.DirectedGraphAPI.DirectedEdge;
import DataStructures.Graph.DirectedGraphAPI.EdgeWeightedDigraph;

import java.util.Stack;


// Works with cycles, but no negative weights; if there is no cycles better do TopologicalShortestPath
// ElogV - depends on IndexMinPQ implementation
// 1) start with 0 vertex, others are initialized as infinity (weight of path to vertex is infinity)
// 2) while priority queue is not empty pick the vertex with the shortest path and relax all the adjacent
public class DijkstraShortestPath {
    private final double[] distTo;
    private final DirectedEdge[] edgeTo;
    private final IndexMinPQ<Double> pq;


    public DijkstraShortestPath(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.v()];
        edgeTo = new DirectedEdge[G.v()];
        pq = new IndexMinPQ<Double>(G.v());
        for (int v = 0; v < G.v(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        pq.offer(s, 0.0);
        while (!pq.isEmpty()) {
            int v = pq.poll();
            for (DirectedEdge e : G.adj(v))
                relax(e);
        }
    }

    public double distTo(int v) { return distTo[v]; }

    public Iterable<DirectedEdge> pathTo(int v) {
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }

    // given an edge with known distance to each vertex,
    // if from v found a shorter path to w change path to w.
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) { // if found shorter path to w
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]); // decreases path weight to w
            else pq.offer(w, distTo[w]);
        }
    }

    // priority queue that sorts stores weights of paths to vertices
    // if you want an implementation just google name of the class to find it
    static class IndexMinPQ<Key extends Comparable<Key>> {
        public IndexMinPQ(int v) {
        }

        void decreaseKey(int w, double distTo) {}

        public void offer(int w, double distTo) {}

        public boolean contains(int w) {
            return true;
        }

        public int poll() {
            return 0;
        }

        public boolean isEmpty() {
             return true;
        }
    }
}
