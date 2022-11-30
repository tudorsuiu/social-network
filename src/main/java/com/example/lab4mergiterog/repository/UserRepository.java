package com.example.lab4mergiterog.repository;

import com.example.lab4mergiterog.domain.User;
import com.example.lab4mergiterog.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User> {
    List<User> entities;
    private static UserRepository instance = null;
    private UserRepository() {
        this.entities = new ArrayList<>();
    }

    public static UserRepository getInstance() {
        if(instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    @Override
    public void create(User entity) {
        entities.add(entity);
    }

    @Override
    public List<User> read() {
        return entities;
    }

    @Override
    public User read(int index) {
        return entities.get(index);
    }

    @Override
    public void update(User oldEntity, User newEntity) {
        entities.set(entities.indexOf(oldEntity),newEntity);
    }

    @Override
    public void delete(User entity) {
        entities.remove(entity);
    }
}
