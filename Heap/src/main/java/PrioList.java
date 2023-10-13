public class PrioList {
    Node first;

    private class Node {
        Integer item;
        Node next;

        private Node(Integer item) {
            this.item = item;
            this.next = null;
        }

        private Node(Integer item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public void add(Integer item) {
        if (item == null) {
            return;
        }

        if (first == null) {
            first = new Node(item);
            return;
        }

        first = new Node(item, first);

    }

    public void add2(Integer item) {
        if (item == null) {
            return;
        }

        if (first == null) {
            first = new Node(item);
            return;
        }

        Node curr = first;
        Node prev = first;

        while (curr != null) {
            if (curr.item > item) {
                break;
            }
            prev = curr;
            curr = curr.next;
        }

        if (curr == first) {
            first = new Node(item, first);
            return;
        }

        if (curr == null) {
            prev.next = new Node(item);
            return;
        }

        prev.next = new Node(item, prev.next);

    }

    public Integer remove2() {
        if (first == null) {
            return null;
        }

        Integer temp = first.item;
        first = first.next;
        return temp;
    }

    public Integer remove() {

        if (first == null) {
            return null;
        }

        Node prio = first;
        Node prPrev = first;

        Node curr = first;
        Node prev = first;

        while (curr != null) {
            if (prio.item > curr.item) {
                prPrev = prev;
                prio = curr;
            }
            prev = curr;
            curr = curr.next;
        }

        Integer temp = prio.item;
        prPrev.next = prio.next;

        if (prio == first) {
            first = prio.next;
        }

        return temp;


    }
}
