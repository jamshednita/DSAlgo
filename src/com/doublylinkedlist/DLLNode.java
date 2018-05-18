package com.doublylinkedlist;

public class DLLNode {
	
	private int data;
	private DLLNode left;
	private DLLNode right;
	
	public DLLNode(int data) {
		super();
		this.data = data;
	}
	
	public DLLNode(int data, DLLNode left, DLLNode right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public DLLNode getLeft() {
		return left;
	}
	public void setLeft(DLLNode left) {
		this.left = left;
	}
	public DLLNode getRight() {
		return right;
	}
	public void setRight(DLLNode right) {
		this.right = right;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + data;
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DLLNode other = (DLLNode) obj;
		if (data != other.data)
			return false;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		return true;
	}

}
