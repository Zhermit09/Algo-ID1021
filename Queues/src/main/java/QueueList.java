public class QueueList<T> {
    Node first;
    Node last;

    public QueueList() {
    }

    public void add(T item) {
        if(item == null){
            return;
        }

        //Node curr = first;

        if (first == null) {
            first = new Node(item, null);
            last = first;
            return;
        }

        /*while (curr.next != null) {
            curr = curr.next;
        }

        curr.next = new Node(item, null);*/

        last.next = new Node(item, null);
        last = last.next;

    }

    public T remove() {

        if (first == null) {
            return null;
        }

        if (first == last) {
            last = null;
        }

        T temp = first.item;
        first = first.next;
        return temp;


    }

    private class Node {
        T item;
        Node next;

        private Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
}