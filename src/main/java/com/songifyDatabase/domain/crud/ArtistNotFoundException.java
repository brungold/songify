package com.songifyDatabase.domain.crud;

class ArtistNotFoundException extends RuntimeException {
    ArtistNotFoundException(final String message) {
        super("artist with id: " + message + "not found");
    }
}
