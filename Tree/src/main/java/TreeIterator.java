import java.util.Iterator;
import java.util.Stack;

public class TreeIterator implements Iterator<Integer> {
    private BinaryTree.Node it;
    private Stack<BinaryTree.Node> stack;

    public TreeIterator(BinaryTree tree) {
        it = tree.root;
        stack = new Stack<>();
    }

    @Override
    public boolean hasNext() {
        return !stack.empty() || it != null;
    }

    @Override
    public Integer next() {

        while (it != null) {
            stack.push(it);
            it = it.left;
        }

        if (!stack.empty()) {
            it = stack.pop();
            int temp = it.val;
            it = it.right;
            return temp;
        }

        return null;
    }
}