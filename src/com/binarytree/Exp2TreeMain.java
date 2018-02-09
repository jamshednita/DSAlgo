package com.binarytree;

public class Exp2TreeMain {
	
	static Node convert2Tree(char[] expression, int i){
		 // Base case
        if (i >= expression.length)
            return null;
      
        // store current character of expression_string
        // [ 'a' to 'z']
        Node root = new Node(expression[i]);
      
        // Move ahead in str
        ++i;
      
        // if current character of ternary expression is '?'
        // then we add next character as a left child of
        // current node
        if (i < expression.length && expression[i]=='?')
            root.left = convert2Tree(expression, i+1);
      
        // else we have to add it as a right child of
        // current node expression.at(0) == ':'
        else if (i < expression.length)
            root.right = convert2Tree(expression, i+1);
      
        return root;
	}

	public static void main(String[] args) {
		String exp = "a?b?c:d:e";
        BinaryTree tree = new BinaryTree();
        char[] expression=exp.toCharArray(); 
        Node root = convert2Tree(expression, 0);
        System.out.println(root);
	}
}
