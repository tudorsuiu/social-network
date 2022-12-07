package com.example.lab4mergiterog.controllers;

import com.example.lab4mergiterog.domain.Friendship;
import com.example.lab4mergiterog.service.FriendshipService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class FriendshipController implements Initializable {
    @FXML
    TextField textFieldFriendshipId;
    @FXML
    TextField textFieldFriendshipFirstUserId;
    @FXML
    TextField textFieldFriendshipSecondUserId;
    @FXML
    Button buttonAddFriendship;

    @FXML
    protected void onAddFriendshipButton(ActionEvent actionEvent) {
//        int id = Integer.parseInt(textFieldFriendshipId.getText());
        int firstUserId = Integer.parseInt(textFieldFriendshipFirstUserId.getText());
        int secondUserId = Integer.parseInt(textFieldFriendshipSecondUserId.getText());
        Friendship friendship = new Friendship(firstUserId, secondUserId);
        FriendshipService.getInstance().create(friendship);
//        textFieldFriendshipId.clear();
        textFieldFriendshipFirstUserId.clear();
        textFieldFriendshipSecondUserId.clear();
        tableViewShowFriendships.getItems().add(FriendshipService.getInstance().read().get(FriendshipService.getInstance().read().size() - 1));
    }

    @FXML
    TableView<Friendship> tableViewShowFriendships;
    @FXML
    TableColumn<Friendship, Integer> tableColumnFriendshipId;
    @FXML
    TableColumn<Friendship, Integer> tableColumnFriendshipFirstUserId;
    @FXML
    TableColumn<Friendship, Integer> tableColumnFriendshipSecondUserId;

    @FXML
    protected void onDeleteFriendshipButton(ActionEvent actionEvent) {
        Friendship friendship = tableViewShowFriendships.getSelectionModel().getSelectedItem();
        FriendshipService.getInstance().delete(friendship);
        tableViewShowFriendships.getItems().remove(friendship);
    }

    ObservableList<Friendship> model = FXCollections.observableArrayList(FriendshipService.getInstance().read());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableColumnFriendshipId.setCellValueFactory(new PropertyValueFactory<Friendship, Integer>("id"));
        tableColumnFriendshipFirstUserId.setCellValueFactory(new PropertyValueFactory<Friendship, Integer>("FirstUserId"));
        tableColumnFriendshipSecondUserId.setCellValueFactory(new PropertyValueFactory<Friendship, Integer>("SecondUserId"));
        tableViewShowFriendships.setItems(model);
    }
}
