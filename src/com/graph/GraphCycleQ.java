package com.graph;

import java.util.Arrays;
import java.util.Iterator;

public class GraphCycleQ {
	/**
	 * Description - Detect Cycle in a Directed Graph. Time Complexity = O(V+E)
	 * @param dirG
	 * @return
	 */
	public static boolean detectCycle(Graph dirG) {
		boolean[] visited = new boolean[dirG.getV()];
		
		for (int i = 0; i < dirG.getV(); i++) {
			Arrays.fill(visited, false);
			
			if(detectCycleDFS(dirG, i, visited))
				return true;
		}
		
		return false;
	}

	private static boolean detectCycleDFS(Graph dirG, int u, boolean[] visited) {
		visited[u] = true;
		boolean result = false;
		
		Iterator<Integer> vi = dirG.getAdjListArr()[u].iterator();
		while (vi.hasNext()) {
			Integer v = (Integer) vi.next();
			
			if(visited[v])
				return true;
			
			 result = detectCycleDFS(dirG, v, visited);
		}
		
		visited[u] = false; // remove visited flag while backtracking
		return result;
	}

	public static void main(String[] args) {
		Graph dirG = new Graph(4);
		
		dirG.addDirectedEdge(0, 1);
		dirG.addDirectedEdge(0, 2);
		dirG.addDirectedEdge(1, 2);
		//dirG.addDirectedEdge(2, 0);
		dirG.addDirectedEdge(2, 3);
		//dirG.addDirectedEdge(3, 3);
		
		System.out.println(detectCycle(dirG));

	}

}
