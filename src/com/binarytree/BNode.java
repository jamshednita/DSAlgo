package com.binarytree;
class BNode 
{
	private int data;
	private BNode left, right;
	private boolean isThread;

	public BNode(int d) 
	{
		data = d;
		left = right = null;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public BNode getLeft() {
		return left;
	}

	public void setLeft(BNode left) {
		this.left = left;
	}

	public BNode getRight() {
		return right;
	}

	public void setRight(BNode right) {
		this.right = right;
	}

	public boolean isThread() {
		return isThread;
	}

	public void setThread(boolean isThread) {
		this.isThread = isThread;
	}
}