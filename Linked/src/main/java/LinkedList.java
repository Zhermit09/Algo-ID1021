public class LinkedList {
    Node first;

    LinkedList(int n) {
        for (int i = 0; i < n; i++) {
            add(n - i);
        }
    }

    public static class Node {

        int val;
        Node next;

        Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }


    }

    public int getVal() {
        return first.val;
    }

    public static Node cr(int val){
        return new Node(val, null);
    }
    public void add(int val) {
        first = new Node(val, first);
    }

    public void add(Node n) {
        if (first == null) {
            first = n;
            return;
        }
        n.next = first;
        first = n;
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
            }
            prev = curr;
            curr = curr.next;
        }
    }

    public void remove(Node n) {
        Node curr = first;
        Node prev = null;

        while (curr != null) {
            if (curr == n) {
                unlink(curr, prev);
                return;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    private void unlink(Node curr, Node prev) {
        if (prev == null) {
            first = first.next;
        } else if (curr.next == null) {
            prev.next = null;
        } else {
            prev.next = curr.next;
        }
    }

    public void append(LinkedList list) {
        if (first == null) {
            first = list.first;
            //list.first = null;
            return;
        }

        Node curr = first;

        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = list.first;
        //list.first = null;

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
}
