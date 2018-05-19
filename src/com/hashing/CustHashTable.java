package com.hashing;

import java.util.ArrayList;
import java.util.Iterator;

public class CustHashTable<K,V> {
	ArrayList<HashNode<K,V>> arrStore = null;
	
	int maxNoOfBucket=10; // default initial bucket
	int size=0;
	
	float load_factor=0.7f; // // default load factor
	
	public CustHashTable() {
		super();
		arrStore = new ArrayList<>(maxNoOfBucket);
		
		for (int i = 0; i < maxNoOfBucket; i++) {
			arrStore.add(null);
		}
	}
	
	public int size() {
		return size;
	}
	
	public V add(K key, V value) {
		int bucketIndex = getBucketIndex(key);
		HashNode<K,V> head = arrStore.get(bucketIndex);
		// Check if this key already exist, if yes then return old value
		while(head!=null) {
			V old=null;
			if(head.key.equals(key)) {
				old=head.value;
				head.value=value;
				return old;
			}
			head=head.next;
		}
		
		size++;
		head = arrStore.get(bucketIndex);
		HashNode<K,V> new_node = new HashNode<K, V>(key, value);
		new_node.setNext(head);
		arrStore.set(bucketIndex, new_node);
		
		// Perform resizing/rehashing if size hits it's load factor
		if(load_factor <= (float)size/maxNoOfBucket) {
			this.rehash(maxNoOfBucket*2);
			/*ArrayList<HashNode<K,V>> temp = arrStore;
			maxNoOfBucket = 2*maxNoOfBucket;
			size=0; // Becouse all element will be added to new buckets.
			arrStore = new ArrayList<>(maxNoOfBucket);
			for (int i = 0; i < maxNoOfBucket; i++) {
				arrStore.add(null);
			}
			
			for (Iterator<HashNode<K, V>> iterator = temp.iterator(); iterator.hasNext();) {
				HashNode<K, V> hashNode = (HashNode<K, V>) iterator.next();
				
				while(hashNode!=null) {
					add(hashNode.key, hashNode.value);
					hashNode=hashNode.next;
				}
			}*/	
		}
		
		return value;
	}
	private void rehash(int new_bucket_size) {
		ArrayList<HashNode<K,V>> temp = arrStore;
		maxNoOfBucket = new_bucket_size;
		size=0; // Becouse all element will be added to new buckets.
		arrStore = new ArrayList<>(maxNoOfBucket);
		for (int i = 0; i < maxNoOfBucket; i++) {
			arrStore.add(null);
		}
		
		for (Iterator<HashNode<K, V>> iterator = temp.iterator(); iterator.hasNext();) {
			HashNode<K, V> hashNode = (HashNode<K, V>) iterator.next();
			
			while(hashNode!=null) {
				add(hashNode.key, hashNode.value);
				hashNode=hashNode.next;
			}
		}
	}
	private int getBucketIndex(K key) {
		return (key.hashCode()%maxNoOfBucket);
	}

	public boolean remove(K key) {
		int bucketIndex = getBucketIndex(key);
		HashNode<K,V> head = arrStore.get(bucketIndex);

		HashNode<K,V> prev = null, temp=head;
		while(temp!=null) {
			if(temp.key.equals(key)) {
				size--;
				break;
			}
			
			prev=temp;
			temp=temp.next;
		}
		if (temp != null) {
			if (prev != null) {
				prev.next = temp.next;
				arrStore.set(bucketIndex, head);
			} else {
				arrStore.set(bucketIndex, head.next);
			}
		}else {
			return false;
		}
		
		// Rehashing/decreasing the size
		if(load_factor > (float)(size/(maxNoOfBucket/2))) {
			this.rehash(maxNoOfBucket/2);
			/*ArrayList<HashNode<K,V>> old = arrStore;
			maxNoOfBucket = maxNoOfBucket/2;
			
			size=0;
			
			arrStore = new ArrayList<>(maxNoOfBucket);
			for (int i = 0; i < maxNoOfBucket; i++) {
				arrStore.add(null);
			}
			
			for (Iterator<HashNode<K, V>> iterator = old.iterator(); iterator.hasNext();) {
				HashNode<K, V> hashNode = (HashNode<K, V>) iterator.next();
				
				while(hashNode!=null) {
					add(hashNode.key, hashNode.value);
					hashNode=hashNode.next;
				}
			}*/	
		}
		
		return true;
	}
	
	public V get(K key) {
		int bucketIndex = getBucketIndex(key);
		HashNode<K,V> head = arrStore.get(bucketIndex);
		
		while(head!=null) {
			if(head.key.equals(key))
				break;
			
			head=head.next;
		}
		
		return head==null?null:head.value;
	}
	
	public static void main(String[] args) {
		CustHashTable<String, Integer> custHashMap = new CustHashTable<>();
		custHashMap.add("One", 1);
		custHashMap.add("two", 2);
		custHashMap.add("three", 3);
		custHashMap.add("four", 4);
		custHashMap.add("five", 5);
		custHashMap.add("six", 6);
		custHashMap.add("seven", 7);
		custHashMap.add("eight", 8);
		
		System.out.println(custHashMap);
		custHashMap.remove("one");
		custHashMap.remove("two");
		custHashMap.remove("three");
		System.out.println(custHashMap);
		
		System.out.println(custHashMap.get("five"));
	}
}
class HashNode<K,V>{
	K key;
	V value;
	HashNode<K,V> next;
	
	public HashNode() {
		super();
	}
	public HashNode(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}
	public HashNode<K, V> getNext() {
		return next;
	}
	public void setNext(HashNode<K, V> next) {
		this.next = next;
	}
}