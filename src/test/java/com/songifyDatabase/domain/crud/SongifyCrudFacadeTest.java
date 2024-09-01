package com.songifyDatabase.domain.crud;

import com.songifyDatabase.domain.crud.dto.ArtistDto;
import com.songifyDatabase.domain.crud.dto.ArtistRequestDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SongifyCrudFacadeTest {

    SongifyCrudFacade songifyCrudFacade = SongifyCrudFacadeConfiguration.createSongifyCrud(
            new InMemorySongRepository(),
            new InMemoryGenreRepository(),
            new InMemoryArtistRepository(),
            new InMemoryAlbumRepository()
    );


    @Test
    public void should_add_artist() {
        // given
        ArtistRequestDto artist = ArtistRequestDto.builder()
                .name("shaw mendes")
                .build();

        // when
        ArtistDto result = songifyCrudFacade.addArtist(artist);

        // then
        assertThat(result.id()).isEqualTo(0L);
        assertThat(result.name()).isEqualTo("shaw mendes");
    }

    @Test
    public void should_return_correct_dto() {
        // given
        ArtistRequestDto artist = ArtistRequestDto.builder()
                .name("shaw mendes")
                .build();

        // when
        ArtistDto result = songifyCrudFacade.addArtist(artist);

        // then
        assertThat(result.id()).isNotNull();
        assertThat(result.name()).isNotNull();
    }
}