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
	/**
	 * Description - A method to find out largest sorted sequence present in Binary Tree.
	 * @param root
	 * @return
	 */
	public static int largestSortedSeq(Node root){
		CustIndex ci= new CustIndex(0, 0);
		largestSortedSeqUtil(root,ci);
		return ci.max>ci.index?ci.max:ci.index;
	}
	private static void largestSortedSeqUtil(Node root, CustIndex ci) {
		if(root==null){
			return;
		}
		largestSortedSeqUtil(root.getLeft(), ci);
		
		if(ci.ptr==null){
			ci.ptr=root;
			ci.index++;
		}else if(ci.ptr.getData()>root.getData()){
			ci.max=ci.max>ci.index?ci.max:ci.index;
			ci.index=1;
			ci.ptr=root;
		}else{
			ci.index++;
			ci.ptr=root;
		}
		
		largestSortedSeqUtil(root.getRight(), ci);
	}
	
	/**
	 * Description - Find the largest BST subtree in a given Binary Tree. O(n) Solution
	 * 
	 * @param root
	 * @param ci
	 * @return
	 */
	public static CustIndex largestSubBST(Node root, CustIndex ci){
		if(root == null)
			return ci;
		
		CustIndex lst = largestSubBST(root.getLeft(), new CustIndex(root.getData(), root.getData())); // Check if left subtree is BST
		CustIndex rst = largestSubBST(root.getRight(), new CustIndex(root.getData(), root.getData())); // Check if right subtree is BST
		if(lst.bst && rst.bst && lst.max<=root.getData() && rst.min>=root.getData()){ // left and right subtree both are BST and max of left should less than roo and min of right should greater than root.
			ci.bst=true;
			ci.index=lst.index+rst.index+1;
			ci.max=rst.max;
			ci.min=lst.min;
		}else{
			ci.bst=false;
			ci.index=lst.index>rst.index?lst.index:rst.index;
		}
		
		return ci;
	}
	
	public static Node mergeTwoBalancedBST(Node root1, Node root2, int n){
		// Step 1 - Create DLL for first tree (root1) in-place
		// Step 2 - Create DLL for second tree (root2) in-place
		// Step 3 - Merge these two sorted DLL
		// Step 4 - Create Balanced BST from sorted DLL in step 3.
		CIndex curr1=new CIndex();
		createDDL4mBST(root1, curr1);
		
		CIndex curr2=new CIndex();
		createDDL4mBST(root2, curr2);
		
		Node combinedDLLsn=mergeDLLs(curr1.ptr, curr2.ptr);
		
		CIndex curr=new CIndex();
		curr.ptr=combinedDLLsn;
		Node finalRoot = buildBST4mDLL(curr, n);
		return finalRoot;
	}
	
	private static void createDDL4mBST(Node root, CIndex curr){
		if(root == null)
			return;
		createDDL4mBST(root.getRight(), curr);
		if(curr.ptr == null){
			curr.ptr=root;
		}else{
			curr.ptr.setLeft(root);
			root.setRight(curr.ptr);
			curr.ptr=root;
		}
		createDDL4mBST(root.getLeft(), curr);
	}
	private static Node mergeDLLs(Node root1, Node root2){
		Node temp1=(root1.getData()>root2.getData()?root2:root1);	// Smaller Root
		Node temp2=(root1.getData()<root2.getData()?root2:root1);	// Greater Root
		Node ret=temp1;// Result start node reference
		while(temp1!=null && temp1.getRight()!=null && temp2!=null){
			Node temp1_next=temp1.getRight();
			Node temp2_next=temp2.getRight();
			
			if(temp2.getData()<temp1_next.getData()){
				temp1.setRight(temp2);
				temp2.setLeft(temp1);
				temp2.setRight(temp1_next);
				temp1_next.setLeft(temp2);
				
				temp1=temp1_next.getLeft(); // temp1 should be next after modification
				temp2=temp2_next;
			}else{
				temp1=temp1_next;
			}
		}
		
		if(temp2!=null){
			temp1.setLeft(temp2);
			temp2.setLeft(temp1);
		}
		return ret;
	}
	/**
	 * This is similar to how to get BST from sorted array.
	 * 
	 * @param curr
	 * @param n
	 * @return
	 */
	private static Node buildBST4mDLL(CIndex curr, int n){
		if(n<=0){
			return null;
		}
		
		Node left= buildBST4mDLL(curr, n/2);
		
		Node root=curr.ptr;
		curr.ptr=curr.ptr.getRight();
		
		root.setLeft(left);
		root.setRight(buildBST4mDLL(curr, n-n/2-1));
		
		return root;
	}
	public static void main(String[] args) {
		/*Node root=new Node(20);
		root.setLeft(new Node(8));
		root.setRight(new Node(22));
		root.getLeft().setLeft(new Node(4));
		root.getLeft().setRight(new Node(12));
		
		System.out.println(getMinFromBST(root).getData());
		
		System.out.println("Total no of BST if n=3 : "+catalon(3));
		
		int[] arr={4,2,5,1,3};
		inorderFromLevelOrder(arr);
		
		System.out.println(inorderSuccessor(root, root.getLeft().getRight()).getData());
		printbetweenk1k2(root, 5, 21);*/
		
		/*Node root = new Node(50);
		root.left = new Node(10);
		root.right = new Node(60);
		root.left.left = new Node(5);
		root.left.right = new Node(20);
		root.right.left = new Node(55);
		root.right.left.left = new Node(45);
		root.right.right = new Node(70);
		root.right.right.left = new Node(65);
		root.right.right.right = new Node(80);
		
		System.out.println(largestSubBST(root, new CustIndex(root.getData(), root.getData())).index);*/
		
		Node root = new Node(100);
		root.left = new Node(50);
		root.right = new Node(300);
		root.left.left = new Node(20);
		root.left.right = new Node(70);

		Node root1 = new Node(80);  
		root1.left = new Node(40);
		root1.right = new Node(120);

		System.out.println(mergeTwoBalancedBST(root, root1, 8));
	}

}

class CustIndex{
	Node ptr=null;
	int index=0,max=0,min=0;
	boolean bst=true;
	public CustIndex(int max, int min) {
		super();
		this.max = max;
		this.min = min;
	}

}
