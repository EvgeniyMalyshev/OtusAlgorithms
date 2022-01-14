

import graph.Edge;

import java.util.Arrays;

public class PrimsAlgorithm {

	public static Edge[] findMST(int[][] graph) {
		final int[] keys = new int[graph.length];
		final boolean[] mstUsed = new boolean[graph.length];
		final Edge[] mst = new Edge[graph.length - 1];

		Arrays.fill(keys, 1, keys.length, Integer.MAX_VALUE);

		for (int counter = 0; counter < graph.length - 1; counter++) {
			final int u = minKey(keys, mstUsed);
			mstUsed[u] = true;

			for (int v = 0; v < graph.length; v++) {
				if (graph[u][v] != 0 && !mstUsed[v] && graph[u][v] < keys[v]) {
					mst[v - 1] = new Edge(u, v, graph[u][v]);
					keys[v] = graph[u][v];
				}
			}
		}

		return mst;
	}

	private static int minKey(int[] keys, boolean[] mstUsed) {
		int minValue = Integer.MAX_VALUE, minIndex = -1;

		for (int i = 0; i < keys.length; i++) {
			if (!mstUsed[i] && keys[i] < minValue) {
				minValue = keys[i];
				minIndex = i;
			}
		}

		return minIndex;
	}
}
