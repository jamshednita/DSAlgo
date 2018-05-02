package com.linkedlist;

public class FLinkedList {
	
	public FNode flattenList(FNode root){
		if(null==root || null==root.getNext()){
			return root;
		}
		FNode next= root.getNext().getNext();
		FNode res = Merge(root, root.getNext());
		res.setNext(next);
		return flattenList(res);
	}

	public FNode Merge(FNode one, FNode two){
		FNode result=null;
		if(null==one){
			return two;
		}
		else if(null==two){
			return one;
		}
		if(one.getData()<two.getData()){
			result=one;
			result.setDown(Merge(one.getDown(),two));
		}else if(one.getData()>two.getData()){
			result=two;
			result.setDown(Merge(two.getDown(),one));
		}
		
		return result;
	}
	
	public void display(FNode nodeSt){
		FNode temp=nodeSt;
		while(temp!=null){
			System.out.print(temp.getData());
			if (temp.getDown() != null) {
				System.out.print("-->");
			}
			
			temp=temp.getDown();
		}
		
		System.out.println();
	}
}
