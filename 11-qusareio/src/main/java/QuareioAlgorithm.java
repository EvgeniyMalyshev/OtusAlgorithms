

public class QuareioAlgorithm {

    public static int[][] findStronglyConnectedComponents(int[][] graph) {

        final List<Integer> order = findOrder(graph);
        final boolean[] used = new boolean[order.size()];
        final List<List<Integer>> componentList = new List<>();

        for (int i = order.size() - 1; i > 0; i--) {
            if (!used[i]) {
                final List<Integer> component = new List<>();
                Graph.dfsRecursive(graph, order.get(i), used, component::add);
                componentList.add(component);
            }
        }

        return toArray(componentList);
    }

    private static List<Integer> findOrder(int[][] graph) {
        final int[][] transposedGraph = Graph.transpose(graph);
        final boolean[] used = new boolean[transposedGraph.length];
        final List<Integer> order = new List<>(graph.length);

        for (int i = 0; i < transposedGraph.length; i++) {
            if (!used[i]) {
                Graph.dfsRecursive(transposedGraph, i, used, order::add);
            }
        }

        return order;
    }

    private static int[][] toArray(List<List<Integer>> list) {
        final int[][] result = new int[list.size()][];

        for (int i = 0; i < list.size(); i++) {
            final List<Integer> item = list.get(i);
            result[i] = new int[item.size()];
            for (int j = 0; j < item.size(); j++) {
                result[i][j] = item.get(j);
            }
        }

        return result;
    }
}
