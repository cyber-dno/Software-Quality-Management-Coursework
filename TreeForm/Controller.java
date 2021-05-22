package sample;

import Tree.Tree;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;


public class Controller {
    Tree<Integer, Integer> tree = new Tree<Integer,Integer>();

    @FXML
    private TextArea Out;

    @FXML
    private TextField Input;

    @FXML
    private void click_add() {
        int num = Integer.parseInt(Input.getText());
        Input.clear();
        tree.put(num, 0);
        Out.clear();

        Out.setText(tree.printTree());
    }

    @FXML
    private void click_del() {
        int num = Integer.parseInt(Input.getText());
        Input.clear();
        tree.remove(num);
        Out.clear();

        Out.setText(tree.printTree());
    }
}
