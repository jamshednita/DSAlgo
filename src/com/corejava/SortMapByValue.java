package com.corejava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class SortMapByValue {

	public static void main(String[] args) {

		// WAY-1 :
		/*Map<Integer, Integer> mapOne = new HashMap<>();
		mapOne.put(101, 80);
		mapOne.put(161, 70);
		mapOne.put(145, 10);
		mapOne.put(176, 40);
		mapOne.put(123, 60);
		
		ValueComparator comp1= new ValueComparator(mapOne);
		
		TreeMap<Integer, Integer> treeMapOne = new TreeMap<>(comp1);
		treeMapOne.putAll(mapOne);
		
		System.out.println(treeMapOne);*/
		
		// WAY-2 :
		
		Map<Integer, Integer> mapOne = new HashMap<>();
		mapOne.put(101, 80);
		mapOne.put(161, 70);
		mapOne.put(145, 10);
		mapOne.put(176, 40);
		mapOne.put(123, 60);
		System.out.println(mapOne);
		Set<Entry<Integer, Integer>> entrySet = mapOne.entrySet();
		List<Entry<Integer, Integer>> entryList = new ArrayList<>(entrySet);
		
		Collections.sort(entryList, new Comparator<Entry<Integer, Integer>>() {

			@Override
			public int compare(Entry<Integer, Integer> e1, Entry<Integer, Integer> e2) {
				// TODO Auto-generated method stub
				return e1.getValue().compareTo(e2.getValue());
			}
		});
		
		Map<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
		
		Iterator<Entry<Integer, Integer>> entryListItr = entryList.iterator();
		
		for (Entry<Integer, Integer> entry : entryList) {
			linkedHashMap.put(entry.getKey(), entry.getValue());
			System.out.println(entry.getKey()+" : "+entry.getValue());
		}
		
		System.out.println(linkedHashMap);
	}

}

class ValueComparator implements Comparator<Integer>{

	Map<Integer, Integer> map=null;
	
	public ValueComparator(Map<Integer, Integer> map) {
		super();
		this.map = new HashMap<>();
		this.map.putAll(map);
	}

	@Override
	public int compare(Integer key1, Integer key2) {
		return map.get(key1).compareTo(map.get(key2));
	}
	
}
