package com.spring.dynamodb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.dynamodb.DynamodbApplication;
import com.spring.dynamodb.entity.SongEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = DynamodbApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SongCreateIntegrationTest {
    private static final String SONG_TITLE = "This is Nina Simone";
    private static final String ALBUM_TITLE = "REM's Greatest Hits";
    private static final String ARTIST_NAME = "Nina Simone";

    @Autowired
    private MockMvc mvc;

    @Test
    public void createsong_integrationtest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        // GIVEN
        SongEntity request = new SongEntity();
        request.setAlbumTitle(ALBUM_TITLE);
        request.setArtist(ARTIST_NAME);
        request.setSongTitle(SONG_TITLE);

        SongEntity expectedSong = new SongEntity();
        expectedSong.setAlbumTitle(ALBUM_TITLE);
        expectedSong.setArtist(ARTIST_NAME);
        expectedSong.setSongTitle(SONG_TITLE);
        expectedSong.setAwards(0);
        // WHEN
        String response = this.mvc.perform(
                MockMvcRequestBuilders.post("/songs/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        SongEntity result = mapper.readValue(response, new TypeReference<SongEntity>() {});
        // THEN

        Assertions.assertEquals(result, expectedSong);
    }
}