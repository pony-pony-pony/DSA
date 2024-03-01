package Algorithms.Graph.MinimumSpanningTree;

import Algorithms.UnionFind;
import DataStructures.Graph.UndirectedGraphAPI.Edge;
import DataStructures.Graph.UndirectedGraphAPI.EdgeWeightedGraph;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

// MST - graph that connects all the vertices in weighted graph in the way that
// total weight of all the edges is minimal
// Cut - partition of vertices into two (nonempty) sets
// Crossing edges - edges that connect element from both sets
// Theorem: given any cut, the crossing edge of min weight is in the MST

// Using this theorem we can build greedy algorithm: make any cut without black edge
// (in the beginning there're no black ones), find min-weight crossing edge - mark this edge black,
// repeat until v-1 edges are colored black.

// ElogE
// 1) sort all egdes in descending order of their weight
// 2) while there are v-1 edges (property of MST),
// add min-weight edge to MST if it doesn't create a cycle in mst
public class KruskalMinimumSpanningTree {
    private Queue<Edge> mst = new LinkedList<>();

    public KruskalMinimumSpanningTree(EdgeWeightedGraph G) {
        PriorityQueue<Edge> minEdgePQ = new PriorityQueue<>(Comparator.comparingDouble(Edge::weight).reversed());
        for (Edge e : G.edges())
            minEdgePQ.offer(e);
        UnionFind uf = new UnionFind(G.v());
        while (!minEdgePQ.isEmpty() && mst.size() < G.v()-1) {
            Edge e = minEdgePQ.poll();
            int v = e.either(), w = e.other(v);
            if (!uf.connected(v, w)) {
                uf.union(v, w);
                mst.offer(e);
            }
        }
    }

    public  Iterable<Edge> edges() { return mst; }
}
