package Algorithms.Graph.ShortestPath;


import Algorithms.Graph.DirectedGraph.TopologicalOrder;
import DataStructures.Graph.DirectedGraphAPI.DirectedEdge;
import DataStructures.Graph.DirectedGraphAPI.EdgeWeightedDigraph;

import java.util.Stack;

// Works for no cycles and negative weights
// 1) since graph is acyclic, it has a topological order. Find topological order
// 2) iterate in topological order and relax all the ages adjacent to vertex
public class AcyclicShortestPath {
    private final double[] distTo;
    private final DirectedEdge[] edgeTo;

    public AcyclicShortestPath(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.v()];
        edgeTo = new DirectedEdge[G.v()];

        for (int v = 0; v < G.v(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        for (int v : new TopologicalOrder(G).order())
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
