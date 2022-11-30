package com.example.lab4mergiterog.domain;

public class Entity {
    protected Integer id;

    /**
     * Default constructor
     */
    public Entity() {
    }

    /**
     * Constructor with parameters
     * @param id int - Entity id
     */
    public Entity(Integer id) {
        this.id = id;
    }

    /**
     * Id getter
     * @return int - Entity id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Id setter
     * @param id int - new Entity id
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
