package Mihail;

public class Main {

    public static void main(String[] args) {
        Tree<Integer, Integer> tree = new Tree<>();
        for(int i = 0; i < 20; i++) {
            tree.put(i, 0);
        }
        tree.printTree();
    }
}
