package com.threadedbst;

public class ThreadedNode {
	private int data;
	private ThreadedNode left, right;
	boolean lThread, rThread;
	
	public ThreadedNode(int data) {
		super();
		this.data = data;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public ThreadedNode getLeft() {
		return left;
	}
	public void setLeft(ThreadedNode left) {
		this.left = left;
	}
	public ThreadedNode getRight() {
		return right;
	}
	public void setRight(ThreadedNode right) {
		this.right = right;
	}
	public boolean islThread() {
		return lThread;
	}
	public void setlThread(boolean lThread) {
		this.lThread = lThread;
	}
	public boolean isrThread() {
		return rThread;
	}
	public void setrThread(boolean rThread) {
		this.rThread = rThread;
	}

}
