import java.util.EmptyStackException;

public class DynamicStack extends Stack {

    public DynamicStack(int size) {
        super(size);
    }

    @Override
    public void push(int val) {
        if (isFull()) {
            extend();
        }
        sp++;
        buffer[sp] = val;
    }

    @Override
    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        if((int)(0.3 * buffer.length - 0.5) >= sp && buffer.length > 1){
            shrink();
        }
        return buffer[sp--];
    }

    private void extend() {
        int[] temp = new int[2 * buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            temp[i] = buffer[i];
        }
        buffer = temp;
    }

    private void shrink() {
        int[] temp = new int[buffer.length / 2];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = buffer[i];
        }
        buffer = temp;
    }
}
