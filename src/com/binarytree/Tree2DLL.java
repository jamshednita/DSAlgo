package com.binarytree;

public class Tree2DLL {

	public static void buildInOrderDLL(MyIndex mi, Node root){
		if(root==null){
			return;
		}
		
		buildInOrderDLL(mi, root.getRight());
		
		if(mi.getIndx()==null){
			mi.setIndx(root);
		}else{
			Node temp = mi.getIndx();
			temp.setLeft(root);
			root.setRight(temp);
			mi.setIndx(root);
		}
		
		buildInOrderDLL(mi, root.getLeft());
	}
	
	public static void main(String[] args) {
		Node root = new Node(30);
		Node left = new Node(25);
		Node right = new Node(40);
		
		root.setLeft(left);
		root.setRight(right);
		
		left.setLeft(new Node(15));
		left.setRight(new Node(27));
		
		right.setLeft(new Node(37));
		
		MyIndex mi = new MyIndex();
		buildInOrderDLL(mi, root);
		
		Node itr = mi.getIndx();
		while(itr!=null){
			System.out.print(itr.getData() + "  ");
			itr=itr.getRight();
		}
	}
}
class MyIndex{
	Node indx;

	public Node getIndx() {
		return indx;
	}

	public void setIndx(Node indx) {
		this.indx = indx;
	}
	
}