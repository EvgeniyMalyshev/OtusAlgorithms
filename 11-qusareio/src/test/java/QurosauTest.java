import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class QurosauTest {

	@Test
	void findStronglyConnectedComponents() {
		final int[][] graph = {{1}, {2, 4, 5}, {3, 6}, {2, 7}, {0, 5}, {6}, {5}, {3, 6}};
		final int[][] expectedResult = {{6, 5}, {2, 7, 3}, {1, 0, 4}};

		assertArrayEquals(expectedResult, QuareioAlgorithm.findStronglyConnectedComponents(graph));
	}
}
