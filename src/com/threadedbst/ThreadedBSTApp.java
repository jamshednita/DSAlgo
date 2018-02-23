package com.threadedbst;

public class ThreadedBSTApp {
	/**
	 * Description - Threaded Binary Tree | Insertion. O(n)
	 * 
	 * @param root - Root of the threaded BST.
	 * @param data - Key for the new node to be inserted.
	 * @return - Root of the modified tree.
	 */
	public static ThreadedNode insert(ThreadedNode root, int data){
		if(root == null)
			return new ThreadedNode(data);
		
		if(root.getData() > data){
			if(root.islThread()){
				ThreadedNode ln=new ThreadedNode(data);
				
				ln.setlThread(root.islThread()); // New node's left thread = Root's left thread
				ln.setLeft(root.getLeft()); // New node's left thread = Root's left thread
				
				ln.setrThread(true); // New node successor is root
				ln.setRight(root); // New node successor is root
				root.setlThread(false);// Now, root is not pointing to it's predecessor anymore.
				root.setLeft(ln);
			}else{
				root.setLeft(insert(root.getLeft(), data));
				if(root.getLeft().getRight() == null){
					root.getLeft().setRight(root);
					root.getLeft().setrThread(true);
				}
			}
			
		}
		else{
			if(root.isrThread()){
				ThreadedNode rn=new ThreadedNode(data);
				
				rn.setrThread(root.isrThread()); // New node's right thread = Root's right thread
				rn.setRight(root.getRight()); // New node's right thread = Root's right thread
				
				rn.setlThread(true); // New node predecessor is root
				rn.setLeft(root); // New node predecessor is root
				root.setrThread(false);// Now, root is not pointing to it's successor anymore.
				root.setRight(rn);
			}else{
				root.setRight(insert(root.getRight(), data));
				if(root.getRight().getLeft() == null){
					root.getRight().setLeft(root);
					root.getRight().setlThread(true);
				}
			}
		}
		
		return root;
	}

	public static ThreadedNode delete(ThreadedNode root, int data){
		if(root == null)
			return null;
		if(root.getData() == data){
			if((root.getLeft() == null && root.getRight() == null)){
				// If node is root.
				return null;
			}else if(root.getLeft() == null && root.isrThread()){
				// Leftmost leaf node
				return null;
			}else if(root.getRight() == null && root.islThread()){
				// Rightmost leaf node
				return null;
			}else if(root.getLeft() != null && !root.islThread() && root.isrThread()){
				// If the node is having one left child
				root.getLeft().setrThread(root.isrThread());
				root.getLeft().setRight(root.getRight());
				
				return root.getLeft();
			}else if(root.getRight() != null && !root.isrThread() && root.islThread()){
				// If the node is having one right child
				root.getRight().setlThread(root.islThread());
				root.getRight().setLeft(root.getLeft());
				
				return root.getRight();
			}else if(root.islThread() && root.isrThread()){
				// Case if node is leaf node with both lthread and rthread enabled.
				if(root.getRight().getLeft() == root){
					root.getRight().setlThread(true);
					return root.getLeft();
				} else if(root.getLeft().getRight() == root){
					root.getLeft().setrThread(true);
					return root.getRight();
				}
				/*if(rootKey<root.getData())
					return root.getLeft();
				else
					return root.getRight();*/
			}else{
				// This is the case when node have both child
				int sucsr = getSuccessor(root.getRight());
				root.setData(sucsr);
				//return delete(root.getRight(), sucsr);
				root.setRight(delete(root.getRight(), sucsr));
				return root;
			}
		}
		else if(root.getData()>data){
			//ThreadedNode prevLeft = root.getLeft();
			root.setLeft(delete(root.getLeft(), data));
			/*if(root.getLeft() != null && root.getLeft().isrThread()){
				root.getLeft().setRight(root);
			}*/
			
			/*if((prevLeft != root.getLeft() && prevLeft.getData() != root.getLeft().getData()) && root.getLeft().getRight() == root){
				//root.getLeft().setrThread(true);
				root.setlThread(true);
			}*/
		}else{
			//ThreadedNode prevRight=root.getRight();
			root.setRight(delete(root.getRight(), data));
			/*if(root.getRight() == null){
				//root.setR
			}else */
			/*if(root.getRight()!=null && root.getRight().islThread()){
				root.getRight().setLeft(root);
			}*/

			/*if((prevRight != root.getRight() && prevRight.getData() != root.getRight().getData()) && root.getRight().getLeft() == root){
				//root.getRight().setlThread(true);
				root.setrThread(true);
			}*/
		}
		
		return root;
	}
	private static int getSuccessor(ThreadedNode node) {
		// TODO Auto-generated method stub
		if(node.islThread())
			return node.getData();
		else{
			return getSuccessor(node.getLeft());
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ThreadedNode root = new ThreadedNode(40);
		insert(root, 20);
		insert(root, 25);
		insert(root, 15);
		insert(root,60);
		insert(root, 75);
		insert(root, 50);
		insert(root, 10);
		
		System.out.println(root.getData());
		
		delete(root, 25);
		System.out.println(root.getData());
		delete(root, 60);
		System.out.println(root.getData());
		delete(root, 15);
		System.out.println(root.getData());
	}

}
