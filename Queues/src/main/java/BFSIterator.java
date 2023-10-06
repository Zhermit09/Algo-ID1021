import java.util.Iterator;

public class BFSIterator implements Iterator<Integer> {
    private BinaryTree.Node it;
    private QueueList<BinaryTree.Node> q;

    public BFSIterator(BinaryTree tree) {
        it = tree.root;
        q = new QueueList<>();
    }

    @Override
    public boolean hasNext() {
        return it != null || !q.empty();
    }

    @Override
    public Integer next() {
        Integer val = null;

        if (it != null) {
            q.add(it.left);
            q.add(it.right);
            val = it.val;
        }

        if (!q.empty()) {
            it = q.remove();
            return val;
        }

        if (it != null) {
            it = null;
        }

        return val;
    }
}