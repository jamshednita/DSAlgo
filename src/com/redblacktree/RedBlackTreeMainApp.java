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