package com.songifyDatabase.domain.crud;

public class AlbumNotFoundException extends RuntimeException {
    AlbumNotFoundException(final String message) {
        super(message);
    }
}
