package com.example.lab4mergiterog.repository;

import com.example.lab4mergiterog.domain.Friendship;
import com.example.lab4mergiterog.domain.User;

import java.util.ArrayList;
import java.util.List;

public class FriendshipRepository implements Repository<Friendship> {
    List<Friendship> entities;
    private static FriendshipRepository instance = null;
    private FriendshipRepository() {
        this.entities = new ArrayList<>();
    }

    public static FriendshipRepository getInstance() {
        if(instance == null) {
            instance = new FriendshipRepository();
        }
        return instance;
    }

    @Override
    public void create(Friendship entity) {
        entities.add(entity);
    }

    @Override
    public List<Friendship> read() {
        return entities;
    }

    @Override
    public Friendship read(int index) {
        return entities.get(index);
    }

    @Override
    public void update(Friendship oldEntity, Friendship newEntity) {
        entities.set(entities.indexOf(oldEntity),newEntity);
    }

    @Override
    public void delete(Friendship entity) {
        entities.remove(entity);
    }
}
