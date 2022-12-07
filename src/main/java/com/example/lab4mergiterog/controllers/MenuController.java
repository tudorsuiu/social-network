package com.example.lab4mergiterog.controllers;

import com.example.lab4mergiterog.Application;
import com.example.lab4mergiterog.domain.Friendship;
import com.example.lab4mergiterog.domain.User;
import com.example.lab4mergiterog.service.FriendshipService;
import com.example.lab4mergiterog.service.Service;
import com.example.lab4mergiterog.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @FXML
    private Button buttonShowUsers;
    @FXML
    private Button buttonShowFriendships;

    @FXML
    protected void onShowUsersButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader showUsersLoader = new FXMLLoader(Application.class.getResource("show-users-view.fxml"));
        Scene scene = new Scene(showUsersLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Users");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onShowFriendshipsButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader showFriendshipsLoader = new FXMLLoader(Application.class.getResource("show-friendships-view.fxml"));
        Scene scene = new Scene(showFriendshipsLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Friendships");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onExitButton(ActionEvent actionEvent) throws IOException {

    }
}