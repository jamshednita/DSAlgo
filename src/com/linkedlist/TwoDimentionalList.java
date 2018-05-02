package com.linkedlist;

public class TwoDimentionalList {
	
	private TwoDPoint start;
	private int size;
	
	/**
	 * This method removes points between two horizontal points OR vertical points.
	 * If more than two consecutive point nodes having same x-coordinate OR y-coordinate 
	 * then nodes in between first and last point node will be removed.
	 * 
	 * Input: (0,10)->(1,10)->(5,10)->(7,10) 
	 *                                  | 
	 *                                (7,5)->(20,5)->(40,5) 
	 * Output:
	 * Linked List should be changed to following 
	 *        (0,10)->(7,10)
	 *                  | 
	 *                (7,5)->(40,5)
	 */

	public void removePointsFromLine(){
		TwoDPoint point=this.start;
		TwoDPoint prevPoint=null;
		
		while(null!=point.getNextPoint()){
			if(null==prevPoint){
				prevPoint=point;
				point=point.getNextPoint();
			}else{
				TwoDPoint temp=point;
				point=point.getNextPoint();
				
				if(prevPoint.getxCoOrdinate()==temp.getxCoOrdinate() && temp.getxCoOrdinate()==point.getxCoOrdinate()){
					prevPoint.setNextPoint(point);
					this.size--;
				}else if(prevPoint.getyCoOrdinate()==temp.getyCoOrdinate() && temp.getyCoOrdinate()==point.getyCoOrdinate()){
					prevPoint.setNextPoint(point);
					this.size--;
				}else{
					prevPoint=temp;
				}
			}
		}
		
	}

	public void addPoint(int xCoOrdinate, int yCoOrdinate){
		TwoDPoint begin=this.start;
		while(null!=begin.getNextPoint()){
			begin=begin.getNextPoint();
		}
		
		begin.setNextPoint(new TwoDPoint(xCoOrdinate, yCoOrdinate, null));
		this.size++;
	}
}
