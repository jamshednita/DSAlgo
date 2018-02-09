package com.ternarytree;

public class TNode {

	private int data;
	private TNode left;
	private TNode mid;
	private TNode right;
	
	public TNode(int data) {
		super();
		this.data = data;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public TNode getLeft() {
		return left;
	}

	public void setLeft(TNode left) {
		this.left = left;
	}

	public TNode getMid() {
		return mid;
	}

	public void setMid(TNode mid) {
		this.mid = mid;
	}

	public TNode getRight() {
		return right;
	}

	public void setRight(TNode right) {
		this.right = right;
	}	
	
}
