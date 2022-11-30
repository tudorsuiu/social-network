package com.example.lab4mergiterog.controllers;

import com.example.lab4mergiterog.Application;
import com.example.lab4mergiterog.domain.User;
import com.example.lab4mergiterog.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class UserController {
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
    TextField textFieldDeleteUserById;
    @FXML
    Button buttonAddUser;
    @FXML
    Button buttonSubmit;

    @FXML
    protected void onAddUserButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader addUserLoader = new FXMLLoader(Application.class.getResource("add-user-view.fxml"));
        Scene scene = new Scene(addUserLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Add user");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onSubmitButton(ActionEvent actionEvent) {
        String firstName = textFieldFirstName.getText();
        String lastName = textFieldLastName.getText();
        int age = Integer.parseInt(textFieldAge.getText());
        String email = textFieldEmail.getText();
        String password = passwordFieldPassword.getText();
        User user = new User(1, firstName, lastName, age, email, password);
        UserService.getInstance().create(user);
        textFieldFirstName.clear();
        textFieldLastName.clear();
        textFieldAge.clear();
        textFieldEmail.clear();
        passwordFieldPassword.clear();
        for (User u : UserService.getInstance().read()) {
            System.out.println(u);
        }
    }

    @FXML
    Button buttonDeleteUserById;

    @FXML
    protected void onDeleteUserByIdButton(ActionEvent actionEvent) {

    }

    @FXML
    TableView<User> tableViewShowUsers;
    @FXML
    TableColumn<User, Integer> tableColumnUserId = new TableColumn<>();
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

    ObservableList<User> model = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        tableColumnUserId.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        tableColumnUserFirstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        tableColumnUserLastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        tableColumnUserAge.setCellValueFactory(new PropertyValueFactory<User, Integer>("age"));
        tableColumnUserEmail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        tableColumnUserPassword.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        tableViewShowUsers.setItems(model);
    }
}
