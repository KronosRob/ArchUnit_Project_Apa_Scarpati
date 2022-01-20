package alexp.blog.service;

import alexp.blog.exception.ValidationError;

public class AlreadyVotedException extends Exception implements ValidationError {

    public AlreadyVotedException(String message) {
        super(message);
    }
}
