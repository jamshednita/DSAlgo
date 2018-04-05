package com.binaryheap;

public class MinHeap {
	
	private int[] minHeapElements;
	private int capacity; // maximum possible size of min heap
	private int heapSize; // Current number of elements in min heap
	
	public MinHeap(int capacity) {
		super();
		this.capacity = capacity;
		this.minHeapElements = new int[capacity];
		this.heapSize=0;
	}
	
	private int parent(int i){
		return (i-1)/2;
	}
	private int left(int i){
		return (2*i+1);
	}
	private int right(int i){
		return (2*i+2);
	}
	// Inserts a new key 'k'
	public void insert(int key){
		if(heapSize == capacity){
			System.err.println("Min Heap is full !!");
			return;
		}
		
		heapSize++;
		minHeapElements[heapSize-1] = key;
		
		int i=heapSize-1;
		// Fix min heap property if violated.
		while(i!=0 && (minHeapElements[parent(i)] > minHeapElements[i])){
			// Swap parent of i and i 
			int temp = minHeapElements[parent(i)];
			minHeapElements[parent(i)]=minHeapElements[i];
			minHeapElements[i]=temp;
			
			i=parent(i);
		}
	}
	// A recursive method to heapify a subtree with root at given index
	// This method assumes that the subtrees are already heapified
	public void minHeapify(int i){
		
		int l=left(i);
		int r=right(i);
		int smallest=i;
		if(l<heapSize && minHeapElements[l]<minHeapElements[i])
			smallest = l;
		
		if(r<heapSize && minHeapElements[r]<minHeapElements[smallest])
			smallest=r;
		
		// if i'th  node is greater than any of it's child then swap these value and recursively check using smallest array index bcoz now it has greater element which further can violate with it's child.
		if(smallest!=i){
			// swap code -
			int temp = minHeapElements[smallest];
			minHeapElements[smallest] = minHeapElements[i];
			minHeapElements[i] = temp;
			
			minHeapify(smallest);
		}		
	}
	// Decreases value of key at index 'i' to new_val. It is assumed that new_val is smaller than minHeapElements[i].
	public void decreaseKey(int i, int new_val){
		minHeapElements[i]=new_val;
		// This code make sure parent is always smaller than child in min-heap case.
		while(i!=0 && minHeapElements[parent(i)]>minHeapElements[i]){
			// swap code -
			int temp = minHeapElements[parent(i)];
			minHeapElements[parent(i)] = minHeapElements[i];
			minHeapElements[i] = temp;
			
			i=parent(i);
		}
	}

	// Method to remove minimum element (or root) from min heap
	public int extractMin(){
		if(heapSize<=0){
			return Integer.MIN_VALUE;
		}
		
		if(heapSize == 1){
			heapSize--;
			return minHeapElements[heapSize];
		}
		
		int min = minHeapElements[0];
		heapSize--;
		minHeapElements[0] = minHeapElements[heapSize];
		minHeapify(0);
		
		return min;
	}
	// This function deletes key at index i. It first reduced value to minus
	// infinite, then calls extractMin()
	public void delete(int i){
		/*
		 * 1. first replace the i'th element with minimum possible value(-ve) using decreaseKey method.
		 * 2. call extractMin to remove out the newly replaced value i.e. minimum possible value -ve from minHeap.
		 */
		
		decreaseKey(i, Integer.MIN_VALUE);
		extractMin();
	}
	
	public static void main(String[] args) {
		MinHeap nh = new MinHeap(25);
		nh.insert(3);
		nh.insert(2);
		nh.delete(1);
		nh.insert(15);
		nh.insert(5);
		nh.insert(4);
		nh.insert(45);
		
		System.out.println(nh.extractMin());
	}

}
