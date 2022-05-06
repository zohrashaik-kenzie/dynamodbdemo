package com.spring.dynamodb.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.spring.dynamodb.entity.SongEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SongsRepository {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public List<SongEntity> getAllSongs(){
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        PaginatedScanList<SongEntity> songList = dynamoDBMapper.scan(SongEntity.class, scanExpression);
        return songList;
    }

    public List<SongEntity> getSongsByAwards(String minAwards, String maxAwards){
       // PaginatedScanList<Song> songList = dynamoDBMapper.scan(Song.class, scanExpression);
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":minAwards", new AttributeValue().withN(minAwards));
        valueMap.put(":maxAwards", new AttributeValue().withN(maxAwards));
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("Awards between :minAwards and :maxAwards")
                .withExpressionAttributeValues(valueMap);
        PaginatedScanList<SongEntity> songList = dynamoDBMapper.scan(SongEntity.class, scanExpression);
        return songList;
    }



    public List<SongEntity> getSongsByArtist(String artist, String exclusiveStartSongTitle) {
        SongEntity song = new SongEntity();
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

        DynamoDBQueryExpression<SongEntity> queryExpression = new DynamoDBQueryExpression<SongEntity>()
                .withHashKeyValues(song)
                .withExclusiveStartKey(exclusiveStartKey)
                .withLimit(3);
        QueryResultPage<SongEntity> songQueryResults = dynamoDBMapper.queryPage(SongEntity.class, queryExpression);
        return songQueryResults.getResults();

    }

    public List<SongEntity> getArtistSongsByTitle(String artist, String songTitle) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":artist", new AttributeValue().withS(artist));
        valueMap.put(":songTitle", new AttributeValue().withS(songTitle));

        DynamoDBQueryExpression<SongEntity> queryExpression = new DynamoDBQueryExpression<SongEntity>()
                .withKeyConditionExpression("Artist = :artist and SongTitle = :songTitle")
                .withExpressionAttributeValues(valueMap);
        PaginatedQueryList<SongEntity> songList = dynamoDBMapper.query(SongEntity.class, queryExpression);
        return songList;
    }

        public SongEntity saveSong(SongEntity song) {
        try {
            dynamoDBMapper.save(song);
        }
        catch(DynamoDBMappingException e){

        }
        return song;
    }
}


