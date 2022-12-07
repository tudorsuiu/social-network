package com.example.lab4mergiterog;

import com.example.lab4mergiterog.domain.User;
import com.example.lab4mergiterog.repository.Repository;
import com.example.lab4mergiterog.repository.dbrepository.UserRepositoryDB;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("Reading data from file");
        String username="postgres";
        String pasword="admin";
        String url="jdbc:postgresql://localhost:5432/socialnetwork";
        UserRepositoryDB.getInstance().read().forEach(x-> System.out.println(x));
        FXMLLoader menuLoader = new FXMLLoader(Application.class.getResource("log-in-page.fxml"));
        Scene scene = new Scene(menuLoader.load());
        stage.setTitle("Log in");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}