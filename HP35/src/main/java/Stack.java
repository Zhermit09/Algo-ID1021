public abstract class Stack {
    protected int sp = -1;
    protected int[] buffer;

    public Stack(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Invalid stack SIZE");
        }
        buffer = new int[size];
    }

    public boolean isEmpty() {
        return sp < 0;
    }

    public boolean isFull() {
        return sp >= buffer.length - 1;
    }

    public abstract void push(int val);

    public abstract int pop();
}
