package com.example.lab4mergiterog.domain.validators;

import com.example.lab4mergiterog.domain.Friendship;

import java.util.Objects;

public class FriendshipValidator implements Validator<Friendship> {
    /**
     * This method checks if our object is valid or not
     * @param friendship Friendship object
     * @throws ValidationException
     */
    @Override
    public void validate(Friendship friendship) throws ValidationException {
        if(friendship.getId() <= 0) {
            throw new ValidationException("Id-ul friendshipului nu poate fi negativ!");
        }
        if(Objects.equals(friendship.getFirstUserId(), friendship.getSecondUserId())) {
            throw new ValidationException("Nu poate exista o relatie de prietenie intre un utilizator si el insusi.");
        }
    }
}
