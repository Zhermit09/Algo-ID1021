import java.util.EmptyStackException;

public class DListDynamicStack extends DListStack {

    public DListDynamicStack(int size) {
        super(size);
    }

    @Override
    public void push(int val) {
        buffer.add(val);
        sp++;
    }

    @Override
    public int pop() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        int temp = buffer.getVal();
        buffer.remove(temp);
        sp--;
        return temp;
    }


}
