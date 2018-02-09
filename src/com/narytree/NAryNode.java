package com.narytree;

public class NAryNode {
	
	private int data;
	private NAryNode[] childs = null;
	
	public NAryNode(int data, int noOfChild) {
		super();
		this.data = data;
		
		childs = new NAryNode[noOfChild];
	}

	public void addChild(int atIndex, NAryNode node){
		childs[atIndex] = node;
	}
	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public NAryNode[] getChilds() {
		return childs;
	}

	public void setChilds(NAryNode[] childs) {
		this.childs = childs;
	}
}
