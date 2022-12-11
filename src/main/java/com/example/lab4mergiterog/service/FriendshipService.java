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
        Integer maxim = 0;
        if(FriendshipRepositoryDB.getInstance().read().size() == 0) {
            return 1;
        }
        else {
            List<Friendship> friendships = FriendshipRepositoryDB.getInstance().read();
            for(Friendship f : friendships) {
                if(f.getId() > maxim) {
                    maxim = f.getId();
                }
            }
        }
        return maxim + 1;
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
                    Objects.equals(entity.getSecondUserId(), f.getSecondUserId()))) {
                if(Objects.equals(f.getStatus(), "accepted") && Objects.equals(entity.getStatus(), "accepted")) {
                    throw new ValidationException("You are already friend with: " +
                            UserService.getInstance().getUserById(entity.getSecondUserId()).getFirstName() + " " +
                            UserService.getInstance().getUserById(entity.getSecondUserId()).getLastName() + "!");
                }
                if(Objects.equals(f.getStatus(), "pending") && Objects.equals(entity.getStatus(), "pending")) {
                    throw new ValidationException("A friend request is already sent. Wait for " +
                            UserService.getInstance().getUserById(entity.getSecondUserId()).getFirstName() + " " +
                            UserService.getInstance().getUserById(entity.getSecondUserId()).getLastName() + " to respond!");
                }
            }
            else if((Objects.equals(entity.getFirstUserId(), f.getSecondUserId()) &&
                    Objects.equals(entity.getSecondUserId(), f.getFirstUserId()))) {
                if(Objects.equals(f.getStatus(), "accepted") && Objects.equals(entity.getStatus(), "accepted")) {
                    throw new ValidationException("You are already friend with: " +
                            UserService.getInstance().getUserById(entity.getSecondUserId()).getFirstName() + " " +
                            UserService.getInstance().getUserById(entity.getSecondUserId()).getLastName() + "!");
                }
                if(Objects.equals(f.getStatus(), "pending") && Objects.equals(entity.getStatus(), "pending")) {
                    throw new ValidationException("A friend request is already sent. " +
                            "You can accept it by going into requests section!");
                }
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

    public Friendship getFriendshipByIdsAndStatus(Integer firstUserId, Integer secondUserId, String status) {
        for(Friendship f : FriendshipRepositoryDB.getInstance().read()) {
            if(Objects.equals(f.getFirstUserId(), firstUserId) &&
                    Objects.equals(f.getSecondUserId(), secondUserId) &&
                    Objects.equals(f.getStatus(), status)) {
                return f;
            }
        }
        throw new ValidationException("A friendship relation with these attributes was not found!");
    }

    public Friendship getFriendshipByIds(Integer firstUserId, Integer secondUserId) {
        for(Friendship f : FriendshipRepositoryDB.getInstance().read()) {
            if((Objects.equals(f.getFirstUserId(), firstUserId) &&
                    Objects.equals(f.getSecondUserId(), secondUserId)) ||
                    (f.getSecondUserId() == firstUserId && f.getFirstUserId() == secondUserId)) {
                return f;
            }
        }
        throw new ValidationException("A friendship relation between these two users was not found!");
    }
}
