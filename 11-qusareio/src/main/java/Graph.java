

import java.util.Arrays;
import java.util.function.Consumer;

public class Graph {

    public static void dfsRecursive(int[][] graph, int startVertex, Consumer<Integer> visitor) {
        final boolean[] used = new boolean[graph.length];
        dfsRecursive(graph, startVertex, used, visitor);
    }

    public static void dfsRecursive(int[][] graph, int vertex, boolean[] used, Consumer<Integer> visitor) {
        used[vertex] = true;

        for (int adjacentVertex : graph[vertex]) {
            if (!used[adjacentVertex]) {
                dfsRecursive(graph, adjacentVertex, used, visitor);
            }
        }

        visitor.accept(vertex);
    }

    public static void dfsIterative(int[][] graph, int startVertex, Consumer<Integer> visitor) {
        final boolean[] used = new boolean[graph.length];
        dfsIterative(graph, startVertex, used, visitor);
    }

    public static void dfsIterative(int[][] graph, int startVertex, boolean[] used, Consumer<Integer> visitor) {
        final Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);

        while (!stack.empty()) {
            final int vertex = stack.pop();
            used[vertex] = true;

            for (int adjacentVertex : graph[vertex]) {
                if (!used[adjacentVertex] && !stack.contains(adjacentVertex)) {
                    stack.push(adjacentVertex);
                }
            }

            visitor.accept(vertex);
        }
    }

    public static int[][] transpose(int[][] graph) {
        final int[][] transposedGraph = new int[graph.length][];

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                final int index = graph[i][j];
                if (transposedGraph[index] == null) {
                    transposedGraph[index] = new int[1];
                } else {
                    transposedGraph[index] = Arrays.copyOf(transposedGraph[index], transposedGraph[index].length + 1);
                }
                transposedGraph[index][transposedGraph[index].length - 1] = i;
            }
        }

        return transposedGraph;
    }
}
