public class NBTree extends AbstractBinaryTree<TreeNode> {

    @Override
    protected TreeNode createNode(int key) {
        return new NBTreeNode(key);
    }
}
