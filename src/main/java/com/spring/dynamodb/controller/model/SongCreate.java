package com.spring.dynamodb.controller.model;
import com.fasterxml.jackson.annotation.JsonProperty;
public class SongCreate {

    @JsonProperty("ArtistsName")
    private String artist;

    @JsonProperty("SongsName")
    private String songTitle;

    @JsonProperty("AlbumsTitle")
    private String albumTitle;


}
