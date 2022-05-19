package com.spring.dynamodb.service;

import com.spring.dynamodb.controller.SongResponse;
import com.spring.dynamodb.controller.model.Song;
import com.spring.dynamodb.entity.SongEntity;
import com.spring.dynamodb.repository.SongsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import utilities.util;

import java.util.ArrayList;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;

@Service
public class SongService {
    @Autowired
    private SongsRepository songsRepository;

    @Cacheable("List<SongResponse>")
    public List<SongResponse> getAllSongs(){
        try
        {
            System.out.println("Going to sleep for 5 Secs.. to simulate backend call.");
            Thread.sleep(1000*5);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
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

    @Cacheable("List<SongEntity>")
    public List<SongEntity> getArtistSongsByTitle(String artist, String songTitle){
        try
        {
            System.out.println("Going to sleep for 5 Secs.. to simulate backend call.");
            Thread.sleep(1000*5);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return songsRepository.getArtistSongsByTitle(artist, songTitle);
    }

    public List<SongEntity> getSongsByAwards(String minAwards, String maxAwards){
        return songsRepository.getSongsByAwards(minAwards,maxAwards);
    }
}
