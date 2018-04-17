package com.binomialheap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BinomialHeapApp {
	
	public List<BinomialTreeNode> insert(List<BinomialTreeNode> biHeapHead, int key){
		
		BinomialTreeNode newNode = new BinomialTreeNode(key);
		List<BinomialTreeNode> temp = new ArrayList<BinomialTreeNode>();
		temp.add(newNode);
		
		temp = unionBinomialHeap(biHeapHead, temp);
		
		return null;
	}
	
	public List<BinomialTreeNode> unionBinomialHeap(List<BinomialTreeNode> heap1, List<BinomialTreeNode> heap2){
		// This method adds all binomial trees from heap1 and heap2 according to increasing degree
		List<BinomialTreeNode> ret = new ArrayList<BinomialTreeNode>();
		int itr1 = 0, itr2 = 0;
		
		BinomialTreeNode btn1=null;
		BinomialTreeNode btn2=null;
		
		while(itr1<=heap1.size()-1 && itr2<=heap2.size()-1) {
			btn1=heap1.get(itr1);
			btn2=heap2.get(itr2); 
			
			if(btn1.getDegree()<=btn2.getDegree()) {
				ret.add(btn1);
				itr1++;
				
			}else {
				ret.add(btn2);
				itr2++;
			}
		}
		
		while(itr1<=heap1.size()-1) {
			btn1=heap1.get(itr1);
			ret.add(btn1);
			itr1++;
		}
		while(itr2<=heap2.size()-1) {
			btn2=heap2.get(itr2);
			ret.add(btn2);
			itr2++;
		}
		
		return ret;
	}
	
	public List<BinomialTreeNode> adjust(List<BinomialHeapApp> heap){
		// Work in progress
		int prev = 0, curr = 0, next=0;
		
		if(heap.size() == 2) {
			curr = 0;
			next = 1;
		}else {
			
		}
		return null;
	}

}
