import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RandomBRTreeAdvancedTest {

    private static final int HEAD_KEY = 10;
    private static final int LEFT_KEY = 5;
    private static final int RIGHT_KEY = 20;
    private static final int LEFT_LEFT_KEY = 1;
    private static final int LEFT_RIGHT_KEY = 7;
    private static final int RIGHT_LEFT_KEY = 15;
    private static final int RIGHT_RIGHT_KEY = 25;
    private static final int[] KEYS = new int[]{
            HEAD_KEY, LEFT_KEY, RIGHT_KEY, LEFT_LEFT_KEY, LEFT_RIGHT_KEY, RIGHT_LEFT_KEY, RIGHT_RIGHT_KEY};

    private RandomNode root;

    @BeforeEach
    void setUp() {
        root = constructFullTree();
    }

    private RandomNode constructFullTree() {
        RandomNode root = new RandomNode(HEAD_KEY);
        root.left = new RandomNode(LEFT_KEY);
        root.left.left = new RandomNode(LEFT_LEFT_KEY);
        root.left.right = new RandomNode(LEFT_RIGHT_KEY);
        root.right = new RandomNode(RIGHT_KEY);
        root.right.left = new RandomNode(RIGHT_LEFT_KEY);
        root.right.right = new RandomNode(RIGHT_RIGHT_KEY);
        RandomBRTree.updateSize(root.right);
        RandomBRTree.updateSize(root.left);
        RandomBRTree.updateSize(root);
        return root;
    }

    @Test
    void testNodeSize() {
        assertEquals(7, root.size);
        assertEquals(7, RandomBRTree.nodeSize(root));
        assertEquals(3, root.left.size);
        assertEquals(3, RandomBRTree.nodeSize(root.left));
        assertEquals(3, root.right.size);
        assertEquals(3, RandomBRTree.nodeSize(root.right));
        assertEquals(1, root.left.left.size);
        assertEquals(1, RandomBRTree.nodeSize(root.left.left));
        assertEquals(1, root.left.right.size);
        assertEquals(1, RandomBRTree.nodeSize(root.left.right));
        assertEquals(1, root.right.left.size);
        assertEquals(1, RandomBRTree.nodeSize(root.right.left));
        assertEquals(1, root.right.right.size);
        assertEquals(1, RandomBRTree.nodeSize(root.right.right));
    }

    @Test
    public void rotateLeftOfLeaf() {
        rotateLeftOfLeaf(root.left.left);
        rotateLeftOfLeaf(root.left.right);
        rotateLeftOfLeaf(root.right.left);
        rotateLeftOfLeaf(root.right.right);
    }

    private void rotateLeftOfLeaf(RandomNode leaf) {
        RandomNode node = RandomBRTree.rotateLeft(leaf);
        assertEquals(leaf.key, node.key);
        assertEquals(1, leaf.size);
        assertEquals(KEYS.length, root.size);
    }

    @Test
    public void rotateRightOfLeaf() {
        rotateRightOfLeaf(root.left.left);
        rotateRightOfLeaf(root.left.right);
        rotateRightOfLeaf(root.right.left);
        rotateRightOfLeaf(root.right.right);
    }

    private void rotateRightOfLeaf(RandomNode leaf) {
        RandomNode node = RandomBRTree.rotateRight(leaf);
        assertEquals(leaf.key, node.key);
        assertEquals(1, leaf.size);
        assertEquals(KEYS.length, root.size);
    }

    @Test
    public void insertRoot() {
        root = RandomBRTree.insertRoot(root, 40);
        assertEquals(KEYS.length + 1, root.size);
    }
}
