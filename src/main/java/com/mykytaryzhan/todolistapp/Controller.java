package com.mykytaryzhan.todolistapp;

import datamodel.TodoData;
import datamodel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Controller {

private List<TodoItem> todoItems;

@FXML
private TextArea itemDetailsTextArea;

@FXML
private Label deadLineLabel;
@FXML
    private ListView<TodoItem> todoListView;
@FXML
private BorderPane mainBorderPane;

int i = 0;

    public void initialize(){
//        TodoItem item1 = new TodoItem("Mail birthday card", "Buy a 30th birthday card for john",
//                LocalDate.of(2016, Month.APRIL, 25));
//        TodoItem item2 = new TodoItem("Doctor's appointment", "See dr.Smith at 123 Main St.",
//                LocalDate.of(2016, Month.MAY, 3));
//        TodoItem item3 = new TodoItem("Finish design", "I promised Mike I'd email website mockups by Friday 22nd April",
//                LocalDate.of(2016, Month.APRIL, 22));
//        TodoItem item4 = new TodoItem("Pick up dry cleaner", "The closes should be ready by Wednesday",
//                LocalDate.of(2016, Month.APRIL, 20));
//
//
//        todoItems = new ArrayList<TodoItem>();
//        todoItems.add(item1);
//        todoItems.add(item2);
//        todoItems.add(item3);
//        todoItems.add(item4);
//
//        TodoData.getInstance().setTodoItems(todoItems);

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observableValue, TodoItem todoItem, TodoItem t1) {
                if(t1 != null) {
                    TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                    itemDetailsTextArea.setText(item.getDetails().toString());
                    deadLineLabel.setText(item.getDeadLine().toString());
                    System.out.println("change happened " + i++ );
            }
        }});

        todoListView.setItems(TodoData.getInstance().getTodoItems());
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();

        todoListView.setCellFactory(new Callback<ListView<TodoItem>, ListCell<TodoItem>>() {
            @Override
            public ListCell<TodoItem> call(ListView<TodoItem> todoItemListView) {
                ListCell<TodoItem> cell = new ListCell<TodoItem>() {
                        @Override
                        protected void updateItem(TodoItem item, boolean empty) {
                            super.updateItem(item, empty);
                            if(empty) {
                                setText(null);
                            } else {
                                setText(item.getShortDescription());
                                if(item.getDeadLine().equals(LocalDate.now())){
                                    setTextFill(Color.RED);
                                } else if(item.getDeadLine().equals(LocalDate.now().plusDays(1))){
                                    setTextFill(Color.BROWN);
                                }
                            }
                            System.out.println("Update happened" + item);
                        }
                    };
                    return cell;
                }
            });
    }

    @FXML
    public void handleClickListView(){
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
        itemDetailsTextArea.setText(item.getDetails().toString());
        itemDetailsTextArea.setText(item.getDetails().toString());
        deadLineLabel.setText(item.getDeadLine().toString());
        //        StringBuilder sb = new StringBuilder(item.getDeteils());
//        sb.append("\n\n\n\n");
//        sb.append("Due date:  ");
//        sb.append(item.getDeadLine().toString());
//        itemDetailsTextArea.setText(sb.toString());

    }

    @FXML
    public void showNewItemDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add a new item");
        dialog.setHeaderText("You can set a header here");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoItemDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Coudnt print dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get() ==ButtonType.OK) {
            DialogConroller controller = fxmlLoader.getController();
            TodoItem newItem = controller.processResault();
            todoListView.getSelectionModel().select(newItem);

        } else {
            System.out.println("Cancel pressed");
        }

    }

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}