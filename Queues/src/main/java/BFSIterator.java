import java.util.Iterator;

public class BFSIterator implements Iterator<Integer> {
    private BinaryTree.Node it;
    private QueueArray<BinaryTree.Node> q;

    public BFSIterator(BinaryTree tree) {
        it = tree.root;
        q = new QueueArray<>();
    }

    @Override
    public boolean hasNext() {
        //return it != null || q.last != null;
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

        if (!q.empty()) { // q.last != null
            it = q.remove();
            return val;
        }

        if (it != null) {
            it = null;
        }

        return val;
    }
}