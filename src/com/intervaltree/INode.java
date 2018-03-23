package com.intervaltree;

public class INode {
	Interval i;
	int max;
	INode left, right;
	
	public INode(Interval i) {
		super();
		this.i = i;
	}
	public Interval getI() {
		return i;
	}
	public void setI(Interval i) {
		this.i = i;
	}
	public INode getLeft() {
		return left;
	}
	public void setLeft(INode left) {
		this.left = left;
	}
	public INode getRight() {
		return right;
	}
	public void setRight(INode right) {
		this.right = right;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	
}
