package com.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MyDS<T> {
	ArrayList<T> store = null;
	HashMap<T, Integer> hashTable = null;
	
	public MyDS() {
		super();
		
		store = new ArrayList<>();
		hashTable = new HashMap<>();
	}

	public boolean insert(T x) {
		if(hashTable.containsKey(x))
			return false;
		
		int index = store.size();
		hashTable.put(x, index);
		store.add(x);
		
		return true;
	}
	
	public boolean remove(T x) {
		if(!hashTable.containsKey(x))
			return false;
		
		int index = hashTable.get(x);
		hashTable.remove(x);
		
		store.set(index, store.get(store.size()-1));
		hashTable.put(store.get(index), index);
		
		store.remove(store.size()-1);
		
		return true;
	}
	
	public int search(T x) {
		return hashTable.get(x);
	}
	
	public T getRandom() {
		Random ran= new Random();
		int random=ran.nextInt(store.size());
		
		return store.get(random);
	}

	public static void main(String[] args) {
		MyDS<Integer> ds = new MyDS<>();
        ds.insert(10);
        ds.insert(20);
        ds.insert(30);
        ds.insert(40);
        System.out.println(ds.search(30));
        ds.remove(20);
        ds.insert(50);
        System.out.println(ds.search(50));
        System.out.println(ds.getRandom());
	}

}
