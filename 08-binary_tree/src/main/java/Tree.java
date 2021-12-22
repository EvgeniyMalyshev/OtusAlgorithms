import java.util.Random;

public class Tree extends BinarySearchTree {

	private final Random random = new Random();

	private Node merge(Node leftNode, Node rightNode) {
		if (leftNode == null) {
			return rightNode;
		}

		if (rightNode == null) {
			return leftNode;
		}

		if (leftNode.getPriority() > rightNode.getPriority()) {
			leftNode.setRight(merge(leftNode.getRight(), rightNode));
			return leftNode;
		} else {
			rightNode.setLeft(merge(leftNode, rightNode.getLeft()));
			return rightNode;
		}
	}

	private SplitResult split(Node node, int key) {
		final SplitResult result = new SplitResult();

		if (node == null) {
			return result;
		}

		if (node.getKey() <= key) {
			result.leftNode = node;
			final SplitResult rightSplit = split(node.getRight(), key);
			result.leftNode.setRight(rightSplit.leftNode);
			result.rightNode = rightSplit.rightNode;
		} else {
			result.rightNode = node;
			final SplitResult leftSplit = split(node.getLeft(), key);
			result.rightNode.setLeft(leftSplit.rightNode);
			result.leftNode = leftSplit.leftNode;
		}

		return result;
	}

	@Override
	protected Node insert(Node node, int key) {
		Node newNode = new Node(key);
		newNode.setPriority(random.nextInt());
		final SplitResult splitResult = split(node, key);
		return merge(merge(splitResult.leftNode, newNode), splitResult.rightNode);
	}

	@Override
	protected Node remove(Node node, int key) {
		final SplitResult leftSplit = split(node, key - 1);
		final SplitResult rightSplit = split(leftSplit.rightNode, key);
		return merge(leftSplit.leftNode, rightSplit.rightNode);
	}

	private static class SplitResult {

		private Node leftNode;

		private Node rightNode;
	}

}
