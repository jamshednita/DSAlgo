package com.graph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

public class GraphCycleQ {
	/**
	 * Description - Detect Cycle in a Directed Graph. Time Complexity = O(V+E)
	 * @param dirG
	 * @return
	 */
	public static boolean detectCycleInDirG(Graph dirG) {
		boolean[] visited = new boolean[dirG.getV()];
		
		for (int i = 0; i < dirG.getV(); i++) {
			Arrays.fill(visited, false);
			
			if(detectCycleInDirGDFS(dirG, i, visited))
				return true;
		}
		
		return false;
	}

	private static boolean detectCycleInDirGDFS(Graph dirG, int u, boolean[] visited) {
		visited[u] = true;
		//boolean result = false;
		
		Iterator<Integer> vi = dirG.getAdjListArr()[u].iterator();
		while (vi.hasNext()) {
			Integer v = (Integer) vi.next();
			
			if(visited[v])
				return true;
			else if(detectCycleInDirGDFS(dirG, v, visited))
				return true;
			 //result = detectCycleDFS(dirG, v, visited);
		}
		
		visited[u] = false; // remove visited flag while backtracking
		//return result;
		return false;
	}
	/**
	 * Description - Detect cycle in an undirected graph. O(V+E)
	 * @param g
	 * @return
	 */
	public static boolean detectCycleInUnDirG(Graph g) {
		boolean[] visited = new boolean[g.getV()];
		
		for (int i = 0; i < g.getV(); i++) {
			if(!visited[i]) {
				if(detectCycleInUnDirGDFS(g, i, -1, visited))
					return true;
			}
		}
		return false;
	}

	private static boolean detectCycleInUnDirGDFS(Graph g, int u, int parent, boolean[] visited) {
		visited[u] = true;
		
		Iterator<Integer> vi = g.getAdjListArr()[u].iterator();
		while(vi.hasNext()) {
			Integer v = vi.next();
			
			if(!visited[v]) { // If it's not visited then surely it's not parent bcoz parent will be visited first.
				if(detectCycleInUnDirGDFS(g, v, u, visited))
					return true;
			}else if(v!=parent)
				return true;
		}
		return false;
	}
	/**
	 * Description - Detect Cycle in a directed graph using colors.
	 * @param dirG
	 * @return
	 */
	public static boolean detectCycleUsingColor(Graph dirG) {
		Color[] colors = new Color[dirG.getV()];
		Arrays.fill(colors, Color.WHITE); // fill WHITE initially.
		
		for (int i = 0; i < dirG.getV(); i++) {
			if (colors[i] == Color.WHITE) {
				if(detectCycleUsingColorDFS(dirG, i, colors)) // If Cycle detected the n return true.
					return true;
			}
		}
		
		return false;
	}

	private static boolean detectCycleUsingColorDFS(Graph dirG, int u, Color[] colors) {
		colors[u] = Color.GRAY;
		
		Iterator<Integer> vi = dirG.getAdjListArr()[u].iterator();
		while(vi.hasNext()) {
			Integer v = vi.next();
			
			if(colors[v] == Color.GRAY) // (colors[v] != Color.WHITE) this will be equally work good but .. To with cycle in graph, node will not become BLACK until cycle detected. 
				return true;
			else if(colors[v] == Color.WHITE && detectCycleUsingColorDFS(dirG, v, colors))
				return true;
		}
		
		colors[u] = Color.BLACK;
		return false;
	}
	
	/**
	 * Description - Assign directions to edges so that the directed graph remains acyclic
					 Given a graph with both directed and undirected edges. It is given that the directed edges don’t form cycle. How to assign directions to undirected edges so that the graph (with all directed edges) remains acyclic even after the assignment?
	 * @param g
	 * @param u
	 * @param v
	 * @return
	 */
	public static Graph addDirectionToUndirectedEdge(Graph g, int u, int v) {
		// G is directed graph without unDirected edges.
		// Get Topological sorting of g.
		Stack<Integer> topSortStack = topologicalSorting(g);
		
		// See if u-position is ahead of v-position then add directed edge u-->v otherwise v-->u.
		int uPos = topSortStack.indexOf(u);
		int vPos = topSortStack.indexOf(v);
		
		if(uPos>vPos) {
			g.addDirectedEdge(u, v);
		}else {
			g.addDirectedEdge(v, u);
		}
		
		return g; // Changed DAG.
	}

	/**
	 * Description - 
	 * @param g
	 */
	public static Stack<Integer> topologicalSorting(Graph g) {
		Stack<Integer> topSortStack = new Stack<>();
		boolean[] visited = new boolean[g.getV()];
		
		for (int i = 0; i < g.getV(); i++) {
			if (!visited[i]) {
				topologicalSortingDFS(g, i, visited, topSortStack);
			}
		}
		
		System.out.println("Topological Sorting :: "+topSortStack);
		
		return topSortStack;
	}
	private static void topologicalSortingDFS(Graph g, int u, boolean[] visited, Stack<Integer> topSortStack) {
		visited[u] = true;
		
		Iterator<Integer> vi = g.getAdjListArr()[u].iterator();
		while(vi.hasNext()) {
			Integer v = vi.next();
			if(!visited[v]) {
				topologicalSortingDFS(g, v, visited, topSortStack);
			}
		}
		
		topSortStack.push(u);
	}
	
	public int countCycles(int[][] gm, int V, int n) {
		CustSum total = new CustSum();
		
		boolean[] marked = new boolean[V];
		
		for (int i = 0; i < (V-n+1); i++) {
			countCyclesDFS(gm, i, i, n-1, marked, total);
			
			marked[i] = true;
		}
		
		return total.count/2;
	}

	private void countCyclesDFS(int[][] gm, int u, int start, int n, boolean[] marked, CustSum total) {
		// mark the u vert as visited
		marked[u] = true;
		// if the path of length (n-1) is found
		if(n==0) {
			// mark u as un-visited to 
            // make it usable again
			marked[u] = false;
			// Check if vertex u end 
            // with vertex start
			if(gm[u][start] == 1) {
				total.count++;
			}
			
			return;
		}
		// For searching every possible 
        // path of length (n-1)
		for (int i = 0; i < marked.length; i++) {
			if(!marked[i] && gm[u][i]==1) {
				// DFS for searching path by
                // decreasing length by 1
				countCyclesDFS(gm, i, start, n-1, marked, total);
			}
		}
		// marking u as unvisited to make it
        // usable again
		marked[u] = false;
	}
	/**
	 * Description - Check if there is a cycle with odd weight sum in an undirected graph.
	 * @param wg
	 * @return
	 */
	public static boolean isOddWeightCycle(WeightedGraph wg) {
		int[] weights = new int[wg.getV()];
		boolean[] visited = new boolean[wg.getV()];
		
		for (int i = 0; i < wg.getV(); i++) {
			if(!visited[i] && (wg.getAdjListArr()[i]!=null && !wg.getAdjListArr()[i].isEmpty())) {
				weights[i]=0;
				if(isOddWeightCycleDFS(wg, i, -1, visited, weights))
					return true;
			}
		}
		
		return false;
	}
	private static boolean isOddWeightCycleDFS(WeightedGraph wg, int u, int parent, boolean[] visited, int[] weights) {
		visited[u] = true;
		Iterator<AdjListNode> vi = wg.getAdjListArr()[u].iterator();
		
		while(vi.hasNext()) {
			AdjListNode v = vi.next();
			
			if(!visited[v.getVertex()]) {
				weights[v.getVertex()] = weights[u]+v.getWeight();
				if(isOddWeightCycleDFS(wg, v.getVertex(), u, visited, weights))
					return true;
			}else if(v.getVertex() != parent) {
				weights[v.getVertex()] = weights[u]+v.getWeight();
				if(weights[v.getVertex()]%2 != 0)
					return true;
			}
		}
		
		return false;
	}

	public static void main(String[] args) {
		/*Graph dirG = new Graph(4);
		
		dirG.addDirectedEdge(0, 1);
		dirG.addDirectedEdge(0, 2);
		dirG.addDirectedEdge(1, 2);
		//dirG.addDirectedEdge(2, 0);
		dirG.addDirectedEdge(2, 3);
		//dirG.addDirectedEdge(3, 3);
		
		System.out.println("Has Cycle :: "+detectCycleInDirG(dirG));
		System.out.println("Has Cycle Using Color :: "+detectCycleUsingColor(dirG));*/
		
		// Create a graph given in the above diagram
        /*Graph udg = new Graph(5);
        udg.addUnDirectedEdge(1, 0);
        udg.addUnDirectedEdge(0, 2);
        udg.addUnDirectedEdge(0, 3);
        udg.addUnDirectedEdge(2, 3);
        udg.addUnDirectedEdge(3, 4);
        
        System.out.println("Has Cycle :: "+detectCycleInUnDirG(udg));*/
		
		// Prepare Directed Graph Without UnDirected Edges.
		/*Graph tpsg = new Graph(6);
		tpsg.addDirectedEdge(0, 1);
		tpsg.addDirectedEdge(0, 5);

		tpsg.addDirectedEdge(1, 2);
		tpsg.addDirectedEdge(1, 3);
		tpsg.addDirectedEdge(1, 4);
		
		tpsg.addDirectedEdge(2, 3);
		tpsg.addDirectedEdge(2, 4);
		
		tpsg.addDirectedEdge(3, 4);
		
		tpsg.addDirectedEdge(5, 2);
		tpsg.addDirectedEdge(5, 1);
		
		//topologicalSorting(tpsg);
		
		tpsg = addDirectionToUndirectedEdge(tpsg, 0, 2);
		tpsg = addDirectionToUndirectedEdge(tpsg, 0, 3);
		tpsg = addDirectionToUndirectedEdge(tpsg, 5, 4);
		
		System.out.println(tpsg);
		
		int[][] graph = {{0, 1, 0, 1, 0},
                		{1, 0, 1, 0, 1},
                		{0, 1, 0, 1, 0},
                		{1, 0, 1, 0, 1},
                		{0, 1, 0, 1, 0}};
		int n = 4;
		
		System.out.println(new GraphCycleQ().countCycles(graph, 5, n));*/
		
		WeightedGraph wg = new WeightedGraph(4);
		wg.addUnDirectedEdge(0, 1, 12);
		wg.addUnDirectedEdge(1, 2, 1);
		wg.addUnDirectedEdge(2, 3, 1);
		wg.addUnDirectedEdge(3, 0, 20);
		
		System.out.println("Is odd weight cycle present :: "+isOddWeightCycle(wg));
		WeightedGraph wg1 = new WeightedGraph(5);
		wg1.addUnDirectedEdge(1, 2, 1);
		wg1.addUnDirectedEdge(3, 2, 1);
		wg1.addUnDirectedEdge(3, 1, 1);
		
		System.out.println("Is odd weight cycle present :: "+isOddWeightCycle(wg1));
	}
	
	class CustSum{
		int count=0;
	}
	
	enum Color{
		WHITE,GRAY,BLACK;
	}

}
