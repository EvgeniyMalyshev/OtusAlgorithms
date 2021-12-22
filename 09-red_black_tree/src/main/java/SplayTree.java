

public class SplayTree implements IntTree, HasSize, HasHeight {

    private SplayNode root;

    public SplayTree() {
    }

    SplayTree(SplayNode root) {
        this.root = root;
    }

    static int size(SplayNode x) {
        return (x == null) ? 0 : 1 + size(x.left) + size(x.right);
    }

    static int height(SplayNode x) {
        return (x == null) ? 0 : 1 + Math.max(height(x.left), height(x.right));
    }

    static SplayNode rotateLeft(SplayNode splayNode) {
        SplayNode right = splayNode.right;
        splayNode.right = right.left;
        right.left = splayNode;
        return right;
    }

    static SplayNode rotateRight(SplayNode splayNode) {
        SplayNode left = splayNode.left;
        splayNode.left = left.right;
        left.right = splayNode;
        return left;
    }

    static SplayNode splay(SplayNode splayNode, int key) {
        // Base cases: node is null or
        // key is present at node
        if (splayNode == null || splayNode.key == key)
            return splayNode;

        // Key lies in left subtree
        if (splayNode.key > key) {
            // Key is not in tree, we are done
            if (splayNode.left == null) return splayNode;

            // Zig-Zig (Left Left)
            if (splayNode.left.key > key) {
                // First recursively bring the
                // key as node of left-left
                splayNode.left.left = splay(splayNode.left.left, key);

                // Do first rotation for node,
                // second rotation is done after else
                splayNode = rotateRight(splayNode);
            } else if (splayNode.left.key < key) // Zig-Zag (Left Right)
            {
                // First recursively bring
                // the key as node of left-right
                splayNode.left.right = splay(splayNode.left.right, key);

                // Do first rotation for node.left
                if (splayNode.left.right != null)
                    splayNode.left = rotateLeft(splayNode.left);
            }

            // Do second rotation for node
            return (splayNode.left == null) ?
                    splayNode : rotateRight(splayNode);
        } else // Key lies in right subtree
        {
            // Key is not in tree, we are done
            if (splayNode.right == null) return splayNode;

            // Zag-Zig (Right Left)
            if (splayNode.right.key > key) {
                // Bring the key as node of right-left
                splayNode.right.left = splay(splayNode.right.left, key);

                // Do first rotation for node.right
                if (splayNode.right.left != null)
                    splayNode.right = rotateRight(splayNode.right);
            } else if (splayNode.right.key < key)// Zag-Zag (Right Right)
            {
                // Bring the key as node of
                // right-right and do first rotation
                splayNode.right.right = splay(splayNode.right.right, key);
                splayNode = rotateLeft(splayNode);
            }

            // Do second rotation for node
            return (splayNode.right == null) ?
                    splayNode : rotateLeft(splayNode);
        }
    }

    @Override
    public boolean search(int key) {
        if (root == null) {
            return false;
        }
        root = splay(root, key);
        return (root.key == key);
    }

    @Override
    public void insert(int key) {
        if (root == null) {
            root = new SplayNode(key);
            return;
        }
        root = splay(root, key);
        SplayNode splayNode = new SplayNode(key);
        if (key < root.key) {
            splayNode.left = root.left;
            splayNode.right = root;
            root.left = null;
            root = splayNode;
        } else if (key > root.key) {
            splayNode.right = root.right;
            splayNode.left = root;
            root.right = null;
            root = splayNode;
        }
    }

    @Override
    public void remove(int key) {
        if (root == null) {
            return;
        }
        root = splay(root, key);
        if (key == root.key) {
            if (root.left == null) {
                root = root.right;
            } else {
                SplayNode x = root.right;
                root = root.left;
                root = splay(root, key);
                root.right = x;
            }
        }
    }

    public int height() {
        return height(root);
    }

    public int size() {
        return size(root);
    }

}
