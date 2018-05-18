package com.doublylinkedlist;

public class DoublyLinkedList {
	
	DLLNode head;
	DLLNode end;
	int size;
	
	public DoublyLinkedList() {
		super();
		head=null;
		end=null;
		size=0;
	}
	
	public void insertAtPosition(int data, int position){
		DLLNode ptr=this.head, temp=null;
		DLLNode newNode=new DLLNode(data, null, null);
		
		if(position>size || position<1){
			System.out.println("Incorrect position OR there is less node in the list");
			return;
		}else{
			for(int pos=1; pos<position; pos++){
				temp=ptr;
				ptr=ptr.getRight();
			}
		}
		
		if(null!=head && null!=end){
			head=newNode;
			end=newNode;
			size++;
		}else{
			newNode.setLeft(temp);
			temp.setRight(newNode);
			
			ptr.setLeft(newNode);
			newNode.setRight(ptr);
			
			size++;
		}
	}

}
