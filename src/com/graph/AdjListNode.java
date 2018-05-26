package com.graph;

public class AdjListNode {
	private int vertex;
	private int weight;

	public AdjListNode(int vertex, int weight) {
		super();
		this.vertex = vertex;
		this.weight = weight;
	}
	public int getVertex() {
		return vertex;
	}
	public void setVertex(int vertex) {
		this.vertex = vertex;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}

}
