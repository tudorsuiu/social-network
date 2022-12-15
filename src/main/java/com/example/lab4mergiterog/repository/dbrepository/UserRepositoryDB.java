package com.example.lab4mergiterog.repository.dbrepository;

import com.example.lab4mergiterog.domain.User;
import com.example.lab4mergiterog.domain.validators.Validator;
import com.example.lab4mergiterog.repository.FriendshipRepository;
import com.example.lab4mergiterog.repository.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class UserRepositoryDB implements Repository<User> {
    private String url = "jdbc:postgresql://localhost:5432/socialnetwork";
    private String username = "postgres";
    private String password = "admin";
    private static UserRepositoryDB instance = null;
    private UserRepositoryDB() {
    }

    public static UserRepositoryDB getInstance() {
        if(instance == null) {
            instance = new UserRepositoryDB();
        }
        return instance;
    }

    @Override
    public void create(User entity) {
        String sql = "insert into users (id, first_name, last_name, age, email, password) values (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1,entity.getId());
            ps.setString(2, entity.getFirstName());
            ps.setString(3, entity.getLastName());
            ps.setInt(4, entity.getAge());
            ps.setString(5, entity.getEmail());
            ps.setString(6,entity.getPassword());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> read() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from users");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Integer age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                User user = new User(id, firstName, lastName, age, email, password);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User read(int index) {
        return null;
    }

    @Override
    public void update(User oldEntity, User newEntity) {
        String sql = "UPDATE users SET first_name = (?), last_name = (?), age = (?), email = (?), password = (?) WHERE id = (?)";
        try(Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1,newEntity.getFirstName());
            ps.setString(2,newEntity.getLastName());
            ps.setInt(3,newEntity.getAge());
            ps.setString(4,newEntity.getEmail());
            ps.setString(5,newEntity.getPassword());
            ps.setInt(6, oldEntity.getId());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User entity) {
        String sql = "DELETE FROM users u WHERE u.id = (?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1,entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
