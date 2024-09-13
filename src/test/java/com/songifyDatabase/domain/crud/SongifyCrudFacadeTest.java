package com.songifyDatabase.domain.crud;

import com.songifyDatabase.domain.crud.dto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

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
    @DisplayName("Should add artist 'amigo' with id:0 When amigo was sent")
    public void should_add_artist_amigo_with_id_zero_when_amigo_was_sent() {
        // given
        ArtistRequestDto artist = ArtistRequestDto.builder()
                .name("amigo")
                .build();
        Set<ArtistDto> allArtists = songifyCrudFacade.findAllArtists(Pageable.unpaged());
        assertTrue(allArtists.isEmpty());
        // when
        ArtistDto result = songifyCrudFacade.addArtist(artist);
        // then
        assertThat(result.id()).isEqualTo(0L);
        assertThat(result.name()).isEqualTo("amigo");
        int size = songifyCrudFacade.findAllArtists(Pageable.unpaged()).size();
        assertThat(size).isEqualTo(1);
    }

    @Test
    @DisplayName("Should throw exception ArtistNotFound When id was: 0")
    public void should_throw_exception_artist_not_found_when_id_was_zero() {
        // given
        assertThat(songifyCrudFacade.findAllArtists(Pageable.unpaged())).isEmpty();
        // when
        Throwable throwable = catchThrowable(() -> songifyCrudFacade.deleteArtistByIdWithAlbumsAndSongs(0L));
        // then
        assertThat(throwable).isInstanceOf(ArtistNotFoundException.class);
        assertThat(throwable.getMessage()).isEqualTo("artist with id: 0 not found");
    }

    @Test
    @DisplayName("Should delete artist by id When he have no albums")
    public void should_delete_artist_by_id_when_he_have_no_albums() {
        //given
        ArtistRequestDto shawMendes = ArtistRequestDto.builder()
                .name("shaw mendes")
                .build();
//        ArtistDto artistDto = songifyCrudFacade.addArtist(shawMendes);
//        Long artistId = artistDto.id();
        Long artistId = songifyCrudFacade.addArtist(shawMendes).id();
        assertThat(songifyCrudFacade.findAlbumsByArtistId(artistId)).isEmpty();
        //when
        songifyCrudFacade.deleteArtistByIdWithAlbumsAndSongs(artistId);
        //then
        assertThat(songifyCrudFacade.findAllArtists(Pageable.unpaged())).isEmpty();
    }

    @Test
    @DisplayName("Should delete artist with album and songs by id When artist had one album and he was the only artist in album")
    public void should_delete_artist_with_album_and_songs_by_id_when_artist_had_one_album_and_he_was_the_only_artist_in_album() {
        //given
        ArtistRequestDto shawMendes = ArtistRequestDto.builder()
                .name("shaw mendes")
                .build();
        Long artistId = songifyCrudFacade.addArtist(shawMendes).id();

        SongRequestDto song = SongRequestDto
                .builder()
                .name("song1")
                .language(SongLanguageDto.ENGLISH)
                .build();
        SongDto songDto = songifyCrudFacade.addSong(song);
        Long songId = songDto.id();

        AlbumDto albumDto = songifyCrudFacade.addAlbumWithSong(AlbumRequestDto
                .builder()
                .songIds(Set.of(songId))
                .title("album title 1")
                .build());

        Long albumId = albumDto.id();
        songifyCrudFacade.addArtistToAlbum(artistId, albumId);

        assertThat(songifyCrudFacade.findAlbumsByArtistId(artistId)).size().isEqualTo(1);
        assertThat(songifyCrudFacade.countArtistsByAlbumId(albumId)).isEqualTo(1);
        //when
        songifyCrudFacade.deleteArtistByIdWithAlbumsAndSongs(artistId);
        //then
        assertThat(songifyCrudFacade.findAllArtists(Pageable.unpaged())).isEmpty();
        Throwable throwable = catchThrowable(() -> songifyCrudFacade.findSongDtoById(songId));
        assertThat(throwable).isInstanceOf(SongNotFoundException.class);
        assertThat(throwable.getMessage()).isEqualTo("Song with id: 0 not found");
        Throwable throwable2 = catchThrowable(() -> songifyCrudFacade.findAlbumById(albumId));
        assertThat(throwable2).isInstanceOf(AlbumNotFoundException.class);
        assertThat(throwable2.getMessage()).isEqualTo("Album with id: 0 not found");
    }


    @Test
    @DisplayName("Should add album with song")
    public void should_add_album_with_song() {
        //given
        SongRequestDto songRequestDto = SongRequestDto.builder()
                .name("song1")
                .language(SongLanguageDto.ENGLISH)
                .build();
        SongDto songDto = songifyCrudFacade.addSong(songRequestDto);

        AlbumRequestDto album = AlbumRequestDto.builder()
                .songIds(Set.of(songDto.id()))
                .title("album title 1")
                .build();
        assertThat(songifyCrudFacade.findAllAlbums()).isEmpty();
        //when
        AlbumDto albumDto = songifyCrudFacade.addAlbumWithSong(album);
        //then
        assertThat(songifyCrudFacade.findAllAlbums()).isNotEmpty();
        AlbumInfo albumWithSongs = songifyCrudFacade.findAlbumByIdWithArtistsAndSongs(albumDto.id());
        Set<AlbumInfo.SongInfo> songs = albumWithSongs.getSongs();
        assertTrue(songs.stream()
                .anyMatch(song -> song.getId().equals(songDto.id()))
        );
    }

    @Test
    @DisplayName("Should add song")
    public void should_add_song() {
        //given
        SongRequestDto song = SongRequestDto
                .builder()
                .name("song1")
                .language(SongLanguageDto.ENGLISH)
                .build();
        assertThat(songifyCrudFacade.findAllSongs(Pageable.unpaged())).isEmpty();
        //when
        SongDto songDto = songifyCrudFacade.addSong(song);
        Long songId = songDto.id();
        //then
        assertThat(songifyCrudFacade.findAllSongs(Pageable.unpaged())).isNotEmpty();
        assertThat(songifyCrudFacade.findSongDtoById(songId).id()).isEqualTo(0L);
        assertThat(songifyCrudFacade.findSongDtoById(songId).name()).isEqualTo("song1");
        List<SongDto> allSongs = songifyCrudFacade.findAllSongs(Pageable.unpaged());
        assertThat(allSongs)
                .extracting(SongDto::id)
                .containsExactly(0L);

        assertThat(allSongs)
                .extracting(SongDto::name)
                .containsExactly("song1");
    }

    @Test
    @DisplayName("Should add artist to album")
    public void should_add_artist_to_album() {
        //given
        ArtistRequestDto shawMendes = ArtistRequestDto.builder()
                .name("shaw mendes")
                .build();
        Long artistId = songifyCrudFacade.addArtist(shawMendes).id();
        assertThat(songifyCrudFacade.findAlbumsByArtistId(artistId)).isEmpty();

        SongRequestDto songRequestDto = SongRequestDto.builder()
                .name("song1")
                .language(SongLanguageDto.ENGLISH)
                .build();
        SongDto songDto = songifyCrudFacade.addSong(songRequestDto);
        Long songId = songDto.id();

        AlbumDto album = songifyCrudFacade.addAlbumWithSong(AlbumRequestDto.builder()
                .songIds(Set.of(songId))
                .title("album title 1")
                .build());
        Long albumId = album.id();
        assertThat(songifyCrudFacade.findAlbumsByArtistId(artistId)).isEmpty();
        //when
        songifyCrudFacade.addArtistToAlbum(artistId, albumId);
        //then
        //assertThat(songifyCrudFacade.findAlbumsByArtistId(artistId)).isNotEmpty();
        assertThat(songifyCrudFacade.findAlbumsByArtistId(artistId)).isEqualTo(Set.of(new AlbumDto(0L, "album title 1")));
        Set<AlbumDto> albumsByArtistId = songifyCrudFacade.findAlbumsByArtistId(artistId);
        assertThat(albumsByArtistId)
                .extracting(album1 -> album.id())
                .containsExactly(0L);
    }

    @Test
    @DisplayName("Should return album by id")
    public void should_return_album_by_id() {
        //given
        SongRequestDto songRequestDto = SongRequestDto.builder()
                .name("song1")
                .language(SongLanguageDto.ENGLISH)
                .build();
        SongDto songDto = songifyCrudFacade.addSong(songRequestDto);
        Long songId = songDto.id();

        assertThat(songifyCrudFacade.findAllAlbums()).isEmpty();

        AlbumDto albumDto = songifyCrudFacade.addAlbumWithSong(AlbumRequestDto
                .builder()
                .songIds(Set.of(songId))
                .title("album title 1")
                .build());
        Long albumId = albumDto.id();
        assertThat(songifyCrudFacade.findAllAlbums()).isNotEmpty();
        //when
        AlbumDto albumById = songifyCrudFacade.findAlbumById(albumId);
        //then
        assertThat(albumById)
                .isEqualTo(new AlbumDto(albumId, "album title 1"));
    }

    @Test
    @DisplayName("Should throw exception When album not found by id")
    public void should_throw_exception_When_album_not_found_by_id() {
        //given
        assertThat(songifyCrudFacade.findAllAlbums()).isEmpty();
        //when
        Throwable throwable = catchThrowable(() -> songifyCrudFacade.findAlbumById(1L));
        //then
        assertThat(throwable).isInstanceOf(AlbumNotFoundException.class);
        assertThat(throwable.getMessage()).isEqualTo("Album with id: 1 not found");
    }

    @Test
    @DisplayName("Should throw exception When song not found by id")
    public void should_throw_exception_When_song_not_found_by_id() {
        //given
        assertThat(songifyCrudFacade.findAllSongs(Pageable.unpaged())).isEmpty();
        //when
        Throwable throwable = catchThrowable(() -> songifyCrudFacade.findSongDtoById(1L));
        //then
        assertThat(throwable).isInstanceOf(SongNotFoundException.class);
        assertThat(throwable.getMessage()).isEqualTo("Song with id: 1 not found");
    }

    @Test
    @DisplayName("Should delete only artist from album by id When there were more than 1 artist in album")
    public void should_delete_only_artist_from_album_by_id_when_there_were_more_than_one_artist_in_album() {
        //given
        ArtistRequestDto shawMendes = ArtistRequestDto.builder()
                .name("shaw mendes")
                .build();
        ArtistRequestDto camilaCabello = ArtistRequestDto.builder()
                .name("camila cabello")
                .build();
        Long artistId1 = songifyCrudFacade.addArtist(shawMendes).id();
        Long artistId2 = songifyCrudFacade.addArtist(camilaCabello).id();

        SongRequestDto songRequestDto = SongRequestDto.builder()
                .name("Seniorita")
                .language(SongLanguageDto.ENGLISH)
                .build();
        SongDto songDto = songifyCrudFacade.addSong(songRequestDto);

        AlbumDto albumDto = songifyCrudFacade.addAlbumWithSong(AlbumRequestDto
                .builder()
                .songIds(Set.of(songDto.id()))
                .title("Album with Seniorita")
                .build());
        Long albumId = albumDto.id();
        songifyCrudFacade.addArtistToAlbum(artistId1, albumId);
        songifyCrudFacade.addArtistToAlbum(artistId2, albumId);
        assertThat(songifyCrudFacade.countArtistsByAlbumId(albumId)).isEqualTo(2);
        //when
        songifyCrudFacade.deleteArtistByIdWithAlbumsAndSongs(artistId1);
        //then
        assertThat(songifyCrudFacade.countArtistsByAlbumId(albumId)).isEqualTo(1);
        AlbumInfo album = songifyCrudFacade.findAlbumByIdWithArtistsAndSongs(albumId);
        Set<AlbumInfo.ArtistInfo> artists = album.getArtists();
        assertThat(artists)
                .extracting("id")
                //.extracting(AlbumInfo.ArtistInfo::getId)
                .containsOnly(artistId2);
    }

    @Test
    @DisplayName("Should delete artist with albums and songs by id when artist was the only artist in albums")
    public void should_delete_artist_with_albums_and_songs_by_id_when_artist_was_the_only_artist_in_albums() {
        //given
        ArtistRequestDto camilaCabello = ArtistRequestDto.builder()
                .name("camila cabello")
                .build();
        Long artistId = songifyCrudFacade.addArtist(camilaCabello).id();

        SongRequestDto song = SongRequestDto.builder()
                .name("song1")
                .language(SongLanguageDto.ENGLISH)
                .build();
        SongRequestDto song2 = SongRequestDto.builder()
                .name("song2")
                .language(SongLanguageDto.ENGLISH)
                .build();
        SongRequestDto song3 = SongRequestDto.builder()
                .name("song3")
                .language(SongLanguageDto.ENGLISH)
                .build();
        SongRequestDto song4 = SongRequestDto.builder()
                .name("song4")
                .language(SongLanguageDto.ENGLISH)
                .build();
        SongDto songDto = songifyCrudFacade.addSong(song);
        SongDto songDto2 = songifyCrudFacade.addSong(song2);
        SongDto songDto3 = songifyCrudFacade.addSong(song3);
        SongDto songDto4 = songifyCrudFacade.addSong(song4);
        Long songId = songDto.id();
        Long songId2 = songDto2.id();
        Long songId3 = songDto3.id();
        Long songId4 = songDto4.id();

        AlbumDto albumDto = songifyCrudFacade.addAlbumWithSong(AlbumRequestDto
                .builder()
                .songIds(Set.of(songId, songId2))
                .title("Album1")
                .build());

        AlbumDto albumDto2 = songifyCrudFacade.addAlbumWithSong(AlbumRequestDto
                .builder()
                .songIds(Set.of(songId3, songId4))
                .title("Album2")
                .build());

        Long albumId = albumDto.id();
        Long albumId2 = albumDto2.id();
        songifyCrudFacade.addArtistToAlbum(artistId, albumId);
        songifyCrudFacade.addArtistToAlbum(artistId, albumId2);

        assertThat(songifyCrudFacade.countArtistsByAlbumId(albumId)).isEqualTo(1);
        assertThat(songifyCrudFacade.countArtistsByAlbumId(albumId2)).isEqualTo(1);
        assertThat(songifyCrudFacade.findAllArtists(Pageable.unpaged()).size()).isEqualTo(1);
        assertThat(songifyCrudFacade.findAllAlbums().size()).isEqualTo(2);
        assertThat(songifyCrudFacade.findAllSongs(Pageable.unpaged()).size()).isEqualTo(4);
        //when
        songifyCrudFacade.deleteArtistByIdWithAlbumsAndSongs(artistId);
        //then
        assertThat(songifyCrudFacade.findAllArtists(Pageable.unpaged())).isEmpty();
        assertThat(songifyCrudFacade.findAllAlbums()).isEmpty();
        assertThat(songifyCrudFacade.findAllSongs(Pageable.unpaged())).isEmpty();
    }
}