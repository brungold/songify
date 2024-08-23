package com.songifyDatabase.domain.crud;

class GenreWasNotDeletedException extends RuntimeException {
    GenreWasNotDeletedException(final String message) {
        super(message);
    }
}
