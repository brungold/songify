package com.songifyDatabase.domain.crud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
class AlbumDeleter {

    private final AlbumRepository albumRepository;

    void deleteAllAlbumsById(final Set<Long> albumsIdsToDelete) {
        albumRepository.deleteByIdIn(albumsIdsToDelete);
    }
}
