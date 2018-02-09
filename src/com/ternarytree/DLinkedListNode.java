package com.ternarytree;

public class DLinkedListNode {

	private int data;
	private DLinkedListNode prev;
	private DLinkedListNode next;
	
	public DLinkedListNode(int data) {
		super();
		this.data = data;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public DLinkedListNode getPrev() {
		return prev;
	}

	public void setPrev(DLinkedListNode prev) {
		this.prev = prev;
	}

	public DLinkedListNode getNext() {
		return next;
	}

	public void setNext(DLinkedListNode next) {
		this.next = next;
	}
	
}
