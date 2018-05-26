package com.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class IntroDFSnBFSQ {
	/**
	 * Description - Breadth First Search or BFS for a Graph. BFS can be performed using queue. Time Complexity = O(E+V)
	 * @param graph - Graph to be BFSed
	 * @param startVertex - starting vertex from where rest vertices are reachable.
	 */
	public static void BFS(Graph graph, int startVertex) {
		boolean[] visited = new boolean[graph.getV()]; // To track visited nodes.
		
		//BFSUtil(graph, startVertex, visited);
		// Above statement will traverse all the node reachable from startVertex. But in disconnected graph some vertices might not be reachable so to traverse them as well below code is used. 
		for (int i = 0; i < visited.length; i++) {
			if (visited[i]==false) {
				BFSUtil(graph, i, visited);
			}
		}
	}

	private static void BFSUtil(Graph graph, int startVertex, boolean[] visited) {
		LinkedList<Integer>[] adjListArr = graph.getAdjListArr();

		LinkedList<Integer> queue = new LinkedList<>(); // Queue to store unvisited node.
		queue.add(startVertex);

		while (!queue.isEmpty()) {
			Integer s = queue.poll();
			visited[s] = true;
			System.out.print(s + " ");

			Iterator<Integer> adjListItr = adjListArr[s].iterator();
			// Go to all adjecent node and add it to queue if not visited earlier.
			while (adjListItr.hasNext()) {
				Integer vertex = adjListItr.next();

				if (!visited[vertex]) {
					queue.add(vertex);
				}
			}
		}
	}
	/**
	 * Description - Depth First Search or DFS for a Graph. DFS can be implemented using stack or recursive calls.
	 * @param graph
	 * @param startVertex
	 */
	public static void DFS(Graph graph, int startVertex) {
		boolean[] visited = new boolean[graph.getV()];
		
		//DFSUtil(graph, startVertex, visited); 
		// Above statement will traverse all the node reachable from startVertex. But in disconnected graph some vertices might not be reachable so to traverse them as well below code is used. 
		
		for (int i = 0; i < visited.length; i++) {
			if(visited[i]==false)
				DFSUtil(graph, i, visited);
		}
		
	}
	private static void DFSUtil(Graph graph, int startVertex, boolean[] visited) {
		if(visited[startVertex] == false) {
			visited[startVertex] = true;
			System.out.print(startVertex+" ");
		}
		Iterator<Integer> adjListItr = graph.getAdjListArr()[startVertex].iterator();
		
		while (adjListItr.hasNext()) {
			Integer vertex = adjListItr.next();
			
			if (!visited[vertex]) {
				DFSUtil(graph, vertex, visited);
			}
			
		}
	}

	/**
	 * Description - Longest Path in a Directed Acyclic Graph. Given a Weighted Directed Acyclic Graph (DAG) and a source vertex s in it, find the longest distances from s to all other vertices in the given graph.
	 * @param wGraph
	 * @param startVertex
	 */
	public static void longestPathInDAG(WeightedGraph wGraph, int startVertex) {
		Stack<Integer> stack = new Stack<>();
		boolean[] visited = new boolean[wGraph.getV()];
		int[] dist= new int[wGraph.getV()];
		
		for (int i = 0; i < visited.length; i++) {
			if(!visited[i])
				longestPathInDAGUtil(stack, wGraph, visited, i);
		}
		
		for (int i = 0; i < dist.length; i++) {
			dist[i]=Integer.MIN_VALUE;
		}
		dist[startVertex]=0;
		
		while(!stack.isEmpty()) {
			int u = stack.peek();
			stack.pop();
			
			List<AdjListNode> adjList = wGraph.getAdjListArr()[u];
			if(adjList!=null) {
				Iterator<AdjListNode> adjListItr = adjList.iterator();
				
				while (adjListItr.hasNext()) {
					AdjListNode vNode = (AdjListNode) adjListItr.next();
					
					if(dist[vNode.getVertex()] < dist[u] + vNode.getWeight())
						dist[vNode.getVertex()] = dist[u] + vNode.getWeight();
				}
			}
		}
		
		for (int i = 0; i < dist.length; i++) {
			if (dist[i]==Integer.MIN_VALUE) {
				System.out.print("INFINITE ");
			}else {
				System.out.println(dist[i]+" ");
			}
		}
	}
	private static void longestPathInDAGUtil(Stack<Integer> stack, WeightedGraph wGraph, boolean[] visited, int v) {
		visited[v]=true;
		List<AdjListNode> adjList = wGraph.getAdjListArr()[v];
		
		if (adjList != null) {
			Iterator<AdjListNode> adjListItr = wGraph.getAdjListArr()[v].iterator();

			while (adjListItr.hasNext()) {
				AdjListNode adjListNode = (AdjListNode) adjListItr.next();
				if (!visited[adjListNode.getVertex()]) {
					longestPathInDAGUtil(stack, wGraph, visited, adjListNode.getVertex());
				}
			}
		}
		
		stack.push(v);
	}

	public static void main(String[] args) {
		/*Graph grph = new Graph(5);
		grph.addUnDirectedEdge(0, 1);
		grph.addUnDirectedEdge(0, 4);
		grph.addUnDirectedEdge(1, 2);
		grph.addUnDirectedEdge(1, 3);
		grph.addUnDirectedEdge(1, 4);
		grph.addUnDirectedEdge(2, 3);
		grph.addUnDirectedEdge(3, 4);
		
		grph.printEdge();*/
		
		/*Graph dirGraph = new Graph(4);
		dirGraph.addDirectedEdge(0, 1);
		dirGraph.addDirectedEdge(0, 2);
		dirGraph.addDirectedEdge(1, 2);
		dirGraph.addDirectedEdge(2, 0);
		dirGraph.addDirectedEdge(2, 3);
		dirGraph.addDirectedEdge(3, 3);
		
		//BFS(dirGraph, 2);
		DFS(dirGraph, 2);*/
		WeightedGraph wG = new WeightedGraph(6);
		
		wG.addDirectedEdge(0, 1, 5);
		wG.addDirectedEdge(0, 2, 3);
		wG.addDirectedEdge(1, 3, 6);
		wG.addDirectedEdge(1, 2, 2);
		wG.addDirectedEdge(2, 4, 4);
		wG.addDirectedEdge(2, 5, 2);
		wG.addDirectedEdge(2, 3, 7);
		wG.addDirectedEdge(3, 5, 1);
		wG.addDirectedEdge(3, 4, -1);
		wG.addDirectedEdge(4, 5, -2);
		
		longestPathInDAG(wG, 1);
	}

}