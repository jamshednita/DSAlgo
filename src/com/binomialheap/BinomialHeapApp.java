package com.binomialheap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BinomialHeapApp {
	
	public static List<BinomialTreeNode> insert(List<BinomialTreeNode> biHeapHead, int key){
		
		BinomialTreeNode newNode = new BinomialTreeNode(key);
		List<BinomialTreeNode> temp = new ArrayList<BinomialTreeNode>();
		temp.add(newNode);
		
		if(biHeapHead == null) return temp; // If heap is empty
		
		temp = unionBinomialHeap(biHeapHead, temp);
		
		return adjust(temp);
	}
	
	public static List<BinomialTreeNode> unionBinomialHeap(List<BinomialTreeNode> heap, List<BinomialTreeNode> heap2){
		// This method adds all binomial trees from heap1 and heap2 according to increasing degree
		List<BinomialTreeNode> ret = new ArrayList<BinomialTreeNode>();
		int itr1 = 0, itr2 = 0;
		
		BinomialTreeNode btn1=heap.get(itr1);
		BinomialTreeNode btn2=heap2.get(itr2);
		
		while(itr1<=heap.size()-1 && itr2<=heap2.size()-1) { 
			
			if(btn1.getDegree()<=btn2.getDegree()) {
				ret.add(btn1);
				itr1++;
				if(itr1<=heap.size()-1)
					btn1 = heap.get(itr1);
				
			}else {
				ret.add(btn2);
				itr2++;
				if(itr2<=heap2.size()-1)
					btn2 = heap2.get(itr2);
			}
		}
		
		while(itr1<=heap.size()-1) {
			btn1=heap.get(itr1);
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
	
	public static List<BinomialTreeNode> adjust(List<BinomialTreeNode> heap){
		int prev = 0, curr = 0;
		BinomialTreeNode btnPrev = null;
		BinomialTreeNode btnCurr = null;
		if(heap.size() >= 2) {
			curr = 1;
			
			btnPrev = heap.get(prev);
			btnCurr = heap.get(curr);
		}
		while(curr<=heap.size()-1 && prev<curr) {
			
			if(btnPrev.getDegree() < btnCurr.getDegree()) {
				prev=curr;
				if(curr+1<=heap.size()-1) {
					curr++;
				}
				
				btnPrev = heap.get(prev);
				btnCurr = heap.get(curr);
			}else if(curr+1<=heap.size()-1 && btnPrev.getDegree() == btnCurr.getDegree() && btnCurr.getDegree()==heap.get(curr+1).getDegree()) {
				prev=curr;
				curr++;
				btnPrev = heap.get(prev);
				btnCurr = heap.get(curr);
			}else if(btnPrev.getDegree() == btnCurr.getDegree()) {
				BinomialTreeNode temp = meargeBinomialTreeNodes(btnPrev, btnCurr);
				
				heap.set(prev, temp);
				btnPrev = temp;
				
				heap.remove(curr);
				if(curr<=heap.size()-1)
					btnCurr = heap.get(curr);
			}
			
		}
		
		return heap;
	}

	private static BinomialTreeNode meargeBinomialTreeNodes(BinomialTreeNode btnPrev, BinomialTreeNode btnCurr) {
		BinomialTreeNode mergedRoot = btnPrev;
		if(btnPrev.getData()>btnCurr.getData()) {
			mergedRoot = btnCurr;
			
			btnPrev.setParent(mergedRoot);
			btnPrev.setSibling(mergedRoot.getLeftChild());
			
			mergedRoot.setLeftChild(btnPrev);
		}else {
			btnCurr.setParent(mergedRoot);
			btnCurr.setSibling(mergedRoot.getLeftChild());
			
			mergedRoot.setLeftChild(btnCurr);
		}
		
		mergedRoot.setDegree(mergedRoot.getDegree()+1);
		
		return mergedRoot;
	}
	
	public static BinomialTreeNode getMin(List<BinomialTreeNode> heap) {
		Iterator<BinomialTreeNode> itr = heap.iterator();
		BinomialTreeNode min = itr.hasNext()?itr.next():null;
		
		while (itr.hasNext()) {
			BinomialTreeNode binomialTreeNode = (BinomialTreeNode) itr.next();
			if(binomialTreeNode.getData()<min.getData())
				min=binomialTreeNode;
		}
		
		return min;
	}
	
	
	public static BinomialTreeNode extractMin(List<BinomialTreeNode> heap) {
		BinomialTreeNode min = getMin(heap);
		List<BinomialTreeNode> new_heap = new ArrayList<>();
		
		for (BinomialTreeNode binomialTreeNode : heap) {
			if(binomialTreeNode != min)
				new_heap.add(binomialTreeNode);
		}
		
		BinomialTreeNode minChild = min.getLeftChild();
		
		while(minChild!=null) {
			minChild.setParent(null); // Because it's parent is no more part of heap after extraction.
			
			List<BinomialTreeNode> temp = new ArrayList<>();
			temp.add(minChild);
			new_heap = unionBinomialHeap(new_heap, temp);
			
			minChild = minChild.getSibling();
		}
		
		heap.clear();
		heap.addAll(adjust(new_heap));
		
		return min;
	}
	
	public static void main(String[] args) {
		List<BinomialTreeNode> heap = null;
		
		heap = insert(heap, 10);
		heap = insert(heap, 20);
		heap = insert(heap, 30);
		heap = insert(heap, 40);
		heap = insert(heap, 50);
		
		System.out.println(heap.size());
		
		BinomialTreeNode min = extractMin(heap);
		System.out.println(heap.size());
	}

}
