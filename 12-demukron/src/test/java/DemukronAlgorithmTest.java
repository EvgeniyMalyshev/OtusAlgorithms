import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertArrayEquals;


class DemukronAlgorithmTest {

	private final int[][] graph = {
			{0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
			{0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
			{0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
			{0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
	};

	private final int[][] expectedResult = {
			{4, 7},
			{1, 8, 9},
			{0, 6, 13},
			{5},
			{3, 10, 11, 12},
			{2},
	};

	@Test
	void topologicalSort() {
		assertArrayEquals(expectedResult, DemukronAlgorithm.topologicalSort(graph));
	}
}
