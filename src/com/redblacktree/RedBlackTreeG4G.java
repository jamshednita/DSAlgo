package com.redblacktree;


public class RedBlackTreeG4G {
	public static RedBlackNode insert(RedBlackNode root,int data){
		RedBlackNode newNode = new RedBlackNode(data);
		root = bstInsert(root, newNode);
		System.out.println("Inside insert before balancing "+root.getData()+" "+root.isColor());
		root=performBalancing(root, newNode);
		System.out.println("Inside insert after balancing "+root.getData()+" "+root.isColor());
		return root;
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
	
	private static RedBlackNode rotateLeft(RedBlackNode root, RedBlackNode pt){
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
		
		return root;		
	}
	
	private static RedBlackNode rotateRight(RedBlackNode root, RedBlackNode pt){
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
		
		return root;		
	}
	
	private static RedBlackNode performBalancing(RedBlackNode root, RedBlackNode newNode){
		if(newNode==root){
			root.setColor(true); // TRUE=BLACK
			newNode.setColor(true);
			
		}
		RedBlackNode parent_nd=newNode.getParent();
		if(parent_nd != null && !parent_nd.isColor()){ // Only if parent_nd's color is RED(false).
			RedBlackNode grandParent = newNode.getParent().getParent();
			
			if(parent_nd==grandParent.getLeft()){ // If parent_nd is left of grandParent then uncle will be right of grandParent.
				RedBlackNode uncle=grandParent.getRight();
				// Case 1 - if uncle is also RED then coloring is required
				if(uncle!=null && !uncle.isColor()){
					parent_nd.setColor(true);
					uncle.setColor(true);
					grandParent.setColor(false);
					return performBalancing(root, grandParent);
				}else{
					// if uncle is BLACK then Rotation Required and then color swapping b/w grandParent and Parent_nd.
					if(newNode==parent_nd.getRight()){
						root = rotateLeft(root, parent_nd);
						newNode = parent_nd;
						parent_nd = newNode.getParent();
					}
					
					root = rotateRight(grandParent, parent_nd);
					swapColor(parent_nd,grandParent); // Swapping grandParent and parent_nd color.
					newNode=parent_nd;
					return performBalancing(root, newNode);
				}
			} else { // Uncle will be right of grandParent.
				RedBlackNode uncle = grandParent.getLeft();
				// Case 1 - if uncle is also RED then coloring is required
				if (uncle != null && !uncle.isColor()) {
					parent_nd.setColor(true);
					uncle.setColor(true);
					grandParent.setColor(false);
					return performBalancing(root, grandParent);
				} else {
					// if uncle is BLACK then Rotation Required and then color swapping b/w grandParent and Parent_nd.
					if (newNode == parent_nd.getLeft()) {
						root = rotateRight(root, parent_nd);
						newNode = parent_nd;
						parent_nd = newNode.getParent();
					}

					root = rotateLeft(root, grandParent);
					swapColor(parent_nd, grandParent); // Swapping grandParent and parent_nd color.
					newNode = parent_nd;

					return performBalancing(root, newNode);
				}
			}
		}
		return root;
	}
	private static void swapColor(RedBlackNode parent_nd, RedBlackNode grand_parent) {
		boolean tempColor=parent_nd.isColor();
		parent_nd.setColor(grand_parent.isColor());
		grand_parent.setColor(tempColor);
		
	}
	public static void main(String[] args) {
		
		RedBlackNode root=new RedBlackNode(10);
		root.setColor(true);
		
		root = insert(root, 20);
		System.out.println(root.getData() + " " +root.isColor());
		root = insert(root, 30);
		System.out.println(root.getData() + " " +root.isColor());
		root = insert(root, 40);
		System.out.println(root.getData() + " " +root.isColor());
		
	}

}
