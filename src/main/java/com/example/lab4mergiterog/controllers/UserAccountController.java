package com.example.lab4mergiterog.controllers;

import com.example.lab4mergiterog.domain.CurrentUser;
import com.example.lab4mergiterog.domain.Friendship;
import com.example.lab4mergiterog.domain.User;
import com.example.lab4mergiterog.service.FriendshipService;
import com.example.lab4mergiterog.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;

public class UserAccountController implements Initializable {
    User loggedUser = CurrentUser.getInstance().getUser();
    private ObservableList<User> model;
    @FXML
    Button buttonShowAllUsers;
    @FXML
    Button buttonShowAllFriends;
    @FXML
    Button buttonShowAllRequests;
    @FXML
    Label labelUserName;
    @FXML
    TableView<User> tableViewUsers;
    @FXML
    TableColumn<User, String> tableColumnFirstName;
    @FXML
    TableColumn<User, String> tableColumnLastName;
    @FXML
    TableColumn<User, Integer> tableColumnAge;
    @FXML
    TableColumn<User, String> tableColumnEmail;

    @FXML
    protected void onAddFriendButton() {
        User selectedUser = tableViewUsers.getSelectionModel().getSelectedItem();
        Friendship friendship = new Friendship(loggedUser.getId(), selectedUser.getId(), "pending");
        FriendshipService.getInstance().create(friendship);
    }

    @FXML
    protected void onButtonShowAllRequests() {
        List<Friendship> friendships = FriendshipService.getInstance().read();
        List<User> filtered = new ArrayList<>();
        for(Friendship f : friendships) {
            if(Objects.equals(loggedUser.getId(), f.getSecondUserId()) &&
                    Objects.equals(f.getStatus(), "pending")) {
                filtered.add(UserService.getInstance().getUserById(f.getFirstUserId()));
            }
        }
        tableViewUsers.getItems().clear();
        for(User u : filtered) {
            tableViewUsers.getItems().add(u);
        }
    }

    @FXML
    protected void onButtonShowAllFriends() {
        List<Friendship> friendships = FriendshipService.getInstance().read();
        List<User> filtered = new ArrayList<>();
        for(Friendship f : friendships) {
            if(Objects.equals(loggedUser.getId(), f.getFirstUserId()) &&
                    Objects.equals(f.getStatus(), "accepted")) {
                filtered.add(UserService.getInstance().getUserById(f.getSecondUserId()));
            }
            else if(Objects.equals(loggedUser.getId(), f.getSecondUserId()) &&
                    Objects.equals(f.getStatus(), "accepted")) {
                filtered.add(UserService.getInstance().getUserById(f.getFirstUserId()));
            }
        }
        tableViewUsers.getItems().clear();
        for(User u : filtered) {
            tableViewUsers.getItems().add(u);
        }
    }

    @FXML
    protected void onButtonShowAllUsers() {
        List<User> users = UserService.getInstance().read();
        List<User> filtered = new ArrayList<>();
        for(User u : users) {
            if(!Objects.equals(loggedUser.getId(), u.getId())) {
                filtered.add(u);
            }
        }
        System.out.println(filtered);
        tableViewUsers.getItems().clear();
        for(User u : filtered) {
            tableViewUsers.getItems().add(u);
        }
    }

    @FXML
    protected void onButtonAcceptRequest() {
        User user = tableViewUsers.getSelectionModel().getSelectedItem();
        System.out.println(user);
        Friendship oldFriendship = FriendshipService.getInstance().getFriendshipByIdsAndStatus(user.getId(), loggedUser.getId(), "pending");
        System.out.println(oldFriendship);
        Friendship newFriendship = new Friendship(oldFriendship);
        newFriendship.setStatus("accepted");
        System.out.println(newFriendship);
        FriendshipService.getInstance().update(oldFriendship, newFriendship);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelUserName.setText(loggedUser.getFirstName() + " " + loggedUser.getLastName());
        tableColumnFirstName.setCellValueFactory(new PropertyValueFactory<User, String>("FirstName"));
        tableColumnLastName.setCellValueFactory(new PropertyValueFactory<User, String>("LastName"));
        tableColumnAge.setCellValueFactory(new PropertyValueFactory<User, Integer>("Age"));
        tableColumnEmail.setCellValueFactory(new PropertyValueFactory<User, String>("Email"));
        List<User> filtered = new ArrayList<>();
        for(User u : UserService.getInstance().read()) {
            if(!Objects.equals(loggedUser.getId(), u.getId())) {
                filtered.add(u);
            }
        }
        model = FXCollections.observableArrayList(filtered);
        tableViewUsers.setItems(model);
    }
}
