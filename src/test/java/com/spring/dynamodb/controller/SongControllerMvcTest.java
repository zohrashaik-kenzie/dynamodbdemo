package com.spring.dynamodb.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
public class SongControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllSongsShouldReturn23Songs() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        String response = this.mockMvc.perform(
                get("/songs/getallsongs")
                        .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();

        ArrayList<SongResponse> songs = mapper.readValue(response, new TypeReference< ArrayList<SongResponse>>() {});
        Assertions.assertEquals(songs.size(), 23, "You got more songs than you shoud");


    }
}