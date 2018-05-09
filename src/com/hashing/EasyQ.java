package com.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

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
	/**
	 * Description - Check if a given array contains duplicate elements within k distance from each other.
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
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
	/**
	 * Description - Given an array of pairs, find all symmetric pairs in it
	 * @param inArr
	 */
	public static void findSymmetricPairs(int[][] inArr) {
		HashMap<Integer, Integer> hash=new HashMap<>();
		for (int i = 0; i < inArr.length; i++) {
			int key = inArr[i][0];
			int val = inArr[i][1];
			
			if(hash.get(val)!=null && hash.get(val).equals(new Integer(key))) {
				System.out.println("Symmetric Pair :: ("+key+","+val+")");
			}
			
			hash.put(key, val);
		}
		
	}
	/**
	 * Description - Group multiple occurrence of array elements ordered by first occurrence
	 * @param arr
	 */
	public static void groupMultiOccurOrdered(int []arr){
		Map<Integer, Integer> hashMap = new LinkedHashMap<>();
		
		for (int i = 0; i < arr.length; i++) {
			int occurences = 1+(hashMap.get(arr[i])==null?0:hashMap.get(arr[i]));
			
			hashMap.put(arr[i], occurences);
		}
		
		hashMap.forEach((k,v)->{
			for(int i=0;i<v;i++)
				System.out.print(k+" ");
			});
	}
	/**
	 * Description - Given two sets represented by two arrays, how to check if the given two sets are disjoint or not? It may be assumed that the given arrays have no duplicates.
	 * @param set1
	 * @param set2
	 */
	public static void areDisjoint(int[] set1, int[] set2) {
		HashSet<Integer> hashSet = new HashSet<>();
		
		for (int i = 0; i < set1.length; i++) {
			hashSet.add(set1[i]);
		}
		
		for (int i = 0; i < set2.length; i++) {
			if(hashSet.contains(set2[i])) {
				System.out.println("no");
				return;
			}
		}
		
		System.out.println("yes");
	}
	/**
	 * Description - Pair with given product | Set 1 (Find if any pair exists).
	 * @param arr
	 * @param x
	 * @return
	 */
	public static boolean isProduct(int[] arr, int x) {
		if(arr.length < 2)
			return false;
		
		HashSet<Integer> hashSet = new HashSet<>();
		
		for (int i = 0; i < arr.length; i++) {
			if(x==0) {
				if(arr[i] == 0)
					return true;
			}else if(arr[i]!=0 && x%arr[i] == 0){
				if(hashSet.contains(x/arr[i]))
					return true;
			}
			hashSet.add(arr[i]);
		}
		
		return false;
	}
	/**
	 * Description - Find missing elements of a range
	 * @param arr
	 * @param stRange
	 * @param outRange
	 */
	public static void printMissingInRange(int[] arr, int stRange, int outRange) {
		HashSet<Integer> hashSet = new HashSet<>();
		
		Arrays.stream(arr).forEach((element)->{hashSet.add(element);});
		
		for(int i = stRange; i<=outRange; i++) {
			if(!hashSet.contains(i)) {
				System.out.print(i+" ");
			}
		}
	}
	/**
	 * Description - Given an array of integers, and a number ‘sum’, find the number of pairs of integers in the array whose sum is equal to ‘sum’.
	 * @param arr
	 * @param sum
	 * @return
	 */
	public static int countPairsWithGivenSum(int[] arr, int sum) {
		int count = 0;
		HashMap<Integer, Integer> hashMap = new HashMap<>();
		
		for (int i = 0; i < arr.length; i++) {
			hashMap.put(arr[i], (hashMap.get(arr[i])==null?0:1)+1);
		}
		// This code portions calculates actual counts.
		for (Entry<Integer, Integer> entry : hashMap.entrySet()) {
			int key = entry.getKey();
			int keyCount = entry.getValue();
			
			if(sum%2==0 && keyCount>=2 && sum/2==key) { 
				count += (factorial(keyCount)/(factorial(keyCount-2)*factorial(2)));  // Once at most.
			}
			
			int existing = hashMap.get(sum-key)==null?0:hashMap.get(sum-key);
			
			count+=keyCount*existing;
		}
		
		return count/2; // It should return only combination not permutation. LIKE for sum=6, if (1,5) is processed then (5,1) no need. 
	}
	private static int factorial(int n) {
		if(n<=0)
			return n;
		
		return n*factorial(n-1);
	}
	/**
	 * Description - Convert an array to reduced form | Set 1 (Simple and Hashing)
	 * Given an array with n distinct elements, convert the given array to a form where all elements are in range from 0 to n-1. The order of elements is same, i.e., 0 is placed in place of smallest element, 1 is placed for second smallest element, … n-1 is placed for largest element.
	 * @param arr
	 * @return
	 */
	public static int[] convertReduced(int[] arr) {
		int[] output = arr.clone();
		
		Map<Integer, Integer> hashMap = new TreeMap<>();
		
		for (int i = 0; i < arr.length; i++) {
			hashMap.put(arr[i], 0);
		}//O(n)
		int position=0;
		for(Entry<Integer, Integer> entry : hashMap.entrySet()) {
			entry.setValue(position++);
		}// O(n)
		
		for (int i = 0; i < output.length; i++) {
			output[i]=hashMap.get(arr[i]);
		}//O(n)
		return output;
	}
	/**
	 * Description - Return maximum occurring character in an input string
	 * Write an efficient C function to return maximum occurring character in the input string e.g., if input string is “test” then function should return ‘t’.
	 * @param charSeq
	 * @return
	 */
	public static char maxOccurChar(String charSeq) {
		int[] countArr = new int[27];// bcoz " " (Space character) ascii is 32. so for all 26 alphabets and then this space char =27.
		
		for(char c: charSeq.toUpperCase().toCharArray()) {
			if(c==' ')
				countArr[26]=countArr[26]+1;
			else
				countArr[c-'A']=countArr[c-'A']+1;
		}
		
		int max=0;
		char out=' ';
		
		for (int i = 0; i < countArr.length; i++) {
			if(countArr[i]>max) {
				max=countArr[i];
				out=(char) (i+'A');
			}
		}
		
		return out;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Node root = new Node(1);
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
        
        int[][] inArr = {{11, 20}, {30, 40}, {5, 10}, {40, 30}, {10, 5}};
        findSymmetricPairs(inArr);
        
        int arr[] = {10, 5, 3, 10, 10, 4, 1, 3};
        groupMultiOccurOrdered(arr);
        
        int set1[] = {10, 5, 3, 4, 6};
        int set2[] = {8, 7, 9, 3};
        
        areDisjoint(set1, set2);
        int xArr[] = {10, 20, 9, 40, 0};
        System.out.println(isProduct(xArr, 400));
        
        printMissingInRange(xArr, 10, 20);
        
        int[] cArr = {5, 5, 7, -1, 1};//{1, 5, 7, -1};
        
        System.out.println(countPairsWithGivenSum(cArr, 6));
        
        int[] rArr= {10, 40, 20};
        Arrays.stream(convertReduced(rArr)).forEach(System.out::println);*/
		
		char c=' ';
		System.out.println(((int)c));
		
		System.out.println(maxOccurChar("sample string"));
	}

}
