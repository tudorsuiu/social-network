package com.example.lab4mergiterog.repository.dbrepository;

import com.example.lab4mergiterog.domain.Friendship;
import com.example.lab4mergiterog.domain.User;
import com.example.lab4mergiterog.repository.FriendshipRepository;
import com.example.lab4mergiterog.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendshipRepositoryDB implements Repository<Friendship> {
    private String url = "jdbc:postgresql://localhost:5432/socialnetwork";
    private String username = "postgres";
    private String password = "admin";
    private static FriendshipRepositoryDB instance = null;
    private FriendshipRepositoryDB() {
    }

    public static FriendshipRepositoryDB getInstance() {
        if(instance == null) {
            instance = new FriendshipRepositoryDB();
        }
        return instance;
    }

    @Override
    public void create(Friendship entity) {
        String sql = "insert into friendships (id, first_user_id, second_user_id) values (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, entity.getId());
            ps.setInt(2, entity.getFirstUserId());
            ps.setInt(3, entity.getSecondUserId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Friendship> read() {
        List<Friendship> friendships = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from friendships");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Integer firstUserId = resultSet.getInt("first_user_id");
                Integer secondUserId = resultSet.getInt("second_user_id");
                Friendship friendship = new Friendship(id, firstUserId, secondUserId);
                friendships.add(friendship);
            }
            return friendships;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friendships;
    }

    @Override
    public Friendship read(int index) {
        return null;
    }

    @Override
    public void update(Friendship oldEntity, Friendship newEntity) {

    }

    @Override
    public void delete(Friendship entity) {
        String sql = "DELETE FROM friendships f WHERE f.id = (?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1,entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
