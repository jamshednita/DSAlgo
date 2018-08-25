package com.graph;

import java.util.LinkedList;
import java.util.List;

public class GraphNode {
	private int data;
	private List<GraphNode> neighbours = null;
	public GraphNode(int data, List<GraphNode> neighbours) {
		super();
		this.data = data;
		this.neighbours = neighbours;
	}
	public GraphNode(int data) {
		super();
		this.data = data;
		this.neighbours = new LinkedList<>();
	}
	public GraphNode() {
		super();
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public List<GraphNode> getNeighbours() {
		return neighbours;
	}
	public void setNeighbours(List<GraphNode> neighbours) {
		this.neighbours = neighbours;
	}
}
