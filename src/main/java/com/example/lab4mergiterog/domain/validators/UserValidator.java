package com.example.lab4mergiterog.domain.validators;

import com.example.lab4mergiterog.domain.User;

public class UserValidator implements Validator<User> {
    /**
     * This method checks if our object is valid
     * @param user User object
     * @throws ValidationException
     */
    @Override
    public void validate(User user) throws ValidationException {
        if(user.getId() <= 0) {
            throw new ValidationException("Id-ul userului nu poate fi negativ!");
        }
        if(!user.getFirstName().matches("[a-zA-Z]+")) {
            throw new ValidationException("First name-ul userului nu poate contine altceva in afara de litere!");
        }
        if(!user.getLastName().matches("[a-zA-Z]+")) {
            throw new ValidationException("Last name-ul userului nu poate contine altceva in afara de litere!");
        }
        if(user.getAge() < 18) {
            throw new ValidationException("Userul trebuie sa aibe varsta minima de 18 ani!");
        }
    }
}
