package com.example.lab4mergiterog.controllers;

import com.example.lab4mergiterog.domain.User;
import com.example.lab4mergiterog.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    @FXML
    TextField textFieldFirstName;
    @FXML
    TextField textFieldLastName;
    @FXML
    TextField textFieldAge;
    @FXML
    TextField textFieldEmail;
    @FXML
    PasswordField passwordFieldPassword;
    @FXML
    Button buttonAddUser;
    @FXML
    Label labelConfirmation;

    @FXML
    protected void onAddUserButton(ActionEvent actionEvent) throws IOException {
        try {
//        int id = Integer.parseInt(textFieldId.getText());
            String firstName = textFieldFirstName.getText();
            String lastName = textFieldLastName.getText();
            int age = Integer.parseInt(textFieldAge.getText());
            String email = textFieldEmail.getText();
            String password = passwordFieldPassword.getText();
            User user = new User(firstName, lastName, age, email, password);
            UserService.getInstance().create(user);
//        textFieldId.clear();
            textFieldFirstName.clear();
            textFieldLastName.clear();
            textFieldAge.clear();
            textFieldEmail.clear();
            passwordFieldPassword.clear();
            tableViewShowUsers.getItems().add(UserService.getInstance().read().get(UserService.getInstance().read().size() - 1));
            labelConfirmation.setText("User added successfully!");
        }
        catch (Exception e) {
            labelConfirmation.setText(e.getMessage());
            labelConfirmation.setTextFill(Color.color(1,0,0));
        }
    }

    @FXML
    protected void onDeleteUserButton(ActionEvent actionEvent) {
        try {
            User user = tableViewShowUsers.getSelectionModel().getSelectedItem();
            UserService.getInstance().delete(user);
            tableViewShowUsers.getItems().remove(user);
        }
        catch (Exception e) {
            labelConfirmation.setText(e.getMessage());
            labelConfirmation.setTextFill(Color.color(1,0,0));
        }
    }

    @FXML
    TableView<User> tableViewShowUsers;
    @FXML
    TableColumn<User, Integer> tableColumnUserId;
    @FXML
    TableColumn<User, String> tableColumnUserFirstName;
    @FXML
    TableColumn<User, String> tableColumnUserLastName;
    @FXML
    TableColumn<User, Integer> tableColumnUserAge;
    @FXML
    TableColumn<User, String> tableColumnUserEmail;
    @FXML
    TableColumn<User, String> tableColumnUserPassword;

    ObservableList<User> model = FXCollections.observableArrayList(UserService.getInstance().read());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableColumnUserId.setCellValueFactory(new PropertyValueFactory<User, Integer>("Id"));
        tableColumnUserFirstName.setCellValueFactory(new PropertyValueFactory<User, String>("FirstName"));
        tableColumnUserLastName.setCellValueFactory(new PropertyValueFactory<User, String>("LastName"));
        tableColumnUserAge.setCellValueFactory(new PropertyValueFactory<User, Integer>("Age"));
        tableColumnUserEmail.setCellValueFactory(new PropertyValueFactory<User, String>("Email"));
        tableColumnUserPassword.setCellValueFactory(new PropertyValueFactory<User, String>("Password"));
        tableViewShowUsers.setItems(model);
    }
}
