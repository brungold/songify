package com.songifyDatabase.domain.crud;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Log4j2
class ArtistDeleter {

    private final ArtistRepository artistRepository;
    private final ArtistRetriever artistRetriever;
    private final AlbumRetriever albumRetriever;
    private final AlbumDeleter albumDeleter;
    private final SongDeleter songDeleter;


    //chce usunąc artystę i jego albumy jezeli był na nich tylko sam
    void deleteArtistByIdWithAlbumsAndSongs(final Long artistId) {
        Artist artist = artistRetriever.findById(artistId);
        Set<Album> artistAlbums = albumRetriever.findAlbumsByArtistId(artist.getId());
        if (artistAlbums.isEmpty()) {
            log.info("Artist with id: " + artistId + " have 0 albums.");
            artistRepository.deleteById(artistId);
            return;
        }

        /*
        // czy album nalezy tylko do jednego artysty (tylko jego album)
        Set<Album> albumsWithMoreThanOneArtist = artistAlbums.stream()
                .filter(album -> album.getArtists().size() >= 2)
                .collect(Collectors.toSet());
        //z albumów zwierających dwóch lub więcej z tym artystą usuwamy tylko tego artystę
        albumsWithMoreThanOneArtist
                .forEach(album -> album.removeArtist(artist));
         */
        //UPROSZCZENIE POWYŻSZYCH DWÓCH STREAMÓW
        //odfiltruj mi albumy w którym jest ewięcej niż 2 artystów i usuń tego konkretnego artystę
        artistAlbums.stream()
                .filter(album -> album.getArtists().size() >= 2)
                .forEach(album -> album.removeArtist(artist));

        // mam pewność ze dojednego albumy był przypiisany jeden artysta
        Set<Album> albumWithOnlyOneArtist = artistAlbums.stream()
                .filter(album -> album.getArtists().size() == 1)
                .collect(Collectors.toSet());

        // zanim usuniemy albumy tego artysy musinmy usunąć piosenki z nich
        Set<Long> allSongsIdsFromAllAlbumsWhereWasOnlyThisArtist = albumWithOnlyOneArtist
                .stream()
                .flatMap(album -> album.getSongs().stream())
                .map(Song::getId)
                .collect(Collectors.toSet());

        songDeleter.deleteAllSongsById(allSongsIdsFromAllAlbumsWhereWasOnlyThisArtist);

        Set<Long> albumsIdsToDelete = albumWithOnlyOneArtist.stream()
                .map(Album::getId)
                .collect(Collectors.toSet());

        albumDeleter.deleteAllAlbumsById(albumsIdsToDelete);

        artistRepository.deleteById(artistId);

    }
}
