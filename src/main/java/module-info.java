module org.calculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.calculator to javafx.fxml;
    exports org.calculator;
}