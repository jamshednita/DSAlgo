package com.graph;

import java.util.Iterator;

public class IDDFSImpl {
	/**
	 * Description - Iterative Deepening Search(IDS) or Iterative Deepening Depth First Search(IDDFS).
	 * There are two common ways to traverse a graph, BFS and DFS. Considering a Tree (or Graph) of huge height and width, both BFS and DFS are not very efficient due to following reasons.
		1. DFS first traverses nodes going through one adjacent of root, then next adjacent. The problem with this approach is, if there is a node close to root, but not in first few subtrees explored by DFS, then DFS reaches that node very late. Also, DFS may not find shortest path to a node (in terms of number of edges).
		2. BFS goes level by level, but requires more space. The space required by DFS is O(d) where d is depth of tree, but space required by BFS is O(n) where n is number of nodes in tree (Why? Note that the last level of tree can have around n/2 nodes and second last level n/4 nodes and in BFS we need to have every level one by one in queue).
	 
	 * @param iddfsGraph
	 * @param src
	 * @param target
	 * @param maxDepth
	 * @return
	 */
	public static boolean iddfs(Graph iddfsGraph, int src, int target, int maxDepth) {
		for (int i = 0; i < maxDepth; i++) {
			if(LDS(iddfsGraph, src, target, i))
				return true;
		}
		
		return false;
	}

	private static boolean LDS(Graph iddfsGraph, int src, int target, int depth) {
		if(src==target)
			return true;
		if(depth <= 0 )
			return false;
		
		Iterator<Integer> vi = iddfsGraph.getAdjListArr()[src].iterator();
		
		while (vi.hasNext()) {
			Integer v = (Integer) vi.next();
			
			if(LDS(iddfsGraph, v, target, depth-1))
				return true;
			
		}
		
		return false;
	}

	public static void main(String[] args) {
		Graph iddfsGraph = new Graph(7);
		
		iddfsGraph.addDirectedEdge(0, 1);
		iddfsGraph.addDirectedEdge(0, 2);
		
		iddfsGraph.addDirectedEdge(1, 3);
		iddfsGraph.addDirectedEdge(1, 4);
		iddfsGraph.addDirectedEdge(2, 5);
		iddfsGraph.addDirectedEdge(2, 6);
		
		int target = 6, maxDepth = 3, src = 0;
		System.out.println(iddfs(iddfsGraph,src,target,maxDepth));
	}
}
