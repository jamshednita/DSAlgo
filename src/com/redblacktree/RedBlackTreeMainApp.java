package com.redblacktree;


public class RedBlackTreeMainApp {
	public static RedBlackNode insert(RedBlackNode root,int data){
		RedBlackNode newNode = new RedBlackNode(data);
		root = bstInsert(root, newNode);
		root=performInsertBalancing(root, newNode);
		//root=fixInsertViolation(root, newNode);
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
		
		if(pt.getParent() == null)
			root=pt_right;
		else if(pt.getParent().getLeft() == pt){
			pt.getParent().setLeft(pt_right);
		}else 
			pt.getParent().setRight(pt_right);
		
		pt_right.setParent(pt.getParent());
		pt_right.setLeft(pt);
		pt.setParent(pt_right);
		
		return root;
	}
	
	private static RedBlackNode rotateRight(RedBlackNode root, RedBlackNode pt){
		RedBlackNode pt_left=pt.getLeft();
		pt.setLeft(pt_left.getRight());
		
		if(pt_left.getRight() != null)
			pt_left.getRight().setParent(pt_left);
		
		if(pt.getParent() == null)
			root=pt_left;
		else if(pt.getParent().getRight() == pt)
			pt.getParent().setRight(pt_left);
		else
			pt.getParent().setLeft(pt_left);
		
		pt_left.setParent(pt.getParent());
		pt.setParent(pt_left);
		pt_left.setRight(pt);
		
		return root;
	}
	
	private static RedBlackNode performInsertBalancing(RedBlackNode root, RedBlackNode newNode){
		if(newNode==root){
			newNode.setColor(true); // TRUE=BLACK
			return root;
		}
		RedBlackNode parent_nd=newNode.getParent();
		// Problem gets created when we see two consecutive RED in the path after insertion.
		if(newNode!=null && !newNode.isColor() && parent_nd!=null && !parent_nd.isColor()){ // If parent node's color is RED(false).
			RedBlackNode grandParent = newNode.getParent().getParent();
			
			if(parent_nd==grandParent.getLeft()){
				RedBlackNode uncle=grandParent.getRight();
				// Case 1 - if uncle is also RED then coloring is required
				if(uncle!=null && !uncle.isColor()){
					parent_nd.setColor(true);
					uncle.setColor(true);
					grandParent.setColor(false);
					root=performInsertBalancing(root, grandParent);
				}else{
					// if uncle is BLACK then Rotation Required.
					
					if(newNode==parent_nd.getRight()){
						root=rotateLeft(root, parent_nd);
						newNode=parent_nd;
						//parent_nd=newNode.getParent();
					}
					
					root=rotateRight(root, grandParent);
					swapColor(parent_nd, grandParent);
					newNode=parent_nd;
					
					//root=performInsertBalancing(root, newNode); - This step is not required since rotation restores red-black properties alone and it does not creates any red-red relationship with remaining tree.
				}
			}else{
				RedBlackNode uncle=grandParent.getLeft();
				if(uncle!=null && !uncle.isColor()){
					parent_nd.setColor(true);
					uncle.setColor(true);
					grandParent.setColor(false);
					root=performInsertBalancing(root, grandParent);
				}else{
					if(newNode == parent_nd.getLeft()){
						root=rotateRight(root, parent_nd);
						newNode=parent_nd;
						//parent_nd=newNode.getParent();
					}
					root=rotateLeft(root, grandParent);
					swapColor(parent_nd, grandParent);
					newNode=parent_nd;
					//root=performInsertBalancing(root, newNode);
				}
			}
		}
		return root;
	}
	/**
	 * Description - This is iterative version of performInsertBalancing.
	 * @param root
	 * @param pt
	 * @return
	 */
	private static RedBlackNode fixInsertViolation(RedBlackNode root, RedBlackNode pt){
		RedBlackNode parent_pt=null;
		RedBlackNode grand_parent_pt=null;
		
		while(pt!=null && pt.getParent()!=null && (!pt.isColor()) && (!pt.getParent().isColor())){
			parent_pt=pt.getParent();
			grand_parent_pt = parent_pt.getParent();
			
			if(parent_pt==grand_parent_pt.getLeft()){
				RedBlackNode uncle = grand_parent_pt.getRight();
				if(uncle!=null && !uncle.isColor()){
					uncle.setColor(true);
					parent_pt.setColor(true);
					grand_parent_pt.setColor(false);
					
					pt=parent_pt;
				}else{
					
					if(pt==parent_pt.getRight()){
						root=rotateLeft(root, parent_pt);
						pt=parent_pt;
						parent_pt=pt.getParent();
					}
					
					root=rotateRight(root, grand_parent_pt);
					swapColor(parent_pt, grand_parent_pt);
					pt=parent_pt;
				}
			}else{
				RedBlackNode uncle = grand_parent_pt.getLeft();
				
				if(uncle!=null && !uncle.isColor()){
					uncle.setColor(true);
					parent_pt.setColor(true);
					grand_parent_pt.setColor(false);
					
					pt=parent_pt;
				}else{
					
					if(pt==parent_pt.getLeft()){
						root=rotateRight(root, parent_pt);
						pt=parent_pt;
						parent_pt=pt.getParent();
					}
					
					root=rotateLeft(root, grand_parent_pt);
					swapColor(parent_pt, grand_parent_pt);
					pt=parent_pt;
				}
			}
		}
		
		root.setColor(true);
		
		return root;
	}
	
	private static void swapColor(RedBlackNode parent_nd, RedBlackNode grandParent) {
		boolean temp=parent_nd.isColor();
		parent_nd.setColor(grandParent.isColor());
		grandParent.setColor(temp);
	}
	
	public static RedBlackNode delete(RedBlackNode root, RedBlackNode delKey){
		RedBlackNode replacedNode=bstDelete(root, delKey);
		root=fixDeleteViolation(root, delKey, replacedNode);
		return root;
	}
	private static RedBlackNode fixDeleteViolation(RedBlackNode root,
			RedBlackNode delKey, RedBlackNode replacedNode) {
		
		return null;
	}
	private static RedBlackNode bstDelete(RedBlackNode root, RedBlackNode delKey) {
		if(root == null)
			return null;
		
		if(root.getData() == delKey.getData()){
			if(root.getLeft()==null){
				return root.getRight();
			}else if(root.getRight() == null){
				return root.getLeft();
			}else{
				RedBlackNode successor = inSuccessor(root.getRight());
				root.setData(successor.getData());
				
				root.setRight(bstDelete(root.getRight(), successor));
				root.getRight().setParent(root);
			}
		}else if(root.getData()>delKey.getData()){
			root.setLeft(bstDelete(root.getLeft(), delKey));
			root.getLeft().setParent(root);
		}
		else{
			root.setRight(bstDelete(root.getRight(), delKey));
			root.getRight().setParent(root);
		}
		
		return root;
	}
	private static RedBlackNode inSuccessor(RedBlackNode right) {
		RedBlackNode res=null;
		
		while(right!=null){
			res=right;
			right=right.getLeft();
		}
		
		return res;
	}
	
	public static RedBlackNode rbDelete(RedBlackNode root, int del, CustIndex curr) {
		if(root == null)
			return null;
		
		if(root.getData() == del) {
			// If node to be deleted has one not-null child.
			if(root.getLeft() == null || root.getRight() == null) {
				return rbDeleteUtil(root, curr);
			}else {
				int inOrderSucc=inSuccessor(root.getRight()).getData();
				root.setData(inOrderSucc);
				
				root.setRight(rbDelete(root.getRight(), inOrderSucc, curr));
			}
			
		}else if(root.getData()>del){
			root.setLeft(rbDelete(root.getLeft(), del, curr));
		}else {
			root.setRight(rbDelete(root.getRight(), del, curr));
		}
		
		return root;
	}
	/*private static int findSamllest(RedBlackNode node) {
		
		while(node.getLeft()!=null) {
			node=node.getLeft();
		}
		return node==null?0:node.getData();
	}*/
	private static RedBlackNode rbDeleteUtil(RedBlackNode nodeToBeDeleted, CustIndex curr) {
		RedBlackNode child = nodeToBeDeleted.getLeft() == null ? nodeToBeDeleted.getRight() : nodeToBeDeleted.getLeft();
		
		// This replaces nodeToBeDeleted with child node.
		if(nodeToBeDeleted.getParent() == null) {
			curr.ptr = child;
		}else {
			
			if(isLeftChild(nodeToBeDeleted)) {
				nodeToBeDeleted.getParent().setLeft(child);
			}else {
				nodeToBeDeleted.getParent().setRight(child);
			}
			
			if(child!=null) {
				child.setParent(nodeToBeDeleted.getParent());
			}
		}
		
		// If either of nodeToBeDeleted or child is red then color child as Black and DONE
		if(!nodeToBeDeleted.isColor() || (child!=null && !child.isColor())) {
			if(child!=null) {
				child.setColor(true);
			}
		}else { // Otherwise double-black situation and will got to 6-cases.
			rbDeleteCase1(child, nodeToBeDeleted.getParent(), curr);
		}
		
		return child;
	}
	
	private static void rbDeleteCase1(RedBlackNode doubleBlackNode, RedBlackNode doubleBlackParent, CustIndex curr) {
		if(doubleBlackNode.getParent()==null) {
			curr.ptr=doubleBlackNode;
			return;
		}
		
		rbDeleteCase2(doubleBlackNode, doubleBlackParent, curr);
	}
	private static void rbDeleteCase2(RedBlackNode doubleBlackNode, RedBlackNode doubleBlackParent, CustIndex curr) {
		RedBlackNode sibling = getSibling(doubleBlackNode, doubleBlackParent);
		
		if(sibling.isColor()) {
			if(isRightChild(sibling)) {
				curr.ptr=rotateRight(curr.ptr, doubleBlackParent);
			}else {
				curr.ptr=rotateLeft(curr.ptr, doubleBlackParent);
			}
			
			swapColor(doubleBlackParent, sibling);
		}
		rbDeleteCase3(doubleBlackNode, doubleBlackParent, curr);
	}
	private static void rbDeleteCase3(RedBlackNode doubleBlackNode, RedBlackNode doubleBlackParent, CustIndex curr) {
		RedBlackNode sibling=getSibling(doubleBlackNode, doubleBlackParent);
		
		if(doubleBlackParent.isColor() && sibling.isColor() && (sibling.getLeft()==null || !sibling.getLeft().isColor()) && (sibling.getRight() == null || !sibling.getRight().isColor())) {
			sibling.setColor(false);
			
			rbDeleteCase1(doubleBlackParent, doubleBlackParent.getParent(), curr);
		}else {
			rbDeleteCase4(doubleBlackNode, doubleBlackParent, curr);
		}
		
	}
	private static void rbDeleteCase4(RedBlackNode doubleBlackNode, RedBlackNode doubleBlackParent, CustIndex curr) {
		RedBlackNode sibling = getSibling(doubleBlackNode, doubleBlackParent);
		
		if(doubleBlackParent.isColor() && !sibling.isColor() && (sibling.getLeft()==null || !sibling.getLeft().isColor()) && (sibling.getRight()==null || !sibling.getRight().isColor())) {
			swapColor(doubleBlackParent, sibling);
			return;
		}else {
			rbDeleteCase5(doubleBlackNode, doubleBlackParent, curr);
		}
		
	}
	private static void rbDeleteCase5(RedBlackNode doubleBlackNode, RedBlackNode doubleBlackParent, CustIndex curr) {
		RedBlackNode sibling = getSibling(doubleBlackNode, doubleBlackParent);
		
		if(!doubleBlackParent.isColor() && !sibling.isColor()) {
			if(isRightChild(sibling) && (sibling.getLeft()!=null && sibling.getLeft().isColor()) && (sibling.getRight() == null || !sibling.getRight().isColor())) {
				curr.ptr = rotateRight(curr.ptr, sibling);
			}else if(isLeftChild(sibling) && (sibling.getRight() != null && sibling.getRight().isColor()) && (sibling.getLeft() == null || !sibling.getLeft().isColor())) {
				curr.ptr = rotateLeft(curr.ptr, sibling);
			}
		}
		
		rbDeleteCase6(doubleBlackNode, doubleBlackParent, curr);
	}
	private static void rbDeleteCase6(RedBlackNode doubleBlackNode, RedBlackNode doubleBlackParent, CustIndex curr) {
		RedBlackNode sibling = getSibling(doubleBlackNode, doubleBlackParent);
		
		if(isRightChild(sibling) && !sibling.isColor() && (sibling.getRight() != null && sibling.getRight().isColor())) {
			curr.ptr = rotateLeft(curr.ptr, doubleBlackParent);
			
			sibling.getRight().setColor(true);
		}else if (isLeftChild(sibling) && !sibling.isColor() && (sibling.getLeft() != null && sibling.getLeft().isColor())){
			curr.ptr = rotateRight(curr.ptr, doubleBlackParent);
			sibling.getLeft().setColor(true);
		}
		
		swapColor(doubleBlackParent, sibling);
	}
	private static RedBlackNode getSibling(RedBlackNode doubleBlackNode, RedBlackNode doubleBlackParent) {

		if(doubleBlackNode==doubleBlackParent.getLeft()) {
			return doubleBlackParent.getRight();
		}else
			return doubleBlackParent.getLeft();
	}
	private static boolean isLeftChild(RedBlackNode node) {
		RedBlackNode prnt = node.getParent();
		
		if(node == prnt.getLeft())
			return true;
		else {
			return false;
		}
	}
	
	private static boolean isRightChild(RedBlackNode node) {
		RedBlackNode prnt = node.getParent();
		
		if(node == prnt.getRight())
			return true;
		else {
			return false;
		}
	}
	public static void main(String[] args) {
		
		/*RedBlackNode root = insert(null, 10);//null;
		root = insert(root, 20);
		System.out.println(root.getData()+" : "+root.isColor());
		root = insert(root, 30);
		System.out.println(root.getData()+" : "+root.isColor());
		root = insert(root, 40);
		System.out.println(root.getData()+" : "+root.isColor());
		root = insert(root, 50);
		System.out.println(root.getData()+" : "+root.isColor());
		root = insert(root, 60);
		System.out.println(root.getData()+" : "+root.isColor());*/
		
		RedBlackNode root = insert(null, 60);//null;
		root = insert(root, 50);
		System.out.println(root.getData()+" : "+root.isColor());
		root = insert(root, 40);
		System.out.println(root.getData()+" : "+root.isColor());
		root = insert(root, 30);
		System.out.println(root.getData()+" : "+root.isColor());
		root = insert(root, 20);
		System.out.println(root.getData()+" : "+root.isColor());
		root = insert(root, 10);
		System.out.println(root.getData()+" : "+root.isColor());
		root = insert(root, 5);
		System.out.println(root.getData()+" : "+root.isColor());
	}

}
class CustIndex{
	RedBlackNode ptr=null;
}