package com.spring.dynamodb.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SongsControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllSongsShouldReturn22Songs() {
        ArrayList<SongResponse> x = this.restTemplate.getForObject("http://localhost:" + port + "/songs/getallsongs",ArrayList.class);
        Assertions.assertEquals(x.size(), 23, "You got more songs than you shoud");
    }
}
