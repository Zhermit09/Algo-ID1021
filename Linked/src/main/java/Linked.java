class Linked {

    public static void main(String[] args) {

        DLinkedList list = new DLinkedList(10);
        DLinkedList.Node n = list.first;
        list.print();
        list.rprint();
        System.out.println(list.length());
        System.out.println(list.find(n.val));
        list.remove(n);
        list.add(list.cr(11));
        System.out.println("-----------");
        list.print();
        list.rprint();
        System.out.println(list.length());
        System.out.println(list.find(n.val));
        System.out.println();

    }


}