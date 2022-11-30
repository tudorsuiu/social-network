package com.example.lab4mergiterog.repository;

import com.example.lab4mergiterog.domain.Entity;

import java.util.List;

public interface Repository<E extends Entity> {
    void create(E entity);
    List<E> read();
    E read(int index);
    void update(E oldEntity, E newEntity);
    void delete(E entity);
}
