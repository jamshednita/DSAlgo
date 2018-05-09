package com.binarytree;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.TreeMap;

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
			ci.max=ci.max>ci.index?ci.max:ci.index;// Max variable is to store previous largestSorted sequence. 
			
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
	public static int largestSubBST(Node root) {
		CustIndex ci = new CustIndex(root.getData(), root.getData());
		return largestSubBSTUtil(root, ci).index;
	 }
	private static CustIndex largestSubBSTUtil(Node root, CustIndex ci){
		if(root == null)
			return ci;
		/*
		 * 
		 */
		CustIndex lst = largestSubBSTUtil(root.getLeft(), new CustIndex(root.getData(), root.getData())); // Check if left subtree is BST
		CustIndex rst = largestSubBSTUtil(root.getRight(), new CustIndex(root.getData(), root.getData())); // Check if right subtree is BST
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
		createDLL4mBST(root1, curr1);
		
		CIndex curr2=new CIndex();
		createDLL4mBST(root2, curr2);
		
		Node combinedDLLsn=mergeDLLs(curr1.ptr, curr2.ptr);
		
		CIndex curr=new CIndex();
		curr.ptr=combinedDLLsn;
		Node finalRoot = buildBST4mDLL(curr, n);
		return finalRoot;
	}
	
	private static void createDLL4mBST(Node root, CIndex curr){
		if(root == null)
			return;
		createDLL4mBST(root.getRight(), curr);
		if(curr.ptr == null){
			curr.ptr=root;
			curr.end=root;
		}else{
			curr.ptr.setLeft(root);
			root.setRight(curr.ptr);
			curr.ptr=root;
		}
		createDLL4mBST(root.getLeft(), curr);
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
	
	public static void correctBST(Node root) {
		CIndex first=new CIndex(),middle=new CIndex(), last=new CIndex(), prev=new CIndex();
		correctBSTUtil(root, first, middle, last, prev);
		
		if(last.ptr!=null) {
			swapNodesKey(first.ptr, last.ptr);
		}else {
			swapNodesKey(first.ptr, middle.ptr);
		}
	}
	private static void swapNodesKey(Node ptr, Node ptr2) {
		int temp=ptr2.getData();
		ptr2.setData(ptr.getData());
		ptr.setData(temp);
	}
	private static void correctBSTUtil(Node root, CIndex first, CIndex middle, CIndex last, CIndex prev) {
		if(root==null || (middle.ptr!=null && last.ptr!=null))
			return;
		correctBSTUtil(root.getLeft(), first, middle, last, prev);
		
		if(prev.ptr==null)
			prev.ptr=root;
		else if(prev.ptr.getData()>root.getData()){
			if(first.ptr==null) {
				first.ptr=prev.ptr;
				middle.ptr=root;
			}else
				last.ptr=root;
		}
		
		prev.ptr=root;
		
		correctBSTUtil(root.getRight(), first, middle, last, prev);
	}
	
	public static int ceil(Node root, int keyValue) {
		if(root==null)
			return -1;
		
		if(root.getData()==keyValue)
			return root.getData();
		
		if(root.getData()<keyValue)
			return ceil(root.getRight(), keyValue);
		
		int ceilValue=ceil(root.getLeft(), keyValue);
		
		return ceilValue>=keyValue?ceilValue:root.getData();		
	}
	
	public static int floor(Node root, int keyValue) {
		if(root==null)
			return -1;
		
		if(root.getData()==keyValue)
			return root.getData();
		
		if(root.getData()>keyValue)
			return floor(root.getLeft(), keyValue);
		
		int floorValue=floor(root.getRight(), keyValue);
		
		return floorValue==-1?root.getData():floorValue;		
	}
	/**
	 * Description - This method will process each node once from both the tree so time complexity - O(m+n). There are nested while loops but still it will process one node atmost once
	 *               Also, in stack it will be pushed once and popped once.
	 * 
	 * @param root1
	 * @param root2
	 */
	public static void mergeAndPrint(Node root1, Node root2){
		
		Stack<Node> stackOne= new Stack<Node>();
		Stack<Node> stackTwo= new Stack<Node>();
		
		while(root1!=null){
			stackOne.push(root1);
			root1=root1.getLeft();
		}
		
		while(root2!=null){
			stackTwo.push(root2);
			root2=root2.getLeft();
		}
		
		while(!stackOne.isEmpty() && !stackTwo.isEmpty()){
			Node top = null;
			Node recNode = null;
			
			if(stackOne.peek().getData() < stackTwo.peek().getData()){
				top=stackOne.pop();
				
				if(top.getRight()!=null){
					stackOne.push(top.getRight());
					
					recNode = stackOne.peek().getLeft();
					while(recNode!=null){
						stackOne.push(recNode);
						recNode=recNode.getLeft();
					}
				}
			}else{
				top=stackTwo.pop();
				
				if(top.getRight()!=null){
					stackTwo.push(top.getRight());
					
					recNode = stackTwo.peek().getLeft();
					while(recNode!=null){
						stackTwo.push(recNode);
						recNode=recNode.getLeft();
					}	
				}
			}
			System.out.print(top.getData()+ " ");
		}
		
		while(!stackOne.isEmpty()){
			System.out.print(stackOne.peek().getData()+" ");
			inorder(stackOne.pop().getRight());
		}
		
		while(!stackTwo.isEmpty()){
			System.out.print(stackTwo.peek().getData()+" ");
			inorder(stackTwo.pop().getRight());
		}		
	}
	private static void inorder(Node right) {
		if(right == null)
			return;
		
		inorder(right.getLeft());
		System.out.println(right.getData()+" ");
		inorder(right.getRight());	
	}
	/**
	 * Description - Find if there is a triplet in a Balanced BST that adds to zero. Expected time complexity is O(n^2) and only O(Logn) extra space can be used. You can modify given Binary Search Tree. Note that height of a Balanced BST is always O(Logn).
	 * 
	 * @param root
	 */
	public static void printTripletOfZero(Node root){
		// Step 1 - convert in-order of BST to DLL in O(logn) space complexity
		
		CIndex curr=new CIndex();
		createDLL4mBST(root, curr);
		
		// Step 2 - find triplet in O(n^2) time complexity
		
		Node currSt=curr.ptr, currEnd=curr.end.getLeft().getLeft(), tempEnd=curr.end;
		
		while(currSt!=null && currSt!=currEnd){
			int currData=currSt.getData();
			
			Node currNext=currSt.getRight();
			while(currNext.getData()<tempEnd.getData()){
				if(currNext.getData()+tempEnd.getData()+currData == 0){
					System.out.println(currData+" : "+currNext.getData()+" : "+tempEnd.getData());
					return;
				}else if(currNext.getData()+tempEnd.getData()<currData*-1){
					currNext=currNext.getRight();
				}else{
					tempEnd=tempEnd.getLeft();
				}
			}
			
			currSt=currSt.getRight();
		}
		System.err.println("No tripplet that sums to zero !!");
	}
	/**
	 * Description - Find a pair with given sum in a Balanced BST. Expected time complexity is O(n) and only O(Logn) extra space can be used. Any modification to Binary Search Tree is not allowed.
	 * @param root
	 * @param k
	 */
	public static void findPair4SumK(Node root, int k){
		Stack<Node> stkOne = new Stack<Node>();
		Stack<Node> stkTwo = new Stack<Node>();
		/*
		 * Idea is to traverse tree in ascending and descending order at the same time using two stacks and check
		 * if sum of both element is equal to desired sum k then stop doing traversal.
		 * Else if sum<k then find ascending stack top element and recheck
		 * else sum>k then find descending stack top element and recheck
		 */
		
		boolean first=false, second=false;
		Node inOrder=root, revInOrder=root;
		int leftVal=0, rightVal=0;
		while(true){
			
			if(!first){
				while(inOrder!=null){
					stkOne.push(inOrder);
					inOrder=inOrder.getLeft();
				}
				if(!stkOne.isEmpty()){
					Node leftTemp=stkOne.pop();
					leftVal=leftTemp.getData();
					inOrder=leftTemp.getRight();
				}
				first=true;
			}
			
			if(!second){
				while(revInOrder!=null){
					stkTwo.push(revInOrder);
					revInOrder=revInOrder.getRight();
				}
				
				if(!stkTwo.isEmpty()){
					Node rightTemp=stkTwo.pop();
					rightVal=rightTemp.getData();
					revInOrder=rightTemp.getLeft();
					
				}
				second=true;
			}
			
			if(leftVal+rightVal == k){
				System.out.println(leftVal+" : "+rightVal);
				return;
			}else if(leftVal+rightVal > k){
				second=false; // Find the next element from revInorder stack
			}else{
				first=false; // Find the next element from inorder stack
			}
			
			if(leftVal >= rightVal){
				System.err.println("No pair found");
				return;
			}

		}
		
		// Step 1 - Do an iterative in-order traversal using stack.
		// Step 2 - Do another iterative reverse-in-order traversal using stack.
		// Step 3 - Check if sum of step-1 node key + step-2 node key equals to k
		//          if yes = then print and return;
		//			else if(sum>k) find next node in reverse-in-order and check for sum
		//          else find next node in in-order and check for sum
	}
	/**
	 * Description - Remove BST keys outside the given range.
	 * 
	 * @param root - BST root.
	 * @param min - min value of the range.
	 * @param max - max value of the range.
	 * @return
	 */
	public static Node removeOutOfRange(Node root, int min, int max){
		if(root==null)
			return null;
		
		root.setLeft(root.getData()==min?null:removeOutOfRange(root.getLeft(), min, max));
		root.setRight(root.getData()==max?null:removeOutOfRange(root.getRight(), min, max));
		
		if(root.getData()<min){
			return root.getRight();
		}else if(root.getData()>max){
			return root.getLeft();
		}
		return root;
	}
	/**
	 * Description - Design a data structure to do reservations of future jobs on a single machine under following constraints.
				     1) Every job requires exactly k time units of the machine.
					 2) The machine can do only one job at a time. 
					 3) Time is part of the system. Future Jobs keep coming at different times. Reservation of a future job is done only if there is no existing reservation within k time frame (after and before)
					 4) Whenever a job finishes (or its reservation time plus k becomes equal to current time), it is removed from system.
	 * @param root - Root of reservation BST
	 * @param time - New reservation time.
	 * @param k    - Reservation time.
	 * @return     - Root of modified reservation tree.
	 */
	public static Node reserveJobAt(Node root, int time, int k){
		if(root==null)
			return new Node(time);
		
		if(root.getData() < time+k && root.getData()+k > time){
			return root; // Reservation cannot be done at time, slot already reserved.
		}else if(time>root.getData())
			root.setRight(reserveJobAt(root.getRight(), time, k));
		else{
			root.setLeft(reserveJobAt(root.getLeft(), time, k));
		}
		
		return root;
	}
	/**
	 * Description - Finds no of nodes in BST that is in the range of [low, high]. Time Complexity = O(h+k) approx. Less than O(n) for sure.
	 * 
	 * @param root - root of BST.
	 * @param low - lowest range value.
	 * @param high - highest range value.
	 * @return - No of elements in range.
	 */
	public static int nodesInRange(Node root, int low, int high){
		if(root==null){
			return 0;
		}
		// if root's data is inRange then it's both left and right sub tree may have node/s in range. 
		if(root.getData()>=low && root.getData()<=high){
			return 1+nodesInRange(root.getLeft(), low, high)+nodesInRange(root.getRight(), low, high);
		}else if(root.getData()<low) // if root's data is less than low then left sub-tree is out of range for sure but right sub-tree may have node/s in range.
			return nodesInRange(root.getRight(), low, high);
		else  // if root's data is greater than high then right sub-tree is out of range for sure but left sub-tree may have node/s in range.
			return nodesInRange(root.getLeft(), low, high);
	}
	/**
	 * Description - O(h+k), Given a Binary Search Tree (BST) of integer values and a range [low, high], return count of nodes where all the nodes under that node (or subtree rooted with that node) lie in the given range.
	 * 
	 * @param root - Root of BST.
	 * @param low - lowest range value.
	 * @param high - highest range value.
	 * @return - Count of subBST.
	 */
	public static boolean subBSTsInRange(Node root, int low, int high, CustIndex ci){
		if(root==null)
			return true;
		
		//If root's data is greater than high then the sub tree we are looking for is lying in left sub-tree.
		if(root.getData() > high){
			//Return true iff sub-tree under this root is subtree in range and also this root's data falls in range
			return subBSTsInRange(root.getLeft(), low, high, ci) && root.getData() >= low && root.getData() <= high ? true : false;
		}else if(root.getData() < low){ //If root's data is lesser than low then the sub tree we are looking for is lying in left sub-tree.
			//Return true iff sub-tree under this root is subtree in range and also this root's data falls in range. If we skip to check the range this count will be incorrect since it starts counting the root also if root is in range.
			return subBSTsInRange(root.getRight(), low, high, ci) && root.getData() >= low && root.getData() <= high? true : false;
		}else{
			boolean lCount = subBSTsInRange(root.getLeft(), low, high, ci);
			boolean rCount = subBSTsInRange(root.getRight(), low, high, ci);

			if (lCount && rCount && root.getData() >= low && root.getData() <= high) {
				ci.index++;
				return true;
			} else{
				return false;
			}
		}
	}
	/**
	 * Description - The function should change old key value to new key value. The function may assume that old key value always exists in Binary Search Tree.
	 * 
	 * @param root - BST root node
	 * @param oldKey - Key to be replaced
	 * @param newKey - Replacing key
	 */
	public static void changeKey(Node root, int oldKey, int newKey){
		// To replace oldKey with newKey we can delete first oldKey and then insert newKey in BST. Wer cann't directly replace oldKey with newKwy since this might disturb the BST property.
		
		root = BinarySearchTree.deleteRec(root, oldKey);
		
		root = BinarySearchTree.insert(root, newKey);
	}
	/**
	 * Description - Print common of two BST in linear time complexity and with limited space complexity O(h1+h2).
	 * 
	 * @param root1
	 * @param root2
	 */
	public static void printCommonInTwoBst(Node root1, Node root2){
		if(root1==null || root2==null){
			System.err.println("Nothing Common !!");
			return;
		}
		boolean first=true, second=true;
		int val1=0, val2=0;
		
		Stack<Node> stkOne = new Stack<Node>(); // Stack one for first tree in-order traversal
		Stack<Node> stkTwo = new Stack<Node>(); // Stack two for second tree in-order traversal
		
		//Run the loop until either of tree's in-order traversal is done.
		while((!stkOne.isEmpty() || root1!=null) && (!stkTwo.isEmpty() || root2!=null)){
			// Find next in-order element from first tree
			if(first){
				while(root1!=null){
					stkOne.push(root1);
					root1=root1.getLeft();
				}
				// Get the top element from stack1 and point root1 to top's right so that we can find next in-order element.
				if(!stkOne.isEmpty()){
					val1 = stkOne.peek().getData();
					root1 = stkOne.pop().getRight();
				}
				
				first = false;
			}
			
			// Find next in-order element from second tree 
			if(second){
				while(root2!=null){
					stkTwo.push(root2);
					root2=root2.getLeft();
				}
				// Get the top element from stack2 and point root2 to top's right so that we can find next in-order element. 
				if(!stkTwo.isEmpty()){
					val2 = stkTwo.peek().getData();
					root2 = stkTwo.pop().getRight();
				}
				
				second = false;
			}
		
			if(val1==val2){
				// Both element are same then print and find next in-order element from both tree by setting first and second boolean variable to check next for equality.
				System.out.print(val1 + " ");
				first=true;
				second=true;
			}else if(val1>val2){
				// find next in-order element from tree2 since it is less than current in-order element of tree1.
				second = true;
			}else{
				// find next in-order element from tree1 since it is less than current in-order element of tree2.
				first = true;
			}
			
		}
	}
	/**
	 * Description - Inversion Count for an array indicates how far (or close) the array is from being sorted. If array is already sorted then inversion count is 0. If array is sorted in reverse order that inversion count is the maximum.
	 * Two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j. For simplicity, we may assume that all elements are unique.
	 * 
	 * @param arr - input array
	 * @return - inversion count
	 */
	public static int inversionInArray(int[] arr) {
		// Using custom class to track the inversion count.
		CustIndex ci = new CustIndex(0, 0);
		Node root = null;
		for (int i : arr) {
			root = inversionInArrayUtil(root, i, ci);// Prepare a binary search tree or AVLTree by inserting each element from array.
			// While performing insertion, add 1 + size of right subtree if array element is less than current root. That means the root and right subtree element will form an inversion with this array element since it is smaller than them.
			// Time complexity will be O(nlogn) if we use AVLTree else with normal BST O(n^2) and space complexity = O(n).
		}
		
		return ci.index;
	}
	private static Node inversionInArrayUtil(Node root, int key, CustIndex ci) {
		if(root == null) {
			return new Node(key);
		}
		
		if(root.getData() > key) {
			// This is the case of inversion since current array element is less than root's key
			ci.index = ci.index + 1 + (root.getRight()!=null?root.getRight().count:0);
			root.setLeft(inversionInArrayUtil(root.getLeft(), key, ci));
		}else {
			//root.setCount(root.getCount()+1);
			root.setRight(inversionInArrayUtil(root.getRight(), key, ci));
		}
		//root.setCount(root.getCount()+1);
		sizeCorrection(root);
		AVLTree.hightCorrection(root); // Update hight of the root
		
		int lHight=root.getLeft()==null?0:root.getLeft().getHight();
		int rHight=root.getRight()==null?0:root.getRight().getHight();
		int balanceFactor = lHight-rHight;
		if(balanceFactor<-1){
			if(key>root.getRight().getData()){
				// RR case
				// 1. Perform Left Rotation at current root node
				// 2. Update current root and it's right child hight after rotation.
				root = AVLTree.rotateLeft(root);
				
				sizeCorrection(root.getLeft());
				sizeCorrection(root);
			}else {
				// RL case
				// 1. Perform right rotation at current root's right child and then
				// 2. Update current root's right child and it's child hight after rotation.
				root.setRight(AVLTree.rotateRight(root.getRight()));
				
				sizeCorrection(root.getRight().getRight());
				sizeCorrection(root.getRight());
				
				// 3. Perform left rotation at current root node
				// 4. Update current root and it's right child hight after rotation.
				root = AVLTree.rotateLeft(root);
				
				sizeCorrection(root.getLeft());
				sizeCorrection(root);
			}
		}else if(balanceFactor>1){
			if(key<root.getLeft().getData()){
				// LL case
				// 1. Perform Right Rotation at current root node
				// 2. Update current root and it's left child hight after rotation.
				root = AVLTree.rotateRight(root);
				
				sizeCorrection(root.getRight());
				sizeCorrection(root);
			}else {
				// LR case
				// 1. Perform Left rotation at current root's left child and then
				// 2. Update current root's left child and it's child hight after rotation.
				root.setLeft(AVLTree.rotateLeft(root.getLeft()));
				
				sizeCorrection(root.getLeft().getLeft());
				sizeCorrection(root.getLeft());
				// 3. Perform right rotation at current root node
				// 4. Update current root and it's left child hight after rotation.
				root = AVLTree.rotateRight(root);
				
				sizeCorrection(root.getRight());
				sizeCorrection(root);
			}
		}
		return root;
	}
	
	private static void sizeCorrection(Node node) {
		int lSize = node.getLeft() == null ? 0 : node.getLeft().getCount();
		int rSize = node.getRight() == null ? 0 : node.getRight().getCount();
		
		node.setCount(1+lSize+rSize);
	}
	
	/**
	 * Description - Given an array of integers, replace every element with the least greater element on its right side in the array. If there are no greater element on right side, replace it with -1.
	 * Ex - Input: [8, 58, 71, 18, 31, 32, 63, 92, 43, 3, 91, 93, 25, 80, 28]
	 *      Output: [18, 63, 80, 25, 32, 43, 80, 93, 80, 25, 93, -1, 28, -1, -1]
	 * @param arr
	 */
	public static void replaceWithLeastLargest(int[] arr) {
		Node root = null;
		CustIndex ci = null; // Custom class to track down in-order successor node.
		/*
		 * The idea is to insert every array element from right to left (i.e. first last element and so on) into BST or AVLTree and keep track of in-order successor with the help of ci
		 * once element is inserted into AVLTree then replace it with in-order successor tracked in ci.ptr. If ci.ptr=null that means there is no element greater than that element in the array on right side then replace it with -1 in the array
		 */
		
		for (int i = arr.length - 1; i >= 0; i--) {
			ci = new CustIndex(0, 0);
			System.out.println(arr[i]);
			root = replaceWithLeastLargestUtil(root, arr[i], ci);
			arr[i] = ci.ptr != null ? ci.ptr.data : -1;
		}
	}
	private static Node replaceWithLeastLargestUtil(Node root, int key, CustIndex ci) {
		if(root == null) {
			return new Node(key);
		}
		
		if(root.getData() > key) {
			ci.ptr = root;
			root.setLeft(replaceWithLeastLargestUtil(root.getLeft(), key, ci));
		}else{
			root.setRight(replaceWithLeastLargestUtil(root.getRight(), key, ci));
		}
		
		AVLTree.hightCorrection(root); // Update hight of the root
		
		int lHight=root.getLeft()==null?0:root.getLeft().getHight();
		int rHight=root.getRight()==null?0:root.getRight().getHight();
		int balanceFactor = lHight-rHight;
		if(balanceFactor<-1){
			if(key>root.getRight().getData()){
				// RR case
				// 1. Perform Left Rotation at current root node
				// 2. Update current root and it's right child hight after rotation.
				root = AVLTree.rotateLeft(root);
			}else {
				// RL case
				// 1. Perform right rotation at current root's right child and then
				// 2. Update current root's right child and it's child hight after rotation.
				root.setRight(AVLTree.rotateRight(root.getRight()));
				
				// 3. Perform left rotation at current root node
				// 4. Update current root and it's right child hight after rotation.
				root = AVLTree.rotateLeft(root);
			}
		}else if(balanceFactor>1){
			if(key<root.getLeft().getData()){
				// LL case
				// 1. Perform Right Rotation at current root node
				// 2. Update current root and it's left child hight after rotation.
				root = AVLTree.rotateRight(root);
			}else {
				// LR case
				// 1. Perform Left rotation at current root's left child and then
				// 2. Update current root's left child and it's child hight after rotation.
				root.setLeft(AVLTree.rotateLeft(root.getLeft()));
			
				// 3. Perform right rotation at current root node
				// 4. Update current root and it's left child hight after rotation.
				root = AVLTree.rotateRight(root);
			}
		}
		
		return root;
	}
	/**
	 * Description - Find pairs with given sum such that pair elements lie in different BSTs
	 * @param root1 - first BST root
	 * @param root2 - second BST root
	 * @param sum - desired sum
	 */
	public static void findPairsFromTwoBST(Node root1, Node root2, int sum) {
		CIndex ci = new CIndex();
		/*
		 * 1. convert BSTs to in-place doubly linked list
		 * 2. traverse one DLL from begining and other from end and check for sum until either of DLL finishes.
		 * 		a) if sum of nodes data == sum then print both nodes data and advance first DLL node forward and second DLL node backward by one step
		 * 		b) if sum of nodes data > sum then advance second DLL node backward by one step
		 * 		c) if sum of nodes data < sum then advance first DLL node forward by one step
		 */
		createDLL4mBST(root1, ci);
		
		CIndex ci2 = new CIndex();
		createDLL4mBST(root2, ci2);	
		
		findPairsFromTwoBSTUtil(ci.ptr, ci2.end, sum);
	}
	private static void findPairsFromTwoBSTUtil(Node ptr1, Node ptr2, int sum) {
		while(ptr1!=null && ptr2!=null) {
			if(ptr1.getData()+ptr2.getData() == sum) {
				System.out.print("("+ptr1.getData()+", "+ptr2.getData()+") ");
				ptr1 = ptr1.getRight();
				ptr2 = ptr2.getLeft();
			}else if(ptr1.getData()+ptr2.getData() > sum) {
				ptr2=ptr2.getLeft();
			}else {
				ptr1 = ptr1.getRight();
			}
		}
				
	}
	/**
	 * Description - Given a binary search tree and a target node K. The task is to find the node with minimum absolute difference with given target value K.
	 * @param root
	 * @param k
	 */
	public static void nodeWithMinAbsDiff(Node root, int k) {
		CustIndex ci = new CustIndex(0, 0);
		/*
		 * 1. Search the key in BST and record the closest smaller and greater in ptr and end pointer of CustIndex class.
		 * 		a) If root data is greater than k then store this root as possible closest greater.
		 * 		b) If root data is smaller than k then store this root as possible closest smaller.
		 * 
		 * 		Time Complexity = O(h) where h = hight of the tree. Hence this can be improved using AVL tree.
		 * 		Space Complexity = O(1)
		 */
		nodeWithMinAbsDiffUtil(root, k, ci);
		if(ci.ptr!=null && ci.ptr.getData() == k) {
			System.out.println(k);
		}else {
			if(ci.ptr == null ) {
				System.out.println(ci.end.getData());
			}else if(ci.end == null){
				System.out.println(ci.ptr.getData());
			}else {
				int less = ci.ptr.getData();
				int more = ci.end.getData();
				
				System.out.println(k-less > more-k ? more : less);
			}
		}
		
	}
	private static void nodeWithMinAbsDiffUtil(Node root, int k, CustIndex ci) {
		if(root == null) {
			return;
		}
		
		if(root.getData() == k) {
			ci.ptr=root;
			return;
		}
		else if(root.getData() > k) {
			ci.end=root;
			nodeWithMinAbsDiffUtil(root.getLeft(), k, ci);
		}else {
			ci.ptr = root;
			nodeWithMinAbsDiffUtil(root.getRight(), k, ci);
		}
		
	}
	/**
	 * Description - Given Binary Search Tree. The task is to find sum of all elements smaller than and equal to Kth smallest element.
	 * @param root - BST root
	 * @param k
	 * @return - Sum of k smallest element.
	 */
	public static int sumOfKSmallest(Node root, int k) {
		if(root == null)
			return 0;
		// With the help of custom class we can find out sum of k smallest in min member variable of ci.
		CustIndex ci = new CustIndex(0, 0);
		ci.index = k;
		sumOfKSmallestUtil(root, ci);
		return ci.index == 0 ? ci.min : 0;
	}
	private static void sumOfKSmallestUtil(Node root, CustIndex ci) {
		if(root == null || ci.index == 0)
			return;
		
		sumOfKSmallestUtil(root.getLeft(), ci);
		if (ci.index != 0) {
			ci.index--;
			ci.min = ci.min + root.getData();
		}
		sumOfKSmallestUtil(root.getRight(), ci);
	}
	/**
	 * Description - Maximum element between two nodes of BST
	 * @param root - BST root
	 * @param x - starting range
	 * @param y - ending range
	 */
	public static void maxBwTwoNodes(Node root, int x, int y) {
		/*
		 * 1. Find the ancestor of this two nodes
		 * 2. From ancestor, find greatest toward bigger of the value.
		 */
		Node ancestor = findAncestor(root, x, y);
		CustIndex ci = new CustIndex(0, 0);
		maxBwTwoNodesUtil(ancestor, y, ci);
		System.out.println("Max element between "+ci.ptr.getData());
	}
	private static void maxBwTwoNodesUtil(Node ancestor, int y, CustIndex ci) {
		if(ancestor == null)
			return;
		if(ancestor.getData() == y) {
			if(ci.ptr == null) {
				ci.ptr = ancestor;
			}
			
			return;
		}else if(ancestor.getData() > y) {
			ci.ptr = ancestor;
			maxBwTwoNodesUtil(ancestor.getLeft(), y, ci);
		}else {
			maxBwTwoNodesUtil(ancestor.getRight(), y, ci);
		}
	}
	private static Node findAncestor(Node root, int x, int y) {
		if(root == null) {
			return null;
		}
		
		if(x<=root.getData() && root.getData()<=y)
			return root;
		else if(x>root.getData())
			return findAncestor(root.getRight(), x, y);
		else
			return findAncestor(root.getLeft(), x, y);
	}
	/**
	 * Description - Given a Preorder traversal of a Binary Search Tree. The task is to print leaf nodes of the Binary Search Tree.
	 * With 
	 * @param preorder
	 */
	public static void printLeafsFromPre(int[] preorder) {
		Stack<Integer> stk = new Stack<Integer>();
		for (int i = 0; i < preorder.length; i++) {
			/*
			 * Push to array element into stack until stack's top element is greater than processing array element. i.e maintain sorted descending stack.
			 */
			while(stk.isEmpty() || stk.peek()>preorder[i]) {
				stk.push(preorder[i++]);
			}
			/*
			 * Pop the stack until we found top element is greater than current array element. The Idea is if we are able to pop stack atleast twice then first popped element will always be leaf.
			 * i.e if current processing preorder array element is greater than stack's two top element then it can be safely said that current element will be right child of stack 2nd top element not the top element
			 * Hence top stack element is a leaf.
			 */
			int popCount=0;
			int topElement=-1;// Assuming all element are natural numbers i.e >=0
			while(!stk.isEmpty() && stk.peek()<preorder[i]) {
				if(topElement == -1) {
					topElement = stk.pop(); // Get the first poped element.
				}else {
					stk.pop();
				}
				popCount++;
			}
			if(popCount >= 2) {
				System.out.print(topElement + " ");
			}
			stk.push(preorder[i]);
		}
		
		System.out.println(stk.peek()); // Last element of preorder array is always going to be leaf.
	}
	public static int medianOfBST(Node root){
		int countOfNodes = countNodes(root);
		Node curr=root, pre=null, prev=null;
		int medCount=0;
		while(curr!=null){
			if(curr.getLeft() == null){
				// Count node if its left is NULL
				medCount++;
				// Move to its right
				
				if(countOfNodes%2!=0 && medCount == (countOfNodes+1)/2 ){
					System.out.println("First Place");
					return curr.getData();
				}
					
				else if(countOfNodes%2==0 && medCount == (countOfNodes/2)+1){
					return (prev.getData()+curr.getData())/2;
				}
				prev=curr;
				curr=curr.getRight();
			}else{
				/* Find the inorder predecessor of current */
				pre = curr.getLeft();
				
				while(pre.getRight()!=null && pre.getRight()!=curr)
					pre=pre.getRight();
				/* Make current as right child of its
	               inorder predecessor */
				if(pre.getRight()==null){
					pre.setRight(curr);
					curr = curr.getLeft();
				}else{
					/* Revert the changes made in if part to
		               restore the original tree i.e., fix
		               the right child of predecssor */
					
					pre.setRight(null);
					// Increment count if the current
	                // node is to be visited
					medCount++;

					if(countOfNodes%2!=0 && medCount == (countOfNodes+1)/2 ){
						System.out.println("First Place");
						return curr.getData();
					}
						
					else if(countOfNodes%2==0 && medCount == (countOfNodes/2)+1){
						return (prev.getData()+curr.getData())/2;
					}
					
					prev=curr;
					curr = curr.getRight();
					
				}
			}
		}
		return medCount;// This is included to just overcome compiler issue. I bet, this statement will never be executed if tree is not null
	}
	/* Function to count nodes in a  binary search tree
	   using Morris Inorder traversal*/
	private static int countNodes(Node root) {
		Node curr, pre;
		int count=0;
		
		if(root==null)
			return count;
		
		curr=root;
		
		while(curr!=null){
			if(curr.getLeft() == null){
				// Count node if its left is NULL
				count++;
				// Move to its right
				curr=curr.getRight();
			}else{
				/* Find the inorder predecessor of current */
				pre = curr.getLeft();
				
				while(pre.getRight()!=null && pre.getRight()!=curr)
					pre=pre.getRight();
				/* Make current as right child of its
	               inorder predecessor */
				if(pre.getRight()==null){
					pre.setRight(curr);
					curr = curr.getLeft();
				}else{
					/* Revert the changes made in if part to
		               restore the original tree i.e., fix
		               the right child of predecssor */
					
					pre.setRight(null);
					// Increment count if the current
	                // node is to be visited
					count++;
					curr = curr.getRight();
				}
			}
		}
		
		return count;
	}
	/**
	 * Description - Remove all leaf nodes from the binary search tree.
	 * @param root
	 * @return
	 */
	public static Node removeLeafsFromBST(Node root){
		/*
		 * Do preorder traversal of the BST and delete the leaf node. Why pre-order?, If we choose any other depth first traversal than preorder then we might end up deleting a node which was not leaf but after deletion of its leaf it got transformed into leaf.
		 * So, to avoid that process root first and then it's child.
		 */
		if(root == null || (root.getLeft()==null && root.getRight()==null)){
			return null;
		}
		
		root.setLeft(removeLeafsFromBST(root.getLeft()));
		root.setRight(removeLeafsFromBST(root.getRight()));
		
		return root;
	}
	/**
	 * Description - Given a Binary Search Tree and two keys in it. Find the distance between two nodes with given two keys. It may be assumed that both keys exist in BST.
	 * @param root
	 * @param a
	 * @param b
	 * @return
	 */
	public static int pathBwTwoNodes(Node root, int a, int b){
		Node lca=findAncestor(root, a, b); //find lowest common ancestor of a and b.
		int a_lca_dist=distanceUtil(lca, a);
		int b_lca_dist=distanceUtil(lca, b);
		
		return a_lca_dist+b_lca_dist;
	}
	private static int distanceUtil(Node root, int a) {
		if(root.getData() == a)
			return 0;

		if(root.getData()>a){
			return 1+distanceUtil(root.getLeft(), a);
		}else{
			return 1+distanceUtil(root.getRight(), a);
		}
		
	}
	/**
	 * Description - Minimum Possible value of |ai + aj – k| for given array and k.
	 * You are given an array of n integer and an integer K. Find the number of total unordered pairs {i, j} such that absolute value of (ai + aj – K), i.e., |ai + aj – k| is minimal possible where i != j.
	 * @param inArr
	 * @param k
	 */
	public static void pairsWithMinValue(int[] inArr, int k){
		Node root=null;
		
		for (int i = 0; i < inArr.length; i++) {
			root = AVLTree.avlInsertWithDuplicate(root, inArr[i]);
		}
		
		CIndex ci =new CIndex();
		createDLL4mBST(root, ci);
		
		Map<Integer, Integer> minValuePairMap= new TreeMap<Integer, Integer>();
		pairsWithMinValueUtil(ci.ptr, ci.end, k, minValuePairMap);
		
		for (Entry<Integer, Integer> entry : minValuePairMap.entrySet()) {
			System.out.println("Minimum possible value of |ai+aj-k| is  :: "+entry.getKey());
			System.out.println("Pairs of minimum possible value  :: "+entry.getValue());
			return;
		}
	}
	private static void pairsWithMinValueUtil(Node ptr, Node end, int k,
			Map<Integer, Integer> minValuePairMap) {
		while (ptr != null && end != null && ptr.getData() < end.getData()) {
			if (ptr.getData() + end.getData() == k) {
				Integer existing = minValuePairMap.get(0);
				minValuePairMap.put(0, existing != null ? existing.intValue() + (ptr.getCount() * end.getCount()) : (ptr.getCount() * end.getCount()));
			
				ptr=ptr.getRight();
				end=end.getLeft();
			}else if(ptr.getData() + end.getData() > k){
				int key = ptr.getData() + end.getData() - k ;
				Integer existing = minValuePairMap.get(key);
				minValuePairMap.put(key, existing != null ? existing.intValue() + (ptr.getCount() * end.getCount()) : (ptr.getCount() * end.getCount()));
				end=end.getLeft();
			}else{
				int key = k - ptr.getData() + end.getData();
				Integer existing = minValuePairMap.get(key);
				minValuePairMap.put(key, existing != null ? existing.intValue() + (ptr.getCount() * end.getCount()) : (ptr.getCount() * end.getCount()));
				ptr=ptr.getRight();
			}
		}
		
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
		
		/*Node root = new Node(100);
		root.left = new Node(50);
		root.right = new Node(300);
		root.left.left = new Node(20);
		root.left.right = new Node(70);
		Node root1 = new Node(80);  
		root1.left = new Node(40);
		root1.right = new Node(120);
		//System.out.println(mergeTwoBalancedBST(root, root1, 8));
		
		Node iRoot = new Node(100);
		iRoot.left = new Node(20); //new Node(50);
		iRoot.right = new Node(300);
		iRoot.left.left = new Node(50); //new Node(20);
		iRoot.left.right = new Node(70);
		correctBST(iRoot);
		
		System.out.println(iRoot.getData());*/
		
		/*Node root = new Node(8);
		root.left = new Node(4);
		root.right = new Node(12);
		root.left.left = new Node(2);
		root.left.right = new Node(6);
		root.right.left = new Node(10);
		root.right.right = new Node(14);
		
		for(int i=0; i<=15; i++) {
			System.out.println("Ceiling of "+i+" :: "+ceil(root, i));
		}
		for(int i=0; i<=15; i++) {
			System.err.println("Floor of "+i+" :: "+floor(root, i));
		}*/
		
		/*Node root = new Node(100);
		root.left = new Node(50);
		root.right = new Node(300);
		root.left.left = new Node(20);
		root.left.right = new Node(70);
		Node root1 = new Node(80);  
		root1.left = new Node(40);
		root1.right = new Node(120);
		//System.out.println(mergeTwoBalancedBST(root, root1, 8));
		mergeAndPrint(root, root1);*/
		
		/*Node root = new Node(6);
		root.left = new Node(-13);
		root.right = new Node(14);
		root.left.right = new Node(-12);
		
		root.right.left=new Node(13);
		root.right.right=new Node(15);
		root.right.left.left=new Node(7);
		
		//printTripletOfZero(root);
		//findPair4SumK(root, 20);
		//Node rangeRoot=removeOutOfRange(root, -10, 8);
		//System.out.println(rangeRoot.getData());
		
		System.out.println(nodesInRange(root, -13, 7));
		System.out.println(nodesInRange(root, -11, 7));
		System.out.println(nodesInRange(root, -11, 8));
		System.out.println(nodesInRange(root, -1, 5));
		System.out.println(nodesInRange(root, -1, 6));
		System.out.println(nodesInRange(root, 7, 14));
		
		Node jobResRoot=reserveJobAt(null, 15, 4);
		jobResRoot=reserveJobAt(jobResRoot, 7, 4);
		jobResRoot=reserveJobAt(jobResRoot, 20, 4);
		jobResRoot=reserveJobAt(jobResRoot, 3, 4);
		
		jobResRoot=reserveJobAt(jobResRoot, 30, 4);
		jobResRoot=reserveJobAt(jobResRoot, 17, 4);
		jobResRoot=reserveJobAt(jobResRoot, 35, 4);
		jobResRoot=reserveJobAt(jobResRoot, 45, 4);
		
		System.out.println(jobResRoot.getData());
		
		CustIndex ci=new CustIndex(0, 0);
		subBSTsInRange(root, -13, 7, ci);
		System.out.println(ci.index);
		ci=new CustIndex(0, 0);
		subBSTsInRange(root, -11, 7, ci);
		System.out.println(ci.index);
		System.out.println(ci.index);
		ci=new CustIndex(0, 0);
		subBSTsInRange(root, -1, 5, ci);
		System.out.println(ci.index);
		ci=new CustIndex(0, 0);
		subBSTsInRange(root, -1, 6, ci);
		System.out.println(ci.index);
		ci=new CustIndex(0, 0);
		subBSTsInRange(root, 7, 14, ci);
		System.out.println(ci.index);*/
		
		/*Node root = new Node(5);
		//root.left = new Node(1);
		root.right = new Node(10);
		//root.left.left = new Node(0);
		//root.left.right = new Node(4);
		root.right.left=new Node(7);
		root.right.left.right=new Node(9);
		
		Node root2 = new Node(10);
		root2.left = new Node(7);
		root2.right = new Node(20);
		root2.left.left = new Node(4);
		root2.left.right = new Node(9);
		
		printCommonInTwoBst(root, root2);*/
		
		/*int[] arr = {1, 2, 4, 8};//{12,15,24,10,30,5};//{8, 4, 2, 1};
		System.out.println(inversionInArray(arr));
		
		int[] arrLG = {8, 58, 71, 18, 31, 32, 63, 92, 43, 3, 91, 93, 25, 80, 28};
		replaceWithLeastLargest(arrLG);
		System.out.println(arrLG);*/
		
		/*Node root1 = BinarySearchTree.insert(null, 8);
	    root1 = BinarySearchTree.insert(root1, 10);
	    root1 = BinarySearchTree.insert(root1, 3);
	    root1 = BinarySearchTree.insert(root1, 6);
	    root1 = BinarySearchTree.insert(root1, 1);
	    root1 = BinarySearchTree.insert(root1, 5);
	    root1 = BinarySearchTree.insert(root1, 7);
	    root1 = BinarySearchTree.insert(root1, 14);
	    root1 = BinarySearchTree.insert(root1, 13);
	    
	    Node root2 = BinarySearchTree.insert(null, 5);
	    root2 = BinarySearchTree.insert(root2, 18);
	    root2 = BinarySearchTree.insert(root2, 2);
	    root2 = BinarySearchTree.insert(root2, 1);
	    root2 = BinarySearchTree.insert(root2, 3);
	    root2 = BinarySearchTree.insert(root2, 4);
	    
	    findPairsFromTwoBST(root1, root2, 11);*/
		
		/*Node root1 = BinarySearchTree.insert(null, 9);
	    root1 = BinarySearchTree.insert(root1, 4);
	    root1 = BinarySearchTree.insert(root1, 17);
	    root1 = BinarySearchTree.insert(root1, 1);
	    root1 = BinarySearchTree.insert(root1, 6);
	    root1 = BinarySearchTree.insert(root1, 22);
	    root1 = BinarySearchTree.insert(root1, 5);
	    root1 = BinarySearchTree.insert(root1, 7);
	    root1 = BinarySearchTree.insert(root1, 20);
	    
	    nodeWithMinAbsDiff(root1, 4);
	    nodeWithMinAbsDiff(root1, 18);
	    nodeWithMinAbsDiff(root1, 12);
	    nodeWithMinAbsDiff(root1, 19);
	    nodeWithMinAbsDiff(root1, 2);
	    
	    nodeWithMinAbsDiff(root1, 0);
	    nodeWithMinAbsDiff(root1, 25);*/
		
		/*Node root = BinarySearchTree.insert(null, 20);
	    root = BinarySearchTree.insert(root, 8);
	    root = BinarySearchTree.insert(root, 4);
	    root = BinarySearchTree.insert(root, 12);
	    root = BinarySearchTree.insert(root, 10);
	    root = BinarySearchTree.insert(root, 14);
	    root = BinarySearchTree.insert(root, 22);
		
	    System.out.println(sumOfKSmallest(root, 3));*/
		
		/*int arr[] = { 18, 36, 9, 6, 12, 10, 1, 8 };
		Node root = null;
		for (int i = 0; i < arr.length; i++) {
			root = BinarySearchTree.insert(root, arr[i]);
		}
		maxBwTwoNodes(root, 1, 10);*/
		
		/*int[] preArr = {890, 325, 290, 100, 300, 530, 400, 600, 965, 900, 1000};//{890, 325, 290, 625, 965};// {890, 325, 290, 965};// {890, 325, 290, 100, 300, 965, 900, 1000};
		printLeafsFromPre(preArr);*/
		
		/*Node root = BinarySearchTree.insert(null, 50);
		root = BinarySearchTree.insert(root, 30);
		root = BinarySearchTree.insert(root, 20);
		root = BinarySearchTree.insert(root, 40);
		root = BinarySearchTree.insert(root, 70);
		root = BinarySearchTree.insert(root, 60);
		root = BinarySearchTree.insert(root, 80);
		//root = BinarySearchTree.insert(root, 75);
		
		System.out.println(medianOfBST(root));*/
		
		/*Node root = BinarySearchTree.insert(null, 5);
		root = BinarySearchTree.insert(root, 2);
		root = BinarySearchTree.insert(root, 12);
		root = BinarySearchTree.insert(root, 1);
		root = BinarySearchTree.insert(root, 3);
		root = BinarySearchTree.insert(root, 9);
		root = BinarySearchTree.insert(root, 21);
		
		root = BinarySearchTree.insert(root, 19);
		root = BinarySearchTree.insert(root, 25);
		
		System.out.println(pathBwTwoNodes(root, 3, 9));
		System.out.println(pathBwTwoNodes(root, 9, 25));
		System.out.println(pathBwTwoNodes(root, 5, 19));*/
		
		int[] arr = {0, 4, 6, 2, 4};
		pairsWithMinValue(arr, 7);
	}

}

class CustIndex{
	Node ptr=null, end=null;
	int index=0,max=0,min=0;
	boolean bst=true;
	public CustIndex(int max, int min) {
		super();
		this.max = max;
		this.min = min;
	}
}