package com.songifyDatabase.domain.crud;

class AlbumNotFoundException extends RuntimeException {
    AlbumNotFoundException(final String message) {
        super(message);
    }
}
