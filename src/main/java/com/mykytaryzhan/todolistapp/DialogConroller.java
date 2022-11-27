package com.mykytaryzhan.todolistapp;

import datamodel.TodoData;
import datamodel.TodoItem;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class DialogConroller {
    @FXML
    private TextField shortDescriptionField;

    @FXML
    private TextArea detailsArea;

    @FXML
    private DatePicker deadLinePicker;

    public TodoItem processResault(){
        String shortDescriprion = shortDescriptionField.getText().trim();
        String details = detailsArea.getText().trim();
        LocalDate deadLineValue = deadLinePicker.getValue();

        TodoItem newItem = new TodoItem(shortDescriprion, details, deadLineValue);
        TodoData.getInstance().addTodoItem(newItem);
        return newItem;
    }


}
