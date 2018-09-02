package com.graph;

public class EdgeGrapgh {

	private int V;
	private int E;
	
	private Edge[] edges = null;

	public EdgeGrapgh(int v, int e) {
		super();
		V = v;
		E = e;
		this.edges = new Edge[e];
	}
	public int getE() {
		return E;
	}
	public void setE(int e) {
		E = e;
	}
	public int getV() {
		return V;
	}
	public void setV(int v) {
		V = v;
	}
	public Edge[] getEdges() {
		return edges;
	}
	public void setEdges(Edge[] edges) {
		this.edges = edges;
	}

}
