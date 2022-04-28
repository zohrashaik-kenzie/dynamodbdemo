package com.spring.dynamodb.entity;

import ch.qos.logback.classic.db.names.ColumnName;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "Music")
public class Song {

    @DynamoDBHashKey(attributeName="Artist")
    private String artist;

    @DynamoDBRangeKey(attributeName="SongTitle")
    private String songTitle;

    @DynamoDBAttribute(attributeName="AlbumTitle")
    private String albumTitle;

    @DynamoDBAttribute(attributeName="Awards")
    private int awards;


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

    public String getArtist() {
        return artist;
    }
}
