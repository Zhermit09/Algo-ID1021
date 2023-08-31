class HP35 {
    public static void main(String[] args) {

        MyHashMap[] inst = {
                new MyHashMap(OpType.VAL, 1),
                new MyHashMap(OpType.VAL, 2),
                new MyHashMap(OpType.VAL, 3),
                new MyHashMap(OpType.VAL, 4),
                new MyHashMap(OpType.VAL, 5),
                new MyHashMap(OpType.VAL, 6),
                new MyHashMap(OpType.VAL, 7),
                new MyHashMap(OpType.VAL, 8),
                new MyHashMap(OpType.VAL, 9),
                new MyHashMap(OpType.VAL, 10),
                new MyHashMap(OpType.VAL, 11),
                new MyHashMap(OpType.VAL, 12),
                new MyHashMap(OpType.VAL, 13),
                new MyHashMap(OpType.VAL, 14),
                new MyHashMap(OpType.VAL, 15),
                new MyHashMap(OpType.VAL, 16),
                new MyHashMap(OpType.ADD),
                new MyHashMap(OpType.MUL),
                new MyHashMap(OpType.ADD),
                new MyHashMap(OpType.MUL),
                new MyHashMap(OpType.ADD),
                new MyHashMap(OpType.MUL),
                new MyHashMap(OpType.ADD),
                new MyHashMap(OpType.MUL),
                new MyHashMap(OpType.ADD),
                new MyHashMap(OpType.MUL),
                new MyHashMap(OpType.ADD),
                new MyHashMap(OpType.MUL),
                new MyHashMap(OpType.ADD),
                new MyHashMap(OpType.MUL),
                new MyHashMap(OpType.ADD)
        };

        MyHashMap[] inst2 = {
                new MyHashMap(OpType.VAL, 10),
                new MyHashMap(OpType.VAL, 2),
                new MyHashMap(OpType.VAL, 5),
                new MyHashMap(OpType.MUL),
                new MyHashMap(OpType.ADD)
        };

        Calculator calculator = new Calculator(inst, new StaticStack(inst.length));
        calculator.calc();

        Calculator calculator2 = new Calculator(inst, new DynamicStack(4));
        calculator2.calc();
    }
}