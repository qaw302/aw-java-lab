import tree.BinarySearchTree;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();

        bst.add(1, "one");
        bst.add(2, "two");
        bst.add(3, "three");
        bst.add(4, "four");

        System.out.println(bst.toString());
    }
}
