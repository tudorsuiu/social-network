package com.example.lab4mergiterog.service;

import com.example.lab4mergiterog.domain.Friendship;
import com.example.lab4mergiterog.domain.User;
import com.example.lab4mergiterog.domain.validators.UserValidator;
import com.example.lab4mergiterog.domain.validators.Validator;
import com.example.lab4mergiterog.repository.Repository;
import com.example.lab4mergiterog.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class UserService {
    Validator<User> validator;

    private static UserService instance = null;
    private UserService() {
        validator = new UserValidator();
    }

    public static UserService getInstance() {
        if(instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    /**
     * Validates and creates an entity
     * @param entity E - entity
     */
    public void create(User entity) {
        validator.validate(entity);
        UserRepository.getInstance().create(entity);
    }

    /**
     * Reads all entities from repository
     * @return List<E> - all entities from repository
     */
    public List<User> read() {
        return UserRepository.getInstance().read();
    }

    /**
     * Reads an entity by given index
     * @param index int - index
     * @return E - entity with given index
     */
    public User read(int index) {
        return UserRepository.getInstance().read(index);
    }

    /**
     * Validates a new entity and updates the old one with our new entity
     * @param oldEntity E - old entity
     * @param newEntity E - new entity
     */
    public void update(User oldEntity, User newEntity) {
        validator.validate(newEntity);
        UserRepository.getInstance().update(oldEntity, newEntity);
    }

    /**
     * Deletes an entity
     * @param entity E - entity to be deleted
     */
    public void delete(User entity) {
        for(int i = 0; i < FriendshipService.getInstance().read().size(); i++) {
            Friendship friendship = FriendshipService.getInstance().read().get(i);
            if(Objects.equals(friendship.getFirstUserID(), entity.getId()) || Objects.equals(friendship.getSecondUserID(), entity.getId())) {
                i--;
                FriendshipService.getInstance().delete(friendship);
            }
        }
        UserRepository.getInstance().delete(entity);
    }

    /**
     * Return the user from repository that has the id equal to given id
     * @param id integer
     * @return the user from repository that has the id equal to given id
     */
    public User getUserById(int id) {
        for(User u : UserRepository.getInstance().read()) {
            if(u.getId() == id) {
                return u;
            }
        }
        throw new NoSuchElementException();
    }
}
