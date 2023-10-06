class Queues {

    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();
        tree.add(5, 5);
        tree.add(2, 2);
        tree.add(7, 7);
        tree.add(1, 1);
        tree.add(8, 8);
        tree.add(6, 6);
        tree.add(3, 3);
        tree.add(4, 4);

        BFSIterator it = new BFSIterator(tree);

        for (int i = 0; i < 10; i++) {
            System.out.println("next value " + it.next() + "\t\thas next:" + it.hasNext());
        }
        System.out.println();
    }

}


