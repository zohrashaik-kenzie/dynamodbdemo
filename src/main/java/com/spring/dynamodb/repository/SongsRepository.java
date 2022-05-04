package com.spring.dynamodb.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.spring.dynamodb.entity.Customer;
import com.spring.dynamodb.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SongsRepository {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public List<Song> getAllSongs(){
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        PaginatedScanList<Song> songList = dynamoDBMapper.scan(Song.class, scanExpression);
        return songList;
    }

    public List<Song> getSongsByAwards(String minAwards, String maxAwards){
       // PaginatedScanList<Song> songList = dynamoDBMapper.scan(Song.class, scanExpression);
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":minAwards", new AttributeValue().withN(minAwards));
        valueMap.put(":maxAwards", new AttributeValue().withN(maxAwards));
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("Awards between :minAwards and :maxAwards")
                .withExpressionAttributeValues(valueMap);
        PaginatedScanList<Song> songList = dynamoDBMapper.scan(Song.class, scanExpression);
        return songList;
    }



    public List<Song> getSongsByArtist(String artist, String exclusiveStartSongTitle) {
        Song song = new Song();
        song.setArtist(artist);

        //DynamoDBQueryExpression<Song> queryExpression = new DynamoDBQueryExpression<Song>()
               // .withHashKeyValues(song).withLimit(3);
        //PaginatedQueryList<Song> songList = dynamoDBMapper.query(Song.class, queryExpression);
        Map<String, AttributeValue> exclusiveStartKey = null;
        if (exclusiveStartSongTitle != null) {
            exclusiveStartKey = new HashMap<>();
            exclusiveStartKey.put("Artist", new AttributeValue().withS(artist));
            exclusiveStartKey.put("SongTitle", new AttributeValue().withS(exclusiveStartSongTitle));
        }

        DynamoDBQueryExpression<Song> queryExpression = new DynamoDBQueryExpression<Song>()
                .withHashKeyValues(song)
                .withExclusiveStartKey(exclusiveStartKey)
                .withLimit(3);
        QueryResultPage<Song> songQueryResults = dynamoDBMapper.queryPage(Song.class, queryExpression);
        return songQueryResults.getResults();

    }

    public List<Song> getArtistSongsByTitle(String artist, String songTitle) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":artist", new AttributeValue().withS(artist));
        valueMap.put(":songTitle", new AttributeValue().withS(songTitle));

        DynamoDBQueryExpression<Song> queryExpression = new DynamoDBQueryExpression<Song>()
                .withKeyConditionExpression("Artist = :artist and SongTitle = :songTitle")
                .withExpressionAttributeValues(valueMap);
        PaginatedQueryList<Song> songList = dynamoDBMapper.query(Song.class, queryExpression);
        return songList;
    }

        public Song saveSong(Song song) {
        try {
            dynamoDBMapper.save(song);
        }
        catch(DynamoDBMappingException e){

        }
        return song;
    }
}


