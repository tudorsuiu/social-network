package com.example.lab4mergiterog.service;

import com.example.lab4mergiterog.domain.Friendship;
import com.example.lab4mergiterog.domain.User;
import com.example.lab4mergiterog.domain.validators.UserValidator;
import com.example.lab4mergiterog.domain.validators.ValidationException;
import com.example.lab4mergiterog.domain.validators.Validator;
import com.example.lab4mergiterog.repository.Repository;
import com.example.lab4mergiterog.repository.dbrepository.UserRepositoryDB;

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
     * Generates the id for the next entity
     * @return Integer - id
     */
    public Integer idGenerator() {
        Integer maxim = 0;
        if(UserRepositoryDB.getInstance().read().size() == 0) {
            return 1;
        }
        else {
            List<User> users = UserRepositoryDB.getInstance().read();
            for(User u : users) {
                if(u.getId() > maxim) {
                    maxim = u.getId();
                }
            }
        }
        return maxim + 1;
    }

    /**
     * Validates and creates an entity
     * @param entity E - entity
     */
    public void create(User entity) {
        entity.setId(idGenerator());
        for(User u : UserService.getInstance().read()) {
            if(Objects.equals(u.getId(), entity.getId())) {
                throw new ValidationException("An user with this ID already exists.");
            }
            if(Objects.equals(u.getEmail(), entity.getEmail())) {
                throw new ValidationException("There is already an account with this email address.");
            }
        }
        validator.validate(entity);
        UserRepositoryDB.getInstance().create(entity);
    }

    /**
     * Reads all entities from repository
     * @return List<E> - all entities from repository
     */
    public List<User> read() {
        return UserRepositoryDB.getInstance().read();
    }

    /**
     * Reads an entity by given index
     * @param index int - index
     * @return E - entity with given index
     */
    public User read(int index) {
        return UserRepositoryDB.getInstance().read(index);
    }

    /**
     * Validates a new entity and updates the old one with our new entity
     * @param oldEntity E - old entity
     * @param newEntity E - new entity
     */
    public void update(User oldEntity, User newEntity) {
        validator.validate(newEntity);
        UserRepositoryDB.getInstance().update(oldEntity, newEntity);
    }

    /**
     * Deletes an entity
     * @param entity E - entity to be deleted
     */
    public void delete(User entity) {
        for(int i = 0; i < FriendshipService.getInstance().read().size(); i++) {
            Friendship friendship = FriendshipService.getInstance().read().get(i);
            if(Objects.equals(friendship.getFirstUserId(), entity.getId()) || Objects.equals(friendship.getSecondUserId(), entity.getId())) {
                i--;
                FriendshipService.getInstance().delete(friendship);
            }
        }
        UserRepositoryDB.getInstance().delete(entity);
    }

    /**
     * Return the user from repository that has the id equal to given id
     * @param id integer
     * @return the user from repository that has the id equal to given id
     */
    public User getUserById(int id) {
        for(User u : UserRepositoryDB.getInstance().read()) {
            if(u.getId() == id) {
                return u;
            }
        }
        throw new NoSuchElementException();
    }

    public User verifyEmailAndPassword(String email, String password) {
        for(User u : UserService.getInstance().read()) {
            if(Objects.equals(u.getEmail(), email) && Objects.equals(u.getPassword(), password)) {
                return u;
            }
        }
        throw new ValidationException("An account with these credentials doesn't exist.");
    }
}
