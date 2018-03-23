package com.intervaltree;

public class ITreeApp {
	
	public static INode insert(INode root, Interval i){
		if(root==null)
			return new INode(i);
		
		if(root.i.low>i.low){
			root.setLeft(insert(root.getLeft(), i));
		}else
			root.setRight(insert(root.getRight(), i));
		if(root.max<i.high)
			root.max=i.high;
		
		return root;
	}
	
	public static void printConflictingAppointments(Interval[] apps, int n){
		INode root=null;
		root=insert(root, apps[0]);
		
		for (int i = 1; i < n; i++) {
			INode res= searchOverlap(root, apps[i]);
			if(res!=null){
				System.out.println("["+apps[i].low+", "+apps[i].high+"] overlaps with ["+res.i.low+", "+res.i.high+"]");
			}
			root=insert(root, apps[i]);
		}
	}

	private static INode searchOverlap(INode root, Interval interval) {
		if(root==null)
			return null;
		
		if(doOverlap(root, interval))
			return root;
		
		if(root.getLeft()!=null && root.max < interval.low)
			return searchOverlap(root.getLeft(), interval);
		
		else
			return searchOverlap(root.getRight(), interval);
	}

	private static boolean doOverlap(INode root, Interval interval) {
		if(root == null)
			return false;
		
		if(root.i.high>interval.low && interval.high>root.i.low)
			return true;
		
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
