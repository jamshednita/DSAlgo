package com.binarytree;

public class MiscBSTQ {
	/**
	 * Description - Find the node with minimum value in a Binary Search Tree
	 * @param root - Input root of BST
	 * @return - Min key node
	 */
	public static Node getMinFromBST(Node root){
		if(root.getLeft()==null)
			return root;
		
		return getMinFromBST(root.getLeft());
	}
	/**
	 * Description - A binomial coefficient based function to find nth ctalon number. O(n)
	 * 
	 * @param n
	 * @return
	 */
	public static int catalon(int n){
		return binomialCoeff(2*n, n)/(n+1);
	}
	private static int binomialCoeff(int n, int r){
		if(r>n-r)
			r=n-r;
		int res=1;
		for (int i = 0; i < r; i++) {
			res*=(n-i);
			res/=(i+1);
		}
		//System.out.println("Binomial Coeff = "+(res));
		return res;
	}
	/**
	 * Description - Printing in-order traversal from given level-order traversal without sorting. O(n).
	 * 
	 * @param levelOrder
	 */
	public static void inorderFromLevelOrder(int[] levelOrder){
		in4mLevelOrderUtil(levelOrder, 0, levelOrder.length-1);
	}
	private static void in4mLevelOrderUtil(int[] arr, int start, int end){
		if(start>end)
			return;
		
		in4mLevelOrderUtil(arr, 2*start+1, end);
		System.out.print(arr[start] + " ");
		in4mLevelOrderUtil(arr, 2*start+2, end);
	}
	/**
	 * Description - Inorder Successor in Binary Search Tree.
	 * @param root - Root node
	 * @param node - Node against which successor need to be find
	 * @return - successor
	 */
	public static Node inorderSuccessor(Node root, Node node){
		if(node.getRight()!=null){
			Node temp=node.getRight();
			while(temp.getLeft()!=null)
				temp=temp.getLeft();
			
			return temp;
		}
		
		Node succ=null;
		while(root!=null){
			if(root.getData()>node.getData()){
				succ=root;
				root=root.getLeft();
			}else if(root.getData()>node.getData()){
				root=root.getRight();
			}else 
				break;
		}
		
		return succ;
	}
	/**
	 * Description - Print BST keys in the given range
					 Given two values k1 and k2 (where k1 < k2) and a root pointer to a Binary Search Tree. Print all the keys of tree in range k1 to k2. i.e. print all x such that k1<=x<=k2 and x is a key of given BST. Print all the keys in increasing order.
	 * @param root - BST root node
	 * @param k1
	 * @param k2
	 */
	public static void printbetweenk1k2(Node root, int k1, int k2){
		if(root == null)
			return;
		if(root.getLeft()!=null && root.getLeft().getData()>=k1)
			printbetweenk1k2(root.getLeft(), k1, k2);
		
		System.out.print(root.getData()+" ");
			
		if(root.getRight()!=null && root.getRight().getData()<=k2)
			printbetweenk1k2(root.getRight(), k1, k2);
		
	}
	public static void main(String[] args) {
		Node root=new Node(20);
		root.setLeft(new Node(8));
		root.setRight(new Node(22));
		root.getLeft().setLeft(new Node(4));
		root.getLeft().setRight(new Node(12));
		
		/*System.out.println(getMinFromBST(root).getData());
		
		System.out.println("Total no of BST if n=3 : "+catalon(3));*/
		
		/*int[] arr={4,2,5,1,3};
		inorderFromLevelOrder(arr);*/
		
		System.out.println(inorderSuccessor(root, root.getLeft().getRight()).getData());
		printbetweenk1k2(root, 5, 21);
	}

}
