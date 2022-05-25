package com.spring.dynamodb.cache;

import com.spring.dynamodb.controller.SongResponse;
import com.spring.dynamodb.entity.SongEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheStoreBeans {

    @Bean
    public CacheStore<List<SongResponse>> songCache() {
        return new CacheStore<>(120, TimeUnit.SECONDS);
    }


}