package com.example.lab4mergiterog.domain.validators;

import com.example.lab4mergiterog.domain.Friendship;

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
    }
}
