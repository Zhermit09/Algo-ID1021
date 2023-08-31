public class Calculator {
    private final MyHashMap[] inst;
    private final Stack stack;

    public Calculator(MyHashMap[] inst, Stack stack) {
        this.inst = inst;
        this.stack = stack;
    }

    public void calc() {
        for (MyHashMap hashMap : inst) {
            exe(hashMap);
        }
        System.out.println(stack.pop());
    }

    private void exe(MyHashMap item) {
        int a, b;

        switch (item.opType()) {
            case VAL -> stack.push(item.val());

            case ADD -> {
                b = stack.pop();
                a = stack.pop();
                stack.push(a + b);
            }
            case SUB -> {
                b = stack.pop();
                a = stack.pop();
                stack.push(a - b);
            }
            case MUL -> {
                b = stack.pop();
                a = stack.pop();
                stack.push(a * b);
            }
            case DIV -> {
                b = stack.pop();
                a = stack.pop();
                stack.push(a / b);
            }
        }
    }
}
