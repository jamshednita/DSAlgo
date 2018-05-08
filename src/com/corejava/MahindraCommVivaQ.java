package com.corejava;

public class MahindraCommVivaQ {
	
	public static int maxSumSubArray(int[] arr) {
		int maxSum=arr[0], currSum=arr[0];
		
		for(int i=1; i<arr.length; i++) {
			if(currSum+arr[i]>currSum && currSum+arr[i]>arr[i]) {
				currSum+=arr[i];
			}else {
				if(maxSum<currSum) {
					maxSum = currSum;
				}
				currSum = arr[i];
			}
		}
		
		return maxSum>currSum?maxSum:currSum;
	}
	
	public static void printSubArray4SumK(int[] arr, int k) {
		int start=0, end=0, tempSum=arr[0];
		if(tempSum == k) {
			System.out.println(tempSum);
			return;
		}
		for(end=1;end<arr.length;end++) {
			tempSum+=arr[end];
			if(tempSum == k) {
				printSubArray(arr, start, end);
				return;
			}else if(tempSum>k){
				while(start<=end && tempSum>k) {
					tempSum-=arr[start++];
				}
				
				if(tempSum == k) {
					printSubArray(arr, start, end);
					return;
				}
			}
		}
	}
	
	private static void printSubArray(int[] arr, int start, int end) {
		for(;start<=end;start++) {
			System.out.print(arr[start]+" ");
		}
		
	}

	public static void main(String[] args) {
		/*int[] arr= {4,3,1,2};//{-1,-2,1,2};
		System.out.println(maxSumSubArray(arr));*/
		
		int[] inArr= {4,3,1,2,9,5,7};
		printSubArray4SumK(inArr, 11);
	}

}
