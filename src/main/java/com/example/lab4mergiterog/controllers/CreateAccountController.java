package com.example.lab4mergiterog.controllers;

import com.example.lab4mergiterog.domain.User;
import com.example.lab4mergiterog.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateAccountController {
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
    Button buttonCreateAccount;
    @FXML
    Label labelErrors;
    @FXML
    protected void onCreateAccount(ActionEvent actionEvent) {
        try {
            String firstName = textFieldFirstName.getText();
            String lastName = textFieldLastName.getText();
            Integer age = Integer.parseInt(textFieldAge.getText());
            String email = textFieldEmail.getText();
            String password = passwordFieldPassword.getText();
            User newUser = new User(firstName, lastName, age, email, password);
            UserService.getInstance().create(newUser);
            textFieldFirstName.clear();
            textFieldLastName.clear();
            textFieldAge.clear();
            textFieldEmail.clear();
            passwordFieldPassword.clear();
            labelErrors.setText("Account created successfully!");
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (Exception e) {
            labelErrors.setText(e.getMessage());
        }
    }
}
