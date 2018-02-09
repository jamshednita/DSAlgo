package com.binarytree;

public class BoundaryTraversal {

	public static void printBoundary(BNode bNode) {
		if(bNode != null){
			System.out.print(bNode.getData()+" ");
			
			printLeftBoundary(bNode.getLeft());
			printLeaves(bNode.getLeft());
			printLeaves(bNode.getRight());
			printRightBoundary(bNode.getRight());
		}
	}
	
	public static void printLeaves(BNode node){
		if(node != null){
			printLeaves(node.getLeft());
			printLeaves(node.getRight());
			
			if(node.getLeft()==null && node.getRight()==null)
				System.out.print(node.getData()+" ");
		}
	}
	
	public static void printLeftBoundary(BNode node){
		if(node !=null ){
			// Check if it's not a leaf before printing to avoid duplicate printing.
			if(node.getLeft() != null){
				System.out.print(node.getData()+" ");
				printLeftBoundary(node.getLeft());
			}else if(node.getRight() != null){
				System.out.print(node.getData()+" ");
				printLeftBoundary(node.getRight());
			}
		}
	}
	
	public static void printRightBoundary(BNode node){
		if(node !=null ){
			// Check if it's not a leaf before printing to avoid duplicate printing.
			if(node.getRight() != null){
				printRightBoundary(node.getRight());
				System.out.print(node.getData()+" ");
			}else if(node.getLeft() != null){
				printRightBoundary(node.getLeft());
				System.out.print(node.getData()+" ");
			}
		}
	}
	
	public static BNode buildSpecialTree(int[] pre, char[] preNL, MyIndex myIndex, int preEnd){
		
		if(myIndex.index>preEnd){
			return null;
		}
		
		BNode node = new BNode(pre[myIndex.index]);
		
		if(preNL[myIndex.index++]=='N'){
			node.setLeft(buildSpecialTree(pre, preNL, myIndex, preEnd));
			node.setRight(buildSpecialTree(pre, preNL, myIndex, preEnd));
		}
		
		return node;
	}
	
	public static void main(String[] args) {
		/*BNode root = new BNode(20);
		root.setLeft(new BNode(8));
		root.setRight(new BNode(22));
		
		root.getLeft().setLeft(new BNode(4));
		root.getLeft().setRight(new BNode(12));
		
		root.getLeft().getRight().setLeft(new BNode(10));
		root.getLeft().getRight().setRight(new BNode(14));
		
		root.getRight().setRight(new BNode(25));
		
		printBoundary(root);*/
		
		int[] pre={10,30,20,5,15};
		char[] preNL={'N','N','L','L','L'};
		
		BNode root = buildSpecialTree(pre, preNL, new BoundaryTraversal().new MyIndex(), pre.length);
		System.out.println(root.getData());
	}
	
	class MyIndex{
		int index=0;
	}
}
