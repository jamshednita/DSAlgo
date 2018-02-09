package com.binarytree;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainApp {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		/*File file =  new File("C:\\Users\\jansari1\\ValidatorJSON\\Excercise\\src\\SampleText.txt");
		BufferedReader buffReader = new BufferedReader(new FileReader(file));
		
		String line=null;
		while ((line=buffReader.readLine())!=null) {
			System.out.println(line);
			
			String[] tokens = line.split("-");
			System.out.println(tokens.length);
		}*/
		/*System.out.println(isIpAddress("68.32.65.58"));
		System.out.println(isValidIP("www.google.com"));
		//System.out.println(IPAddressLookup.isIPv4InDotDecimalNotation("68.32.65.58"));
		
		Node root = new Node(2);
		Node left = new Node(1);
		Node right = new Node(3);
		
		root.setLeft(left);
		root.setRight(right);
		doubleTree(root);
		System.out.println(root.getRight().getLeft().getData());
		
		Node root = new Node(1);
		Node left = new Node(2);
		Node right = new Node(3);
		
		
		Node left1 = new Node(4);
		Node right1 = new Node(5);
		
		Node left2 = new Node(6);
		Node right2 = new Node(7);
		Node left3 = new Node(8);
		Node right3 = new Node(9);

		Node left4 = new Node(10);
		Node right4 = new Node(11);
		
		Node left5 = new Node(12);
		Node right6 = new Node(13);

		Node left7 = new Node(14);
		Node right8 = new Node(15);
		
		root.setLeft(left);
		root.setRight(right);
		left.setLeft(left1);
		left.setRight(right1);
		right.setLeft(left2);
		right.setRight(right2);
		
		left1.setLeft(left3);
		left1.setRight(right3);
		right1.setLeft(left5);
		
		right2.setLeft(left4);
		
		right3.setLeft(right6);
		right3.setRight(left7);
		left4.setRight(right4);
		left7.setLeft(right8);
		
		removeKLess(root, 0, 100);
		
		System.out.println(root.getData());*/
		
		/*Node root = new Node(1);
		Node left = new Node(2);
		Node right = new Node(3);
		
		
		Node left1 = new Node(4);
		Node right1 = new Node(5);
		
		Node left2 = new Node(6);
		Node right2 = new Node(7);
		Node left3 = new Node(8);
		Node right3 = new Node(9);
		Node left4 = new Node(10);
		Node right4 = new Node(11);
		
		root.setLeft(left);
		root.setRight(right);
		left.setLeft(left1);

		right.setLeft(right1);
		right.setRight(left2);
		
		right1.setRight(right2);
		left2.setRight(left3);
		right2.setLeft(right3);
		
		left3.setRight(left4);
		left4.setLeft(right4);
		
		System.out.println(depthOfOddLeaf(root, 1));
		System.out.println(oddEvenSumDiff(root));*/
		
		Node nodeA = new Node((int)'A'); //65
		Node nodeB = new Node((int)'B'); //66
		Node nodeC = new Node((int)'C'); //67
		Node nodeD = new Node((int)'D'); //68
		
		Node nodeE = new Node((int)'E'); //69
		Node nodeF = new Node((int)'F'); //70
		Node nodeG = new Node((int)'G'); //71
		Node nodeH = new Node((int)'H'); //72
		
		Node nodeI = new Node((int)'I'); //73
		Node nodeJ = new Node((int)'J'); //74
		Node nodeK = new Node((int)'K'); //75
		
		nodeA.setLeft(nodeB);
		nodeA.setRight(nodeC);
		
		nodeC.setLeft(nodeE);
		nodeC.setRight(nodeF);
		
		nodeE.setLeft(nodeG);
		nodeG.setLeft(nodeI);
		nodeG.setRight(nodeJ);
		
		nodeF.setRight(nodeH);
		nodeH.setLeft(nodeK);
		
		Min m= new Min();
		
		System.out.println(" Closest Leaf at ::  "+nearestLeafUtil(nodeA, (int)'G', m));
		System.out.println(m.min + " ===== " +(char)m.minNode.getData());
		int[] dArr = new int[50];
		diagonalSumUtil(nodeA, dArr, 0);
		int i=0;
		while(dArr[i]>0){
			System.out.println(dArr[i++]);
		}
		
	}
	
	public static boolean isIpAddress(String host) {
	    String ip_filter = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
	    if (host.toLowerCase().contains(ip_filter.toLowerCase())){
	        return true;
	    }
	    return false;
	}
	
	public static boolean isValidIP(String ipAddr){
        
        Pattern ptn = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$");
        Matcher mtch = ptn.matcher(ipAddr);
        return mtch.find();
    }
	
	public static void doubleTree(Node node){
		if(node ==  null) return ;
		
		Node nn = new Node(node.getData());
		
		nn.setLeft(node.getLeft());
		node.setLeft(nn);
		
		doubleTree(nn.getLeft());
		doubleTree(node.getRight());
	}
	
	public static boolean removeKLess(Node node, int sum, int k){
		if(node.getLeft()!=null && sum+node.getData()<k){
			//boolean flag = removeKLess(node.getLeft(), sum+node.getData(), k);
			if(removeKLess(node.getLeft(), sum+node.getData(), k)){
				node.setLeft(null);
			}
		}
		if(node.getRight()!=null && sum+node.getData()<k){
			//removeKLess(node.getRight(), sum+node.getData(), k);
			if(removeKLess(node.getRight(), sum+node.getData(), k)){
				node.setRight(null);
			}
		}
		
		if(node.getLeft()==null && node.getRight()==null && sum+node.getData()<k){
			return true;
		}
		return false;
	}
	
	public static int depthOfOddLeaf(Node node, int level){
		if(node == null) return 0;
		
		if(node.getLeft() == null && node.getRight() == null && (level&1)!=0 ){
			return level;
		}
		
		return Math.max(depthOfOddLeaf(node.getLeft(), level+1), depthOfOddLeaf(node.getRight(), level+1));
	}
	
	static int oddEvenSumDiff(Node node){
		if(node==null) return 0;
		
		return node.getData() - oddEvenSumDiff(node.getLeft()) - oddEvenSumDiff(node.getRight());
	}
	
	static int nearestLeaf(Node node, Node k){
		if(node == null) return -1;
		
		if(node == k){
			return nearestDown(node);
		}
		
		int leftRes = nearestLeaf(node.getLeft(), k);
		
		if(leftRes >= 0){
			return Math.min(leftRes, nearestDown(node.getRight()));
		}
		
		int rightRes = nearestLeaf(node.getLeft(), k);
		
		if(leftRes >= 0){
			return Math.min(leftRes, nearestDown(node.getRight()));
		}
		
		return -1; // INCORRECT
	}
	
	static int nearestLeafUtil(Node node, int k, Min m){
		if(node==null){
			return -1;
		}
		
		if(node.getData() == k){
			nearestDown(node,m,0);
			return 1;
		}
		
		int l=nearestLeafUtil(node.getLeft(), k, m);
		if(l>0){
			nearestDown(node.getRight(), m, l+1);
			return l+1;
		}
		
		int r=nearestLeafUtil(node.getRight(), k, m);
		if(r>0){
			nearestDown(node.getLeft(), m, r+1);
			return r+1;
		}
		
		return -1;
	}

	private static void nearestDown(Node node, Min m, int level) {

		if(node == null){
			return;
		}
		
		if(node.getLeft() == null && node.getRight() == null){
			if(m.min > Math.min(m.min, level)){
				m.min = Math.min(m.min, level);
				m.minNode = node;
			}
			return;
		}
		
		nearestDown(node.getLeft(), m, level+1);
		nearestDown(node.getRight(), m, level+1);
	}

	private static int nearestDown(Node node) {
		if(node == null) return Integer.MAX_VALUE;
		
		if(node.getLeft() == null && node.getRight() == null) return 0;
		
		return 1+ Math.min(nearestDown(node.getLeft()), nearestDown(node.getLeft()));
	}

	static void diagonalSumUtil(Node node, int[] arr, int diag){
		if(node == null) return;
		
		arr[diag]+=node.getData();
		
		diagonalSumUtil(node.getRight(), arr, diag);
		diagonalSumUtil(node.getLeft(), arr, diag+1);
	}
}
