package com.spring.dynamodb.controller;
import com.spring.dynamodb.entity.Customer;
import com.spring.dynamodb.entity.Song;
import com.spring.dynamodb.repository.CustomerRepository;
import com.spring.dynamodb.repository.SongsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLDataException;
import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongsController {
    @Autowired
    private SongsRepository songsRepository;

    @GetMapping("/getSongsByArtist")

    public List<Song> getSongsByArtist(@RequestParam("artist") String artist, @RequestParam String pageNumber) {

        List<Song> firstPageOfSongs = songsRepository.getSongsByArtist(artist, null);
        // retrieve title of last song returned on the first page using a helper method (method implementation not shown),
        // which would return "Where is the Love?" in this case and returns null if the provided list is empty
        Song lastSongTitle = firstPageOfSongs.get(firstPageOfSongs.size() - 1);
        //this call will return the song "Let's Get it Started"
        List<Song> secondPageOfSongs = songsRepository.getSongsByArtist(artist, lastSongTitle.getSongTitle());
        if (pageNumber.equals("1"))
            return firstPageOfSongs;
        else
            return secondPageOfSongs;

    }

    @PostMapping("/add")
    public Song saveSong(@RequestBody Song song) throws SQLDataException {
        return songsRepository.saveSong(song);
    }
}

