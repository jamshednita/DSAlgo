package com.linkedlist;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SinglyLinkedList list=new SinglyLinkedList();
		/*list.insertAtPosition(1,11);
		list.insertAtEnd(77);
		list.insertAtPosition(2,22);
		
		//System.out.println("List Size = " +list.getSize());
		//list.display();
		
		list.insertAtEnd(88);
		list.insertAtPosition(3,33);
		list.insertAtPosition(4,44);
		//System.out.println("List Size = " +list.getSize());
		//list.display();
		
		//list.deleteAtPosition(6);
		//list.insertAtPosition(10, 100);
		System.out.println("List Size = " +list.getSize());
		list.display();
		
		SinglyLinkedList rev=list.reverseList();
		rev.display();*/
		//Node n1 = new Node(11);
		//Node n2 = new Node(22);
		//Node n3 = new Node(33);
		//sNode n4 = new Node(11);
		//list.insertAtBeganing(12);
		//list.insertAtEnd(13);
		/*list.insertAtBeganing(5);
		list.insertAtEnd(40);
		list.insertAtPosition(2, 30);
		list.insertAtPosition(2, 19);
		list.insertAtPosition(2, 8);
		
		SinglyLinkedList listTwo=new SinglyLinkedList();
		
		listTwo.insertAtBeganing(10);
		listTwo.insertAtEnd(95);
		listTwo.insertAtPosition(2, 21);
		//listTwo.insertNode(n2);
		//listTwo.insertNode(n3);
		list.display();
		System.out.println("----------");
		listTwo.display();
		System.out.println("----------");
		//System.out.println("Intersection node data : " + list.intersectionPoint(listTwo).getData());
		
		list.mergeSortedList(listTwo);
		list.display();
		System.out.println("----------");
		listTwo.display();
		*/
		
		/*
		// Seggregate Function check.
		SinglyLinkedList lstSgg=new SinglyLinkedList();
		lstSgg.insertAtBeganing(9);
		lstSgg.insertAtEnd(6);
		lstSgg.insertAtEnd(7);
		lstSgg.insertAtEnd(9);
		//lstSgg.insertAtEnd(8);
		//lstSgg.insertAtEnd(9);
		lstSgg.display();
		System.out.println("=================");
		//lstSgg.seggEvenOdd();
		//lstSgg.display();
		
		SinglyLinkedList lst=new SinglyLinkedList();
		lst.insertAtBeganing(6);
		lst.insertAtEnd(7);
		lst.insertAtEnd(7);
		//lstSgg.insertAtEnd(9);
		lst.display();
		System.out.println("=================");
		SinglyLinkedList reList=lstSgg.addNoRepByList(lst);
		reList.display();
		
		TwoDimentionalList twoDList=new TwoDimentionalList();
		twoDList.removePointsFromLine();
		*/
		/*// Flattening List Example --
		FLinkedList fList = new FLinkedList();
		
		FNode node1 =new FNode(5, null,null);
		FNode node2 =new FNode(7, null,null);
		FNode node3 =new FNode(18, null,null);
		FNode node4 =new FNode(30, null,null);
		node1.setDown(node2);
		node2.setDown(node3);
		node3.setDown(node4);
		fList.display(node1);
		
		FNode node5 =new FNode(10, null,null);
		FNode node6 =new FNode(20, null,null);
		node5.setDown(node6);
		
		FNode node7 =new FNode(19, null,null);
		FNode node8 =new FNode(22, null,null);
		FNode node9 =new FNode(50, null,null);
		node7.setDown(node8);
		node8.setDown(node9);
		
		FNode node10 =new FNode(28, null,null);
		FNode node11 =new FNode(35, null,null);
		FNode node12 =new FNode(40, null,null);
		FNode node13 =new FNode(45, null,null);
	    node10.setDown(node11);
	    node11.setDown(node12);
	    node12.setDown(node13);
	    
	    node1.setNext(node5);
	    node5.setNext(node7);
	    node7.setNext(node10);
	    
	    FNode res=fList.flattenList(node1);
	    
		fList.display(node1);*/
		
		SinglyLinkedList listArrange=new SinglyLinkedList();
		listArrange.insertAtEnd(1);
		listArrange.insertAtEnd(2);
		listArrange.insertAtEnd(3);
		listArrange.insertAtEnd(4);
		//listArrange.insertAtEnd(5);
		listArrange.display();
		listArrange.rearrangeList();
		
		listArrange.display();
	}
	

}
