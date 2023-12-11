package org.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {
    private final Model model = new Model();
    @FXML
    private Label screen;
    private String currentNumber = "";
    private boolean start = true;

    @FXML
    public void processNumbers(ActionEvent event) {
        if (start) {
            screen.setText("");
            start = false;
        }
        String value = ((Button) event.getSource()).getText();
        currentNumber += value;
        screen.setText(screen.getText() + value);
    }

    @FXML
    public void processOperators(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        model.addOperatorToList(value);
        model.addNumberToList(Float.parseFloat(currentNumber));
        currentNumber = "";
        screen.setText(screen.getText() + value);
    }

    @FXML
    public void processEqualsAndClear(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        if (value.equals("=")) {
            model.addNumberToList(Float.parseFloat(currentNumber));
            screen.setText(String.valueOf(model.getResult()));
        } else {
            screen.setText("");
        }
        currentNumber = "";
        model.clear();
        start = true;
    }
}
