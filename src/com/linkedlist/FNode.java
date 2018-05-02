package com.linkedlist;

public class FNode {
	
	private int data;
	private FNode down;
	private FNode next;
	
	public FNode(){
		
	}
	public FNode(int data, FNode down, FNode next) {
		super();
		this.data = data;
		this.down = down;
		this.next = next;
	}
	
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public FNode getDown() {
		return down;
	}
	public void setDown(FNode down) {
		this.down = down;
	}
	public FNode getNext() {
		return next;
	}
	public void setNext(FNode next) {
		this.next = next;
	}
	
}
