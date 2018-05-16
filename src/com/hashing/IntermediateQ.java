package com.hashing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class IntermediateQ {
	
	public static class Pair{
		int first, second;
		
		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}
	
	/**
	 * Description - Given a list of tickets, find itinerary in order using the given list. 
	 * Ex - Input: 	"Chennai" -> "Banglore" 
	 * 				"Bombay" -> "Delhi" 
	 * 				"Goa" -> "Chennai" 
	 * 				"Delhi" -> "Goa"
	 * 
	 * Output: Bombay->Delhi, Delhi->Goa, Goa->Chennai, Chennai->Banglore,
	 * 
	 * @param ticketMap
	 */
	public static void printStart2EndJourney(Map<String, String> ticketMap) {
		Collection<String> desinationList = ticketMap.values();
		
		String start = null;
		for(Entry<String, String> ticket : ticketMap.entrySet()) {
			if(!desinationList.contains(ticket.getKey()))
				start = ticket.getKey();
		}
		
		if(start==null) {
			System.err.println("Invalid Inputs !");
			return;
		}
		
		while(ticketMap.containsKey(start)) {
			System.out.print(start + "->" + ticketMap.get(start)+" ");
			start=ticketMap.get(start);
		}
	}
	/**
	 * Description - Find number of Employees Under every Employee. Given a dictionary that contains mapping of employee and his manager as a number of (employee, manager) pairs
	 * @param empMngr - Employee-Manager map
	 * @return - Map of Manager and their reportees(direct and indirect) counts.
	 */
	public static Map<String, Integer> mngrEmpCount(Map<String, String> empMngr){
		Map<String, Integer> result = new HashMap<>();
		Map<String, List<String>> mngrEmpListMap = new HashMap<>();
		
		// Step 1 - create mngr-empList map.
		for(Entry<String, String> entry : empMngr.entrySet()) {
			String emp = entry.getKey();
			String mngr = entry.getValue();
			if (!emp.equals(mngr)) {
				List<String> empList = mngrEmpListMap.get(mngr);
				if (empList == null)
					empList = new ArrayList<>();

				empList.add(emp);
				mngrEmpListMap.put(mngr, empList);
			}
		}
		//Step 2 - Iterate input emp-mngr map's values and see if mngr is present in calculated mngr-empList map if not then there is zero employees under this mngr. else there are direct reportees and then recursively calculate the their reportess.
		for(String eachMngr : empMngr.keySet()) {
			int totalEmps = mngrEmpCountUtil(eachMngr, mngrEmpListMap, result);
			result.put(eachMngr, totalEmps);
		}
		
		return result;
	}
	private static int mngrEmpCountUtil(String eachMngr, Map<String, List<String>> mngrEmpListMap, Map<String, Integer> result) {
		int count=0;
		if(!mngrEmpListMap.containsKey(eachMngr))
			return 0;
		else {
			count = mngrEmpListMap.get(eachMngr).size();
		}
		
		for(String empAsMngr : mngrEmpListMap.get(eachMngr)) {
			count += mngrEmpCountUtil(empAsMngr, mngrEmpListMap, result);
		}
		
		return count;
	}
	/**
	 * Description - Check if an array can be divided into pairs whose sum is divisible by k.
	 * @param arr
	 * @param k
	 * @return
	 */
	public static boolean isPairDivByK(int[] arr, int k) {
		Set<Integer> inputSet = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			int rem = k - arr[i]%k;
			if(inputSet.contains(rem)) {
				return true;
			}else
				inputSet.add(rem);
		}
		return false;
	}
	/**
	 * Description - Check if an array can be divided into pairs whose sum is divisible by k. Print all possible pairs.
	 * @param arr
	 * @param k
	 * @return
	 */
	public static void printPairDivByK(int[] arr, int k) {
		Map<Integer, List<Integer>> remElementMap = printPairDivByKUtil(arr, k);
		int totalPairs = 0;
		Iterator<Entry<Integer, List<Integer>>> remItr = remElementMap.entrySet().iterator();
		
		while(remItr.hasNext()) {
			Entry<Integer, List<Integer>> entry = remItr.next();
			int rem = entry.getKey();
			List<Integer> elements = entry.getValue();
			int n = elements.size();
			if(n>=2 && rem==k/2 && k%2==0) {
				totalPairs +=  (factorial(n)/((factorial(n-2)*(factorial(2)))));
				
				printPairs(elements, null); //This method is to print all pairs 
			}
			else if(remElementMap.get(k-rem) != null) {
				List<Integer> nextElements = remElementMap.get(k-rem);
				int n2 = nextElements.size();
				
				totalPairs += (factorial(n)/((factorial(n-n2)*(factorial(n2)))));
				printPairs(elements, nextElements); //This method is to print all pairs 
			}
			
			remItr.remove();
		}
		
		System.out.println("Total Pair count :: " +totalPairs);
	}
	private static Map<Integer, List<Integer>> printPairDivByKUtil(int[] arr, int k) {
		Map<Integer, List<Integer>> remElementMap = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			int rem = arr[i]%k;
			if(remElementMap.get(rem) != null) {
				List<Integer> existing = remElementMap.get(rem);
				existing.add(arr[i]);
				remElementMap.put(rem, existing);
			}else {
				List<Integer> list = new ArrayList<>();
				list.add(arr[i]);
				remElementMap.put(rem, list);
			}
		}
		return remElementMap;
	}
	private static void printPairs(List<Integer> firstList, List<Integer> secondList) {
		if(secondList == null)
			secondList = firstList;
		
		for(int i=0; i<firstList.size(); i++) {
			for(int j=i; j<secondList.size(); j++) {
				if(firstList.get(i)!=secondList.get(j))
					System.out.println("("+firstList.get(i)+", "+secondList.get(j)+")");
			}
		}
		
	}
	private static int factorial(int n) {
		if(n<=0)
			return 1;
		
		return n*factorial(n-1);
	}
	/**
	 * Description - Find four elements a, b, c and d in an array such that a+b = c+d. It just print one of possible quartet.
	 * @param arr
	 * @return
	 */
	public static boolean isPairsAplusBeqCplusD(int[] arr) {
		Map<Integer, Pair> hashMap = new HashMap<>();
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = i+1; j < arr.length; j++) {
				int sum=arr[i]+arr[j];
				if(!hashMap.containsKey(sum))
					hashMap.put(sum, new Pair(arr[i], arr[j]));
				else {
					Pair existing = hashMap.get(sum);
					System.out.println(existing.first + ", "+existing.second+ " = " +arr[i]+", "+arr[j]);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Description - Find four elements a, b, c and d in an array such that a*b = c*d. It just print one of possible quartet.
	 * @param arr
	 * @return
	 */
	public static boolean isPairsAmulBeqCmulD(int[] arr) {
		Map<Integer, Pair> hashMap = new HashMap<>();
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = i+1; j < arr.length; j++) {
				int mul=arr[i]*arr[j];
				if(!hashMap.containsKey(mul))
					hashMap.put(mul, new Pair(arr[i], arr[j]));
				else {
					Pair existing = hashMap.get(mul);
					System.out.println(existing.first + ", "+existing.second+ " = " +arr[i]+", "+arr[j]);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Description - Find four elements a, b, c and d in an array such that a+b = c+d. Print all possible quartet.
	 * @param arr
	 * @return
	 */
	public static void printPairsAplusBeqCplusD(int[] arr) {
		// Step 1 - Create a map containing all pair list as value for a perticular sum as key.
		Map<Integer, List<Pair>> hashMap = new HashMap<>();
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = i+1; j < arr.length; j++) {
				int sum=arr[i]+arr[j];
				if(!hashMap.containsKey(sum)) {
					List<Pair> list = new ArrayList<>();
					list.add(new Pair(arr[i], arr[j]));
					hashMap.put(sum, list);
				}else {
					List<Pair> existing = hashMap.get(sum);
					existing.add(new Pair(arr[i], arr[j]));
					hashMap.put(sum, existing);	
				}
			}
		}
		
		// Step 2 - Iterate the map and see if any key contains list with more than 2 elements. If yes, print app possible pairs.
		printPairs(hashMap, false);
	}
	
	private static void printPairs(Map<Integer, List<Pair>> hashMap, boolean muliply) {
		Iterator<Entry<Integer, List<Pair>>> entryItr = hashMap.entrySet().iterator();
		
		while (entryItr.hasNext()) {
			Map.Entry<Integer, List<Pair>> entry = entryItr.next();
			int mul = entry.getKey();
			List<Pair> list = entry.getValue();
			
			if(list.size()>=2) {
				for (int i = 0; i < list.size() - 1; i++) {
					System.out.println("****** Quartet for " + mul + " ******");
					for (int j = i + 1; j < list.size(); j++) {
						if (muliply)
							System.out.println(list.get(i).first + "*" + list.get(i).second + " = " + list.get(j).first+ "*" + list.get(j).second);
						else
							System.out.println(list.get(i).first + "+" + list.get(i).second + " = " + list.get(j).first+ "+" + list.get(j).second);
					}
				}
			}
		}
		
	}
	
	/**
	 * Description - Find four elements a, b, c and d in an array such that a*b = c*d. Print all possible quartet.
	 * @param arr
	 * @return
	 */
	public static void printPairsAmulBeqCmulD(int[] arr) {
		// Step 1 - Create a map containing all pair list as value for a perticular sum as key.
		Map<Integer, List<Pair>> hashMap = new HashMap<>();
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = i+1; j < arr.length; j++) {
				int mul=arr[i]*arr[j];
				if(!hashMap.containsKey(mul)) {
					List<Pair> list = new ArrayList<>();
					list.add(new Pair(arr[i], arr[j]));
					hashMap.put(mul, list);
				}else {
					List<Pair> existing = hashMap.get(mul);
					existing.add(new Pair(arr[i], arr[j]));
					hashMap.put(mul, existing);	
				}
			}
		}
		
		// Step 2 - Iterate the map and see if any key contains list with more than 2 elements. If yes, print app possible pairs.
		printPairs(hashMap, true);
	}
	/**
	 * Description - Given an array of integers, find length of the largest subarray with sum equals to 0.
	 * @param arr
	 * @return
	 */
	public static int maxLengthZeroSumSubArray(int[] arr) {
		int sum=0, max_len=0;
		Map<Integer, Integer> hashMap =  new HashMap<>();
		
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			// If element is zero and max_len is zero also then  max_len = 1.
			if(arr[i] == 0 && max_len == 0)
				max_len = 1;
			// If element is zero and max_len is equal to length of subarray from starting till this index (max).
			if(sum == 0)
				max_len = i+1;
			
			if(!hashMap.containsKey(sum)) {
				hashMap.put(sum, i) ;
			}else {
				max_len = Math.max(max_len, i-hashMap.get(sum));
			}
		}
		
		return max_len;
	}
	
	public static int largestConsecutiveSubSeq(int[] arr) {
		int res=0;
		Set<Integer> hashSet =  new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			hashSet.add(arr[i]);
		}
		
		for (int i = 0; i < arr.length; i++) {
			int temp=0;
			if(!hashSet.contains(arr[i]-1)) {
				temp++;
				while(hashSet.contains(arr[i]+1))
					temp++;
			}
			
			res = Math.max(temp, res);
		}
		
		return res;
	}
	
	/**
	 * Description - Given an array of size n and an integer k, return the of count
	 * of distinct numbers in all windows of size k. Time Complexity - O(n) Input:
	 * arr[] = {1, 2, 1, 3, 4, 2, 3}; k = 4 Output: 3 4 4 3
	 * 
	 * Explanation: 
	 * First window is {1, 2, 1, 3}, count of distinct numbers is 3
	 * Second window is {2, 1, 3, 4} count of distinct numbers is 4 
	 * Third window is {1, 3, 4, 2} count of distinct numbers is 4 
	 * Fourth window is {3, 4, 2, 3} count of distinct numbers is 3
	 * 
	 * @param arr
	 * @param k
	 */
	public static void printDistinctInWindow(int[] arr, int k) {
		Map<Integer, Integer> countMap =  new HashMap<>();
		int i = 0;
		for (; i < k; i++) {
			if (countMap.containsKey(arr[i])) {
				countMap.put(arr[i], countMap.get(arr[i])+1);
			}else {
				countMap.put(arr[i], 1);
			}
		}
		int dist_count=countMap.keySet().size();
		System.out.println("distint count " +dist_count);
		
		while(i<arr.length) {
			int count = countMap.get(arr[i-k]);
			
			if(count>1) {
				countMap.put(arr[i-k], count-1);
			}else {
				countMap.remove(arr[i-k]);
				dist_count--;
			}
			
			if (countMap.containsKey(arr[i])) {
				countMap.put(arr[i], countMap.get(arr[i])+1);
			}else {
				countMap.put(arr[i], 1);
				dist_count++;
			}
			System.out.println("distint count " +dist_count);
			i++;
		}
	}
	public static void main(String[] args) {
		/*Map<String, String> dataSet = new HashMap<String, String>();
        dataSet.put("Chennai", "Banglore");
        dataSet.put("Bombay", "Delhi");
        dataSet.put("Goa", "Chennai");
        dataSet.put("Delhi", "Goa");
        
        printStart2EndJourney(dataSet);
        
        Map<String, String> empMngrDataSet = new HashMap<String, String>();
        empMngrDataSet.put("A", "C");
        empMngrDataSet.put("B", "C");
        empMngrDataSet.put("C", "F");
        empMngrDataSet.put("D", "E");
        empMngrDataSet.put("E", "F");
        empMngrDataSet.put("F", "F");
        
        System.out.println(mngrEmpCount(empMngrDataSet));
        
        int arr[] = { 92, 75, 65, 48, 45, 35 };
        System.out.println(isPairDivByK(arr, 10));
        printPairDivByK(arr, 10);
		
		int abcdAddArr[] = {3, 4, 7, 1, 2, 9, 8};
		//System.out.println(isPairsAplusBeqCplusD(abcdAddArr));
		//System.out.println(isPairsAmulBeqCmulD(abcdAddArr));
		
		//printPairsAplusBeqCplusD(abcdAddArr);
		printPairsAmulBeqCmulD(abcdAddArr);
		
		int zeroArr[] = {15, -2, 2, -8, 1, 7, 10, 23};
		System.out.println(maxLengthZeroSumSubArray(zeroArr));*/
		
		int arr[] =  {1, 2, 1, 3, 4, 2, 3};
        int k = 4;
        printDistinctInWindow(arr, k);
		
	}
}