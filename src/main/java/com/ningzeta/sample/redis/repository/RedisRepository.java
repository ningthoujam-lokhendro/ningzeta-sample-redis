package com.ningzeta.sample.redis.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ningzeta.sample.redis.domain.NoteBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * Repository for the Redis data-store.
 *
 * @author Ningthoujam Lokhendro
 * @since 5th Feb 2016
 */
@Repository
public class RedisRepository {

    @Autowired
    private RedisTemplate redisTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void save(NoteBook noteBook) {
        this.redisTemplate.boundHashOps("Notebook")
                .put(noteBook.getTitle(), objectMapper.convertValue(noteBook, HashMap.class));
    }

    public NoteBook findNoteBook(String title) {
        Object obj = this.redisTemplate.boundHashOps("Notebook").get(title);
        return objectMapper.convertValue(obj, NoteBook.class);
    }
}
