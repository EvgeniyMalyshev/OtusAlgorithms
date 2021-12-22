import java.util.Optional;
import java.util.Random;

public class RandomBRTree implements IntTree, HasSize {

    private static final Random random = new Random(System.currentTimeMillis());

    private RandomNode root;

    static int nodeSize(RandomNode p) {
        return (p == null) ? 0 : p.size;
    }

    static void updateSize(RandomNode p) {
        p.size = nodeSize(p.left) + nodeSize(p.right) + 1;
    }

    static RandomNode rotateRight(RandomNode p) {
        RandomNode q = p.left;
        if (q == null) {
            return p;
        }
        p.left = q.right;
        q.right = p;
        q.size = p.size;
        updateSize(p);
        return q;
    }

    static RandomNode rotateLeft(RandomNode q) {
        RandomNode p = q.right;
        if (p == null) {
            return q;
        }
        q.right = p.left;
        p.left = q;
        p.size = q.size;
        updateSize(q);
        return p;
    }

    static RandomNode insertRoot(RandomNode p, int key) {
        if (p == null) {
            return new RandomNode(key);
        }
        if (key < p.key) {
            p.left = insertRoot(p.left, key);
            updateSize(p);
            return rotateRight(p);
        } else {
            p.right = insertRoot(p.right, key);
            updateSize(p);
            return rotateLeft(p);
        }
    }

    static RandomNode insert(RandomNode p, int key) {
        if (p == null) {
            return new RandomNode(key);
        }
        if (p.key == key) {
            return p;
        }
        if (random.nextInt() % (p.size + 1) == 0) {
            return insertRoot(p, key);
        }
        if (p.key > key) {
            p.left = insert(p.left, key);
        } else {
            p.right = insert(p.right, key);
        }
        updateSize(p);
        return p;
    }

    static Optional<RandomNode> find(RandomNode p, int key) {
        if (p == null) {
            return Optional.empty();
        }
        if (key == p.key) {
            return Optional.of(p);
        }
        if (key < p.key) {
            return find(p.left, key);
        } else {
            return find(p.right, key);
        }
    }

    static RandomNode remove(RandomNode p, int k) {
        if (p == null) {
            return null;
        }
        if (p.key == k) {
            RandomNode q = join(p.left, p.right);
            p.left = null;
            p.right = null;
            return q;
        } else if (k < p.key) {
            p.left = remove(p.left, k);
            updateSize(p);
        } else {
            p.right = remove(p.right, k);
            updateSize(p);
        }
        return p;
    }

    static RandomNode join(RandomNode p, RandomNode q) {
        if (p == null) return q;
        if (q == null) return p;
        if (random.nextInt() % (p.size + q.size) < p.size) {
            p.right = join(p.right, q);
            updateSize(p);
            return p;
        } else {
            q.left = join(p, q.left);
            updateSize(q);
            return q;
        }
    }

    @Override
    public void insert(int key) {
        root = insert(root, key);
    }

    @Override
    public boolean search(int key) {
        return find(root, key).isPresent();
    }

    @Override
    public void remove(int key) {
        root = remove(root, key);
    }

    public int size() {
        return nodeSize(root);
    }
}
