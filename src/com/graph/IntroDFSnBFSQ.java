package com.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
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
		int V=5;
		Vector<Integer>[] adjList = new Vector[V];
		for (int i = 0; i < adjList.length; i++) {
			adjList[i] = new Vector<>();
		}
		addEdge(adjList, 0, 1);
		addEdge(adjList, 0, 2);
		addEdge(adjList, 3, 4);
		
		System.out.println(countNoOfTrees(adjList, V));
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