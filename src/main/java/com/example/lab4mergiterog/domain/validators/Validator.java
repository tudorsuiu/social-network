package com.example.lab4mergiterog.domain.validators;

public interface Validator<E> {
    /**
     * Checks if E object is valid
     * @param entity E object
     * @throws ValidationException
     */
    void validate(E entity) throws ValidationException;
}
