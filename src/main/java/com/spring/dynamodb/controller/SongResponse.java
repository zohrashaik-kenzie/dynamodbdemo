package com.spring.dynamodb.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SongResponse {

    @JsonProperty("ArtistName")
    private String artist;
    @JsonProperty("SongTitle")
    private String songTitle;
    @JsonProperty("AlbumTitle")
    private String albumTitle;
    @JsonProperty("NumberOfAwards")
    private int awards;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public int getAwards() {
        return awards;
    }

    public void setAwards(int awards) {
        this.awards = awards;
    }
}
