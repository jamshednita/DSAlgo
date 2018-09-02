package com.graph;

import java.util.Arrays;

public class BellmanFordCycleDetection {
	
	public static boolean isNegativeCycleBellmanFord(EdgeGrapgh eg, int src) {
		int V=eg.getV();
		int E=eg.getE();
		int[] dist = new int[V];
		
		// Step 1: Initialize distances from src
	    // to all other vertices as INFINITE
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;
		
		// Step 2: Relax all edges |V| - 1 times.
	    // A simple shortest path from src to any
	    // other vertex can have at-most |V| - 1 edges
		
		/*
		 * As discussed in Bellman Ford algorithm, for a given source, it first
		 * calculates the shortest distances which have at-most one edge in the path.
		 * Then, it calculates shortest paths with at-nost 2 edges, and so on. After the
		 * i-th iteration of outer loop, the shortest paths with at most i edges are
		 * calculated. There can be maximum |V| – 1 edges in any simple path, that is
		 * why the outer loop runs |v| – 1 times. If there is a negative weight cycle,
		 * then one more iteration would give a shorted path.
		 */
		for (int i = 0; i < V-1; i++) {
			for (int j = 0; j < E; j++) {
				int uj = eg.getEdges()[j].getSrc();
				int vj = eg.getEdges()[j].getDest();
				int uvjWeight = eg.getEdges()[j].getWeight();
				
				if(dist[uj] != Integer.MAX_VALUE && (dist[uj] + uvjWeight < dist[vj])) {
					dist[vj] = dist[uj] + uvjWeight;
				}
			}
		}
		
		// Step 3: check for negative-weight cycles.
	    // The above step guarantees shortest distances
	    // if graph doesn't contain negative weight cycle.
	    // If we get a shorter path, then there is a cycle.
		for (int j = 0; j < E; j++) {
			int uj = eg.getEdges()[j].getSrc();
			int vj = eg.getEdges()[j].getDest();
			int uvjWeight = eg.getEdges()[j].getWeight();
			
			if(dist[uj] != Integer.MAX_VALUE && (dist[uj] + uvjWeight < dist[vj])) {
				return true;
			}
		}
		
		return false;
	}
	/**
	 * Description - Detect a negative cycle in a Graph | (Bellman Ford). For disconnected graph.
	 * @param eg
	 * @return
	 */
	public static boolean isNegCycleDisconnected(EdgeGrapgh eg) {
		int[] dist = new int[eg.getV()];
		Arrays.fill(dist, Integer.MAX_VALUE);
		boolean[] visited = new boolean[eg.getV()];
		
		for (int i = 0; i < eg.getV(); i++) {
			if(!visited[i]) {
				if(isNegCycleBellmanFord(eg, dist, i, visited))
					return true;
			}
		}
		
		return false;
	}
	
	private static boolean isNegCycleBellmanFord(EdgeGrapgh eg, int[] dist, int src, boolean[] visited) {
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;
		visited[src]=true;
		int V=eg.getV();
		int E=eg.getE();
		
		for (int i = 0; i < V-1; i++) {
			for (int j = 0; j < E; j++) {
				int uj = eg.getEdges()[j].getSrc();
				int vj = eg.getEdges()[j].getDest();
				int uvjWeight = eg.getEdges()[j].getWeight();
				
				if(dist[uj] != Integer.MAX_VALUE && (dist[uj] + uvjWeight < dist[vj])) {
					dist[vj] = dist[uj] + uvjWeight;
					
					visited[vj]=true;
				}
			}
		}
		
		// Step 3: check for negative-weight cycles.
	    // The above step guarantees shortest distances
	    // if graph doesn't contain negative weight cycle.
	    // If we get a shorter path, then there is a cycle.
		for (int j = 0; j < E; j++) {
			int uj = eg.getEdges()[j].getSrc();
			int vj = eg.getEdges()[j].getDest();
			int uvjWeight = eg.getEdges()[j].getWeight();
			
			if(dist[uj] != Integer.MAX_VALUE && (dist[uj] + uvjWeight < dist[vj])) {
				return true;
			}
		}
		
		return false;
	}

	public static void main(String[] args) {
		EdgeGrapgh eg = new EdgeGrapgh(5, 8);
		eg.getEdges()[0]=new Edge(0, 1, -1);
		eg.getEdges()[1]=new Edge(0, 2, 4);
		eg.getEdges()[2]=new Edge(1, 2, 3);
		eg.getEdges()[3]=new Edge(1, 3, 2);
		eg.getEdges()[4]=new Edge(1, 4, 2);
		eg.getEdges()[5]=new Edge(3, 2, 5);
		eg.getEdges()[6]=new Edge(3, 1, 1);
		eg.getEdges()[7]=new Edge(4, 3, -3);
		
		System.out.println(isNegativeCycleBellmanFord(eg, 0));
		System.out.println(isNegCycleDisconnected(eg));
		
		EdgeGrapgh ceg = new EdgeGrapgh(8, 13);
		ceg.getEdges()[0] = new Edge(0, 1, 4);
		ceg.getEdges()[1] = new Edge(0, 2, 4);
		
		ceg.getEdges()[2] = new Edge(2, 4, 4);
		ceg.getEdges()[3] = new Edge(2, 5, -2);
		
		ceg.getEdges()[4] = new Edge(3, 2, 2);
		ceg.getEdges()[5] = new Edge(3, 0, 3);
		
		ceg.getEdges()[6] = new Edge(4, 3, 1);
		ceg.getEdges()[7] = new Edge(4, 6, -2);
		
		ceg.getEdges()[8] = new Edge(5, 1, 3);
		ceg.getEdges()[9] = new Edge(5, 4, -3);
		
		ceg.getEdges()[10] = new Edge(6, 5, 2);
		ceg.getEdges()[11] = new Edge(6, 7, 2);
		
		ceg.getEdges()[12] = new Edge(7, 4, -2);
		
		System.out.println(isNegativeCycleBellmanFord(ceg, 0));
		System.out.println(isNegCycleDisconnected(ceg));
		
	}

}
