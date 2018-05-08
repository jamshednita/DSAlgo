package com.corejava;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee emp1 = new Employee(101, "XYZ");
		Employee emp2 = new Employee(102, "XYZ");
		
		Set<Employee> sortedEmp= new TreeSet<Employee>();
		
		sortedEmp.add(emp1);
		sortedEmp.add(emp2);
		
		Set<Employee> emps= new HashSet<Employee>();
		
		emps.add(emp1);
		emps.add(emp2);
		
		System.out.println(sortedEmp.size());
		System.out.println(emps.size());
		
		System.out.println(throwEx());
		
		// Sort a map based on values ==
		Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("java", 20);
        map.put("C++", 45);
        map.put("Java2Novice", 2);
        map.put("Unix", 67);
        map.put("MAC", 26);
        map.put("Why this kolavari", 93);
        
        Set<Entry<String, Integer>> set = map.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<>(set);
        Collections.sort(list, new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> arg0, Entry<String, Integer> arg1) {
				// Reverse order
				return arg1.getValue().compareTo(arg0.getValue());
			}
		});
        
        list.forEach((e)->{System.out.println(e.getKey() + " :: " + e.getValue());});
	}
	
	static int throwEx(){
		try {
			throw new SQLException("Custom SQL Exception");
		} /*catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/finally{
			//return 1;
			throw new NullPointerException("Custom Null Pointer Exception"); 
			// Any throw OR return statement in finally block will override the throw OR return of try block
			// That's why we should always refrain of putting such statement in finally block.
			// Finally block designed for safeguarding/cleanup purpose so that if any abnormal conditions occurs then we should close all
			// open or running external connection.
		}
	}

}
