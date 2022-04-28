package com.spring.dynamodb.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.spring.dynamodb.entity.Customer;
import com.spring.dynamodb.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SongsRepository {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

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

//          QueryResultPage<Song> songQueryResults = dynamoDBMapper.queryPage(Song.class, queryExpression);
//          List<Song> songList = songQueryResults.getResults();
//
//        return songList;
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


/**
 Map<String, AttributeValue> exclusiveStartKey = null;
 if (exclusiveStartSongTitle != null) {
 exclusiveStartKey = new HashMap<>();
 exclusiveStartKey.put("artist", new AttributeValue().withS(artist));
 exclusiveStartKey.put("song_title", new AttributeValue().withS(exclusiveStartSongTitle));
 }

 DynamoDBQueryExpression<Song> queryExpression = new DynamoDBQueryExpression<Song>()
 .withHashKeyValues(song)
 .withExclusiveStartKey(exclusiveStartKey)
 .withLimit(3);
 QueryResultPage<Song> songQueryResults = mapper.queryPage(Song.class, queryExpression);
 return songQueryResults.getResults();

 List<Song> firstPageOfSongs = songDao.getSongsByArtist("Black Eyed Peas", null);
 // retrieve title of last song returned on the first page using a helper method (method implementation not shown),
 // which would return "Where is the Love?" in this case and returns null if the provided list is empty
 String lastSongTitle = SongHelper.getLastSongTitle(firstPageOfSongs);
 //this call will return the song "Let's Get it Started"
 List<Song> secondPageOfSongs = songDao.getSongsByArtist("Black Eyed Peas", lastSongTitle);

 **/