package com.linkedlist;

public class TwoDPoint {
	private int xCoOrdinate;
	private int yCoOrdinate;
    private TwoDPoint nextPoint;
    
    public TwoDPoint(int xCoOrdinate, int yCoOrdinate, TwoDPoint nextPoint){
    	this.xCoOrdinate=xCoOrdinate;
    	this.yCoOrdinate=yCoOrdinate;
    	this.nextPoint=nextPoint;
    }
	public int getxCoOrdinate() {
		return xCoOrdinate;
	}
	public void setxCoOrdinate(int xCoOrdinate) {
		this.xCoOrdinate = xCoOrdinate;
	}
	public int getyCoOrdinate() {
		return yCoOrdinate;
	}
	public void setyCoOrdinate(int yCoOrdinate) {
		this.yCoOrdinate = yCoOrdinate;
	}
	public TwoDPoint getNextPoint() {
		return nextPoint;
	}
	public void setNextPoint(TwoDPoint nextPoint) {
		this.nextPoint = nextPoint;
	}
   
}
