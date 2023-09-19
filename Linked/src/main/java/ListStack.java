public abstract class ListStack {
    protected int sp = -1;
    protected LinkedList buffer;

    public ListStack(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Invalid stack SIZE");
        }
        this.buffer = new LinkedList(0);
    }

    public boolean isEmpty() {
        return sp < 0;
    }

    public boolean isFull() {
        return sp >= buffer.length() - 1;
    }

    public abstract void push(int val);

    public abstract int pop();
}
