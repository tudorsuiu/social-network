module com.example.lab4mergiterog {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.lab4mergiterog to javafx.fxml;
    exports com.example.lab4mergiterog;
    exports com.example.lab4mergiterog.controllers;
    opens com.example.lab4mergiterog.controllers to javafx.fxml;
}