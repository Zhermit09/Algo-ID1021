public class PrioTree {
    Node root;

    public void add(Integer val) {

        if (root == null) {
            root = new Node(val);
            return;
        }

        Node curr = root;

        while (true) {
            if (curr.val <= val) {
                if (curr.lBranch <= curr.rBranch) {

                    curr.lBranch++;
                    if (curr.left == null) {
                        curr.left = new Node(val);
                        return;
                    }
                    curr = curr.left;
                } else {

                    curr.rBranch++;
                    if (curr.right == null) {
                        curr.right = new Node(val);
                        return;
                    }
                    curr = curr.right;
                }
            } else {
                Integer temp = curr.val;
                curr.val = val;
                val = temp;
            }
        }
    }

    public Integer remove() {
        if (root == null) {
            return null;
        }

        Node curr = root;
        Integer val = root.val;

        while (true) {
            if (curr.left != null && curr.right != null) {
                if (curr.left.val <= curr.right.val) {
                    if (promLeft(curr)) {
                        return val;
                    }
                    curr = curr.left;
                } else {
                    if (promRight(curr)) {
                        return val;
                    }
                    curr = curr.right;
                }
            } else if (curr.left != null) {
                if (promLeft(curr)) {
                    return val;
                }
                curr = curr.left;
            } else if (curr.right != null) {
                if (promRight(curr)) {
                    return val;
                }
                curr = curr.right;
            } else {
                root = null;
                return val;
            }
        }
    }

    private boolean promLeft(Node curr) {
        curr.lBranch--;
        curr.val = curr.left.val;

        if (curr.left.left == null && curr.left.right == null) {
            curr.left = null;
            return true;
        }
        return false;
    }

    private boolean promRight(Node curr) {
        curr.rBranch--;
        curr.val = curr.right.val;

        if (curr.right.left == null && curr.right.right == null) {
            curr.right = null;
            return true;
        }
        return false;
    }

    public int push(int inc) {
        if (root == null) {
            return 0;
        }

        int depth = 1;
        Node curr = root;
        root.val += inc;

        while (true) {
            if (curr.left != null && curr.right != null) {
                if (curr.val > curr.left.val || curr.val > curr.right.val) {
                    int temp = curr.val;
                    if (curr.left.val <= curr.right.val) {
                        curr.val = curr.left.val;
                        curr.left.val = temp;
                        depth++;

                        curr = curr.left;
                    } else {
                        curr.val = curr.right.val;
                        curr.right.val = temp;
                        depth++;

                        curr = curr.right;
                    }
                } else {
                    break;
                }
            } else if (curr.left != null) {
                if (curr.val > curr.left.val) {
                    int temp = curr.val;
                    curr.val = curr.left.val;
                    curr.left.val = temp;
                    depth++;

                    curr = curr.left;
                } else {
                    break;
                }
            } else if (curr.right != null) {
                if (curr.val > curr.right.val) {
                    int temp = curr.val;
                    curr.val = curr.right.val;
                    curr.right.val = temp;
                    depth++;

                    curr = curr.right;
                } else {
                    break;
                }
            } else {
                return depth;
            }
        }
        return depth;
    }

    public class Node {
        public Integer val, lBranch, rBranch;
        public Node left, right;

        public Node(int val) {
            this.val = val;
            this.left = this.right = null;
            this.lBranch = this.rBranch = 0;
        }
    }
}
