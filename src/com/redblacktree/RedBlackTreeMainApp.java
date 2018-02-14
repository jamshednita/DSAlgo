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
						//rotateLeft(parent_nd, newNode);
					}
					
					//rotateRight(grandParent, parent_nd);
				}
			}
		}
	}
	public static void main(String[] args) {
		
	}

}
