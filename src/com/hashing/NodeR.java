package com.hashing;

public class NodeR {
	
	int data;
	NodeR left, right, random;
	
	public NodeR(int data) {
		super();
		this.data = data;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public NodeR getLeft() {
		return left;
	}

	public void setLeft(NodeR left) {
		this.left = left;
	}

	public NodeR getRight() {
		return right;
	}

	public void setRight(NodeR right) {
		this.right = right;
	}

	public NodeR getRandom() {
		return random;
	}

	public void setRandom(NodeR random) {
		this.random = random;
	}
	
}
