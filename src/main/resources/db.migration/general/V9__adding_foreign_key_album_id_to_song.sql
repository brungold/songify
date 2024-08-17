-- ALTER TABLE song
--     ADD album_id BIGINT;
--
-- ALTER TABLE song
--     ADD CONSTRAINT FK_SONG_ON_ALBUM FOREIGN KEY (album_id) REFERENCES album (id);

ALTER TABLE song
    ADD album_id BIGINT REFERENCES album(id);