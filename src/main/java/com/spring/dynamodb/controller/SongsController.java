package com.spring.dynamodb.controller;
import com.spring.dynamodb.controller.model.Song;
import com.spring.dynamodb.entity.SongEntity;
import com.spring.dynamodb.repository.SongsRepository;
import com.spring.dynamodb.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLDataException;
import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongsController {
    private SongService songService;

    SongsController(SongService songService) {
        this.songService = songService;
    }


    @GetMapping("/getSongsByArtist")
    public List<SongResponse> getSongsByArtist(@RequestParam("artist") String artist, @RequestParam String pageNumber) {

       return songService.getSongsByArtist(artist,pageNumber);

    }

    @PostMapping("/add")
    public SongEntity saveSong(@RequestBody SongEntity song) throws SQLDataException {
        return songService.saveSong(song);
    }

    @GetMapping("/getArtistSongsByTitle")
    public List<SongResponse> getArtistSongsByTitle(@RequestParam("artist") String artist, @RequestParam("songTitle") String songTitle) {
        return songService.getArtistSongsByTitle(artist, songTitle);
    }

    @GetMapping("/getSongsByAwards")
    public List<SongEntity> getSongs(@RequestParam("minAwards") String minAwards, @RequestParam("maxAwards") String maxAwards) {
        return songService.getSongsByAwards(minAwards,maxAwards);
    }

    @GetMapping("/getAllSongs")
    public ResponseEntity<List<SongResponse>> getSongs() {
        List<SongResponse> songs = songService.getAllSongs();
        return ResponseEntity.ok(songs);
    }

}

