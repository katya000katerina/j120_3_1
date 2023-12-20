package org.calculator;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final List<Float> numbers = new ArrayList<>();
    private final List<String> operators = new ArrayList<>();

    public void addNumberToList(Float num) {
        numbers.add(num);
    }

    public void addOperatorToList(String op) {
        operators.add(op);
    }

    public void removeLastOperatorFromList() {
        operators.remove(operators.size() - 1);
    }

    public void clear() {
        numbers.clear();
        operators.clear();
    }

    public float getResult() {
        multiplyAndDivide();
        addAndSubtract();
        return numbers.get(0);
    }

    private void multiplyAndDivide() {
        performInverseOperations("*", "/");
    }

    private void addAndSubtract() {
        performInverseOperations("+", "-");
    }

    private void performInverseOperations(String op1, String op2) {
        while (operators.contains(op1) || operators.contains(op2)) {
            int op1Index = operators.indexOf(op1);
            int op2Index = operators.indexOf(op2);
            if (op1Index != -1 && op2Index != -1) {
                executeBinaryOperation(Integer.min(op1Index, op2Index));
            } else {
                executeBinaryOperation(Integer.max(op1Index, op2Index));
            }
        }
    }

    private void executeBinaryOperation(int index) {
        float temp = 0;
        String operator = operators.get(index);
        switch (operator) {
            case "*":
                temp = numbers.get(index) * numbers.get(index + 1);
                break;
            case "/":
                temp = numbers.get(index) / numbers.get(index + 1);
                break;
            case "-":
                temp = numbers.get(index) - numbers.get(index + 1);
                break;
            case "+":
                temp = numbers.get(index) + numbers.get(index + 1);
                break;
        }
        numbers.remove(index);
        numbers.set(index, temp);
        operators.remove(index);
    }
}
