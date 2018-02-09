package com.binarytree;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class BootomViewOfBT {
	
	static void bottomView(Map<Integer, BVNode> resMap, BVNode node, int horizonDist, int lvl){
		if(node == null)
			return;
		node.setLevel(lvl);
		if(resMap.containsKey(horizonDist)){
			BVNode temp = resMap.get(horizonDist);
			if(temp.getLevel()<lvl){
				resMap.put(horizonDist, node);
			}
		}else{
			resMap.put(horizonDist, node);
		}
			
		bottomView(resMap, node.getLeft(), horizonDist-1, lvl+1);
		bottomView(resMap, node.getRight(), horizonDist+1, lvl+1);
		
		/*bottomView(resMap, BVNode.getLeft(), horizonDist-1);
		
		resMap.put(horizonDist, BVNode);
		
		bottomView(resMap, BVNode.getRight(), horizonDist+1);*/
	}

	public static void main(String[] args) {
		BVNode root = new BVNode(20);
		BVNode left = new BVNode(8);
		BVNode right = new BVNode(22);
		
		BVNode left1 = new BVNode(5);
		BVNode right1 = new BVNode(3);
		
		BVNode left2 = new BVNode(4);
		BVNode right2 = new BVNode(25);
		
		BVNode left3 = new BVNode(10);
		BVNode right3 = new BVNode(14);
		
		root.setLeft(left);
		root.setRight(right);
		
		left.setLeft(left1);
		left.setRight(right1);
		
		//right.setLeft(left2);
		right.setRight(right2);
		
		right1.setLeft(left3);
		right1.setRight(right3);
		
		Map<Integer, BVNode> map=new TreeMap<Integer, BVNode>();
		
		bottomView(map, root, 0, 0);
		
		for (Entry<Integer, BVNode> entry : map.entrySet()) {
			System.out.println(entry.getValue().getData());
		}
	}
}
