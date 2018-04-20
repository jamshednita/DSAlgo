package com.binaryheap;

import java.util.Arrays;

public class HeapSort {
	
	public static void sort(int[] arr, int n) {
		
		// Rearrange array according to max heap
		for (int i = n/2 -1; i >= 0; i--) {
			heapify(arr, n, i);
		}
		//Now root(arr[0]) is max element so place max at the end, decrease size of heap and call heapify to pop out another max at root(top).
		for(int j=n-1; j>=0; j--) {
			int temp = arr[0];
			arr[0] = arr[j];
			arr[j]=temp;
			
			heapify(arr, j, 0);
		}
	}

	// This function heapify the subtree rooted at node i which i'th element in arr.
	private static void heapify(int[] arr, int n, int i) {
		int largest = i;
		int l = 2*i+1;
		int r = 2*i+2;
		
		if(l<n && arr[l]>arr[largest])
			largest = l;
		
		if(r<n && arr[r]>arr[largest])
			largest = r;
		
		if(largest != i) {
			int temp = arr[i];
			arr[i] = arr[largest];
			arr[largest] = temp;
			
			heapify(arr, n, largest);
		}
	}
	
	public static void main(String[] args) {
		int arr[] = {12, 11, 13, 5, 6, 7};
		
		sort(arr, arr.length);
		Arrays.stream(arr).forEach(System.out::println);
		//Arrays.stream(arr).forEach(i->{System.out.println(i);});
	}
}
