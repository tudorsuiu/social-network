package com.example.lab4mergiterog.service;

import com.example.lab4mergiterog.domain.Entity;
import com.example.lab4mergiterog.domain.validators.Validator;
import com.example.lab4mergiterog.repository.Repository;

import java.util.List;

public abstract class Service<E extends Entity> {
    Repository<E> repository;
    Validator<E> validator;

    /**
     * Validates and creates an entity
     * @param entity E - entity
     */
    public void create(E entity) {
        validator.validate(entity);
        repository.create(entity);
    }

    /**
     * Reads all entities from repository
     * @return List<E> - all entities from repository
     */
    public List<E> read() {
        return repository.read();
    }

    /**
     * Reads an entity by given index
     * @param index int - index
     * @return E - entity with given index
     */
    public E read(int index) {
        return repository.read(index);
    }

    /**
     * Validates a new entity and updates the old one with our new entity
     * @param oldEntity E - old entity
     * @param newEntity E - new entity
     */
    public void update(E oldEntity, E newEntity) {
        validator.validate(newEntity);
        repository.update(oldEntity, newEntity);
    }

    /**
     * Deletes an entity
     * @param entity E - entity to be deleted
     */
    public void delete(E entity) {
        repository.delete(entity);
    }
}
