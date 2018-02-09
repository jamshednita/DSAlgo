package com.binarytree;

import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class TopViewOfBT {

	static void topView(Map<Integer, BVNode> resMap, BVNode node, int horizonDist, int lvl){
		
		if(node == null)
			return;
		node.setLevel(lvl);
		
		if(resMap.containsKey(horizonDist)){
			BVNode temp = resMap.get(horizonDist);
			if(temp.getLevel()>lvl){
				resMap.put(horizonDist, node);
			}
		}else{
			resMap.put(horizonDist, node);
		}
			
		topView(resMap, node.getLeft(), horizonDist-1, lvl+1);
		topView(resMap, node.getRight(), horizonDist+1, lvl+1);
		
	}
	

	public static void main(String[] args) {
		BVNode root = new BVNode(1);
		BVNode left = new BVNode(2);
		BVNode right = new BVNode(3);
		
		BVNode left1 = new BVNode(4);
		BVNode right1 = new BVNode(5);
		
		BVNode left2 = new BVNode(6);
		BVNode right2 = new BVNode(7);
		
		/*
		 *   1
      	   /   \
    	  2     3
           \   
            4  
             \
              5
               \
                6
		 * 
		 * 
		 * root.setLeft(left);
		root.setRight(right);           
		
		left.setRight(left1);
		
		//right.setLeft(left2);
		left1.setRight(right1);
		right1.setRight(left2);*/
		
		root.setLeft(left);
		root.setRight(right);
		
		left.setLeft(left1);
		left.setRight(right1);
		
		right.setLeft(left2);
		right.setRight(right2);
		
		
		Map<Integer, BVNode> map=new TreeMap<Integer, BVNode>();
		
		topView(map, root, 0, 0);
		
		for (Entry<Integer, BVNode> entry : map.entrySet()) {
			System.out.println(entry.getValue().getData());
		}
	}
}
