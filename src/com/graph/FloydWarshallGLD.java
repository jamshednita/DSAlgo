package com.graph;

public class FloydWarshallGLD {
	
	public static boolean isNegCycleFloydWarshall(int[][] gMatrix, int V) {
		/* dist[][] will be the output matrix that will 
        finally have the shortest 
        distances between every pair of vertices */
		
		/* Initialize the solution matrix same as input
        graph matrix. Or we can say the initial values 
        of shortest distances are based on shortest 
        paths considering no intermediate vertex. */
		int[][] dist = gMatrix.clone();
		
		/* Add all vertices one by one to the set of 
        intermediate vertices.
        ---> Before start of a iteration, we have shortest
            distances between all pairs of vertices such 
            that the shortest distances consider only the
            vertices in set {0, 1, 2, .. k-1} as intermediate 
            vertices.
        ----> After the end of a iteration, vertex no. k is 
            added to the set of intermediate vertices and 
            the set becomes {0, 1, 2, .. k} */
		for (int k = 0; k < dist.length; k++) {
			// Pick all vertices as source one by one
			for (int i = 0; i < V; i++) {
				// Pick all vertices as destination for the
                // above picked source
				for (int j = 0; j < V; j++) {
					// If vertex k is on the shortest path from
                    // i to j, then update the value of dist[i][j]
					if(dist[i][k] + dist[k][j] < dist[i][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
					
				}
				
			}
			
		}
		
		// If distance of any verex from itself
        // becomes negative, then there is a negative
        // weight cycle.
		for (int i = 0; i < V; i++)
            if (dist[i][i] < 0)
                return true;
		
		return false;
	}

	public static void main(String[] args) {
		
		int[][] graph = { {0, 1, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 0, -1, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, -1},
                {-1, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}};

		System.out.println(isNegCycleFloydWarshall(graph, 4));
	}

}
