package com.songifyDatabase.domain.crud;

import com.songifyDatabase.domain.crud.dto.AlbumDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Service
class SongAssigner {
    private final AlbumRetriever albumRetriever;
    private final SongRetriever songRetriever;

    AlbumDto assignSongToAlbum(Long albumId, Long songId) {
        Album album = albumRetriever.findById(albumId);
        Song songById = songRetriever.findSongById(songId);
        album.addSongToAlbum(songById);
        return new AlbumDto(
                album.getId(),
                album.getTitle(),
                album.getSongsIds()
        );
    }
}