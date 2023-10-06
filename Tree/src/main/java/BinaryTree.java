import java.util.Iterator;

public class BinaryTree implements Iterable<Integer> {
    Node root;

    public void add(Integer key, Integer val) {

        if (root == null) {
            root = new Node(key, val);
            return;
        }

        Node curr = root;

        while (true) {
            if (key < curr.key) {
                if (curr.left == null) {
                    curr.left = new Node(key, val);
                    return;
                }
                curr = curr.left;

            } else if (key > curr.key) {
                if (curr.right == null) {
                    curr.right = new Node(key, val);
                    return;
                }
                curr = curr.right;

            } else {
                curr.val = val;
                return;
            }
        }
    }

    public Integer find(Integer key) {
        if (root == null) {
            return null;
        }

        Node curr = root;

        while (true) {
            if (key < curr.key) {
                if (curr.left == null) {
                    return null;
                }
                curr = curr.left;

            } else if (key > curr.key) {
                if (curr.right == null) {
                    return null;
                }
                curr = curr.right;

            } else {
                return curr.val;
            }
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new BFSIterator(this);
    }

    public class Node {
        public Integer key, val;
        public Node left, right;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.left = this.right = null;
        }

        public void print(DFSIterator it) {
            if (left != null)
                left.print(it);
            System.out.printf("#%5s%12d%10s%12d%13s%12d%10s%10s\n", "key:", key, "value:", val, "it-value:", it.next(), "next?:", it.hasNext());
            if (right != null)
                right.print(it);
        }
    }


}
