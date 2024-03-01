package Algorithms.Graph.DirectedGraph;//package Algorithms.Graph.DirectedGraph;
//
//
//import DataStructures.Graph.DirectedGraphAPI.Digraph;
//
//// Kosaraju Sharir's algorithm
//// 1) reverse graph
//// 2) get topological order (DepthSearchFirstOrder)
//// 3) run dfs in topological order on the given graph
//public class StronglyConnectedComponents {
//    private boolean[] marked;
//    private int[] id;
//    private int count;
//
//
//    public StronglyConnectedComponents(Digraph G) {
//        id = new int[G.v()];
//        marked = new boolean[G.v()];
//        count = 0;
//
//        TopologicalOrder dfs = new TopologicalOrder(G.reverse());
//
//        for (int v : dfs.reversePost()) {
//            if (!marked[v]) {
//                dfs(G, v);
//                count++;
//            }
//        }
//    }
//
//    public boolean stronglyConnected(int v, int w) {
//        return id[v] == id[w];
//    }
//
//    private void dfs(Digraph G, int v) {
//        marked[v] = true;
//        id[v] = count;
//        for (int w : G.adj(v))
//            if (!marked[w])
//                dfs(G, w);
//
//
//    }
//}
