package com.binomialheap;

public class BinomialTreeNode {
	
	private int data;
	private int degree;
	
	private BinomialTreeNode leftChild;
	private BinomialTreeNode sibling;
	private BinomialTreeNode parent;
	
	public BinomialTreeNode(int data) {
		super();
		this.data = data;
		this.degree = 1;
		this.leftChild = null;
		this.sibling = null;
		this.parent = null;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	public BinomialTreeNode getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(BinomialTreeNode leftChild) {
		this.leftChild = leftChild;
	}
	public BinomialTreeNode getSibling() {
		return sibling;
	}
	public void setSibling(BinomialTreeNode sibling) {
		this.sibling = sibling;
	}
	public BinomialTreeNode getParent() {
		return parent;
	}
	public void setParent(BinomialTreeNode parent) {
		this.parent = parent;
	}

}
