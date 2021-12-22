public class AVLTree extends BinarySearchTree {

    @Override
    protected Node insert(Node node, int key) {
        final Node unbalanced = super.insert(node, key);
        return rebalance(unbalanced);
    }

    @Override
    protected Node remove(Node node, int key) {
        final Node unbalanced = super.remove(node, key);
        return rebalance(unbalanced);
    }

    private Node smallLeftRotation(Node y) {
        final Node x = y.getRight();
        final Node z = x.getLeft();

        y.setLeft(x);
        y.setRight(z);

        recalculateHeight(y);
        recalculateHeight(x);

        return x;
    }

    private Node rebalance(Node node) {
        recalculateHeight(node);
        final int balance = getBalance(node);

        if (balance > 1) {
            node = getHeight(node.getRight().getLeft()) < getHeight(node.getRight().getRight()) ? smallLeftRotation(node) : bigLeftRotation(node);
        } else if (balance < -1) {
            node = getHeight(node.getLeft().getRight()) < getHeight(node.getLeft().getLeft()) ? smallRightRotation(node) : bigRightRotation(node);
        }

        return node;
    }

    private Node smallRightRotation(Node y) {
        final Node x = y.getLeft();
        final Node z = x.getRight();

        x.setRight(y);
        y.setLeft(z);

        recalculateHeight(y);
        recalculateHeight(x);

        return x;
    }

    private Node bigLeftRotation(Node node) {
        node.setRight(smallRightRotation(node.getRight()));
        return smallLeftRotation(node);
    }

    private Node bigRightRotation(Node node) {
        node.setLeft(smallLeftRotation(node.getLeft()));
        return smallRightRotation(node);
    }

    private int getHeight(Node node) {
        return node == null ? 0 : node.getHeight();
    }

    private int getBalance(Node node) {
        return node == null ? 0 : getHeight(node.getRight()) - getHeight(node.getLeft());
    }

    private void recalculateHeight(Node node) {
        if (node != null) {
            node.setHeight(Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1);
        }
    }
}
