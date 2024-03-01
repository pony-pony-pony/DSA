package Algorithms.Graph.MinCutMaxFlow;

import DataStructures.Graph.MinCutMaxFlow.FlowEdge;
import DataStructures.Graph.MinCutMaxFlow.FlowNetwork;

import java.util.LinkedList;
import java.util.Queue;

public class FordFulkerson {
    private boolean[] marked;  // true if s->v path is residual network
    private FlowEdge[] edgeTo; // last edge of s->v path
    private double value;      // value of flow

    public FordFulkerson(FlowNetwork G, int s, int t) {
        value = 0.0;
        while (hasAugmentingPath(G, s, t)) {
            double bottle = Double.POSITIVE_INFINITY;
            for (int v = t; v != s; v = edgeTo[v].other(v))
                bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));

            for (int v = t; v != s; v = edgeTo[v].other(v))
                edgeTo[v].addResidualFlowTo(v, bottle);

            value += bottle;
        }
    }

    private boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
        edgeTo = new FlowEdge[G.v()];
        marked = new boolean[G.v()];

        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        marked[s] = true;
        while (!q.isEmpty()) {
            int v = q.poll();

            for (FlowEdge e : G.adj(v)) {
                int w = e.other(v);
                if (e.residualCapacityTo(w) > 0 && !marked[w]) {
                    edgeTo[w] = e;
                    marked[w] = true;
                    q.offer(w);
                }
            }
        }

        return marked[t];
    }

    public double value() { return value; }
    public boolean inCut(int v) { return marked[v]; }
}
