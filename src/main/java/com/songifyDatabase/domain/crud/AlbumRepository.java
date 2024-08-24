package com.songifyDatabase.domain.crud;

import com.songifyDatabase.domain.crud.dto.AlbumInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

interface AlbumRepository extends Repository<Album, Long> {

    Optional<Album> findById(Long albumId);

    @Modifying
    @Query("delete from Album a where a.id in :ids")
    int deleteByIdIn(Collection<Long> ids);


    Album save(Album album);

//    Optional<Album> findById(Long id);

    @Query("""
            select a from Album a
            join fetch a.songs songs
            join fetch a.artists artists
            where a.id = :id
            """)
    Optional<AlbumInfo> findAlbumByIdWithSongsAndArtists(Long id);

    @Query("""
            select a from Album a 
            inner join a.artists artists 
            where artists.id = :id
            """)
    Set<Album> findAllAlbumsByArtistId(@Param("id") Long id);

}
