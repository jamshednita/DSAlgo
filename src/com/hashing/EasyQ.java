package com.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.binarytree.Node;

public class EasyQ {
	/**
	 * Description - Print a Binary Tree in Vertical Order | Set 2 (Map based Method)
	 * @param root
	 */
	public static void printBinaryTreeVirtical(Node root) {
		Map<Integer, LinkedList<Integer>> verMap= new HashMap<>();
		getVerticalOrder(root, verMap, 0);
		
		verMap.forEach((k,v)->{System.out.println(v);});
	}
	private static void getVerticalOrder(Node node, Map<Integer, LinkedList<Integer>> verticalMap, int hd) {
		if(node==null)
			return;
		
		LinkedList<Integer> list=verticalMap.get(hd);
		if(list==null) {
			list=new LinkedList<>();
			list.add(node.getData());
			
			verticalMap.put(hd, list);
		}else
			list.add(node.getData());
		
		getVerticalOrder(node.getLeft(), verticalMap, hd-1);
		getVerticalOrder(node.getRight(), verticalMap, hd+1);
		
	}
	/**
	 * Description (Hash Table Based Implementation, time complexity- O(n) where n = size of arr)- 
	 * 				 1) Create a Hash Table (Any hash based data structure like HashSet or HashMap) for all the elements of arr1[].
	 * 				 2) Traverse arr2[] and search for each element of arr2[] in the Hash Table. If element is not found then return 0.
	 *               3) If all elements are found then return true.
	 * @param arr
	 * @param subset
	 * @return
	 */
	public static boolean isSubset(int[] arr, int[] subset) {
		HashSet<Integer> hashTable = new HashSet<>();
		
		Arrays.stream(arr).forEach(ele->{hashTable.add(ele);});
		
		for (int i = 0; i < subset.length; i++) {
			if(!hashTable.contains(subset[i]))
				return false;
		}
		
		return true;
	}
	/**
	 * Description - Union and Intersection of two Linked Lists OR arrays
	 * @param arr1
	 * @param arr2
	 */
	public static void unionAndIntersect(int[] arr1, int[] arr2) {
		List<Integer> union = new ArrayList<>();
		List<Integer> intersection = new ArrayList<>();
		
		HashSet<Integer> hash= new HashSet<>();
		
		Arrays.stream(arr1).forEach(e1->{
			hash.add(e1);
			union.add(e1);
			});
		
		for (int i = 0; i < arr2.length; i++) {
			if(hash.contains(arr2[i]))
			{
				intersection.add(arr2[i]);
			}else {
				union.add(arr2[i]);
			}
		}
		
		union.forEach(System.out::println);
		intersection.forEach(System.out::println);
	}
	/**
	 * Description (Time complexity = O(n))- Given an array A[] and a number x, check for pair in A[] with sum as x.
	 * @param arr
	 * @param k
	 */
	public static void pair4sumK(int[] arr, int k) {
		HashSet<Integer> hashTable = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			int key=k-arr[i];
			
			if(hashTable.contains(key)) {
				System.out.println("Pair of sum K :: "+arr[i]+" , "+key);
				return;
			}
			hashTable.add(arr[i]);
		}
	}
	
	public static boolean checkDuplicatesWithinK(int[] arr, int k) {
		Set<Integer> hashTableSet= new HashSet<>();
		
		for(int i=0; i<arr.length; i++) {
			if(hashTableSet.contains(arr[i])) {
				return true;
			}
			
			hashTableSet.add(arr[i]);
			
			if(i>k) {
				hashTableSet.remove(arr[i-k]);
			}
		}
		
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node root = new Node(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));
        root.getRight().setLeft(new Node(6));
        root.getRight().setRight(new Node(7));
        root.getRight().getLeft().setRight(new Node(8));
        root.getRight().getRight().setRight(new Node(9));
        
        printBinaryTreeVirtical(root);
        
        int inputArr[] = {10, 5, 3, 4, 3, 5, 6};
        if (checkDuplicatesWithinK(inputArr, 4))
           System.out.println("Yes");
        else
           System.out.println("No");
	}

}
