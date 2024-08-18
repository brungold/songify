package com.songifyDatabase.domain.crud;

import com.songifyDatabase.domain.crud.dto.ArtistDto;
import org.springframework.data.repository.Repository;

interface ArtistRepository extends Repository<Artist, Long> {
    Artist save(Artist artist);
}
