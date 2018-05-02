package com.linkedlist;

public class SinglyLinkedList {

	private Node start;
	private Node end;
	private int size;
	
	public SinglyLinkedList(){
		this.start=null;
		this.end=null;
		this.size=0;
	}
	//Rearrange a singly linked list -- 
	public void rearrangeList(){
		if (start.getLink().getLink() != null)
			this.rearrangeListUtil(this.start, start.getLink(),(size%2==0?size:(size + 1)));
	}
	private void rearrangeListUtil(Node left, Node right, int count){
		if(right.getLink().getLink()!=null){
			int nextCount=count-2;
			rearrangeListUtil(left, right.getLink(), nextCount);
		}
		
		if(count<size){
			System.out.println("Call -- " + left.getData());
			//left=left.getLink();
			Node tempLeft=left;
			for(int i=2; i<count; i++){
				tempLeft=tempLeft.getLink();
			}
			Node tempRight=right.getLink();

			tempRight.setLink(tempLeft.getLink());
			tempLeft.setLink(tempRight);
			right.setLink(null);
		}
	}
	//Add two numbers represented by linked lists | Set 2, first node is most significant node here.
	public SinglyLinkedList addNoRepByList(SinglyLinkedList secondList){
		return addList(this, secondList);
	}
	
	private SinglyLinkedList addList(SinglyLinkedList firstList, SinglyLinkedList secondList){
		boolean control=true;
		int diff=firstList.size-secondList.size;
		Node ptr=null;
		
		if(firstList.size<secondList.size){
			control=false;
			diff=secondList.size-firstList.size;
			ptr=secondList.start;
			for (int i = 0; i < diff; i++) {
				ptr=ptr.getLink();
			}
		}else{
			ptr=firstList.start;
			for (int i = 0; i < diff; i++) {
				ptr=ptr.getLink();
			}
		}
		
		SinglyLinkedList result=new SinglyLinkedList();
		
		if(control){
			addToList(ptr, secondList.start, result);
			adjustCarry(diff, firstList, result, ((ptr.getData()+secondList.start.getData())/10));
		}else{
			addToList(ptr, firstList.start, result);
			adjustCarry(diff, secondList, result, ((ptr.getData()+firstList.start.getData())/10));
		}
		
		return result;
	}
	
	private void adjustCarry(int diff, SinglyLinkedList begin, SinglyLinkedList result, int carry){
		if(null==begin)
			return;
		
		if(carry==0)
			return;
		
		for(int i=diff; i>0 ; i--){
			Node ptr=begin.start;
			for(int j=0; j<i-1 ; j++){
				ptr=ptr.getLink();
			}
			if(carry>0){
			     int data=(ptr.getData()+carry)%10;
			     carry=(ptr.getData()+carry)/10;
			     result.insertAtBeganing(data);
			}else{
				result.insertAtBeganing(ptr.getData());
			}
		}
		
		if(carry>0)
			result.insertAtBeganing(carry);
		
	}
	
	private void addToList(Node first, Node second, SinglyLinkedList result){
		int carry=0;
		int sum=0;
		
		if(null==first || null==second)
			return;
		
		if(null!=first.getLink() && null!=second.getLink()){
			carry=(first.getLink().getData() + second.getLink().getData())/10;
		}
		sum=(first.getData()+second.getData()+carry)%10;
		
		// recursive call is here
		addToList(first.getLink(), second.getLink(), result);
		
		result.insertAtBeganing(sum);
	}
	
	//Segregate even and odd nodes in a Linked List
	public void seggEvenOdd(){
		Node ptr=this.start;
		Node temp=null;
		Node prevTemp=null;
		Node tempSt=null;
		
		for(int i=0; i<this.size; i++){
			temp=ptr;
			ptr=ptr.getLink();
			
			if(temp.getData()%2==0){
				if(i==0){
					tempSt=temp;
				}else if(null==tempSt){
					prevTemp.setLink(ptr);
					tempSt=temp;
					tempSt.setLink(this.start);
					this.start=tempSt;
				}else{
					if (prevTemp == null)
						tempSt = temp;
					else {
						prevTemp.setLink(ptr);
						temp.setLink(tempSt.getLink());
						tempSt.setLink(temp);
						tempSt = temp;
					}
				}
			}else{
				prevTemp=temp;
			}
			
			if(null!=prevTemp && null!=tempSt)
				this.end=prevTemp;
		}
		
		
	}
	//Merge two sorted linked lists.
	public void mergeSortedList(SinglyLinkedList targetList){
		if(this.end.getData()<=targetList.start.getData()){
			this.end.setLink(targetList.start);
			this.size+=targetList.size;
			return;
		}else if(this.start.getData()>targetList.end.getData()){
			targetList.end.setLink(this.start);
			this.start=targetList.start;
			this.size+=targetList.size;
			return;
		}else{
		
			boolean count = false;
			Node srcStart = null;
			Node trgtStart = null;
			SinglyLinkedList srcList=this;

			if (this.start.getData() > targetList.start.getData()) {
				count = true;
				trgtStart = this.start;
				srcStart = targetList.start;
				srcList=targetList;
			} else {
				trgtStart = targetList.start;
				srcStart = this.start;
			}

			for (int i = 1; i <= (count ? targetList.size : this.size); i++) {
				if (null != srcStart && null != trgtStart) {
					if (srcStart.getData() <= trgtStart.getData()) {
						srcStart = srcStart.getLink();
					} else {
						// In this case src shuld be remain same becouse targetNode data inserted before.
						srcList.insertAtPosition(i, trgtStart.getData());
						trgtStart = trgtStart.getLink();
					}
				}else{
					break;
				}

			}
			
			while(null!=trgtStart){
				srcList.insertAtEnd(trgtStart.getData());
				trgtStart=trgtStart.getLink();
			}
			this.start=srcList.start;
			this.end=srcList.end;
			this.size=srcList.size;
		}
	}
	
	public Node intersectionPoint(SinglyLinkedList list){
        if(!this.end.equals(list.end)){
			System.out.println("There is no intersection between these two list");
			return null;
		}else{
			int sizeDiff=((this.size-list.size)>=0 ? this.size-list.size : list.size-this.size);
			Node ptr=null;
			Node ptrSmall=null;
			int local;
			if(this.size>list.size){
				ptr=this.start;
				ptrSmall=list.start;
				local=list.size;
			}else{
				ptr=list.start;
				ptrSmall=this.start;
				local=this.size;
			}

			for(int i=0; i<sizeDiff; i++){
				ptr=ptr.getLink();
			}
			
			for(int i=0; i<local; i++){
				if(ptr.equals(ptrSmall)){
					System.out.println("Intercestion found");
					break;
				}
				ptr=ptr.getLink();
				ptrSmall=ptrSmall.getLink();
			}
			return ptr;
		}
	}
	public SinglyLinkedList reverseList(){
		SinglyLinkedList revList=new SinglyLinkedList();
		
		Node ptr=this.start;
		for(int i=0;i<size;i++){
			Node temp=new Node(ptr.getData());
			
			if(i==0){
				revList.start=temp;
				revList.end=temp;
				revList.end.setLink(null);
			}else{
				temp.setLink(revList.start);
				revList.start=temp;
			}
			revList.size++;
			ptr=ptr.getLink();
		}
		return revList;
	}
	
	public boolean isEmpty(){
		return size==0;
	}
	
	public void insertAtBeganing(int data){
		Node nptr=new Node(data);
		
		if(start==null){
			start=nptr;
			end=nptr;
			size++;
		}else{
			nptr.setLink(start);
			start=nptr;
			size++;
		}	
	}
	
	public void insertAtEnd(int data){
		Node nptr=new Node(data);
		
		if(end==null){
			start=nptr;
			end=nptr;
			size++;
		}else{
			end.setLink(nptr);
			end=nptr;
			size++;
		}
	}
	
	public void insertAtPosition(int pos, int data){
		if (isEmpty() && pos == 1) { // This if block is needed for insertion at 1st position when list is empty.
			this.insertAtBeganing(data);
			return;
		} else {
            // This is the core logic to insert at any position.
			Node nptr = new Node(data);
			Node ptr = start;

			boolean insert = false;
			pos--;
			for (int i = 1; i < size; i++) {

				if (i == pos) {
					Node temp = ptr.getLink();
					ptr.setLink(nptr);
					nptr.setLink(temp);
					size++;
					insert = true;
					break;
				} else
					ptr = ptr.getLink();
			}
			if (!insert)
				System.out.println("Data not inserted at " + (pos+1) + " position - Please try insert at appropriate position.");
		}
	}
	
	public void insertNode(Node node){
		if(this.isEmpty()){
			this.start=node;
			this.end=node;
			this.size++;
			return;
		}else{
			this.end.setLink(node);
			this.end=node;
			this.size++;
		}
	}
	
	public void deleteAtPosition(int pos){
		boolean delete=false;
		if(pos==1){
			start=start.getLink();
			size--;
			delete=true;
		}else{
			Node ptr=start;
			pos--;
			for(int i=1;i<size;i++){
				if(pos==i){
					Node temp=ptr.getLink();
					temp=temp.getLink();
					ptr.setLink(temp);
					size--;
					delete=true;
					break;
				}else
					ptr=ptr.getLink();
			}
		}
		
		if(!delete){
			System.out.println("Node not deleted at " + (pos+1) + " position - Please try delete at appropriate position.");
		}
	}
	
	public int getSize() {
		return size;
	}	
	
	public void display(){
		Node ptr=this.start;
		for(int i=0;i<size;i++){
			System.out.println(i+1 +" Node Data " +ptr.getData());
			ptr=ptr.getLink();
		}
	}
}
