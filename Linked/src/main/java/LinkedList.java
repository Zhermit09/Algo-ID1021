public class LinkedList {
    Node first;

    LinkedList(int n) {
        for (int i = 0; i < n; i++) {
            add(n - i);
        }
    }

    private class Node {

        private int val;
        private Node next;

        Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }


    }

    public int getVal() {
        return first.val;
    }
    public void add(int val) {
        first = new Node(val, first);
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
                if (prev == null) {
                    first = first.next;
                    return;
                } else if (curr.next == null) {
                    prev.next = null;
                    return;
                } else {
                    prev.next = curr.next;
                    return;
                }
            }
            prev = curr;
            curr = curr.next;
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
