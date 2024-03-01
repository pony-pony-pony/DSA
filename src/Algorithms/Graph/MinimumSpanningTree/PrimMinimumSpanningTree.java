package Algorithms.Graph.MinimumSpanningTree;

import DataStructures.Graph.UndirectedGraphAPI.Edge;
import DataStructures.Graph.UndirectedGraphAPI.EdgeWeightedGraph;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


// see. KruskalMinimumSpanningTree class for more info about MST.

// The following code is a lazy implementation of Prim's algorithm - we have a priority queue, that
// contains edges that can't be used, and we delete these edges only when we encounter them;
// (actually we just ignore them). There is also a eager implementation - in priority queue
// we store only v elements max - min-weight edge for each vertex not in the tree
// (edge connects tree element and element that is not in tree yet).

// ElogE; E - extra space (worst case)
// 1) start with vertex 0
// 2) find min-weight edge that connects vertex from MST and vertex that is not in MST,
// add this edge to MST and mark vertex
public class PrimMinimumSpanningTree {
    private final boolean[] marked; // is vertex in the tree
    private final Queue<Edge> mst;
    private final PriorityQueue<Edge> minEdgePQ;

    public PrimMinimumSpanningTree(EdgeWeightedGraph G) {
        marked = new boolean[G.v()];
        mst = new LinkedList<>();
        minEdgePQ = new PriorityQueue<>(Comparator.comparingDouble(Edge::weight).reversed());
        visit(G, 0);
        while (!minEdgePQ.isEmpty()) {
            Edge e = minEdgePQ.poll();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue; // if both vertices are already in MST ignore the edge
            mst.offer(e);
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);
        }
    }

    public Iterable<Edge> mst() { return mst; }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            if (!marked[e.other(v)]) minEdgePQ.offer(e);
        }
    }
}
