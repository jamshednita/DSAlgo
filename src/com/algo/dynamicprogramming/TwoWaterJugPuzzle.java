package com.algo.dynamicprogramming;

public class TwoWaterJugPuzzle {

	/**
	 * Description - The Two Water Jug Puzzle. You are at the side of a river. You are given a m litre jug and a n litre jug where 0 < m < n. Both the jugs are initially empty. The jugs don’t have markings to allow measuring smaller quantities. You have to use the jugs to measure d litres of water where d < n. Determine the minimum no of operations to be performed to obtain d litres of water in one of jug.
					 The operations you can perform are:
					 (a) Empty a Jug
                     (b) Fill a Jug
                     (c) Pour water from one jug to the other until one of the jugs is either empty or full.

	 * @param m
	 * @param n
	 * @param d
	 * @return
	 */
	public static int countSteps(int m, int n, int d) {
		int stepCount = 0;
		int jug1=0, jug2=0;
		
		//Step 1 - Fill Jug1
		jug1=m;
		stepCount++;
		
		while(jug1!=d && jug2!=d) {
			// Step 2 - Pour from Jug1 to Jug2
			if(jug2<n) {
				int jug2Capacity = n-jug2;
				
				if(jug1<=jug2Capacity) {
					jug2+=jug1;
					jug1=0;
				}else {
					jug2=n;
					jug1-=jug2Capacity;
				}
				stepCount++;
			}
			// Check if either jug has required water.
			if(jug2==d || jug1==d)
				break;
			// Step 3 - If Jug2 is full empty it
			if(jug2==n) {
				jug2=0;
				stepCount++;
			}
			
			// Step 4 - If Jug1 is empty fill it again.
			if(jug1==0) {
				jug1=m;
				stepCount++;
			}
		}
		
		return stepCount;
	}
	/**
	 * Description - Basic Euclidean Algorithm for GCD. The algorithm is based on below facts.
	 * 				 (A) If we subtract smaller number from larger (we reduce larger number), GCD doesn’t change. So if we keep subtracting repeatedly the larger of two, we end up with GCD.
                     (B) Now instead of subtraction, if we divide smaller number, the algorithm stops when we find remainder 0.
	 * @param a
	 * @param b
	 * @return
	 */
	private static int gcd(int a, int b) {
		// extended Euclidean Algorithm
		if(a==0)
			return b;
		
		return gcd(b%a, a);
	}
	
	public static int minStepsRequired(int m, int n, int d) {
		// It is assumed that m<n.
		if(d<0 || d>Math.max(m, n) || (d%gcd(m, n)!=0))
			return -1;
		
		else
			return Math.min(countSteps(m, n, d), countSteps(n, m, d));
	}
	public static void main(String[] args) {
		System.out.println(minStepsRequired(3, 5, 4));
	}

}
