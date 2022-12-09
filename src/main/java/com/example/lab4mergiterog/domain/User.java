package com.example.lab4mergiterog.domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class User extends Entity {
    private String firstName;
    private String  lastName;
    private Integer age;
    private String email;
    private String password;

    /**
     * Constructor with parameters
     * @param id int - User id
     * @param firstName String - User first name
     * @param lastName String - User second name
     * @param age int - User age
     * @param email String - User email
     * @param password String - User password
     */
    public User(Integer id,String firstName, String lastName, Integer age, String email, String password) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public User(String firstName, String lastName, Integer age, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    /**
     * Copy-constructor
     * @param user User - new User object
     */
    public User(User user) {
        super(user.getId());
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.age = user.age;
        this.email = user.email;
        this.password = user.password;
    }

    /**
     * Default constructor
     */
    public User() {
    }

    /**
     * First name getter
     * @return String - first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * First name setter
     * @param firstName String - new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Last name getter
     * @return String - last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Last name setter
     * @param lastName String - new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Age getter
     * @return int - age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Age setter
     * @param age int - new age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Email getter
     * @return String - email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Email setter
     * @param email String - new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Password getter
     * @return String - password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Password setter
     * @param password String - new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * To string formatter
     * @return User object to string
     */
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}' + "\n";
    }
}
