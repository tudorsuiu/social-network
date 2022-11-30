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
    private Button buttonAddUser;
    @FXML
    private Button buttonDeleteUser;
    @FXML
    private Button buttonAddFriendship;
    @FXML
    private Button buttonDeleteFriendship;
    @FXML
    private Button buttonShowUsers;
    @FXML
    private Button buttonShowFriendships;

//    Service<Friendship> friendshipService;
//    Service<User> userService;
//
//    public MenuController(Service<User> userService, Service<Friendship> friendshipService) {
//        this.friendshipService = friendshipService;
//        this.userService = userService;
//    }

//    @FXML
//    protected void onAddUserButton() throws IOException {
//        FXMLLoader addUserLoader = new FXMLLoader(Application.class.getResource("add-user-view.fxml"));
//        Scene scene = new Scene(addUserLoader.load());
//        Stage stage = new Stage();
//        stage.setTitle("Add user");
//        stage.setScene(scene);
//        stage.show();
//    }

    @FXML
    protected void onDeleteUserButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader deleteUserLoader = new FXMLLoader(Application.class.getResource("delete-user-view.fxml"));
        Scene scene = new Scene(deleteUserLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Delete user");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onAddFriendshipButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader addFriendshipLoader = new FXMLLoader(Application.class.getResource("add-friendship-view.fxml"));
        Scene scene = new Scene(addFriendshipLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Add friendship");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void onDeleteFriendshipButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader deleteFriendshipLoader = new FXMLLoader(Application.class.getResource("delete-friendship-view.fxml"));
        Scene scene = new Scene(deleteFriendshipLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Delete friendship");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onShowUsersButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader showUsersLoader = new FXMLLoader(Application.class.getResource("show-users-view.fxml"));
        Scene scene = new Scene(showUsersLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Show users");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onShowFriendshipsButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader showFriendshipsLoader = new FXMLLoader(Application.class.getResource("show-friendships-view.fxml"));
        Scene scene = new Scene(showFriendshipsLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Show friendships");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onExitButton(ActionEvent actionEvent) throws IOException {
        
    }
}