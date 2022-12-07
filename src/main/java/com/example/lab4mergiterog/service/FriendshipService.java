package com.example.lab4mergiterog.service;

import com.example.lab4mergiterog.domain.Friendship;
import com.example.lab4mergiterog.domain.validators.FriendshipValidator;
import com.example.lab4mergiterog.domain.validators.ValidationException;
import com.example.lab4mergiterog.domain.validators.Validator;
import com.example.lab4mergiterog.repository.FriendshipRepository;
import com.example.lab4mergiterog.repository.Repository;
import com.example.lab4mergiterog.repository.dbrepository.FriendshipRepositoryDB;
import com.example.lab4mergiterog.repository.dbrepository.UserRepositoryDB;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

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
     * Generates the id for the next entity
     * @return Integer - id
     */
    public Integer idGenerator() {
        if(FriendshipRepositoryDB.getInstance().read().size() == 0) {
            return 1;
        }
        else {
            return FriendshipRepositoryDB.getInstance().read().get(FriendshipRepositoryDB.getInstance().read().size() - 1).getId() + 1;
        }
    }

    /**
     * Validates and creates an entity
     * @param entity E - entity
     */
    public void create(Friendship entity) {
        entity.setId(idGenerator());
        for(Friendship f : FriendshipService.getInstance().read()) {
            if(Objects.equals(f.getId(), entity.getId())) {
                throw new ValidationException("A friendship with this ID already exists.");
            }
            if((Objects.equals(entity.getFirstUserId(), f.getFirstUserId()) &&
                    Objects.equals(entity.getSecondUserId(), f.getSecondUserId())) ||
                    (Objects.equals(entity.getFirstUserId(), f.getSecondUserId()) &&
                            Objects.equals(entity.getSecondUserId(), f.getFirstUserId()))) {
                throw new ValidationException("A friendship relation between these 2 users already exist.");
            }
        }
        validator.validate(entity);
        FriendshipRepositoryDB.getInstance().create(entity);
    }

    /**
     * Reads all entities from repository
     * @return List<E> - all entities from repository
     */
    public List<Friendship> read() {
        return FriendshipRepositoryDB.getInstance().read();
    }

    /**
     * Reads an entity by given index
     * @param index int - index
     * @return E - entity with given index
     */
    public Friendship read(int index) {
        return FriendshipRepositoryDB.getInstance().read(index);
    }

    /**
     * Validates a new entity and updates the old one with our new entity
     * @param oldEntity E - old entity
     * @param newEntity E - new entity
     */
    public void update(Friendship oldEntity, Friendship newEntity) {
        validator.validate(newEntity);
        FriendshipRepositoryDB.getInstance().update(oldEntity, newEntity);
    }

    /**
     * Deletes an entity
     * @param entity E - entity to be deleted
     */
    public void delete(Friendship entity) {
        FriendshipRepositoryDB.getInstance().delete(entity);
    }

    /**
     * Friendship getter by friendship ID
     * @param id int - ID
     * @return Friendship - object
     */
    public Friendship getFriendshipById(int id) {
        for(Friendship f : FriendshipRepositoryDB.getInstance().read()) {
            if(f.getId() == id) {
                return f;
            }
        }
        throw new NoSuchElementException();
    }
}
