import java.util.EmptyStackException;

public class StaticStack extends Stack {

    public StaticStack(int size) {
        super(size);
    }

    @Override
    public void push(int val) {
        if (this.isFull()) {
            throw new StackOverflowError("Stack if FULL");
        }
        sp++;
        buffer[sp] = val;
    }

    @Override
    public int pop() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return buffer[sp--];
    }
}
