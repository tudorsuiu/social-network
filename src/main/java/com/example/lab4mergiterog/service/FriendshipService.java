package com.example.lab4mergiterog.service;

import com.example.lab4mergiterog.domain.Friendship;
import com.example.lab4mergiterog.domain.validators.FriendshipValidator;
import com.example.lab4mergiterog.domain.validators.Validator;
import com.example.lab4mergiterog.repository.FriendshipRepository;
import com.example.lab4mergiterog.repository.Repository;

import java.util.List;
import java.util.NoSuchElementException;

public class FriendshipService{
    Validator<Friendship> validator;

    private static FriendshipService instance = null;
    private FriendshipService() {
        validator = new FriendshipValidator();
    }

    public static FriendshipService getInstance() {
        if(instance == null) {
            instance = new FriendshipService();
        }
        return instance;
    }

    /**
     * Validates and creates an entity
     * @param entity E - entity
     */
    public void create(Friendship entity) {
        validator.validate(entity);
        FriendshipRepository.getInstance().create(entity);
    }

    /**
     * Reads all entities from repository
     * @return List<E> - all entities from repository
     */
    public List<Friendship> read() {
        return FriendshipRepository.getInstance().read();
    }

    /**
     * Reads an entity by given index
     * @param index int - index
     * @return E - entity with given index
     */
    public Friendship read(int index) {
        return FriendshipRepository.getInstance().read(index);
    }

    /**
     * Validates a new entity and updates the old one with our new entity
     * @param oldEntity E - old entity
     * @param newEntity E - new entity
     */
    public void update(Friendship oldEntity, Friendship newEntity) {
        validator.validate(newEntity);
        FriendshipRepository.getInstance().update(oldEntity, newEntity);
    }

    /**
     * Deletes an entity
     * @param entity E - entity to be deleted
     */
    public void delete(Friendship entity) {
        FriendshipRepository.getInstance().delete(entity);
    }

    /**
     * Friendship getter by friendship ID
     * @param id int - ID
     * @return Friendship - object
     */
    public Friendship getFriendshipById(int id) {
        for(Friendship f : FriendshipRepository.getInstance().read()) {
            if(f.getId() == id) {
                return f;
            }
        }
        throw new NoSuchElementException();
    }
}
