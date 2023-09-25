import java.util.Random;

class Tree {

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

        for (int i : tree) {
            System.out.println("next value " + i);
        }

    }


}