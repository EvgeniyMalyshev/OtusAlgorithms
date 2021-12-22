public class BinarySearchTree {

	private Node root;

	public Node getRoot() {
		return root;
	}

	public void insert(int value) {
		root = insert(root, value);
	}

	protected Node insert(Node node, int key) {
		if (node == null) {
			return new Node(key);
		}

		if (key == node.getKey()) {
			return node;
		}

		if (key < node.getKey()) {
			node.setLeft(insert(node.getLeft(), key));
		} else {
			node.setRight(insert(node.getRight(), key));
		}

		return node;
	}

	public void remove(int key) {
		root = remove(root, key);
	}

	protected Node remove(Node node, int key) {
		if (node == null) {
			return null;
		}

		if (key == node.getKey()) {
			if (node.getLeft() == null && node.getRight() == null) {
				return null;
			}

			if (node.getLeft() == null) {
				return node.getRight();
			}

			if (node.getRight() == null) {
				return node.getLeft();
			}

			final int smallestValue = findSmallestValue(node.getRight());
			node.setKey(smallestValue);
			node.setRight(remove(node.getRight(), smallestValue));

			return node;
		}

		if (key < node.getKey()) {
			node.setLeft(remove(node.getLeft(), key));
		} else {
			node.setRight(remove(node.getRight(), key));
		}

		return node;
	}

	private int findSmallestValue(Node node) {
		return node.getLeft() == null ? node.getKey() : findSmallestValue(node.getLeft());
	}

	public boolean search(int value) {
		return search(root, value);
	}

	private boolean search(Node node, int key) {
		if (node == null) {
			return false;
		}

		if (key == node.getKey()) {
			return true;
		}

		return key < node.getKey() ? search(node.getLeft(), key) : search(node.getRight(), key);
	}
}
