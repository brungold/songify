package com.songifyDatabase.domain.crud;

import com.songifyDatabase.domain.crud.dto.AlbumDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
class AlbumAdder {

    private final SongRetriever songRetriever;
    private final AlbumRepository albumRepository;

    AlbumDto addAlbum(final Set<Long> songIds, final String title, final Instant instant) {
//        Song song = songRetriever.findSongById(songId);
        Set<Song> songs = songIds.stream()
                .map(songRetriever::findSongById)
                .collect(Collectors.toSet());
        // todo refactor write songRetriever.findAllSongsByIds

        Album album = new Album();
        album.setTitle(title);
        album.addSongsToAlbum(songs);
        album.setReleaseDate(instant);
        Album savedAlbum = albumRepository.save(album);
        return new AlbumDto(savedAlbum.getId(), savedAlbum.getTitle());
    }

    Album addAlbum(final String title, final Instant instant) {
        Album album = new Album();
        album.setTitle(title);
        album.setReleaseDate(instant);
        return albumRepository.save(album);
    }
}
