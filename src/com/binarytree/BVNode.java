package com.binarytree;
/**
 * 
 * @author JANSARI1
 * 
 * Desc - This is node is designed for bottom view of binary tree.
 *
 */
public class BVNode {

	int data, level;
	BVNode left,right;
	
	public BVNode(int data) {
		super();
		this.data = data;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public BVNode getLeft() {
		return left;
	}
	public void setLeft(BVNode left) {
		this.left = left;
	}
	public BVNode getRight() {
		return right;
	}
	public void setRight(BVNode right) {
		this.right = right;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
}
