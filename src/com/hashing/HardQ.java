package com.hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class HardQ {
	/**
	 * Description - Clone a Binary Tree with Random Pointers. HahMap Based Solution.
	 * 				 The random pointer points to any random node of the binary tree and can even point to NULL, clone the given binary tree.
	 * @param org_root
	 * @return
	 */
	public static NodeR cloneBTwithRandomNode(NodeR org_root) {
		NodeR cloned_root = null;
		
		Map<NodeR, NodeR> hashMap = new HashMap<>();
		cloneNodesInMap(org_root, hashMap);
		
		cloned_root = cloneTree(org_root, hashMap);
		
		return cloned_root;
	}
	private static NodeR cloneTree(NodeR org_root, Map<NodeR, NodeR> hashMap) {
		if(org_root == null)
			return null;
		
		NodeR cloned = hashMap.get(org_root);
		cloned.setLeft(cloneTree(org_root.getLeft(), hashMap));
		cloned.setRight(cloneTree(org_root.getRight(), hashMap));
		
		cloned.setRandom(hashMap.get(org_root.getRandom()));
		
		return cloned;
	}
	private static void cloneNodesInMap(NodeR org_root, Map<NodeR, NodeR> hashMap) {
		if(org_root == null)
			return;
		
		hashMap.put(org_root, new NodeR(org_root.getData()));
		
		cloneNodesInMap(org_root.getLeft(), hashMap);
		cloneNodesInMap(org_root.getRight(), hashMap);
	}
	/**
	 * Description - Given an array containing only 0s and 1s, find the largest subarray which contain equal no of 0s and 1s. Expected time complexity is O(n).
	 * 				Input: arr[] = {1, 0, 1, 1, 1, 0, 0}
	 * 				Output: 1 to 6 (Starting and Ending indexes of output subarray)
	 * @param arr
	 */
	public static void largestSubArr0and1(int arr[]) {
		int zero_count=0, one_count=0, start=0, end=arr.length-1;
		
		for (int i = 0; i < arr.length; i++) {
			if(arr[i]==0)
				zero_count++;
			else
				one_count++;
		}
		if(zero_count==0 || one_count==0) {
			System.out.println("Subarray not possible -- only 1 or 0 present");
			return;
		}
		while(zero_count!=one_count && start<end) {
			if(zero_count>one_count) {
				if(arr[end] == 0) {
					end--;
					zero_count--;
				}else if(arr[start] == 0) {
					start++;
					zero_count--;
				}else {
					one_count--;
					end--;
				}
			}else {
				if(arr[end] == 1) {
					end--;
					one_count--;
				}else if(arr[start] == 1) {
					start++;
					one_count--;
				}else {
					zero_count--;
					end--;
				}
			}
		}
		System.out.println("0 and 1 containing largest sub array starts at "+start+" and ends at "+end);
	}
	
	public static void largestSubArr0and1Tricky(int[] arr) {
		// Replace 0 with -1 and find largest sub array whose sum is zero.
		for (int i = 0; i < arr.length; i++) {
			if(arr[i]==0)
				arr[i] = -1;
		}
		
		HashMap<Integer, Integer> sumMap = new HashMap<>();
		int largest=0, sum=0,num=0;
		
		for (int i = 0; i < arr.length; i++) {
			sum+=arr[i];

			if(!sumMap.containsKey(sum))
				sumMap.put(sum, i);
			else {
				if(largest<i-sumMap.get(sum)) {
					largest=i;
					num=sum;
				}
			}
			
		}
		
		for (int i = 0; i < arr.length; i++) {
			if(arr[i]==-1)
				arr[i] = 0;
		}
		if(sumMap.get(num)!=null)
			System.out.println("Range :: ("+(sumMap.get(num)+1)+", "+(largest)+")");
		else {
			System.out.println("Invalid Input !");
		}
	}
	/**
	 * Description - Find smallest range containing elements from k lists. Given k sorted lists of integers of size n each, find the smallest range that includes at least element from each of the k lists. If more than one smallest ranges are found, print any one of them.
	 * @param twoDArr
	 * @param k
	 * @param n
	 */
	public static void smallestRange(int[][] twoDArr, int k, int n) {
		HeapElement[] heap = new HeapElement[k];
		int range=Integer.MAX_VALUE;
		int min=Integer.MAX_VALUE;
		int max=Integer.MIN_VALUE;
		
		int start = 0,end=0;
		
		for (int i = 0; i < k; i++) {
			heap[i] = new HeapElement(twoDArr[i][0]);
			heap[i].col=1;
			heap[i].row=i;
			
			if(max<twoDArr[i][0]) {
				max = twoDArr[i][0];
			}
		}
		
		for (int i = 0; i < (k-1)/2; i++) {
			minHeapify(heap, i, k);
		}
		
		while(true) {
			HeapElement tempMin = heap[0];
			min=tempMin.data;
			
			if(range > max-min+1) {
				range=max-min+1;
				start=min;
				end=max;
			}
			
			if(tempMin.col<n) {
				heap[0] = new HeapElement(twoDArr[tempMin.row][tempMin.col]);
				heap[0].row = tempMin.row;
				heap[0].col = tempMin.col+1;
				
				if(max<heap[0].data) {
					max=heap[0].data;
				}
			}else {

				break;
			}
			
			minHeapify(heap, 0, k);
		}
		
		System.out.println("Smallest range :: ("+start+","+end+")");
	}
	private static void minHeapify(HeapElement[] elements, int i, int size) {
		int l=2*i+1;
		int r=2*i+2;
		
		int smallest=i;
		
		if(l<size && elements[l].data<elements[smallest].data) {
			smallest = l;
		}
		
		if(r<size && elements[r].data<elements[smallest].data) {
			smallest = r;
		}
		
		if(i!=smallest) {
			HeapElement temp = elements[i];
			elements[i] = elements[smallest];
			elements[smallest] = temp;
			
			minHeapify(elements, smallest,size);
		}
	}
	/**
	 * Description - Given an array, the task is to calculate the sum of lengths of contiguous subarrays having all elements distinct.
	 * Input :  arr[] = {1, 2, 3}
	 * Output : 10
	 * {1, 2, 3} is a subarray of length 3 with distinct elements. Total length of length three = 3.
	 * {1, 2}, {2, 3} are 2 subarray of length 2 with distinct elements. Total length of lengths two = 2 + 2 = 4
	 * {1}, {2}, {3} are 3 subarrays of length 1 with distinct element. Total lengths of length one = 1 + 1 + 1 = 3
	 * Sum of lengths = 3 + 4 + 3 = 10
	 * 
	 * Input :  arr[] = {1, 2, 1}  Output : 7
	 * Input :  arr[] = {1, 2, 3, 4} Output : 20
	 * @param arr
	 * @return
	 */
	public static int distinctAllSubArrSum(int[] arr) {
		int sum=0;
		int j=0;
		
		HashSet<Integer> arrSet = new HashSet<>();
		
		for (int i = 0; i < arr.length; i++) {
			
			while(j<arr.length && !arrSet.contains(arr[j])) {
				arrSet.add(arr[j]);
				j++;
			}
			
			sum+=(j-i)*(j-i+1)/2; // Formulla copied from post. Still not able to understand.
			
			arrSet.remove(arr[i]);
		}
		
		return sum;
	}
	public static void main(String[] args) {
		/*NodeR root = new NodeR(1);
		root.left = new NodeR(2);
		root.right = new NodeR(3);
		
		root.left.left = new NodeR(4);
		root.left.right =  new NodeR(5);
		
		root.random = root.left.right;
		
		root.left.left.random = root;
		root.left.right.random = root.right;
		
		NodeR clonedRoot = cloneBTwithRandomNode(root);
		System.out.println(clonedRoot.random.data);*/
		
		/*int[] arr =  {1, 1, 1, 1};//{1, 1, 1, 1};//{1, 0, 1, 1, 1, 0, 0};//{1, 0, 0, 1, 0, 1, 1};
		largestSubArr0and1(arr);
		largestSubArr0and1Tricky(arr);*/
		
		/*int[][] twoDArr = {{4, 7, 9, 12, 15},
    	   		{0, 8, 10, 14, 20},
    	   		{6, 12, 16, 30, 50}};;
                	   		{{4, 7, 9, 13, 15},
				{0, 8, 10, 14, 20},
				{5, 12, 16, 30, 50}};
		
		smallestRange(twoDArr, 3, 5);*/
		
		int[] disArr = {1,2,3,4};//{1,2,1};
		
		System.out.println(distinctAllSubArrSum(disArr));
	}

}
