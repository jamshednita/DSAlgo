package com.graph;

import java.util.Iterator;
import java.util.LinkedList;

public class Graph {
	
	private int V;
	private LinkedList<Integer>[] adjListArr=null;
	
	@SuppressWarnings("unchecked")
	public Graph(int v) {
		super();
		V = v;
		adjListArr = new LinkedList[V];
		for (int i = 0; i < V; i++) {
			adjListArr[i] = new LinkedList<>();
		}
	}
	// O(1)
	public void addUnDirectedEdge(int i, int j) {
		adjListArr[i].add(j);
		adjListArr[j].add(i);
		// For undirected graph, i-to-j == i->j & j->i
	}
	// O(1)
	public void addDirectedEdge(int i, int j) {
		adjListArr[i].add(j);
	}
	//O(V)
	public boolean removeUnDirectedEdge(int i, int j) {
		Iterator<Integer> adjListOneItr = adjListArr[i].iterator();
		Iterator<Integer> adjListTwoItr = adjListArr[j].iterator();
		
		while (adjListOneItr.hasNext()) {
			Integer element = adjListOneItr.next();
			if(element == j) {
				adjListOneItr.remove();
				break;
			}
		}
		
		while (adjListTwoItr.hasNext()) {
			Integer element = adjListOneItr.next();
			if(element == i) {
				adjListOneItr.remove();
				return true;
			}
		}
		
		System.err.println("No such edge exists!");
		return false;
	}

	// O(V)
	public boolean removeDirectedEdge(int i, int j) {
		Iterator<Integer> adjListOneItr = adjListArr[i].iterator();

		while (adjListOneItr.hasNext()) {
			Integer element = adjListOneItr.next();
			if (element == j) {
				adjListOneItr.remove();
				return true;
			}
		}

		System.err.println("No such edge exists!");
		return false;
	}
	// O(E+V)
	public void printEdge() {
		for (int i = 0; i < adjListArr.length; i++) {
			System.out.println("Edges from vertex "+i);
			for (Integer integer : adjListArr[i]) {
				System.out.print(integer+ " ");
			}
			System.out.println();// new_line
		}
	}
	public int getV() {
		return V;
	}
	public void setV(int v) {
		V = v;
	}
	public LinkedList<Integer>[] getAdjListArr() {
		return adjListArr;
	}
	public void setAdjListArr(LinkedList<Integer>[] adjListArr) {
		this.adjListArr = adjListArr;
	}
	
}
