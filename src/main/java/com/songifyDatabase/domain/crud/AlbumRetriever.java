package com.songifyDatabase.domain.crud;

import com.songifyDatabase.domain.crud.dto.AlbumDto;
import com.songifyDatabase.domain.crud.dto.AlbumDtoWithArtistsAndSongs;
import com.songifyDatabase.domain.crud.dto.AlbumInfo;
import com.songifyDatabase.domain.crud.dto.ArtistDto;
import com.songifyDatabase.domain.crud.dto.SongDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
class AlbumRetriever {

    private final AlbumRepository albumRepository;

    Set<Album> findAlbumsByArtistId(final Long artistId) {
        return albumRepository.findAllAlbumsByArtistId(artistId);
    }

    Set<AlbumDto> findAlbumsDtoByArtistId(final Long artistId) {
        return findAlbumsByArtistId(artistId)
                .stream()
                .map(album -> new AlbumDto(album.getId(), album.getTitle(), album.getSongsIds()))
                .collect(Collectors.toSet());

    }

    AlbumInfo findAlbumByIdWithArtistsAndSongs(final Long id) {
        return albumRepository.findAlbumByIdWithSongsAndArtists(id)
                .orElseThrow(() -> new AlbumNotFoundException("Album with id: " + id + " not found"));
    }

    long countArtistsByAlbumId(final Long id) {
        return findById(id)
                .getArtists()
                .size();
    }

    Album findById(final Long albumId) {
        return albumRepository.findById(albumId)
                .orElseThrow(() -> new AlbumNotFoundException("Album with id: " + albumId + " not found"));
    }

    AlbumDto findDtoById(final Long albumId) {
        Album album = findById(albumId);
        return new AlbumDto(
                album.getId(),
                album.getTitle(),
                album.getSongsIds()
        );
    }

    Set<AlbumDto> findAll() {
        return albumRepository.findAll()
                .stream()
                .map(album -> new AlbumDto(album.getId(), album.getTitle(), album.getSongsIds()))
                .collect(Collectors.toSet());
    }
//    AlbumDtoWithArtistsAndSongs findAlbumByIdWithArtistsAndSongs(final Long id) {
//        Album album = albumRepository.findAlbumByIdWithSongsAndArtists(id)
//                .orElseThrow(() -> new AlbumNotFoundException("Album with id: " + id + " not found."));
//
//        Set<Artist> artists = album.getArtists();
//        Set<Song> songs = album.getSongs();
//
//        AlbumDto albumDto = new AlbumDto(album.getId(), album.getTitle());
//
//        Set<ArtistDto> artistsDto = artists.stream()
//                .map(artist -> new ArtistDto(
//                        artist.getId(),
//                        artist.getName()
//                ))
//                .collect(Collectors.toSet());
//
//        Set<SongDto> songsDto = songs.stream()
//                .map(artist -> new SongDto(
//                        artist.getId(),
//                        artist.getName()
//                ))
//                .collect(Collectors.toSet());
//
//        return new AlbumDtoWithArtistsAndSongs(
//                albumDto,
//                artistsDto,
//                songsDto
//        );
}

