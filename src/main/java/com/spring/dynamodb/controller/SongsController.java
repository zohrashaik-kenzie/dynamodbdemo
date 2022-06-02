package com.spring.dynamodb.controller;
import com.spring.dynamodb.controller.model.Song;
import com.spring.dynamodb.entity.SongEntity;
import com.spring.dynamodb.repository.SongsRepository;
import com.spring.dynamodb.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLDataException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/songs")

public class SongsController {
    private SongService songService;

    SongsController(SongService songService) {
        this.songService = songService;
    }


    @GetMapping("/getSongsByArtist")
    public List<SongResponse> getSongsByArtist2(@RequestParam("artist") String artist, @RequestParam String pageNumber) {

       return songService.getSongsByArtist(artist,pageNumber);

    }

    @GetMapping("/getSongsByArtist2")
    public List<SongResponse> getSongsByArtist(@RequestParam("artist2") String artist, @RequestParam String pageNumber) {

        return songService.getSongsByArtist(artist,pageNumber);

    }

    @PostMapping(path="/add2",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public SongEntity saveSong2( String artist, String songTitle, String albumTitle) throws SQLDataException
    {
        SongEntity song = new SongEntity();
        song.setArtist(artist);
        song.setAlbumTitle(songTitle);
        song.setSongTitle(albumTitle);
        song.setAwards(0);
        return songService.saveSong(song);
    }

    @PostMapping("/add")
    public SongEntity saveSong(@RequestBody SongEntity song) throws SQLDataException {
        return songService.saveSong(song);
    }

    @PostMapping("/add2")
    public SongEntity saveSong2(SongEntity song) throws SQLDataException {
        return songService.saveSong(song);
    }

    @GetMapping("/getArtistSongsByTitle")
    public List<SongResponse> getArtistSongsByTitle(@RequestParam("artist") String artist, @RequestParam("songTitle") String songTitle) {
        return songService.getArtistSongsByTitle(artist, songTitle);
    }

    @GetMapping("/get_artist_songs_by_artist/{artist}")
    public List<SongResponse> getArtistSongsByArtist(@PathVariable("artist") String artist) {
        return songService.getArtistSongsByTitle(artist,"");
    }

    @GetMapping("/getSongsByAwards")
    public List<SongEntity> getSongs(@RequestParam("minAwards") String minAwards, @RequestParam("maxAwards") String maxAwards) {
        return songService.getSongsByAwards(minAwards,maxAwards);
    }


    @RequestMapping(method = RequestMethod.GET, path = "/getallsongs")
    public ResponseEntity<List<SongResponse>> getSongs() {
        try {
            List<SongResponse> songs = songService.getAllSongs();
            return ResponseEntity.ok(songs);
        }
        catch(Exception e){
            return (ResponseEntity<List<SongResponse>>) ResponseEntity.noContent();
        }
    }

}

