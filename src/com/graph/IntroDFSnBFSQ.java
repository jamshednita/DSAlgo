package com.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class IntroDFSnBFSQ {
	/**
	 * Description - Breadth First Search or BFS for a Graph. BFS can be performed using queue. Time Complexity = O(E+V)
	 * @param graph - Graph to be BFSed
	 * @param startVertex - starting vertex from where rest vertices are reachable.
	 */
	public static void BFS(Graph graph, int startVertex) {
		boolean[] visited = new boolean[graph.getV()]; // To track visited nodes.
		LinkedList<Integer>[] adjListArr = graph.getAdjListArr();
		
		LinkedList<Integer> queue = new LinkedList<>(); // Queue to store unvisited node.
		queue.add(startVertex);
		
		while(!queue.isEmpty()) {
			Integer s = queue.poll();
			visited[s]=true;
			System.out.print(s+" ");
			
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
		
		DFSUtil(graph, startVertex, visited);
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
		
		Graph dirGraph = new Graph(4);
		dirGraph.addDirectedEdge(0, 1);
		dirGraph.addDirectedEdge(0, 2);
		dirGraph.addDirectedEdge(1, 2);
		dirGraph.addDirectedEdge(2, 0);
		dirGraph.addDirectedEdge(2, 3);
		dirGraph.addDirectedEdge(3, 3);
		
		//BFS(dirGraph, 2);
		DFS(dirGraph, 2);
	}

}