import graph.Edge;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


class PrimsAlgorithmTest {

	private final int[][] graph = {
			{0, 7, 0, 5, 0, 0, 0},
			{7, 0, 8, 9, 7, 0, 0},
			{0, 8, 0, 0, 5, 0, 0},
			{5, 9, 0, 0, 15, 6, 0},
			{0, 7, 5, 15, 0, 8, 9},
			{0, 0, 0, 6, 8, 0, 11},
			{0, 0, 0, 0, 9, 11, 0},
	};

	private final Edge[] expectedResult = new Edge[]{
			new Edge(0, 1, 7),
			new Edge(4, 2, 5),
			new Edge(0, 3, 5),
			new Edge(1, 4, 7),
			new Edge(3, 5, 6),
			new Edge(4, 6, 9),
	};

	@Test
	void findMST() {
		final Edge[] result = PrimsAlgorithm.findMST(graph);

		assertArrayEquals(expectedResult, result);
	}
}
