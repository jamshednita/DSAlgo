package com.binarytree;

public class BinaryTreeConvAndConstruction {
	
	public static Node binaryTreeFromPreIn(int[] pre, int[] in, int inStart, int inEnd, CustIndex ci) {
		if(inStart>inEnd)
			return null;
		
		Node root = new Node(pre[ci.index++]);
		
		if(inStart==inEnd)
			return root;
		int rootIndex=getRootIndex(in, root.getData(), inStart, inEnd);
		
		root.setLeft(binaryTreeFromPreIn(pre, in, inStart, rootIndex-1, ci));
		root.setRight(binaryTreeFromPreIn(pre, in, rootIndex+1, inEnd, ci));
		
		return root;
	}
	
	private static int getRootIndex(int[] in, int data, int inStart, int inEnd) {
		for (int i = inStart; i <= inEnd; i++) {
			if(in[i]==data)
				return i;
		}
		return -1;
	}

	public static Node binaryTreeFromPostIn(int[] post, int[] in, int inStart, int inEnd, CustIndex ci) {
		if(inStart>inEnd)
			return null;
		
		Node root = new Node(post[ci.index--]);
		
		if(inStart==inEnd)
			return root;
		int rootIndex=getRootIndex(in, root.getData(), inStart, inEnd);
		
		root.setRight(binaryTreeFromPostIn(post, in, rootIndex+1, inEnd, ci));
		root.setLeft(binaryTreeFromPostIn(post, in, inStart, rootIndex-1, ci));
		
		return root;
	}
	public static void main(String[] args) {
		int[] in= {4,2,5,1,3,6};
		int[] pre= {1,2,4,5,3,6};
		
		Node nodePreIn = binaryTreeFromPreIn(pre, in, 0, in.length-1, new CustIndex(0, 0));
		System.out.println(nodePreIn.getData());
		
		int[] post= {8, 4, 5, 2, 6, 7, 3, 1};
		int[] inPost= {4, 8, 2, 5, 1, 6, 3, 7};
		
		CustIndex ci= new CustIndex(0, 0);
		ci.index=inPost.length-1;
		Node nodePostIn= binaryTreeFromPostIn(post, inPost, 0, inPost.length-1, ci);
		
		System.out.println(nodePostIn.getData());
	}

}
