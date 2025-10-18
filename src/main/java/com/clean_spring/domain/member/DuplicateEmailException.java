package com.clean_spring.domain.member;

public class DuplicateEmailException extends RuntimeException{
    public  DuplicateEmailException(String message) {
        super(message);
    }
}
