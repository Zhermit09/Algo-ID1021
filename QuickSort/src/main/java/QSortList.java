public class QSortList {
    Node first;
    Node last;


    public static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
            this.next = null;
        }
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
    public void add(Node n) {
        if(n == null){
            return;
        }
        if (first == null) {
            first = n;
            last = n;
            return;
        }
        n.next = first;
        first = n;
    }


    public Node remove() {
        if(first == null){
            return null;
        }
        if (first == last){
            last = null;
        }

        Node temp = first;
        first = first.next;
        temp.next = null;
        return temp;

    }

    public void append(QSortList list) {
        if (first == null) {
            first = list.first;
            last = list.last;
            return;
        }
        last.next = list.first;
        last = list.last;

    }

    public void print() {
        Node curr = first;

        if (curr != null) {
            System.out.print("["+curr.val);
            curr = curr.next;
        }
        while (curr != null) {
            System.out.print(", " + curr.val);
            curr = curr.next;
        }
        System.out.print("]\n");
    }
}
