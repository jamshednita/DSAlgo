package com.graph;

import java.util.LinkedList;
import java.util.List;

public class WeightedGraph {
	private int V;
	private List<AdjListNode>[] adjListArr=null;
	
	@SuppressWarnings("unchecked")
	public WeightedGraph(int v) {
		super();
		V = v;
		
		adjListArr = new LinkedList[V];
	}
	
	public void addDirectedEdge(int i, int j, int w) {
		List<AdjListNode> adjList=adjListArr[i];
		
		if(adjList==null)
			adjList = new LinkedList<>();
		
		adjList.add(new AdjListNode(j, w));
		adjListArr[i]=adjList;
	}

	public int getV() {
		return V;
	}

	public void setV(int v) {
		V = v;
	}

	public List<AdjListNode>[] getAdjListArr() {
		return adjListArr;
	}

	public void setAdjListArr(List<AdjListNode>[] adjListArr) {
		this.adjListArr = adjListArr;
	}
	
}
