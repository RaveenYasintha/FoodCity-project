module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
    opens com.example.demo1.TO to javafx.fxml;
    exports com.example.demo1.TO;
    opens com.example.demo1.TM to javafx.fxml;
    exports com.example.demo1.TM;
    exports com.example.demo1.controller;
    opens com.example.demo1.controller to javafx.fxml;
}