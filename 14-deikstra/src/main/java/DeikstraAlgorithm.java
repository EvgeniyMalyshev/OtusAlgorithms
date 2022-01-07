import graph.Array;
import graph.Edge;
import graph.List;

import java.util.Arrays;

public class DeikstraAlgorithm {

    private final Integer[][] graph;

    private final int startVertex;

    private final float[] distances;

    private final int[] prev;

    private final boolean[] used;

    public DeikstraAlgorithm(Integer[][] graph, int startVertex) {
        this.graph = graph;
        this.startVertex = startVertex;

        distances = new float[graph.length];
        Arrays.fill(distances, Float.POSITIVE_INFINITY);
        distances[startVertex] = 0f;

        prev = new int[graph.length];
        used = new boolean[graph.length];

        findShortestPaths();
    }

    public Edge[] getShortestPath(int destination) {
        if (destination == startVertex) {
            return new Edge[]{};
        }

        final List<Edge> path = new List<>(graph.length - 1);
        int vertex = destination;

        while (vertex != startVertex) {
            final int previous = prev[vertex];
            path.add(new Edge(previous, vertex, graph[previous][vertex]));
            vertex = previous;
        }

        final Edge[] result = path.toArray(new Edge[path.size()]);
        Array.reverse(result);

        return result;
    }

    private void findShortestPaths() {
        int vertex = findMinNotUsedVertex();

        while (vertex != -1) {
            for (int i = 0; i < graph[vertex].length; i++) {
                final Integer neighborDistance = graph[vertex][i];
                if (neighborDistance != null) {
                    final float distance = distances[vertex] + neighborDistance;
                    if (distance < distances[i]) {
                        distances[i] = distance;
                        prev[i] = vertex;
                    }
                }
            }

            used[vertex] = true;
            vertex = findMinNotUsedVertex();
        }
    }

    private int findMinNotUsedVertex() {
        int min = -1;

        for (int i = 0; i < distances.length; i++) {
            if (!used[i] && (min == -1 || distances[i] < distances[min])) {
                min = i;
            }
        }

        return min;
    }
}
