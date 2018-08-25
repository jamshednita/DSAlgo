package com.graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class GraphCloning {
	public static void addUndirEdge(GraphNode u, GraphNode v) {
		u.getNeighbours().add(v);
		v.getNeighbours().add(u);
	}
	/**
	 * Description - Clone an Undirected Graph. BFS Approach.
	 * @param org
	 * @return
	 */
	public static GraphNode cloneGraph(GraphNode org) {
		//A Map to keep track of all the nodes which have already been created
		Map<GraphNode, GraphNode> clonedMap = new HashMap<>();
		
		Queue<GraphNode> bfsqu = new LinkedList<>();
		bfsqu.add(org);
		
		//Clone and Put the clone node into the Map
		GraphNode cn = new GraphNode(org.getData());
		clonedMap.put(org, cn);
		
		while(!bfsqu.isEmpty()) {
			//Get the front node from the queue and then visit all its neighbours
			GraphNode u = bfsqu.poll();
			
			Iterator<GraphNode> vi = u.getNeighbours().iterator();
			while (vi.hasNext()) {
				GraphNode v = (GraphNode) vi.next();
				
				// Check if this node has already been created
				if(!clonedMap.containsKey(v)) {
					// If not then create a new Node and put into the HashMap
					bfsqu.add(v);
					clonedMap.put(v, new GraphNode(v.getData()));
				}
				// add these neighbours to the cloned graph node
				clonedMap.get(u).getNeighbours().add(clonedMap.get(v));
			}
		}
		// Return the cloned source graph node
		return clonedMap.get(org);
	}
	
	public static void main(String[] args) {
		GraphNode gn1 = new GraphNode(1);
		GraphNode gn2 = new GraphNode(2);
		GraphNode gn3 = new GraphNode(3);
		GraphNode gn4 = new GraphNode(4);
		
		addUndirEdge(gn1, gn2);
		addUndirEdge(gn2, gn3);
		addUndirEdge(gn3, gn4);
		addUndirEdge(gn4, gn1);	
		
		System.out.println(gn1);
		GraphNode cnUsingBfs = cloneGraph(gn1);
		System.out.println(cnUsingBfs);
	}
	
}
