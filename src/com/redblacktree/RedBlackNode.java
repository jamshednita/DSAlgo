package com.redblacktree;

public class RedBlackNode {
	
	private int data;
	private boolean color; // TRUE = BLACK, FALSE = RED
	private RedBlackNode left, right, parent;
	
	public RedBlackNode(int data) {
		super();
		this.data = data;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public boolean isColor() {
		return color;
	}

	public void setColor(boolean color) {
		this.color = color;
	}

	public RedBlackNode getLeft() {
		return left;
	}

	public void setLeft(RedBlackNode left) {
		this.left = left;
	}

	public RedBlackNode getRight() {
		return right;
	}

	public void setRight(RedBlackNode right) {
		this.right = right;
	}

	public RedBlackNode getParent() {
		return parent;
	}

	public void setParent(RedBlackNode parent) {
		this.parent = parent;
	}
}
