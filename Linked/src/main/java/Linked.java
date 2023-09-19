class Linked {

    public static void main(String[] args) {

        LinkedList list = new LinkedList(0);
        list.add(3);
        list.add(0);
        list.add(3);
        list.print();
        System.out.println(list.find(3));
        System.out.println("-----------");
        list.remove(3);
        list.print();
        System.out.println(list.find(3));


        System.out.println();

    }


}