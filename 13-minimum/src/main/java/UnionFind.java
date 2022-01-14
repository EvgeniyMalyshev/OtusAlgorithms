import java.util.stream.IntStream;

public class UnionFind {

    private final int[] parents;

    private final int[] ranks;

    public UnionFind(int size) {
        ranks = new int[size];
        parents = IntStream.range(0, size).toArray();
    }

    public int find(int v) {
        return parents[v] == v ? v : find(parents[v]);
    }

    public void union(int u, int v) {
        final int uParent = find(u);
        final int vParent = find(v);

        if (uParent == vParent) {
            return;
        }

        if (ranks[uParent] < ranks[vParent]) {
            parents[uParent] = vParent;
        } else {
            parents[vParent] = uParent;
            if (ranks[uParent] == ranks[vParent]) {
                ranks[uParent]++;
            }
        }
    }
}
