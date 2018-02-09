package com.corejava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

abstract class ConcurModifExep {
	
	public static void main(String[] args) {
		/*List<String> myList = new ArrayList<String>();
		
		myList.add("1");
		myList.add("2");
		myList.add("3");
		myList.add("4");
		myList.add("5");
		
		Iterator<String> listItr = myList.iterator();
		while(listItr.hasNext()){
			String val = listItr.next();
			System.out.println(" List Value : "+val);
			if(val.equals("3")){
				listItr.remove();
				//myList.remove(val);
			}
		}
		System.out.println("List Size:"+myList.size());
		
		Map<String, String> myMap = new HashMap<String, String>();
		
		myMap.put("1", "1");
		myMap.put("2", "2");
		myMap.put("3", "3");
		
		Iterator<String> mapItr = myMap.keySet().iterator();
		
		while(mapItr.hasNext()){
			String key = mapItr.next();
			
			System.out.println(" Map Value : "+myMap.get(key));
			
			if(key.equals("1")){
				//myMap.remove("3");
				
				myMap.put("3", "4");
				//myMap.put("5", "5");
			}
		}
		System.out.println("Map Size:"+myMap.size());*/
		int i;
		for (i = 0; i < 10; i++) {
			if(i==1)
				break; // This will not let the increment step happen.
		}
		System.out.println(i);
		//modifyConcurrent();
		//subListCheck();
	}

	static void modifyConcurrent() {
		List<String> myList = new CopyOnWriteArrayList<String>();//CopyOnWriteArrayList<String>();

		myList.add("1");
		myList.add("2");
		myList.add("3");
		myList.add("4");
		myList.add("5");

		Iterator<String> listItr = myList.iterator();
		while (listItr.hasNext()) {
			String val = listItr.next();
			System.out.println(" List Value : " + val);
			if (val.equals("4")) {
				//listItr.remove();
				myList.remove("1");
				myList.add("6");
				myList.add("7");
			}
		}
		System.out.println("List Size:" + myList.size());

		Map<String, String> myMap = new ConcurrentHashMap<String, String>();//ConcurrentHashMap<String, String>();

		myMap.put("1", "1");
		myMap.put("2", "2");
		myMap.put("3", "3");
		myMap.put("7", "7");

		Iterator<String> mapItr = myMap.keySet().iterator();

		while (mapItr.hasNext()) {
			String key = mapItr.next();

			System.out.println(key + "  :  Map Value : " + myMap.get(key));

			if (key.equals("3")) {
				myMap.remove("2");//myMap.remove("7");

				myMap.put("4", "4");
				myMap.put("5", "5");
			}
		}
		System.out.println("Map Size:" + myMap.size());
	}
	
	static void subListCheck(){
		List<String> names = new ArrayList<>();
		names.add("Java"); names.add("PHP");names.add("SQL");names.add("Angular 2");
		
		List<String> first2Names = names.subList(0, 2);
		
		System.out.println(names +" , "+first2Names);
		
		names.set(1, "JavaScript");
		//check the output below.
		System.out.println(names +" , "+first2Names);
		
		//Let's modify the list size and get ConcurrentModificationException
		names.add("NodeJS");
		System.out.println(names +" , "+first2Names); //this line throws exception
	}
}
