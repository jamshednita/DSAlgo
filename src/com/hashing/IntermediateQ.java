package com.hashing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class IntermediateQ {
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
	 * Description - Check if an array can be divided into pairs whose sum is divisible by k
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

	public static void main(String[] args) {
		/*Map<String, String> dataSet = new HashMap<String, String>();
        dataSet.put("Chennai", "Banglore");
        dataSet.put("Bombay", "Delhi");
        dataSet.put("Goa", "Chennai");
        dataSet.put("Delhi", "Goa");
        
        printStart2EndJourney(dataSet);*/
        
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
	}
}
