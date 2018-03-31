package com.binarytree;

public class AVLTree {
	
	public static Node avlDelete(Node root, int delKey){
		if(root==null)
			return root;
		
		if(root.getData() > delKey){
			root.setLeft(avlDelete(root.getLeft(), delKey));
		}else if(root.getData() < delKey){
			root.setRight(avlDelete(root.getRight(), delKey));
		}else{
			
			if(root.getLeft() == null){
				return root.getRight();
			}else if(root.getRight() == null){
				return root.getLeft();
			}else{
				int inOrderSucc=findSmallest(root.getRight());
				
				root.setRight(avlDelete(root.getRight(), inOrderSucc));
			}
		}
		hightCorrection(root); // Correct hight of the root after normal BST delete.
		
		int lHight=root.getLeft()==null?0:root.getLeft().getHight();
		int rHight=root.getRight()==null?0:root.getRight().getHight();
		int balanceFactor = lHight-rHight;
		// if balance factor is more than 1 then left subtree has more hight than right subtree and its LL or LR case
		if(balanceFactor>1){
			Node rLeft = root.getLeft();
			// if right subtree hight of rLeft is greater than left subtree hight of rLeft then perform left rotation at rLeft before right rotation at root.
			int rLeft_lh = rLeft.getLeft()==null?0:rLeft.getLeft().getHight();
			int rLeft_rh = rLeft.getRight()==null?0:rLeft.getRight().getHight();
			if(rLeft_rh>rLeft_lh){
				root.setLeft(rotateLeft(rLeft));
			}
			// Do right rotation at root.
			root = rotateRight(root);
		}else if(balanceFactor<-1){// if balance factor is less than -1 then left subtree has less hight than right subtree and its RR or RL case
			Node rRight = root.getRight();
			// if right subtree hight of rRight is lesser than left subtree hight of rRight then perform right rotation at rRight before left rotation at root.
			int rRight_lh = rRight.getLeft()==null?0:rRight.getLeft().getHight();
			int rRight_rh = rRight.getRight()==null?0:rRight.getRight().getHight();
			
			if(rRight_lh>rRight_rh){
				root.setRight(rotateRight(rRight));
			}
			// Do left rotation at root.
			root = rotateLeft(root);
		}
		
		return root;
	}
	
	private static int findSmallest(Node right) {
		while(right.getLeft() != null)
			right=right.getLeft();
		return right.getData();
	}

	public static Node avlInsert(Node root, int key){
		if(root == null){
			return new Node(key);
		}
		
		if(key<root.getData())
			root.setLeft(avlInsert(root.getLeft(), key));
		else
			root.setRight(avlInsert(root.getRight(), key));
		
		hightCorrection(root); // Update hight of the root
		
		int lHight=root.getLeft()==null?0:root.getLeft().getHight();
		int rHight=root.getRight()==null?0:root.getRight().getHight();
		int balanceFactor = lHight-rHight;
		if(balanceFactor<-1){
			if(key>root.getRight().getData()){
				// RR case
				// 1. Perform Left Rotation at current root node
				// 2. Update current root and it's right child hight after rotation.
				root = rotateLeft(root);
			}else {
				// RL case
				// 1. Perform right rotation at current root's right child and then
				// 2. Update current root's right child and it's child hight after rotation.
				root.setRight(rotateRight(root.getRight()));
				// 3. Perform left rotation at current root node
				// 4. Update current root and it's right child hight after rotation.
				root = rotateLeft(root);
			}
		}else if(balanceFactor>1){
			if(key<root.getLeft().getData()){
				// LL case
				// 1. Perform Right Rotation at current root node
				// 2. Update current root and it's left child hight after rotation.
				root = rotateRight(root);
			}else {
				// LR case
				// 1. Perform Left rotation at current root's left child and then
				// 2. Update current root's left child and it's child hight after rotation.
				root.setLeft(rotateLeft(root.getLeft()));
				// 3. Perform right rotation at current root node
				// 4. Update current root and it's left child hight after rotation.
				root = rotateRight(root);
			}
		}
		return root;
	}
	
	public static Node rotateLeft(Node pivotNode){
		Node p_right=pivotNode.getRight();
		
		pivotNode.setRight(p_right.getLeft());
		p_right.setLeft(pivotNode);
		
		// Hight correction for pivotNode & p_right since there position has be changed
		hightCorrection(pivotNode);
		hightCorrection(p_right);
		
		return p_right;
	}
	
	public static Node rotateRight(Node pivotNode){
		Node p_left=pivotNode.getLeft();
		
		pivotNode.setLeft(p_left.getRight());
		p_left.setRight(pivotNode);
		
		// Hight correction for pivotNode & p_left since there position has be changed
		hightCorrection(pivotNode);
		hightCorrection(p_left);
		
		return p_left;
	}
	
	public static void hightCorrection(Node node){
		int lHight=node.getLeft()==null?0:node.getLeft().getHight();
		int rHight=node.getRight()==null?0:node.getRight().getHight();
		
		node.setHight((lHight>rHight?lHight:rHight)+1);
	}
	
	public static void main(String[] args) {
		/*Node avlRoot = avlInsert(null, 20);
		avlRoot = avlInsert(avlRoot, 10);
		avlRoot = avlInsert(avlRoot, 5);
		System.out.println(avlRoot.getData());
		avlRoot = avlInsert(avlRoot, 30);
		avlRoot = avlInsert(avlRoot, 40);
		avlRoot = avlInsert(avlRoot, 50);
		System.out.println(avlRoot.getData());*/
		
		/*Node avlDelRoot = avlInsert(null, 20);
		avlDelRoot = avlInsert(avlDelRoot, 10);
		avlDelRoot = avlInsert(avlDelRoot, 25);
		avlDelRoot = avlInsert(avlDelRoot, 15);
		
		avlDelRoot = avlDelete(avlDelRoot, 25);
		System.out.println(avlDelRoot.getData());*/
		
		/*Node avlDelRoot = avlInsert(null, 20);
		avlDelRoot = avlInsert(avlDelRoot, 10);
		avlDelRoot = avlInsert(avlDelRoot, 35);
		avlDelRoot = avlInsert(avlDelRoot, 25);
		
		avlDelRoot = avlDelete(avlDelRoot, 10);
		System.out.println(avlDelRoot.getData());*/
		
		Node avlDelRoot = avlInsert(null, 20);
		avlDelRoot = avlInsert(avlDelRoot, 10);
		avlDelRoot = avlInsert(avlDelRoot, 40);
		avlDelRoot = avlInsert(avlDelRoot, 5);
		
		avlDelRoot = avlInsert(avlDelRoot, 30);
		avlDelRoot = avlInsert(avlDelRoot, 50);
		avlDelRoot = avlInsert(avlDelRoot, 35);
		
		avlDelRoot = avlDelete(avlDelRoot, 50);
		System.out.println(avlDelRoot.getData());
	}

}
