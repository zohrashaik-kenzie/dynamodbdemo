package com.spring.dynamodb.service;

import com.spring.dynamodb.controller.SongResponse;
import com.spring.dynamodb.controller.model.Song;
import com.spring.dynamodb.entity.SongEntity;
import com.spring.dynamodb.repository.SongsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utilities.util;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongService {
    @Autowired
    private SongsRepository songsRepository;

    public List<SongResponse> getAllSongs(){
        List<SongEntity> songEs = songsRepository.getAllSongs();
        List<Song>  songs = new ArrayList<>();
        for(SongEntity songE:songEs){
            songs.add(util.convertFromSongEntityToSong(songE));
        }

        List<SongResponse>  songsResponse= new ArrayList<>();
        for(Song song:songs){
            songsResponse.add(util.convertFromSongToSongReponse(song));
        }
        return songsResponse;
    }

    public List<SongEntity> getSongsByArtist(String artist, String pageNumber)
    {
        List<SongEntity> firstPageOfSongs = songsRepository.getSongsByArtist(artist, null);
        // retrieve title of last song returned on the first page using a helper method (method implementation not shown),
        // which would return "Where is the Love?" in this case and returns null if the provided list is empty
        SongEntity lastSongTitle = firstPageOfSongs.get(firstPageOfSongs.size() - 1);
        //this call will return the song "Let's Get it Started"
        List<SongEntity> secondPageOfSongs = songsRepository.getSongsByArtist(artist, lastSongTitle.getSongTitle());
        if (pageNumber.equals("1"))
            return firstPageOfSongs;
        else
            return secondPageOfSongs;
    }

    public SongEntity saveSong(SongEntity song){
         return songsRepository.saveSong(song);
    }

    public List<SongEntity> getArtistSongsByTitle(String artist, String songTitle){
        return songsRepository.getArtistSongsByTitle(artist, songTitle);
    }

    public List<SongEntity> getSongsByAwards(String minAwards, String maxAwards){
        return songsRepository.getSongsByAwards(minAwards,maxAwards);
    }
}
