public class BRTree extends AbstractBinaryTree<TreeNode> {

    @Override
    protected TreeNode createNode(int key) {
        return new BRTreeNode(key);
    }
}
