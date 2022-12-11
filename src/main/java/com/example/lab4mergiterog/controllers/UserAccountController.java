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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;

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
    Label labelErrors;
    @FXML
    TextField textFieldSearchUser;
    @FXML
    Label labelTableName;

    @FXML
    protected void onAddFriendButton() {
        try {
            User selectedUser = tableViewUsers.getSelectionModel().getSelectedItem();
            Friendship friendship = new Friendship(loggedUser.getId(), selectedUser.getId(), "pending");
            FriendshipService.getInstance().create(friendship);
            labelErrors.setText("Friend request sent successfully!");
        }
        catch(Exception e) {
            labelErrors.setText(e.getMessage());
        }
    }

    @FXML
    protected void onButtonShowAllRequests() {
        try {
            labelTableName.setText("FRIEND REQUESTS");
            List<Friendship> friendships = FriendshipService.getInstance().read();
            List<User> filtered = new ArrayList<>();
            for (Friendship f : friendships) {
                if (Objects.equals(loggedUser.getId(), f.getSecondUserId()) &&
                        Objects.equals(f.getStatus(), "pending")) {
                    filtered.add(UserService.getInstance().getUserById(f.getFirstUserId()));
                }
            }
            insertIntoTableView(filtered);
            labelErrors.setText("");
        }
        catch(Exception e) {
            labelErrors.setText(e.getMessage());
        }
    }

    @FXML
    protected void onButtonShowAllFriends() {
        try {
            labelTableName.setText("FRIEND LIST");
            List<Friendship> friendships = FriendshipService.getInstance().read();
            List<User> filtered = new ArrayList<>();
            for (Friendship f : friendships) {
                if (Objects.equals(loggedUser.getId(), f.getFirstUserId()) &&
                        Objects.equals(f.getStatus(), "accepted")) {
                    filtered.add(UserService.getInstance().getUserById(f.getSecondUserId()));
                } else if (Objects.equals(loggedUser.getId(), f.getSecondUserId()) &&
                        Objects.equals(f.getStatus(), "accepted")) {
                    filtered.add(UserService.getInstance().getUserById(f.getFirstUserId()));
                }
            }
            insertIntoTableView(filtered);
            labelErrors.setText("");
        }
        catch(Exception e) {
            labelErrors.setText(e.getMessage());
        }
    }

    @FXML
    protected void onButtonShowAllUsers() {
        try {
            List<User> users = UserService.getInstance().read();
            List<User> filtered = new ArrayList<>();
            for (User u : users) {
                if (!Objects.equals(loggedUser.getId(), u.getId())) {
                    filtered.add(u);
                }
            }
            insertIntoTableView(filtered);
            labelErrors.setText("");
        }
        catch(Exception e) {
            labelErrors.setText(e.getMessage());
        }
    }

    @FXML
    protected void onButtonAcceptRequest() {
        try {
            User user = tableViewUsers.getSelectionModel().getSelectedItem();
            System.out.println(user);
            Friendship oldFriendship = FriendshipService.getInstance().getFriendshipByIdsAndStatus(user.getId(), loggedUser.getId(), "pending");
            Friendship newFriendship = new Friendship(oldFriendship);
            newFriendship.setStatus("accepted");
            FriendshipService.getInstance().update(oldFriendship, newFriendship);
            labelTableName.setText("FRIEND LIST");
            List<Friendship> friendships = FriendshipService.getInstance().read();
            List<User> filtered = new ArrayList<>();
            for (Friendship f : friendships) {
                if (Objects.equals(loggedUser.getId(), f.getFirstUserId()) &&
                        Objects.equals(f.getStatus(), "accepted")) {
                    filtered.add(UserService.getInstance().getUserById(f.getSecondUserId()));
                } else if (Objects.equals(loggedUser.getId(), f.getSecondUserId()) &&
                        Objects.equals(f.getStatus(), "accepted")) {
                    filtered.add(UserService.getInstance().getUserById(f.getFirstUserId()));
                }
            }
            insertIntoTableView(filtered);
            labelErrors.setText("");
        }
        catch(Exception e) {
            labelErrors.setText(e.getMessage());
        }
    }

    @FXML
    protected void onDeleteButton() {
        try {
            User friend = tableViewUsers.getSelectionModel().getSelectedItem();
            Friendship deletedFriendship = FriendshipService.getInstance().getFriendshipByIds(loggedUser.getId(), friend.getId());
            FriendshipService.getInstance().delete(deletedFriendship);
            List<Friendship> friendships = FriendshipService.getInstance().read();
            List<User> filtered = new ArrayList<>();
            labelTableName.setText("FRIEND LIST");
            for (Friendship f : friendships) {
                if (Objects.equals(loggedUser.getId(), f.getFirstUserId()) &&
                        Objects.equals(f.getStatus(), "accepted")) {
                    filtered.add(UserService.getInstance().getUserById(f.getSecondUserId()));
                } else if (Objects.equals(loggedUser.getId(), f.getSecondUserId()) &&
                        Objects.equals(f.getStatus(), "accepted")) {
                    filtered.add(UserService.getInstance().getUserById(f.getFirstUserId()));
                }
            }
            insertIntoTableView(filtered);
            labelErrors.setText("");
        }
        catch(Exception e) {
            labelErrors.setText(e.getMessage());
        }
    }

    public void onEnter() {
        try {
            labelTableName.setText("RESULTS");
            List<User> users = UserService.getInstance().read();
            List<User> filtered = new ArrayList<>();
            String fullName = textFieldSearchUser.getText();
            String[] fullNameSplitted = fullName.split(" ");
            if (fullNameSplitted.length == 1) {
                for (User u : users) {
                    if (Objects.equals(u.getFirstName().toLowerCase(), fullNameSplitted[0].toLowerCase()) ||
                            Objects.equals(u.getLastName().toLowerCase(), fullNameSplitted[0].toLowerCase())) {
                        filtered.add(u);
                    }
                }
            } else if (fullNameSplitted.length == 2) {
                for (User u : users) {
                    if ((Objects.equals(u.getFirstName().toLowerCase(), fullNameSplitted[0].toLowerCase()) &&
                            Objects.equals(u.getLastName().toLowerCase(), fullNameSplitted[1].toLowerCase())) ||
                            (Objects.equals(u.getLastName().toLowerCase(), fullNameSplitted[0].toLowerCase()) &&
                                    Objects.equals(u.getFirstName().toLowerCase(), fullNameSplitted[0].toLowerCase()))) {
                        filtered.add(u);
                    }
                }
            }
            insertIntoTableView(filtered);
        }
        catch (Exception e) {
            labelErrors.setText(e.getMessage());
        }
    }

    public void insertIntoTableView(List<User> filtered) {
        tableViewUsers.getItems().clear();
        for(User u : filtered) {
            tableViewUsers.getItems().add(u);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelUserName.setText(loggedUser.getFirstName() + " " + loggedUser.getLastName());
        tableColumnFirstName.setCellValueFactory(new PropertyValueFactory<User, String>("FirstName"));
        tableColumnLastName.setCellValueFactory(new PropertyValueFactory<User, String>("LastName"));
        tableColumnAge.setCellValueFactory(new PropertyValueFactory<User, Integer>("Age"));
        tableColumnEmail.setCellValueFactory(new PropertyValueFactory<User, String>("Email"));
        textFieldSearchUser.setOnKeyPressed(event -> {if(event.getCode().equals(KeyCode.ENTER)) {
            onEnter();
        }});
        labelTableName.setText("FRIEND LIST");
        List<Friendship> friendships = FriendshipService.getInstance().read();
        List<User> filtered = new ArrayList<>();
        for (Friendship f : friendships) {
            if (Objects.equals(loggedUser.getId(), f.getFirstUserId()) &&
                    Objects.equals(f.getStatus(), "accepted")) {
                filtered.add(UserService.getInstance().getUserById(f.getSecondUserId()));
            } else if (Objects.equals(loggedUser.getId(), f.getSecondUserId()) &&
                    Objects.equals(f.getStatus(), "accepted")) {
                filtered.add(UserService.getInstance().getUserById(f.getFirstUserId()));
            }
        }
        model = FXCollections.observableArrayList(filtered);
        tableViewUsers.setItems(model);
    }
}