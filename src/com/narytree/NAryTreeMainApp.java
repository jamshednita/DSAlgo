package com.narytree;

public class NAryTreeMainApp {

	public static void main(String[] args) {
		NAryNode A = new NAryNode((int)'A', 6);
		NAryNode B = new NAryNode((int)'B', 3);
		NAryNode C = new NAryNode((int)'C', 0);
		NAryNode D = new NAryNode((int)'D', 0);
		NAryNode E = new NAryNode((int)'E', 2);
		
		NAryNode F = new NAryNode((int)'F', 0);
		NAryNode G = new NAryNode((int)'G', 1);
		NAryNode H = new NAryNode((int)'H', 2);
		NAryNode I = new NAryNode((int)'I', 0);
		NAryNode J = new NAryNode((int)'J', 0);
		
		NAryNode K = new NAryNode((int)'K', 1);
		NAryNode L = new NAryNode((int)'L', 1);
		NAryNode M = new NAryNode((int)'M', 0);
		NAryNode N = new NAryNode((int)'N', 0);
		NAryNode O = new NAryNode((int)'O', 0);
		
		NAryNode P = new NAryNode((int)'P', 0);
		NAryNode Q = new NAryNode((int)'Q', 1);
		NAryNode R = new NAryNode((int)'R', 1);
		NAryNode S = new NAryNode((int)'S', 1);
		NAryNode T = new NAryNode((int)'T', 1);
		
		NAryNode U = new NAryNode((int)'U', 0);

		A.addChild(0, B);
		A.addChild(1, C);
		A.addChild(2, D);
		A.addChild(3, E);
		A.addChild(4, F);
		A.addChild(5, G);
		
		B.addChild(0, H);
		B.addChild(1, I);
		B.addChild(2, J);
		
		E.addChild(0, K);
		E.addChild(1, L);
		
		G.addChild(0, M);
		
		H.addChild(0, N);
		H.addChild(1, O);
		
		K.addChild(0, P);
		
		L.addChild(0, Q);
		
		Q.addChild(0, R);
		
		R.addChild(0, S);
		
		S.addChild(0, T);
		
		T.addChild(0, U);
		
		System.out.println(minItr2PassMessage(A));
	}
	
	public static int minItr2PassMessage(NAryNode node){
		int min = node.getChilds().length;
		
		if(node == null || node.getChilds() == null || node.getChilds().length == 0) return 0;
		
		for(int i=0; i<node.getChilds().length; i++){
			int tempMin = minItr2PassMessage(node.getChilds()[i]);
			
			if(tempMin+1 > min)
				min=tempMin+1;
		}
		
		return min;
	}

}
