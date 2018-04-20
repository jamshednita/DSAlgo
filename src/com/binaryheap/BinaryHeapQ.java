package com.binaryheap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.print.attribute.standard.RequestingUserName;

import com.binarytree.Node;

public class BinaryHeapQ {
	
	public static int kthLargest(int[] arr, int k, int size) {
		// Step 1 = Build min heap of first k element from the arr.
		for(int i=k/2-1; i>=0; i--) {
			minHeapify(arr, k-1, i);
		}
		// Step 2 = compare root of binary heap with every element from i=k to size-1 and If root<remaining element then swap and call heapify.
		
		for(int j=k ; j<size; j++) {
			if(arr[0] < arr[j]) {
				arr[0] = arr[j];
				minHeapify(arr, k-1, 0);
			}
		}
		
		return arr[0];
	}

	private static void minHeapify(int[] arr, int n, int i) {
		
		int smallest = i;
		int l = 2*i+1;
		int r = 2*i+2;
		
		if(l<=n && arr[l] < arr[smallest])
			smallest = l;
		
		if(r<=n && arr[r] < arr[smallest])
			smallest = r;
		
		if(smallest != i) {
			int temp = arr[i];
			arr[i] = arr[smallest];
			arr[smallest] = temp;
			
			minHeapify(arr, n, smallest);
		}
	}
	
	public static int kthSmallest(int[] arr, int k, int size) {
		// Step 1 = Build min heap of first k element from the arr.
		for(int i=k/2-1; i>=0; i--) {
			maxHeapify(arr, k-1, i);
		}
		// Step 2 = compare root of binary heap with every element from i=k to size-1 and If root<remaining element then swap and call heapify.
		
		for(int j=k ; j<size; j++) {
			if(arr[0] > arr[j]) {
				arr[0] = arr[j];
				maxHeapify(arr, k-1, 0);
			}
		}
		
		return arr[0];
	}

	private static void maxHeapify(int[] arr, int n, int i) {
		
		int largest = i;
		int l = 2*i+1;
		int r = 2*i+2;
		
		if(l<=n && arr[l] > arr[largest])
			largest = l;
		
		if(r<=n && arr[r] > arr[largest])
			largest = r;
		
		if(largest != i) {
			int temp = arr[i];
			arr[i] = arr[largest];
			arr[largest] = temp;
			
			minHeapify(arr, n, largest);
		}
	}
	/**
	 * Description - Given an array of n elements, where each element is at most k away from its target position, devise an algorithm that sorts in O(n log k) time. 
	 * For example, let us consider k is 2, an element at index 7 in the sorted array, can be at indexes 5, 6, 7, 8, 9 in the given array.
	 * 
	 * @param arr - input array to be sorted.
	 * @param size
	 * @param k - a hint to how far element is from it's actual position.
	 */
	public static void sortK(int[] arr, int size, int k) {
		int[] heapArr = new int[k+1]; // Create a new min-heap of size k+1.
		
		for(int i=0; i<=k && i<size; i++) {
			heapArr[i]=arr[i];
		}
		// minHeapify the newly created heap
		for(int j=(k+1)/2-1; j<=k;j++) {
			minHeapify(heapArr, k, j);
		}
		// Root of heap is minimum thus start filling the arr from 0 to k by removing top element from heapArr and replace the top with next element from arr.
		// Since every element is atmost k farthest from its position hence the current top heap element is the minimum to be inserted into the position pointed by y in following loop.
		for(int x=k+1, y=0; y<size; x++,y++) {
			if(x<size) {
				arr[y] = heapArr[0];
				heapArr[0] = arr[x];
			
				minHeapify(heapArr, k, 0);
			}else {
				// once x is beyond size means (n-k-1) element is in it's right place. Place remaining element by extracting top element from heap and place it into arr.
				arr[y] = heapArr[0];
				heapArr[0] = heapArr[k];
				
				minHeapify(heapArr, --k, 0);
			}
		}
	}
	/**
	 * Description - Given a binary tree we need to check it has heap property or not, Binary tree need to fulfill following two conditions for being a heap –
	 *               1. It should be a complete tree (i.e. all levels except last should be full).
	 *               2. Every node’s value should be lesser than or equal to its child node (considering min-heap).
	 * @param root
	 * @return
	 */
	public static boolean isHeap(Node root) {
		List<Node> levelNodes = new ArrayList<>();
		levelNodes.add(root);
		
		boolean isHeap = true;
		
		//Step 1- find out the first node which is leaf or have one left child
		while(!levelNodes.isEmpty() && isHeap) {
			Node node = levelNodes.remove(0);
			
			Node left= node.getLeft();
			Node right= node.getRight();
			
			if(left!=null && right!=null) {
				
				if(node.getData()<left.getData() || node.getData()<right.getData()) // Min Heap Check
					return false;
				
				levelNodes.add(left);
				levelNodes.add(right);
			}else if(left!=null) {
				isHeap=false;
				
				if(node.getData()<left.getData()) // Min Heap Check
					return false;
				
				levelNodes.add(left);
			}else if(right!=null) {
				return false;
			}else {
				isHeap=false;
			}
		}
		//Step 2- Afterwards if any element in the list has child means binary tree is not Heap(CBT + Min Heap)
		while (!levelNodes.isEmpty()) {
			Node temp = levelNodes.remove(0);
			
			if(temp.getLeft()!=null || temp.getRight()!=null)
				return false;
		}
		
		return true;
	}
	/**
	 * Description - Given an array, how to check if the given array represents a Binary Max-Heap.
	 * @param arr
	 * @param n
	 * @return
	 */
	public static boolean isArrayAMaxHeap(int[] arr, int n) {
		for(int i=n/2-1;i>=0;i--) {
			int l=2*i+1;
			int r=2*i+2;
			
			if(l<n && arr[i] < arr[l]) {
				return false;
			}
			
			if(r<n && arr[i] < arr[r]) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		/*int arr[] = {1, 23, 12, 9, 30, 2, 50};
		
		//System.out.println(kthLargest(arr, 3, arr.length));
		System.out.println(kthSmallest(arr, 3, arr.length));*/
		
		int k = 3;
	    int inArr[] = {2, 6, 3, 12, 56, 8};
	    sortK(inArr, inArr.length, k);
	    
	    Arrays.stream(inArr).forEach(System.out::println);
	    
	    Node root = new Node(10);
	    root.setLeft(new Node(9));
	    root.setRight(new Node(8));
	    
	    root.getLeft().setLeft(new Node(7));
	    root.getLeft().setRight(new Node(6));
	    
	    root.getRight().setLeft(new Node(5));
	    root.getRight().setRight(new Node(4));
	    
	    System.out.println("Is Heap == "+isHeap(root));
	    
	    int arr[] = {90, 15, 10, 7, 12, 2};//{90, 15, 10, 7, 12, 200};
	    System.out.println("Is max Heap == "+isArrayAMaxHeap(arr, arr.length));
	}

}
