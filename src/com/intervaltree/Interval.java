package com.intervaltree;

public class Interval {

	int low, high;
	
	public Interval(int low, int high) {
		super();
		this.low = low;
		this.high = high;
	}

	public int getLow() {
		return low;
	}

	public void setLow(int low) {
		this.low = low;
	}

	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}
	
}
