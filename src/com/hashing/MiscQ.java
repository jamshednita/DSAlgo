package com.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MiscQ {
	/**
	 * Description - Last seen array element (last appearance is earliest)
	 * @param inArr
	 * @return
	 */
	public static int lastSeenElement(int[] inArr) {
		Map<Integer, Integer> noPosMap = new HashMap<>(); // Map to store <element, position>.
		for(int i=0; i<inArr.length ; i++) {
			noPosMap.put(inArr[i], i);
		}
		
		List<Entry<Integer, Integer>> mapList = new ArrayList<>(noPosMap.entrySet());
		Collections.sort(mapList, new Comparator<Entry<Integer, Integer>>() {

			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				// TODO Auto-generated method stub
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		
		return mapList.get(0).getKey();
	}
	
	/**
	 * Description - Find four elements that sum to a given value | Set 2 O(n^2) solution).
	 * @param inArr
	 * @param sum
	 */
	public void printQuadruple(int[] inArr, int sum) {
		Map<Integer, Pair> sumPairMap = new HashMap<>();
		List<Integer> result = new ArrayList<>();
		// Create Sum, Pair Map
		for (int i = 0; i < inArr.length-1; i++) {
			for (int j = i+1; j < inArr.length; j++) {
				
				sumPairMap.put(inArr[i] + inArr[j], new Pair(i, j));
			}
		}
		
		
		for (int i = 0; i < inArr.length-1; i++) {
			for (int j = i+1; j < inArr.length; j++) {
				
				if(sumPairMap.get(sum - inArr[i]-inArr[j])!=null) {
					Pair existing = sumPairMap.get(sum - inArr[i]-inArr[j]);
					if(result.contains(inArr[i]) && result.contains(inArr[j]) && result.contains(inArr[existing.i]) &&  result.contains(inArr[existing.j])) {
						
					}else {
						result.clear();
						
						result.add(inArr[i]);
						result.add(inArr[j]);
						result.add(inArr[existing.i]);
						result.add(inArr[existing.j]);
						
						System.out.println(result);
					}
					
				}
			}
		}
	}
	
	/**
	 * Description - Find four elements that sum to a given value | Set 1 O(n^3) solution).
	 * @param inArr
	 * @param sum
	 */
	public static void printQuadrupleOn3(int[] inArr, int sum) {
		Arrays.sort(inArr);
		
		for (int i = 0; i < inArr.length-3; i++) {
			for (int j = i+1; j < inArr.length-2; j++) {
				int k=j+1;
				int l=inArr.length-1;
				
				while(k<l) {
					if(inArr[i] + inArr[j] + inArr[k] + inArr[l] == sum) {
						System.out.println(inArr[i] + ", " + inArr[j] + ", " + inArr[k] + ", " + inArr[l]);
						k++;
						l--;
					}else if(inArr[i] + inArr[j] + inArr[k] + inArr[l] < sum) {
						k++;
					}else {
						l--;
					}
				}
				
			}
		}
	}
	
	public static void main(String[] args) {
		/*int[] arr = {20, 10, 20, 20, 40, 10};//{10, 30, 20, 10, 20};
		
		System.out.println(lastSeenElement(arr));*/
		int[] arr = {-1,0,1,0,-2,2};//{10, 20, 30, 40, 1, 2};
		
		//(new MiscQ()).printQuadruple(arr, 0); //91
		
		printQuadrupleOn3(arr, 0);
	}
	
	class Pair{
		int i,j;
		
		public Pair(int i, int j) {
			this.i=i;
			this.j=j;
		}

		public int getI() {
			return i;
		}

		public void setI(int i) {
			this.i = i;
		}

		public int getJ() {
			return j;
		}

		public void setJ(int j) {
			this.j = j;
		}
	}

}
