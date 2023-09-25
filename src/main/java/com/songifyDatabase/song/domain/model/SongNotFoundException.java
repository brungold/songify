package com.songifyDatabase.song.domain.model;

public class SongNotFoundException extends RuntimeException {
    //RuntimeException klasa nadrzedna. Runtime jest zbyt odgólne
    //Poniższa nie jest sama w sobie runtimeexceptionem. mamy pewność że piosenka nie została odnaleziona
    public SongNotFoundException(String message) {
        super(message);// super -> wywołuje konstruktor klasy która jest dziedziczona
    }
}
