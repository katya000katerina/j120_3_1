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
    private boolean isBinaryOperatorChosen;
    private boolean start = true;

    @FXML
    public void processNumbers(ActionEvent event) {
        if (start) {
            screen.setText("");
            start = false;
        }
        String value = ((Button) event.getSource()).getText();
        if (currentNumber.isBlank() && value.equals(".")) {
            return;
        }
        if (currentNumber.equals("0") && value.equals("0")) {
            return;
        }
        if (currentNumber.equals("0") && !value.equals(".")) {
            currentNumber = value;
            screen.setText(getCurrentTextWithoutLastZero() + value);
        } else {
            currentNumber += value;
            screen.setText(screen.getText() + value);
        }
        isBinaryOperatorChosen = false;
    }

    @FXML
    public void processOperators(ActionEvent event) {
        if (currentNumber.isBlank() && !isBinaryOperatorChosen) {
            return;
        }
        String value = ((Button) event.getSource()).getText();
        if (isBinaryOperatorChosen) {
            model.removeLastOperatorFromList();
            String currentText = screen.getText();
            screen.setText(currentText.substring(0, currentText.length() - 1) + value);
        } else {
            model.addNumberToList(Float.parseFloat(currentNumber));
            currentNumber = "";
            screen.setText(screen.getText() + value);
        }
        isBinaryOperatorChosen = true;
        model.addOperatorToList(value);
    }

    @FXML
    public void processEqualsAndClear(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        if (currentNumber.isBlank() && value.equals("=")) {
            return;
        }
        if (value.equals("=")) {
            model.addNumberToList(Float.parseFloat(currentNumber));
            screen.setText(String.valueOf(model.getResult()));
        } else {
            screen.setText("");
        }
        currentNumber = "";
        model.clear();
        start = true;
        isBinaryOperatorChosen = false;
    }

    private String getCurrentTextWithoutLastZero() {
        String currentText = screen.getText();
        return currentText.substring(0, currentText.length() - 1);
    }
}
