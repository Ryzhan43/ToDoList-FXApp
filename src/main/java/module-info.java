module com.example.todolistapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mykytaryzhan.todolistapp to javafx.fxml;
    exports com.mykytaryzhan.todolistapp;
}