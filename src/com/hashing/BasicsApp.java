package com.hashing;

import java.util.Arrays;

public class BasicsApp {
	
	private int[][] hash=null;
	
	public BasicsApp(int maxRange) {
		hash=new int[maxRange+1][2];
	}
	
	public void insert(int key) {
		if(key>=0) {
			hash[key][0]=1;
		}else {
			hash[Math.abs(key)][1]=1;
		}
	}
	
	public boolean search(int key) {
		return (key>=0?hash[key][0]:hash[Math.abs(key)][1]) == 1?true:false;
	}
	
	public static void main(String[] args) {
		BasicsApp app = new BasicsApp(20);
		
		int arr[] = { -1, 9, -5, -8, -5, -2 };
		Arrays.stream(arr).forEach(a->{app.insert(a);});
		
		System.out.println(app.search(9));
	}

}
