package com.binarytree;
import java.util.Stack;


public class ThrededTreeMain {
	/**
	 *  Time Complexity = O(n)
	 * @param root
	 */
	public static void buildThreadedTree(BNode root){
		Stack<BNode> stk = new Stack<BNode>();
		stk.push(root);
		BNode curr = root.getLeft();
		
		while(!stk.isEmpty()){
			// Visit the leftmost node --
			while(curr!=null){
				stk.push(curr);
				curr = curr.getLeft();
			}
			// Pop out top of the stack and see if it has right children. if Yes do above on right as curr.
			BNode poppedElement = stk.pop();
			
			if(poppedElement.getRight() != null){
				curr = poppedElement.getRight();
				stk.push(curr);
				curr=curr.getLeft();
			}else{
				if (!stk.isEmpty()) {
					BNode temp = stk.peek();
					poppedElement.setRight(temp);
					poppedElement.setThread(true);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		BNode root = new BNode(6);
		
		BNode left = new BNode(3);
		BNode right = new BNode(8);
		
		BNode left8 = new BNode(7);
		BNode right8 = new BNode(11);
		
		root.setLeft(left);
		root.setRight(right);
		
		left.setLeft(new BNode(1));
		left.setRight(new BNode(4));
		
		right.setLeft(left8);
		right.setRight(right8);
		
		right8.setLeft(new BNode(9));
		right8.setRight(new BNode(13));
		
		buildThreadedTree(root);
		System.out.println(root.getData());
	}

}
