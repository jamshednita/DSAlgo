package com.redblacktree;


public class RedBlackTreeMainApp {
	public static void insert(RedBlackNode root,int data){
		RedBlackNode newNode = new RedBlackNode(data);
		root = bstInsert(root, newNode);
	}
	private static RedBlackNode bstInsert(RedBlackNode root, RedBlackNode newNode){
		if(root==null){
			return newNode;
		}
		
		if(root.getData() > newNode.getData()){
			root.setLeft(bstInsert(root.getLeft(), newNode));
			root.getLeft().setParent(root);
		}else{
			root.setRight(bstInsert(root.getRight(), newNode));
			root.getRight().setParent(root);
		}
		
		return root;
		
	}
	
	private static void rotateLeft(RedBlackNode root, RedBlackNode pt){
		RedBlackNode pt_right=pt.getRight();
		pt.setRight(pt_right.getLeft());
		
		if(pt.getRight()!=null)
			pt.getRight().setParent(pt);
		
		pt_right.setParent(pt.getParent());
		
		if(pt.getParent()==null)
			root=pt_right;
		else if(pt==pt.getParent().getLeft())
			pt.getParent().setLeft(pt_right);
		else
			pt.getParent().setRight(pt_right);
		
		pt_right.setLeft(pt);
		pt.setParent(pt_right);
		
	}
	
	private static void rotateRight(RedBlackNode root, RedBlackNode pt){
		RedBlackNode pt_left=pt.getLeft();
		pt.setLeft(pt_left.getRight());
		
		if(pt.getLeft()!=null)
			pt.getLeft().setParent(pt);
		
		pt_left.setParent(pt.getParent());
		
		if(pt.getParent()==null)
			root=pt_left;
		else if(pt==pt.getParent().getRight())
			pt.getParent().setRight(pt_left);
		else
			pt.getParent().setLeft(pt_left);
		
		pt_left.setRight(pt);
		pt.setParent(pt_left);
		
	}
	
	private static void performBalancing(RedBlackNode root, RedBlackNode newNode){
		if(newNode==root){
			newNode.setColor(true); // TRUE=BLACK
			return;
		}
		RedBlackNode parent_nd=newNode.getParent();
		if(parent_nd.isColor()){
			RedBlackNode grandParent = newNode.getParent().getParent();
			
			if(parent_nd==grandParent.getLeft()){
				RedBlackNode uncle=grandParent.getRight();
				// Case 1 - if uncle is also RED then coloring is required
				if(uncle!=null && !uncle.isColor()){
					parent_nd.setColor(true);
					uncle.setColor(true);
					grandParent.setColor(false);
					performBalancing(root, grandParent);
				}else{
					// if uncle is also BLACK then Rotation Required.
					
					if(newNode==parent_nd.getRight()){
						//rotateLeft(root, parent_nd);
					}
					
					//rotateRight(grandParent, parent_nd);
				}
			}
		}
	}
	public static void main(String[] args) {
		
	}

}
