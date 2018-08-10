package com.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

public class IntroDFSnBFSQ {
	/**
	 * Description - Breadth First Search or BFS for a Graph. BFS can be performed using queue. Time Complexity = O(E+V)
	 * @param graph - Graph to be BFSed
	 * @param startVertex - starting vertex from where rest vertices are reachable.
	 */
	public static void BFS(Graph graph, int startVertex) {
		boolean[] visited = new boolean[graph.getV()]; // To track visited nodes.
		
		//BFSUtil(graph, startVertex, visited);
		// Above statement will traverse all the node reachable from startVertex. But in disconnected graph some vertices might not be reachable so to traverse them as well below code is used. 
		for (int i = 0; i < visited.length; i++) {
			if (visited[i]==false) {
				BFSUtil(graph, i, visited);
			}
		}
	}

	private static void BFSUtil(Graph graph, int startVertex, boolean[] visited) {
		LinkedList<Integer>[] adjListArr = graph.getAdjListArr();

		LinkedList<Integer> queue = new LinkedList<>(); // Queue to store unvisited node.
		queue.add(startVertex);

		while (!queue.isEmpty()) {
			Integer s = queue.poll();
			visited[s] = true;
			System.out.print(s + " ");

			Iterator<Integer> adjListItr = adjListArr[s].iterator();
			// Go to all adjecent node and add it to queue if not visited earlier.
			while (adjListItr.hasNext()) {
				Integer vertex = adjListItr.next();

				if (!visited[vertex]) {
					queue.add(vertex);
				}
			}
		}
	}
	/**
	 * Description - Depth First Search or DFS for a Graph. DFS can be implemented using stack or recursive calls.
	 * @param graph
	 * @param startVertex
	 */
	public static void DFS(Graph graph, int startVertex) {
		boolean[] visited = new boolean[graph.getV()];
		
		//DFSUtil(graph, startVertex, visited); 
		// Above statement will traverse all the node reachable from startVertex. But in disconnected graph some vertices might not be reachable so to traverse them as well below code is used. 
		
		for (int i = 0; i < visited.length; i++) {
			if(visited[i]==false)
				DFSUtil(graph, i, visited);
		}
		
	}
	private static void DFSUtil(Graph graph, int startVertex, boolean[] visited) {
		if(visited[startVertex] == false) {
			visited[startVertex] = true;
			System.out.print(startVertex+" ");
		}
		Iterator<Integer> adjListItr = graph.getAdjListArr()[startVertex].iterator();
		
		while (adjListItr.hasNext()) {
			Integer vertex = adjListItr.next();
			
			if (!visited[vertex]) {
				DFSUtil(graph, vertex, visited);
			}
			
		}
	}

	/**
	 * Description - Longest Path in a Directed Acyclic Graph. Given a Weighted Directed Acyclic Graph (DAG) and a source vertex s in it, find the longest distances from s to all other vertices in the given graph.
	 * @param wGraph
	 * @param startVertex
	 */
	public static void longestPathInDAG(WeightedGraph wGraph, int startVertex) {
		Stack<Integer> stack = new Stack<>();
		boolean[] visited = new boolean[wGraph.getV()];
		int[] dist= new int[wGraph.getV()];
		
		for (int i = 0; i < visited.length; i++) {
			if(!visited[i])
				longestPathInDAGUtil(stack, wGraph, visited, i);
		}
		
		for (int i = 0; i < dist.length; i++) {
			dist[i]=Integer.MIN_VALUE;
		}
		dist[startVertex]=0;
		
		while(!stack.isEmpty()) {
			int u = stack.peek();
			stack.pop();
			
			List<AdjListNode> adjList = wGraph.getAdjListArr()[u];
			if(adjList!=null) {
				Iterator<AdjListNode> adjListItr = adjList.iterator();
				
				while (adjListItr.hasNext()) {
					AdjListNode vNode = (AdjListNode) adjListItr.next();
					
					if(dist[vNode.getVertex()] < dist[u] + vNode.getWeight())
						dist[vNode.getVertex()] = dist[u] + vNode.getWeight();
				}
			}
		}
		
		for (int i = 0; i < dist.length; i++) {
			if (dist[i]==Integer.MIN_VALUE) {
				System.out.print("INFINITE ");
			}else {
				System.out.println(dist[i]+" ");
			}
		}
	}
	private static void longestPathInDAGUtil(Stack<Integer> stack, WeightedGraph wGraph, boolean[] visited, int v) {
		visited[v]=true;
		List<AdjListNode> adjList = wGraph.getAdjListArr()[v];
		
		if (adjList != null) {
			Iterator<AdjListNode> adjListItr = wGraph.getAdjListArr()[v].iterator();

			while (adjListItr.hasNext()) {
				AdjListNode adjListNode = (AdjListNode) adjListItr.next();
				if (!visited[adjListNode.getVertex()]) {
					longestPathInDAGUtil(stack, wGraph, visited, adjListNode.getVertex());
				}
			}
		}
		
		stack.push(v);
	}
	
	/**
	 * Description - Find a Mother Vertex in a Graph. A mother vertex in a graph G = (V,E) is a vertex v such that all other vertices in G can be reached by a path from v. Time Complexity : O(V + E).
	 * @param graph
	 * @return
	 */
	public static int motherVertex(Graph graph) {
		boolean[] visited = new boolean[graph.getV()];
		
		int lastVisited=-1; // In DFS of directed graph last visited node is one of the mother vertices.
		
		for (int u = 0; u < visited.length; u++) {
			if(!visited[u]) {
				DFSUtil(graph, u, visited);
				lastVisited = u;
			}	
		}
		
		visited = new boolean[graph.getV()]; // to set all element to false
		
		DFSUtil(graph, lastVisited, visited);
		
		for (int i = 0; i < visited.length; i++) {
			if(!visited[i])
				return -1;
		}
		return lastVisited;
	}
	
	/**
	 * Description - Transitive Closure of a Graph using DFS. Given a directed graph, find out if a vertex v is reachable from another vertex u for all vertex pairs (u, v) in the given graph. Here reachable mean that there is a path from vertex u to v. The reach-ability matrix is called transitive closure of a graph.
	 * Time Complexity = O(V2)
	 * @param graph
	 */
	public static void transitiveClosure(Graph graph) {
		boolean[][] tc = new boolean[graph.getV()][graph.getV()];
		
		for (int i = 0; i < graph.getV(); i++) {
			transitiveClosureUtilDFS(graph, i, i, tc);
		}
		
		for (int i = 0; i < graph.getV(); i++)  {
			for (int j = 0; j < graph.getV(); j++) {
				int val = (tc[i][j]?1:0);
				System.out.print(val + " ");
			}
			System.out.println();
		}
	}
	
	private static void transitiveClosureUtilDFS(Graph graph, int u, int v, boolean[][] tc) {
		tc[u][v]=true;
		// Adjecency list iterator.
		Iterator<Integer> adListItr = graph.getAdjListArr()[v].iterator();
		
		while (adListItr.hasNext()) {
			Integer vi = (Integer) adListItr.next();
			
			if (!tc[u][vi]) {
				transitiveClosureUtilDFS(graph, u, vi, tc);
			}
			
		}
	}
	/**
	 * Description - Find k-cores of an undirected graph. Given a graph G and an integer K, K-cores of the graph are connected components that are left after all vertices of degree less than k have been removed.
	 * @param g
	 * @param k
	 */
	public static void printKCores(Graph g, int k) {
		boolean[] visited = new boolean[g.getV()];
		int[] degree = new int[g.getV()];
		
		int startVertex=0, min_deg=Integer.MAX_VALUE;
		
		for (int i = 0; i < degree.length; i++) {
			degree[i] = g.getAdjListArr()[i].size();
			if(min_deg>degree[i]) {
				min_deg=degree[i];
				startVertex=i;
			}
		}
		// Update the degree of vertices starting from least degree vertex.
		printKCoresDFSUtil(startVertex, g, visited, degree, k);
		
		for (int i = 0; i < g.getV(); i++) {
			if(!visited[i])
				printKCoresDFSUtil(i, g, visited, degree, k);
		}
		// Print K-Cores
		for (int i = 0; i < g.getV(); i++) {
			if(degree[i]>=k) {
				System.out.print(i);
				Iterator<Integer> vi = g.getAdjListArr()[i].iterator();
				while(vi.hasNext()) {
					Integer v = vi.next();
					
					if(degree[v]>=k) {
						System.out.print("->"+v);
					}
				}
			}
			System.out.println();// NEW_LINE
		}
	}
	private static boolean printKCoresDFSUtil(int u, Graph g, boolean[] visited, int[] degree, int k) {
		visited[u]=true;
		
		Iterator<Integer> vi = g.getAdjListArr()[u].iterator();
		while(vi.hasNext()) {
			int v = vi.next();
			
			if(degree[u]<k)
				degree[v]=degree[v]-1;
			
			if(!visited[v]) {
				if(printKCoresDFSUtil(v, g, visited, degree, k))
					degree[u]=degree[u]-1;
			}
		}
		
		return (degree[u]<k);
	}
	
	/**
	 * Description - Iterative Depth First Traversal of Graph. Time Complexity = O(E+V)
	 * @param dirG
	 * @param start
	 */
	public static void iterativeDFS(Graph dirG, int start) {
		boolean[] visited = new boolean[dirG.getV()];
		/*for (int j = 0; j < visited.length; j++) {
			if (!visited[j]) {
				iterativeDFSUtil(j, dirG, visited);
			}
		}*/
		iterativeDFSUtil(start, dirG, visited); // This lists out all vertices reachable from start node. There might be some vertices not reachable from this. So for complete DFS above for loop cab be used.
	}

	private static void iterativeDFSUtil(int u, Graph dirG, boolean[] visited) {
		visited[u]=true;
		Stack<Integer> stack = new Stack<>();
		stack.push(u);
		
		while(!stack.isEmpty()) {
			int topVertex = stack.pop();
			visited[topVertex] = true;
			System.out.print(topVertex+" ");
			
			Iterator<Integer> adjListItr = dirG.getAdjListArr()[topVertex].iterator();
			while(adjListItr.hasNext()) {
				int v = adjListItr.next();
				if(!visited[v])
					stack.push(v);
			}
		}
	}
	/**
	 * Description - Time Complexity = O(E+V). Count the number of nodes at given level in a tree using BFS. Given a tree represented as undirected graph. Count the number of nodes at given level l. It may be assumed that vertex 0 is root of the tree.
	 * @param unDirG - A undirected graph representing n-ary tree.
	 * @param l - Level at which count has to be count and returned.
	 * @param rootVertex - Root vertex of the n-ary tree represented by the graph.
	 * @return - count of the nodes at that level.
	 */
	public static int countVerticesAtLevel(Graph unDirG, int l, int rootVertex) {
		boolean[] visited = new boolean[unDirG.getV()];
		int[] level = new int[unDirG.getV()];
		
		List<Integer> queue = new ArrayList<>();
		queue.add(rootVertex);
		level[rootVertex] = 0;
		visited[rootVertex]=true;
		
		while (!queue.isEmpty()) {
			int parent = queue.remove(0); // ENQUEUE
			
			Iterator<Integer> adjListItr = unDirG.getAdjListArr()[parent].iterator();
			
			while (adjListItr.hasNext()) {
				Integer child = (Integer) adjListItr.next();
				
				if(!visited[child]) {
					queue.add(child);
					visited[child]=true;
					
					level[child] = level[parent]+1;
				}
			}
		}
		int count =0;
		for (int i = 0; i < level.length; i++) {
			if(level[i]==l)
				count++;
		}
		
		return count;
	}
	/**
	 * Description - Time Complexity = O(E+V). Count all possible paths between two vertices. Count the total number of ways or paths that exist between two vertices in a directed graph. These paths doesn’t contain a cycle, the simple enough reason is that a cylce contain infinite number of paths and hence they create problem.
	 * @param DAG - Directed Acyclic Graph
	 * @param start - start vertex of the path
	 * @param end - end vertex of the path
	 * @return - count of all possible paths.
	 */
	public static int countPath(Graph DAG, int start, int end) {
		boolean[] visited = new boolean[DAG.getV()];
		CustSum cSum = (new IntroDFSnBFSQ()).new CustSum();
		countPathDFSUtil(DAG,start,end,visited, cSum);
		
		return cSum.sum;
	}
	private static void countPathDFSUtil(Graph dAG, int u, int end, boolean[] visited, CustSum s) {
		
		visited[u] = true;
		
		Iterator<Integer> vi = dAG.getAdjListArr()[u].iterator();
		
		while (vi.hasNext()) {
			Integer v = (Integer) vi.next();
			
			if(!visited[v]) {
				if(v==end) {
					s.sum++;
				}else
					countPathDFSUtil(dAG, v, end, visited, s);
			}
		}
		visited[u]=false;
	}

	/**
	 * Description - 
	 * @param n1
	 * @param n2
	 * @return
	 */
	public static int shortestPathInPrimes(int n1, int n2) {
		if(n1==n2)
			return 0;
		
		List<Integer> primes = sieveOfEratosthenes(n1, 9999);
		
		Graph dAG = new Graph(primes.size()); // This graph contains index as vertex instead of actual prime no.
		
		for(int i=0; i<primes.size(); i++) {
			for (int j = i+1; j < primes.size(); j++) {
				if(compare(primes.get(i), primes.get(j)))
					dAG.addUnDirectedEdge(i, j); // It's not working with Directed Graph. Which always have parent greater than child.
			}
		}
		
		int shortestPath = calculateShortestPath(dAG, primes, n2);
		
		return shortestPath;
	}
	private static int calculateShortestPath(Graph dAG, List<Integer> primes, int n2) {
		// Perform BFS to find out shortest path.
		List<Integer> bfsQueue = new ArrayList<>();
		//int[] visited = new int[primes.size()];
		boolean[] visited = new boolean[primes.size()]; // we have to keep track of all visited nodes else will end up in endless loop processing same vertex again n again.
		bfsQueue.add(0);
		
		//visited[0]=1;
		visited[0]=true;
		int count = 0;
		
		while (!bfsQueue.isEmpty()) {
			int sizeAtThisLevel = bfsQueue.size();
			count++;
			for (int i = 0; i < sizeAtThisLevel; i++) {
				int u = bfsQueue.remove(0);
				Iterator<Integer> vi = dAG.getAdjListArr()[u].iterator();
				
				while (vi.hasNext()) {
					int v = (Integer) vi.next();
					if(!visited[v]/*visited[v]==0*/) {
						visited[v]=true;
						bfsQueue.add(v);
					}

					if (n2==primes.get(v)) {
						System.out.println("*******OUTPUT******");
						return count;
					}
				}
			}

		}
		return -1;
	}
	private static boolean compare(Integer num1, Integer num2) {
		String n1 = num1.toString();
		String n2 = num2.toString();
		
		int count = 0;
		for (int i = 0; i < n1.length(); i++) {
			if(n1.charAt(i)!=n2.charAt(i))
				count++;
		}
		
		return (count==1?true:false);
	}
	private static List<Integer> sieveOfEratosthenes(int start, int max) {
		List<Integer> primes = new ArrayList<>();
		
		boolean[] nonPrimes = new boolean[max+1];
		
		for(int p=2; p*p<=max; p++) {
			for(int i=p*p; i<=max; i+=p)
				nonPrimes[i]=true;
		}
		
		for (int i = start; i <=max; i++) {
			if(!nonPrimes[i])
				primes.add(i);
		}
		
		return primes;
	}
	/**
	 * Description - Water Jug problem using BFS
					You are given a m litre jug and a n litre jug . Both the jugs are initially empty. The jugs don’t have markings to allow measuring smaller quantities. You have to use the jugs to measure d litres of water where d is less than n.
					(X, Y) corresponds to a state where X refers to amount of water in Jug1 and Y refers to amount of water in Jug2
					Determine the path from initial state (xi, yi) to final state (xf, yf), where (xi, yi) is (0, 0) which indicates both Jugs are initially empty and (xf, yf) indicates a state which could be (0, d) or (d, 0).

					The operations you can perform are:

					1. Empty a Jug, (X, Y)->(0, Y) Empty Jug 1
					2. Fill a Jug, (0, 0)->(X, 0) Fill Jug 1
					3. Pour water from one jug to the other until one of the jugs is either empty or full, (X, Y) -> (X-d, Y+d)
	 * @param a - capacity of Jug1 in liters.
	 * @param b - capacity of Jug2 in liters.
	 * @param target - target water in liters.
	 */
	public void twoJugWaterProblemBFS(int a, int b, int target)
	{
	    // Map is used to store the states, every
	    // state is hashed to binary value to 
	    // indicate either that state is visited 
	    // before or not
	    Map<Pair, Integer> m = new HashMap<>();
	    boolean isSolvable = false;
	    Vector<Pair> path = new Vector<>();
	 
	    List<Pair> q = new ArrayList<>(); // queue to maintain states
	    q.add(new Pair(0,0)); // Initialing with initial state
	 
	    while (!q.isEmpty()) {
	 
	        Pair u = q.get(0); // current state
	 
	        q.remove(0); // pop off used state
	 
	        // if this state is already visited
	        if (m.get(new Pair( u.first, u.second ))!=null && m.get(new Pair( u.first, u.second ))== 1)
	            continue;
	 
	        // doesn't met jug constraints
	        if ((u.first > a || u.second > b ||
	            u.first < 0 || u.second < 0))
	            continue;
	 
	        // filling the vector for constructing
	        // the solution path
	        path.add(new Pair( u.first, u.second ));
	 
	        // marking current state as visited
	        m.put(new Pair( u.first, u.second ), 1);
	 
	        // if we reach solution state, put ans=1
	        if (u.first == target || u.second == target) {
	            isSolvable = true;
	            if (u.first == target) {
	                if (u.second != 0)
	 
	                    // fill final state
	                    path.add(new Pair( u.first, 0 ));
	            }
	            else {
	                if (u.first != 0)
	 
	                    // fill final state
	                    path.add(new Pair( 0, u.second ));
	            }
	 
	            // print the solution path
	            int sz = path.size();
	            for (int i = 0; i < sz; i++)
	            	System.out.println("("+path.get(i).first+", "+path.get(i).second+")");
	            break;
	        }
	 
	        // if we have not reached final state 
	        // then, start developing intermediate 
	        // states to reach solution state
	        q.add(new Pair( u.first, b )); // fill Jug2
	        q.add(new Pair( a, u.second )); // fill Jug1
	 
	        for (int ap = 0; ap <= Math.max(a, b); ap++) {
	 
	            // pour amount ap from Jug2 to Jug1
	            int c = u.first + ap;
	            int d = u.second - ap;
	 
	            // check if this state is possible or not
	            if (c == a || (d == 0 && d >= 0))
	                q.add(new Pair( c, d ));
	 
	            // Pour amount ap from Jug 1 to Jug2
	            c = u.first - ap;
	            d = u.second + ap;
	 
	            // check if this state is possible or not
	            if ((c == 0 && c >= 0) || d == b)
	                q.add(new Pair( c, d ));
	        }
	 
	        q.add(new Pair( a, 0 )); // Empty Jug2
	        q.add(new Pair( 0, b )); // Empty Jug1
	    }
	 
	    // No, solution exists if ans=0
	    if (!isSolvable)
	       System.err.println("No solution");  
	}
	/**
	 * Description - Count number of trees in a forest. Given n nodes of a forest (collection of trees), find the number of trees in the forest.
	 * 
	 * Input :  edges[] = {0, 1}, {0, 2}, {3, 4}
	   Output : 2
       Explanation : There are 2 trees
                   0       3
                  / \       \
                 1   2       4
	 * 
	 * @param adjList
	 * @param V
	 * @return
	 */
	public static int countNoOfTrees(Vector<Integer>[] adjList, int V) {
		int count=0;
		boolean[] visited = new boolean[V];
		
		for (int i = 0; i < V; i++) {
			if(!visited[i]) {
				DFSTreeForestCount(visited, adjList, i);
				count++;
			}
		}
		
		return count;
	}
	private static void DFSTreeForestCount(boolean[] visited, Vector<Integer>[] adjList, int i) {
		visited[i]=true;
		
		Iterator<Integer> vi = adjList[i].iterator();
		
		while (vi.hasNext()) {
			Integer v = (Integer) vi.next();
			if(!visited[v])
				DFSTreeForestCount(visited, adjList, v);
		}
	}
	private static void addEdge(Vector<Integer>[] adjList, int u, int v) {
		adjList[u].add(v);
		adjList[v].add(u);
	}
	/**
	 * Description - BFS using vectors & queue as per the algorithm of CLRS. Breadth-first search traversal of a graph using the algorithm given in CLRS book.
	 * BFS(G,s)
		1  for each vertex u in G.V - {s}
		2     u.color = white
		3     u.d = INF
		4     u.p = NIL
		5  s.color = green
		6  s.d = 0
		7  s.p = NIL
		8  Q = NULL
		9  ENQUEUE(Q,s)
		10 while Q != NULL
		11    u = DEQUEUE(Q)
		12    for each v in G.Adj[u]
		13       if v.color == white
		14          v.color = green
		15          v.d = u.d + 1
		16          v.p = u
		17          ENQUEUE(Q,v)
		18    u.color = dark_green
	 * @param adjList
	 * @param n
	 */
	public static void bfsUsingCLRS(Vector<Integer>[] adjList, int n) {
		String[] color = new String[n];
		int[] dist = new int[n];
		int[] p = new int[n];
		
		Arrays.fill(color, "WHITE");
		Arrays.fill(p, -1);
		Arrays.fill(dist, Integer.MIN_VALUE);
		//dist[0]=0;
		//color[0]="GREEN";
		
		for (int i = 0; i < n; i++) {
			if(color[i].equalsIgnoreCase("WHITE")) {
				bfsUsingCLRSUtil(i, adjList, color, dist, p);
			}
		}
		
	}
	private static void bfsUsingCLRSUtil(int start, Vector<Integer>[] adjList, String[] color, int[] dist, int[] p) {
		List<Integer> queue = new ArrayList<>();
		queue.add(start); // ENQUEUE
		dist[start]=0;
		color[start]="GREEN";
		
		while(!queue.isEmpty()) {
			int u = queue.remove(0); // DEQUEUE
			
			System.out.print(u+" ");
			
			Iterator<Integer> vi = adjList[u].iterator();
			while (vi.hasNext()) {
				Integer v = (Integer) vi.next();
				
				if(color[v].equalsIgnoreCase("WHITE")) {
					queue.add(v); // ENQUEUE
					
					color[v] = "GREEN";
					dist[v]=dist[u]+1;
					p[v]=u;
				}
			}
			
			color[u]="DEEP_GREEN";
		}
	}
	/**
	 * Description - Level of Each node in a Tree from source node (using BFS).
	 * Time Complexity = O(E+V).
	 * @param nAryTree
	 * @param s
	 */
	public static void levelNodesTreeBFS(Graph nAryTree, int s) {
		int[] level = new int[nAryTree.getV()];
		Arrays.fill(level, -1);
		
		List<Integer> queue = new ArrayList<>();
		boolean[] visited = new boolean[nAryTree.getV()];
		
		queue.add(s);
		visited[0]=true;
		level[0]=0;
		
		while (!queue.isEmpty()) {
			int u = queue.remove(0);
			
			Iterator<Integer> vi = nAryTree.getAdjListArr()[u].iterator();
			while (vi.hasNext()) {
				Integer v = (Integer) vi.next();
				if (!visited[v]) {
					level[v]=level[u]+1;
					visited[v]=true;
					queue.add(v);
				}
			}
			
		}
		
		/*Arrays.stream(level).forEach(i->{System.out.println(i);});*/
		
		for (int i = 0; i < level.length; i++) {
			System.out.println(i+" === "+level[i]);
		}
		
	}
	/**
	 * Description - Given n and k, Construct a palindrome of size n using a binary number of size k repeating itself to wrap into the palindrome. The palindrome must always begin with 1 and contains maximum number of zeros.
	 * @param k - Size of repeating binary string.
	 * @param n - Size of palindrome.
	 */
	public static void constructBinaryPalindrome(int k, int n) {
		char[] nStr=new char[n];
		Arrays.fill(nStr, '0');
		
		int i=0;
		nStr[i]='1';
		 while(i+k<n) {
			 nStr[i+k]='1';
			 i+=k;
		 }
		 
		 i=n-1;
		 nStr[i]='1';
		 
		 while(i-k>0) {
			 nStr[i-k]='1';
			 i-=k;
		 }
		 
		 String outputPalindrome = new String(nStr);
		 
		 System.out.println("Binary string of length k = "+outputPalindrome.substring(0, k));
		 System.out.println("Constructed Palindrome = "+outputPalindrome);
	}
	
	/**
	 * Description - Transpose of a directed graph G is another directed graph on the same set of vertices with all of the edges reversed compared to the orientation of the corresponding edges in G. That is, if G contains an edge (u, v) then the converse/transpose/reverse of G contains an edge (v, u) and vice versa.
	 * @param org_g - Original Directed Graph
	 * @return - Transposed Graph
	 */
	public static Graph transpose(Graph org_g) {
		Graph tsp_g=new Graph(org_g.getV());
		
		LinkedList<Integer>[] adjList =  org_g.getAdjListArr();
		
		for (int u=0; u<adjList.length; u++) {
			for (Iterator iterator = adjList[u].iterator(); iterator.hasNext();) {
				Integer v = (Integer) iterator.next();
				
				tsp_g.addDirectedEdge(v, u);
			}
		}
		
		return tsp_g;
	}
	/**
	 * Description - Path in a Rectangle with Circles
	 * @param x - Array contains x co-ordinates of circles
	 * @param y - Array contains y co-ordinates of circles
	 * @param m
	 * @param n
	 * @param k - No of circles OR size of x,y array
	 * @param r -  radius of circle
	 * @return - true if there exist a path from start to end otherwise false.
	 */
	public static boolean isPossible(int[] x, int[] y, int m, int n, int k, int r) {
		int[][] rect = new int[m][n];
		
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				for(int p=0; p<k; p++) {
					
					if(Math.sqrt(Math.pow(x[p]-1-i, 2) + Math.pow(y[p]-1-j, 2))<=r) {
						rect[i][j]=-1; // This cell is blocked by circle.
					}
					
				}
			}
		}
		
		if(rect[0][0]==-1)
			return false;
		
		List<Integer[]> qu= new ArrayList();
		
		Integer[] start = {0,0}; // {x,y}
		qu.add(start);
		
		while (!qu.isEmpty()) {
			Integer[] curr = qu.remove(0);
			
			int elex = curr[0];
			int eley = curr[1];
			
			if(elex-1>=0 && eley-1>=0 && rect[elex-1][eley-1] == 0) {
				rect[elex-1][eley-1] = 1;
				Integer[] topLeft = {elex-1, eley-1};
				
				qu.add(topLeft);
			}
			
			if(elex-1>=0 && eley>=0 && rect[elex-1][eley] == 0) {
				rect[elex-1][eley] = 1;
				Integer[] top = {elex-1, eley};
				
				qu.add(top);
			}
			
			if(elex-1>=0 && eley+1<n && rect[elex-1][eley+1] == 0) {
				rect[elex-1][eley+1] = 1;
				Integer[] topRight = {elex-1, eley+1};
				
				qu.add(topRight);
			}
			
			if(elex>=0 && eley+1<n && rect[elex][eley+1] == 0) {
				rect[elex][eley+1] = 1;
				Integer[] right = {elex, eley+1};
				
				qu.add(right);
			}
			
			if(elex+1<m && eley+1<n && rect[elex+1][eley+1] == 0) {
				rect[elex+1][eley+1] = 1;
				Integer[] bottomRight = {elex+1, eley+1};
				
				qu.add(bottomRight);
			}
			
			if(elex+1<m && eley>=0 && rect[elex+1][eley] == 0) {
				rect[elex+1][eley] = 1;
				Integer[] bottom = {elex+1, eley};
				
				qu.add(bottom);
			}
			
			if(elex+1<m && eley-1>=0 && rect[elex+1][eley-1] == 0) {
				rect[elex+1][eley-1] = 1;
				Integer[] bottomLeft = {elex+1, eley-1};
				
				qu.add(bottomLeft);
			}
			
			if(elex>=0 && eley-1>=0 && rect[elex][eley-1] == 0) {
				rect[elex][eley-1] = 1;
				Integer[] left = {elex, eley-1};
				
				qu.add(left);
			}
		}
		
		return (rect[m-1][n-1] == 1);
	}
	/**
	 * Description - Height of a generic tree from parent array
	 * @param parentArr
	 * @return
	 */
	public static int treeHightUsingParentArr(int[] parentArr) {
		int maxHight=0;
		int[] hightArr = new int[parentArr.length];
		boolean[] visited = new boolean[parentArr.length];
		
		for(int i=0; i<parentArr.length; i++) {
			if(!visited[i]) {
				//hightArr[i] = parentArr[i]+1;
				hightArr[i] = fillHight(parentArr, hightArr, visited, i);
				maxHight = (hightArr[i]>maxHight?hightArr[i]:maxHight);
			}
		}
		
		return maxHight;
	}
	
	private static int fillHight(int[] parent, int[] hight, boolean[] visited, int node) {
		visited[node]=true;
		
		if(parent[node] == -1)
			return 0;
		
		if(!visited[parent[node]])
			return 1+fillHight(parent, hight, visited, parent[node]);
		else
			return 1+hight[parent[node]];
		
	}
	/**
	 * Description - BFS using STL for competitive coding
	 * 
	 * @param gph
	 * @param start
	 */
	public static void bfsUsingSTL(Graph gph, int start) {
		boolean[] visited = new boolean[gph.getV()];
		List<Integer> qu = new ArrayList<Integer>();
		
		qu.add(start);
		
		while(!qu.isEmpty()) {
			int u = qu.remove(0);
			
			System.out.print(u+" ");
			visited[u]=true;
			List<Integer> vi = gph.getAdjListArr()[u];
			
			for (Iterator<Integer> iterator = vi.iterator(); iterator.hasNext();) {
				Integer v = (Integer) iterator.next();
				if(!visited[v])
					qu.add(v);
			}
		}
				
	}
	/**
	 * Description - DFS for a n-ary tree (acyclic graph) represented as adjacency list.
	 * 
	 * @param adjListArr
	 * @param node
	 * @param parent
	 */
	public static void nAryTreeGphDFS(List<Integer>[] adjListArr, int node, int parent) {
		System.out.print(node+" ");
		
		Iterator<Integer> vi = adjListArr[node].iterator();
		
		while (vi.hasNext()) {
			Integer v = (Integer) vi.next();
			if(v!=parent)
				nAryTreeGphDFS(adjListArr, v, node);
		}
	}
	/**
	 * Description - Finds maximum number of edges that can be added without violating Bipartite property.
	 * @param edges
	 * @param n
	 * @return
	 */
	public static int maxEdgesToStayBipertite(Vector<Integer>[] edges, int n) {
		List<Integer> color0=new ArrayList<>();
		List<Integer> color1=new ArrayList<>();
		
		color0.add(1);
		
		for(int u=1; u<edges.length; u++) {
			Vector<Integer> edge = edges[u];
			if (!edge.isEmpty()) {
				if (color0.contains(u))
					color1.addAll(edge);
				else
					color0.addAll(edge);
			}
			
		}
		
		return (color0.size()*color1.size()) - (n-1);
	}
	/**
	 * Descrption - A Peterson Graph Problem. 
	 *             The following graph G is called a Petersen graph and its vertices have been numbered from 0 to 9. Some letters have also been assigned to vertices of G, as can be seen from the following picture:
     *             Let’s consider a walk W in graph G, which consists of L vertices W1, W2, …, WL. A string S of L letters 'A' – 'E' is realized by walk W if the sequence of letters written along W is equal to S. Vertices can be visited multiple times while walking along W.
	 * @param s
	 * @param u
	 * @return
	 */
	public static String petersonGraph(String s, int u) {
		List<Integer>[] alphabetMapping = new ArrayList[5]; // A={0,5}, B={1,6}, C={2,7}, D={3,8}, E={4,9}
		List<Integer>[] numMapping = new ArrayList[10]; // 0={1,4,5}, 1={0,2,6}, 2={1,3,7}, 3={2,4,8}, 4={0,3,9}, 5={0,7,8}, 6={1,8,9}, 7={2,5,9}, 8={3,5,6}, 9={4,6,7}
		
		for(int i=0; i<5; i++) {
			alphabetMapping[i]=new ArrayList<>();
			
			numMapping[i]=new ArrayList<>();
			numMapping[i+5]=new ArrayList<>();
		}
		
		alphabetMapping[0].add(0);
		alphabetMapping[0].add(5);
		
		alphabetMapping[1].add(1);
		alphabetMapping[1].add(6);
		
		alphabetMapping[2].add(2);
		alphabetMapping[2].add(7);
		
		alphabetMapping[3].add(3);
		alphabetMapping[3].add(8);
		
		alphabetMapping[4].add(4);
		alphabetMapping[4].add(9);
		
		numMapping[0].add(1);
		numMapping[0].add(4);
		numMapping[0].add(5);
		
		numMapping[1].add(0);
		numMapping[1].add(2);
		numMapping[1].add(6);
		
		numMapping[2].add(1);
		numMapping[2].add(3);
		numMapping[2].add(7);
		
		numMapping[3].add(2);
		numMapping[3].add(4);
		numMapping[3].add(8);
		
		numMapping[4].add(0);
		numMapping[4].add(3);
		numMapping[4].add(9);
		
		numMapping[5].add(0);
		numMapping[5].add(7);
		numMapping[5].add(8);
		
		numMapping[6].add(1);
		numMapping[6].add(8);
		numMapping[6].add(9);
		
		numMapping[7].add(2);
		numMapping[7].add(5);
		numMapping[7].add(9);
		
		numMapping[8].add(3);
		numMapping[8].add(5);
		numMapping[8].add(6);
		
		numMapping[9].add(4);
		numMapping[9].add(6);
		numMapping[9].add(7);
		
		StringBuffer resultBuff = new StringBuffer();
		resultBuff.append(u);
		
		for(int ch=1; ch<s.length(); ch++) {
			int vc = (int)(s.charAt(ch) - 'A');
			
			int v1 = alphabetMapping[vc].get(0);
			int v2 = alphabetMapping[vc].get(1);
			
			List<Integer> adj = numMapping[u];
			
			if(adj.contains(v1)) {
				resultBuff.append(v1);
				u=v1;
			}else if(adj.contains(v2)) {
				resultBuff.append(v2);
				u=v2;
			}else {
				return null;
			}
		}
		
		return resultBuff.toString();
	}
	
	/**
	 * Description - Print all paths from a given source to a destination (DFS based approach).
	 * 
	 * @param dirG
	 * @param s
	 * @param d
	 */
	public static void printAllPathDFS(Graph dirG, int s, int d) {
		List<Integer> pathTracker = new ArrayList<>();
		boolean[] visited = new boolean[dirG.getV()];
		
		pathTracker.add(s);
		//visited[s]=true;
		
		printAllPathDFSUtil(dirG, s, d, visited, pathTracker);
	}
	
	private static void printAllPathDFSUtil(Graph dirG, int u, int d, boolean[] visited, List<Integer> pathTracker) {
		visited[u]=true; // Mark the current node visited
		
		if(u==d) {
			printPath(pathTracker); // Print if we found the destination.
		}
		
		Iterator<Integer> vi = dirG.getAdjListArr()[u].iterator();
		while (vi.hasNext()) {
			Integer v = (Integer) vi.next();
			
			if(!visited[v]) {
				pathTracker.add(v);
				printAllPathDFSUtil(dirG, v, d, visited, pathTracker);
				
				pathTracker.remove(v); // This should be inside in this if case else it will delete the visited node. We only want to delete the node once it become unvisited and not in our current path.  
			}
		}
		
		visited[u]=false;
	}

	private static void printPath(List<Integer> pathTracker) {
		for (int i=0; i<pathTracker.size(); i++) {
			if(i!=0) {
				System.out.print("->");
			}
			System.out.print(pathTracker.get(i));
		}
		System.out.println(); // FOR NEW_LINE
	}
	
	/**
	 * Description - Print all paths from a given source to a destination using BFS.
	 * 
	 * @param dirG
	 * @param s
	 * @param d
	 */
	public static void printAllPathBFS(Graph dirG, int s, int d) {
		List<List<Integer>> pathQu = new ArrayList<>();
		
		List<Integer> path = new ArrayList<>();
		path.add(s);
		pathQu.add(path);
		
		while (!pathQu.isEmpty()) {
			List<Integer> frontPath = pathQu.get(0);
			pathQu.remove(0);
			
			int last = frontPath.get(frontPath.size()-1);
			if(last == d)
				printPath(frontPath);
			
			Iterator<Integer> vi = dirG.getAdjListArr()[last].iterator();
			
			while(vi.hasNext()) {
				Integer v = vi.next();
				
				if(!frontPath.contains(v)) {
					List<Integer> newPath = new ArrayList<>(frontPath);
					newPath.add(v);
					
					pathQu.add(newPath);
				}
			}
			
		}
	}
	
	/**
	 * Description - Minimum number of edges between two vertices of a Graph. Time C0mplexity -  O(E+V)
	 * 
	 * @param g
	 * @param s
	 * @param d
	 * @return
	 */
	public static int minEdge(Graph g, int s, int d) {
		boolean[] visited = new boolean[g.getV()];
		int minE = 1;
		
		List<Integer> qu = new ArrayList<>();
		qu.add(s);
		visited[s] = true;
		
		if(s==d)
			return 0;
		
		while(!qu.isEmpty()) {
			int currSize = qu.size();
			
			while(currSize>0) {
				int front = qu.remove(0);
				
				Iterator<Integer> vi =g.getAdjListArr()[front].iterator();
				
				while(vi.hasNext()) {
					Integer v = vi.next();
					
					if(v==d)
						return minE;
					
					if(!visited[v])
						qu.add(v);
				}
				currSize--;
			}
			minE++;	
		}
		
		return -1;
	}
	
	/**
	 * Description - (Doubted) - Count nodes within K-distance from all nodes in a set 
	 * Given an undirected tree with some marked nodes and a positive number K. We need to print the count of all such nodes which have distance from all marked nodes less than K that means every node whose distance from all marked nodes is less than K, should be counted in the result.
	 * @param g
	 * @param markedNodes
	 */
	public static void kDistantNode(Graph g, List<Integer> markedNodes, int k) {
		boolean[] visited = new boolean[g.getV()];
		List<Integer> qu = new ArrayList<>();
		
		Set<Integer> result = new HashSet<>();
		
		for (Iterator iterator = markedNodes.iterator(); iterator.hasNext();) {
			Integer mn = (Integer) iterator.next();
			
			visited[mn]=true;
		}
		//qu.add(markedNodes.get(0));
		//qu.remove(0);
		
		for (Iterator itr01 = markedNodes.iterator(); itr01.hasNext();) {
			Integer mrkn = (Integer) itr01.next();

			qu.add(mrkn);

			int control = 1;
			while (control < k) {

				int size = qu.size();
				while (size>0) {
					int curr = qu.get(0);
					qu.remove(0);

					List<Integer> vs = g.getAdjListArr()[curr];
					for (Iterator itr02 = vs.iterator(); itr02.hasNext();) {
						Integer v = (Integer) itr02.next();

						if (!visited[v]) {
							result.add(v);
						}

						qu.add(v);
					}
					size--;
				}
				control++;
			}
			
			qu.removeAll(qu);
		}
		
		System.out.println(result);
	}
	
	/**
	 * Description - Bidirectional Search. Searching a graph is quite famous problem and have a lot of practical use. We have already discussed here how to search for a goal vertex starting from a source vertex using BFS. In normal graph search using BFS/DFS we begin our search in one direction usually from source vertex toward the goal vertex, but what if we start search form both direction simultaneously.
	 * @param g
	 * @param s
	 * @param d
	 */
	public static void biDirectionalTraversal(Graph g, int s, int t) {
		// store visited nodes from  both direction traversal
		boolean[] s_visited = new boolean[g.getV()];
		boolean[] t_visited = new boolean[g.getV()];
		// Queue to store level nodes
		List<Integer> sq = new ArrayList<>();
		List<Integer> tq = new ArrayList<>();
		// To tracke parent for printing path
		int[] s_parent = new int[g.getV()];
		int[] t_parent = new int[g.getV()];
		
		Arrays.fill(s_parent, -1);
		Arrays.fill(t_parent, -1);
		
		sq.add(s);
		tq.add(t);
		
		//s_visited[s]=true;
		//t_visited[t]=true;
		while(!sq.isEmpty() && !tq.isEmpty()) {
			bdtBFS(g, sq, s_visited, s_parent);
			bdtBFS(g, tq, t_visited, t_parent);
			
			int intersection = hasIntersection(s_visited, t_visited);
			
			if(intersection!=-1) {
				printPath(s_parent, t_parent, s, t, intersection);
				return;
			}
		}
		
		System.err.println("PATH DOES NOT EXIST");
	}
	private static void printPath(int[] s_parent, int[] t_parent, int s, int t, int intrsn) {
		Stack<Integer> path = new Stack<>();
		int top = intrsn;
		while(top!=-1) {
			path.push(top);
			top=s_parent[top];
		}
		
		while(!path.empty()) {
			System.out.print(path.pop()+" ");
		}
		top = t_parent[intrsn];
		while(top!=-1) {
			System.out.print(top+" ");
			top = t_parent[top];
		}
	}
	private static int hasIntersection(boolean[] s_visited, boolean[] t_visited) {
		for (int i = 0; i < t_visited.length; i++) {
			if(s_visited[i] && t_visited[i])
				return i;
		}
		return -1;
	}
	private static void bdtBFS(Graph g, List<Integer> sq, boolean[] s_visited, int[] s_parent) {
		Integer u = sq.get(0);
		sq.remove(0);
		
		s_visited[u] = true;
		
		Iterator<Integer> vItr = g.getAdjListArr()[u].iterator();
		
		while(vItr.hasNext()) {
			Integer v = vItr.next();
			
			if(!s_visited[v]) {
				sq.add(v);
				s_parent[v]=u;
				
				s_visited[v] = true;
			}

		}
	}
	
	/**
	 * Description - Minimum edge reversals to make a root. Given a directed tree with V vertices and V-1 edges, we need to choose such a root (from given nodes from where we can reach to every other node) with a minimum number of edge reversal.
	 * @param edges
	 * @param e
	 */
	public void minReversalForTreeRoot(int[][] edges, int e) {
		// number of nodes are one more than number of edges
		int V=e+1;
		// data structure to store directed tree
		Vector<Pair>[] g = new Vector[V];
		
		// disRev stores two values - distance and back
	    // edge count from root node
		Pair[] disRev = new Pair[V];
		
		boolean[] visited = new boolean[V];
		
		for(int i=0; i<e; i++) {
			int u=edges[i][0];
			int v=edges[i][1];
			
			// add 0 weight in direction of u to v
	        if(g[u] == null)
	        	g[u] = new Vector<>();
	        
	        g[u].add(new Pair(v, 0));
	 
	        // add 1 weight in reverse direction
	        if(g[v] == null)
	        	g[v] = new Vector<>();
	        g[v].add(new Pair(u, 1));
		}
		
		//initialize all variables
	    for (int i = 0; i < V; i++)
	    {
	        visited[i] = false;
	        if(disRev[i] == null)
	        	disRev[i] = new Pair(0, 0);
	        else
	        	disRev[i].first = disRev[i].second = 0;
	    }
	    
	    int root = 0;
	    //dfs populates disRev data structure and
	    // store total reverse edge counts
	    
	    int totalRev = dfs(g, disRev, visited, root);
	    
	    // UnComment below lines to print each node's
	    // distance and edge reversal count from root node
	   
	    for (int i = 0; i < V; i++)
	    {
	        System.out.println( i + " : " + disRev[i].first + " " + disRev[i].second);
	    }
	   
	    int res = Integer.MAX_VALUE;
	    
	    // loop over all nodes to choose minimum edge reversal
	    for (int i = 0; i < V; i++)
	    {
	        // (reversal in path to i) + (reversal
	        // in all other tree parts)
	        int edgesToRev = (totalRev - disRev[i].second) +
	                         (disRev[i].first - disRev[i].second);
	 
	        // choose minimum among all values
	        if (edgesToRev < res)
	        {
	            res = edgesToRev;
	            root = i;
	        }
	    }
	 
	    // print the designated root and total
	    // edge reversal made
	    System.out.println(root + " " + res); 
	}
	
	private int dfs(Vector<Pair>[] g, Pair[] disRev, boolean[] visited, int u) {
		// visit current node
		visited[u] = true;
	    int totalRev = 0;
	 
	    // looping over all neighbors
	    for (int i = 0; i < g[u].size(); i++)
	    {
	        int v = g[u].get(i).first;
	        if (!visited[v])
	        {
	            // distance of v will be one more than distance of u
	            disRev[v].setFirst(disRev[u].first + 1);
	 
	            // initialize back edge count same as
	            // parent node's count
	            disRev[v].setSecond(disRev[u].second);
	 
	            // if there is a reverse edge from u to i,
	            // then only update
	            if (g[u].get(i).second>0)
	            {
	                disRev[v].setSecond(disRev[u].second + 1);
	                totalRev++;
	            }
	            totalRev += dfs(g, disRev, visited, v);
	        }
	    }
	 
	    // return total reversal in subtree rooted at u
	    return totalRev;
	}
	/**
	 * Description - Move weighting scale alternate under given constraints
	 * 				Given a weighting scale and an array of different positive weights where we have an infinite supply of each weight. Our task is to put weights on left and right pans of scale one by one in such a way that pans move to that side where weight is put i.e. each time, pans of scale moves to alternate sides.
	 * @param weight
	 * @param steps
	 */
	public static void weightOnScale(int[] weight, int steps) {
		int weightDiff = 0;
		int prevWeight = 0;
		
		int panA=0, panB=0; // PanA and panB weights.
		boolean panFlip=true; // For pan alternation
		
		for(int i=0; i<weight.length;) {
			int currWeight = weight[i];
			
			if(currWeight>weightDiff && currWeight!=prevWeight) {
				System.out.print(currWeight + " ");
				steps--;
				
				prevWeight = currWeight;
				
				if(panFlip) {
					panA+=currWeight;
					panFlip=false;
				}else {
					panB+=currWeight;
					panFlip=true;
				}
				
				weightDiff=(panA-panB)>0?(panA-panB):(panB-panA);
				
				i=0;
			}else
				i++;
			
			if(steps == 0)
				break;
		}
	}
	
	public static void main(String[] args) {
		/*Graph grph = new Graph(5);
		grph.addUnDirectedEdge(0, 1);
		grph.addUnDirectedEdge(0, 4);
		grph.addUnDirectedEdge(1, 2);
		grph.addUnDirectedEdge(1, 3);
		grph.addUnDirectedEdge(1, 4);
		grph.addUnDirectedEdge(2, 3);
		grph.addUnDirectedEdge(3, 4);
		
		grph.printEdge();*/
		
		/*Graph dirGraph = new Graph(4);
		dirGraph.addDirectedEdge(0, 1);
		dirGraph.addDirectedEdge(0, 2);
		dirGraph.addDirectedEdge(1, 2);
		dirGraph.addDirectedEdge(2, 0);
		dirGraph.addDirectedEdge(2, 3);
		dirGraph.addDirectedEdge(3, 3);
		
		//BFS(dirGraph, 2);
		DFS(dirGraph, 2);*/
		/*WeightedGraph wG = new WeightedGraph(6);
		
		wG.addDirectedEdge(0, 1, 5);
		wG.addDirectedEdge(0, 2, 3);
		wG.addDirectedEdge(1, 3, 6);
		wG.addDirectedEdge(1, 2, 2);
		wG.addDirectedEdge(2, 4, 4);
		wG.addDirectedEdge(2, 5, 2);
		wG.addDirectedEdge(2, 3, 7);
		wG.addDirectedEdge(3, 5, 1);
		wG.addDirectedEdge(3, 4, -1);
		wG.addDirectedEdge(4, 5, -2);
		
		longestPathInDAG(wG, 1);*/
		
		/*Graph mGraph = new Graph(7);
		mGraph.addDirectedEdge(0, 1);
		mGraph.addDirectedEdge(0, 2);
		mGraph.addDirectedEdge(1, 3);
		mGraph.addDirectedEdge(4, 1);
		mGraph.addDirectedEdge(6, 4);
		mGraph.addDirectedEdge(5, 6);
		mGraph.addDirectedEdge(5, 2);
		mGraph.addDirectedEdge(6, 0);
		
		System.out.println("Mother Vertex :: "+motherVertex(mGraph));*/
		
		/*Graph tcGraph = new Graph(4);
		tcGraph.addDirectedEdge(0, 1);
		tcGraph.addDirectedEdge(0, 2);
		tcGraph.addDirectedEdge(1, 2);
		tcGraph.addDirectedEdge(2, 0);
		tcGraph.addDirectedEdge(2, 3);
		tcGraph.addDirectedEdge(3, 3);
		
		transitiveClosure(tcGraph);*/
		
		/*Graph gk = new Graph(9);
		gk.addUnDirectedEdge(0, 1);
		gk.addUnDirectedEdge(0, 2);
		gk.addUnDirectedEdge(1, 2);
		gk.addUnDirectedEdge(1, 5);
		gk.addUnDirectedEdge(2, 3);
		gk.addUnDirectedEdge(2, 4);
		gk.addUnDirectedEdge(2, 5);
		gk.addUnDirectedEdge(2, 6);
		gk.addUnDirectedEdge(3, 4);
		gk.addUnDirectedEdge(3, 6);
		gk.addUnDirectedEdge(3, 7);
		gk.addUnDirectedEdge(4, 6);
		gk.addUnDirectedEdge(4, 7);
		gk.addUnDirectedEdge(5, 6);
		gk.addUnDirectedEdge(5, 8);
		gk.addUnDirectedEdge(6, 7);
		gk.addUnDirectedEdge(6, 8);
		printKCores(gk, 3);*/
		
		/*Graph g = new Graph(5);
		g.addDirectedEdge(1, 0);
		g.addDirectedEdge(2, 1);
		g.addDirectedEdge(3, 4);
		g.addDirectedEdge(4, 0);
		g.addDirectedEdge(0, 3);
		g.addDirectedEdge(0, 2);
		
		iterativeDFS(g, 0);*/
		
		/*Graph nAryTreeUnDG = new Graph(6);
		nAryTreeUnDG.addUnDirectedEdge(0, 1);
		nAryTreeUnDG.addUnDirectedEdge(0, 2);
		nAryTreeUnDG.addUnDirectedEdge(1, 3);
		nAryTreeUnDG.addUnDirectedEdge(2, 4);
		nAryTreeUnDG.addUnDirectedEdge(2, 5);
		
		System.out.println(countVerticesAtLevel(nAryTreeUnDG, 2, 0));*/
		
		/*Graph g = new Graph(4);
		g.addDirectedEdge(0, 1);
		g.addDirectedEdge(0, 2);
		g.addDirectedEdge(0, 3);
		g.addDirectedEdge(2, 0);
		g.addDirectedEdge(2, 1);
		g.addDirectedEdge(1, 3);
		
		System.out.println(countPath(g, 2, 3));*/
		/*System.out.println(shortestPathInPrimes(1373, 8017)); // 1373, 8017 ... 1033, 8179
		
		(new IntroDFSnBFSQ()).twoJugWaterProblemBFS(4, 3, 2);
		(new IntroDFSnBFSQ()).twoJugWaterProblemBFS(3, 4, 2);*/
		/*int V=5;
		Vector<Integer>[] adjList = new Vector[V];
		for (int i = 0; i < adjList.length; i++) {
			adjList[i] = new Vector<>();
		}
		addEdge(adjList, 0, 1);
		addEdge(adjList, 0, 2);
		addEdge(adjList, 3, 4);
		
		System.out.println(countNoOfTrees(adjList, V));*/
		
		/*int V=7;
		Vector<Integer>[] adjList = new Vector[V];
		for (int i = 0; i < adjList.length; i++) {
			adjList[i] = new Vector<>();
		}
		
		addEdge(adjList, 0, 1);
	    addEdge(adjList, 0, 2);
	    addEdge(adjList, 1, 3);
	    addEdge(adjList, 1, 4);
	    addEdge(adjList, 2, 5);
	    addEdge(adjList, 2, 6);
	    
	    bfsUsingCLRS(adjList, V);*/
		
		/*Graph nAryTree = new Graph(8);
		
		nAryTree.addDirectedEdge(0,1);
		nAryTree.addDirectedEdge(0,2);
		nAryTree.addDirectedEdge(1,3);
		nAryTree.addDirectedEdge(1,4);
		nAryTree.addDirectedEdge(1,5);
		nAryTree.addDirectedEdge(2,5);
		nAryTree.addDirectedEdge(2,6);
		nAryTree.addDirectedEdge(6,7);
		
		levelNodesTreeBFS(nAryTree, 0);
		
		constructBinaryPalindrome(3, 5);*/
		
		/*Graph org_gph = new Graph(5);
		org_gph.addDirectedEdge(0, 1);
		org_gph.addDirectedEdge(0, 3);
		org_gph.addDirectedEdge(0, 4);
		
		org_gph.addDirectedEdge(2, 0);
		
		org_gph.addDirectedEdge(3, 2);
		
		org_gph.addDirectedEdge(4, 3);
		org_gph.addDirectedEdge(4, 1);
		
		Graph tps_gph = transpose(org_gph);
		
		System.out.println(tps_gph);
		int[] x = {1,1};//{1,3};
		int[] y = {2,3};//{3,3};
		System.out.println(isPossible(x, y, 5, 5, 2, 1));
		
		int parent[] = { -1, 0, 0, 0, 3, 1, 1, 2 };
		
		System.out.println(treeHightUsingParentArr(parent));*/
		
		/*// Number of nodes
        int nodes = 5;
      
        // Adjacency list
        LinkedList<Integer> list[] = new LinkedList[nodes+1];     
         
        for (int i = 0; i < list.length; i++){
            list[i] = new LinkedList<Integer>();
        }
         
        // Designing the tree
        list[1].add(2);
        list[2].add(1);
      
        list[1].add(3);
        list[3].add(1);
      
        list[2].add(4);
        list[4].add(2);
      
        list[3].add(5);
        list[5].add(3);
      
        // Function call
        nAryTreeGphDFS(list, 1, 0);*/
		/*int n=3;//5;
		Vector<Integer>[] edges = new Vector[n+1];
		
		for (int i = 0; i < edges.length; i++) {
			edges[i]=new Vector<>();
		}
		
		edges[1].add(2);
		edges[1].add(3);
		
		edges[2].add(4);
		edges[3].add(5);
		
		System.out.println(maxEdgesToStayBipertite(edges, n));
		
		String walk = "ABB";
		System.out.println(petersonGraph(walk, (walk.charAt(0)-'A')));
		
		System.out.println(petersonGraph(walk, (walk.charAt(0)-'A' + 5)));
		
		Graph printPathG = new Graph(4);
		
		printPathG.addDirectedEdge(0,1);
		printPathG.addDirectedEdge(0,2);
		printPathG.addDirectedEdge(0,3);
		
		printPathG.addDirectedEdge(2,0);
		printPathG.addDirectedEdge(2,1);
		
		printPathG.addDirectedEdge(1,3);
		
		//printAllPathDFS(printPathG, 2, 3);
		printAllPathBFS(printPathG, 2, 3);*/
		
		/*int n = 9;
		
		Graph minEdgeG = new Graph(n);
		minEdgeG.addUnDirectedEdge(1, 0);
		minEdgeG.addUnDirectedEdge(1, 2);
		minEdgeG.addUnDirectedEdge(0, 2);
		minEdgeG.addUnDirectedEdge(0, 4);
		minEdgeG.addUnDirectedEdge(2, 5);
		minEdgeG.addUnDirectedEdge(4, 3);
		minEdgeG.addUnDirectedEdge(6, 4);
		
		System.out.println(minEdge(minEdgeG, 1, 5));
		System.out.println(minEdge(minEdgeG, 0, 5));
		System.out.println(minEdge(minEdgeG, 1, 3));
		
		Graph gh = new Graph(10);
		
		gh.addUnDirectedEdge(1, 0);
		gh.addUnDirectedEdge(0, 8);
		
		gh.addUnDirectedEdge(0, 3);
		gh.addUnDirectedEdge(2, 3);
		
		gh.addUnDirectedEdge(3, 6);
		gh.addUnDirectedEdge(3, 7);
		
		gh.addUnDirectedEdge(3, 5);
		gh.addUnDirectedEdge(4, 5);
		gh.addUnDirectedEdge(5, 9);
		
		List<Integer> mn = new ArrayList<>();
		
		mn.add(1);
		mn.add(2);
		mn.add(4);
		
		kDistantNode(gh, mn, 3);*/
		
		Graph bg = new Graph(15);
		bg.addUnDirectedEdge(0, 4);
		bg.addUnDirectedEdge(1, 4);
		
		bg.addUnDirectedEdge(4, 6);
		
		bg.addUnDirectedEdge(2, 5);
		bg.addUnDirectedEdge(3, 5);
		
		bg.addUnDirectedEdge(5, 6);
		
		bg.addUnDirectedEdge(6, 7);
		bg.addUnDirectedEdge(7, 8);
		
		bg.addUnDirectedEdge(8, 9);
		bg.addUnDirectedEdge(8, 10);
		
		bg.addUnDirectedEdge(9, 11);
		bg.addUnDirectedEdge(9, 12);
		
		bg.addUnDirectedEdge(10, 13);
		bg.addUnDirectedEdge(10, 14);
		
		biDirectionalTraversal(bg, 0, 14);
		
		/*
		 * int edges[][] = { {0, 1}, {2, 1}, {3, 2}, {3, 4}, {5, 4}, {5, 6}, {7, 6} };
		 * 
		 * (new IntroDFSnBFSQ()).minReversalForTreeRoot(edges, 7);
		 */

		int[] wt = { 2, 3, 5, 6 };

		weightOnScale(wt, 10);

	}
	
	class CustSum{
		int sum=0;
	}
	
	class Pair{
		int first, second;
		
		public Pair(int first, int second) {
			this.first=first;
			this.second=second;
		}

		public int getFirst() {
			return first;
		}

		public void setFirst(int first) {
			this.first = first;
		}

		public int getSecond() {
			return second;
		}

		public void setSecond(int second) {
			this.second = second;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + first;
			result = prime * result + second;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (first != other.first)
				return false;
			if (second != other.second)
				return false;
			return true;
		}

		private IntroDFSnBFSQ getOuterType() {
			return IntroDFSnBFSQ.this;
		}
	}
}