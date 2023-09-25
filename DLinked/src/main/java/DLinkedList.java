public class DLinkedList {
    Node first;

    DLinkedList(int n) {
        for (int i = 0; i < n; i++) {
            add(n - i);
        }
    }

    public static class Node {

        int val;
        Node next;
        Node prev;

        public Node(int val, Node next, Node prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }


    }
    public static Node cr(int val){
        return new Node(val, null, null);
    }

    public void add(int val) {
        if (first == null) {
            first = new Node(val, null, null);
            return;
        }
        first = new Node(val, first, null);
        first.next.prev = first;
    }

    public void add(Node n) {
        if (first == null) {
            first = n;
            return;
        }
        first.prev = n;
        first.prev.next = first;
        first = first.prev;

    }

    public int length() {
        int i = 0;
        Node curr = first;
        while (curr != null) {
            i++;
            curr = curr.next;
        }

        return i;
    }

    public boolean find(int val) {
        Node curr = first;

        while (curr != null) {
            if (curr.val == val) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public void remove(int val) {
        Node curr = first;
        Node prev = null;

        while (curr != null) {
            if (curr.val == val) {
                unlink(curr, prev);
                return;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    public void remove(Node curr) {
        if (curr == null) {
            return;
        }
        unlink(curr, curr.prev);

    }

    void unlink(Node curr, Node prev) {
        if(prev == null && curr.next == null){
            first = null;
        }
        else if (prev == null) {
            first = first.next;
            first.prev = null;
        } else if (curr.next == null) {
            curr.prev = null;
            prev.next = null;

        } else {
            prev.next = curr.next;
            curr.next.prev = prev;

        }
    }

    public void print() {
        Node curr = first;

        if (curr != null) {
            System.out.print(curr.val);
            curr = curr.next;
        }
        while (curr != null) {
            System.out.print(", " + curr.val);
            curr = curr.next;
        }
        System.out.print("\n");
    }

    public void rprint() {
        Node curr = first;

        if (curr != null) {
            while (curr.next != null) {
                curr = curr.next;
            }

            System.out.print(curr.val);
            curr = curr.prev;

            while (curr != null) {
                System.out.print(", " + curr.val);
                curr = curr.prev;
            }
            System.out.print("\n");
        }
    }
}
