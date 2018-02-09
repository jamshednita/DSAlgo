package com.ternarytree;

public class MainApp {
	
	public static void buildDlinkedList(TNode root, MyIndexPtr curr){
		DLinkedListNode temp = null;
		if(root == null){
			return ;
		}
		
		if(curr.getCurrPtr().getData() == 0){
			curr.getCurrPtr().setData(root.getData());
		}else{
			temp = new DLinkedListNode(root.getData());
			temp.setPrev(curr.getCurrPtr());
			curr.getCurrPtr().setNext(temp);
			
			curr.setCurrPtr(temp);
		}
		
		buildDlinkedList(root.getLeft(), curr);
		buildDlinkedList(root.getMid(), curr);
		buildDlinkedList(root.getRight(), curr);
	}
	
	public static void main(String[] args) {
		TNode root = new TNode(30);
		
		TNode left = new TNode(5);
		TNode mid = new TNode(11);
		TNode right = new TNode(63);
		
		root.setLeft(left);
		root.setMid(mid);
		root.setRight(right);
		
		left.setLeft(new TNode(1));
		left.setMid(new TNode(4));
		left.setRight(new TNode(8));
		
		mid.setLeft(new TNode(6));
		mid.setMid(new TNode(7));
		mid.setRight(new TNode(15));
		
		right.setLeft(new TNode(31));
		right.setMid(new TNode(55));
		right.setRight(new TNode(65));
		
		DLinkedListNode curr= new DLinkedListNode(0);
		
		//DLinkedListNode temp = curr;
		MyIndexPtr my = new MyIndexPtr();
		my.setCurrPtr(curr);
		buildDlinkedList(root, my);
		System.out.println(curr);
	}

}
class MyIndexPtr{
	DLinkedListNode currPtr;
	public DLinkedListNode getCurrPtr() {
		return currPtr;
	}

	public void setCurrPtr(DLinkedListNode currPtr) {
		this.currPtr = currPtr;
	}
	
}
