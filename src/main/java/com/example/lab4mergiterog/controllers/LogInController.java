package com.example.lab4mergiterog.controllers;

import com.example.lab4mergiterog.Application;
import com.example.lab4mergiterog.domain.User;
import com.example.lab4mergiterog.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInController {
    @FXML
    TextField textFieldLogInEmail;
    @FXML
    PasswordField passwordFieldLogInPassword;
    @FXML
    Button buttonLogIn;
    @FXML
    Label labelCreateAccount;
    @FXML
    Label labelLogInErrors;

    @FXML
    protected void verifyCredentials() {
        try {
            String email = textFieldLogInEmail.getText();
            String password = passwordFieldLogInPassword.getText();
            User loggedUser = UserService.getInstance().verifyEmailAndPassword(email, password);
            System.out.println(loggedUser);
            textFieldLogInEmail.clear();
            passwordFieldLogInPassword.clear();
            labelLogInErrors.setText("");
            FXMLLoader userAccountPage = new FXMLLoader(Application.class.getResource("user-account-page.fxml"));
            Scene scene = new Scene(userAccountPage.load());
            Stage stage = new Stage();
            stage.setTitle("Profile: " + loggedUser.getFirstName() + " " + loggedUser.getLastName());
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            labelLogInErrors.setText(e.getMessage());
            textFieldLogInEmail.clear();
            passwordFieldLogInPassword.clear();
        }
    }

    @FXML
    protected void onCreateAccountLabel() {
        try {
            FXMLLoader createAccountPageLoader = new FXMLLoader(Application.class.getResource("create-account-page.fxml"));
            Scene scene = new Scene(createAccountPageLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Create account");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            labelLogInErrors.setText(e.getMessage());
        }
    }

}
