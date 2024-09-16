package com.songifyDatabase.domain.songplayer;

import com.songifyDatabase.domain.crud.SongifyCrudFacade;
import com.songifyDatabase.domain.crud.dto.SongDto;

class SongPlayerFacade {

    private final SongifyCrudFacade songifyCrudFacade;
    private final YoutubeHttpClient youtubeHttpClient;


    public SongPlayerFacade(SongifyCrudFacade songifyCrudFacade, YoutubeHttpClient youtubeHttpClient) {
        this.songifyCrudFacade = songifyCrudFacade;
        this.youtubeHttpClient = youtubeHttpClient;
    }

    public String playSongWithId(Long id){
        SongDto songDtoById = songifyCrudFacade.findSongDtoById(id);
        String name = songDtoById.name();
        String result = youtubeHttpClient.playSongByName(name);
        if(result.equals("success")){
            return result;
        }
        throw new RuntimeException("some error - result failed");
    }
}
