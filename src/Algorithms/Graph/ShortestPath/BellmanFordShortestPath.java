package Algorithms.Graph.ShortestPath;

import DataStructures.Graph.DirectedGraphAPI.DirectedEdge;
import DataStructures.Graph.DirectedGraphAPI.EdgeWeightedDigraph;

import java.util.Stack;

// Works with negative weights, but no negative cycles
// EV
// 1) Relax each edge V times
// Could be impoved with queue
public class BellmanFordShortestPath {
    private final double[] distTo;
    private final DirectedEdge[] edgeTo;

    public BellmanFordShortestPath(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.v()];
        edgeTo = new DirectedEdge[G.v()];

        for (int v = 0; v < G.v(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        for (int i = 0; i < G.v(); i++)
            for (int v = 0; v < G.v(); v++)
                for (DirectedEdge e : G.adj(v))
                    relax(e);
    }

    public double distTo(int v) { return distTo[v]; }

    public Iterable<DirectedEdge> pathTo(int v) {
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }

    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) { // if found shorter path to w
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }
}
