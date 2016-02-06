package com.ningzeta.sample.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ningzeta.sample.redis.domain.NoteBook;
import com.ningzeta.sample.redis.repository.RedisRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NingzetaSampleRedisApplication.class)
public class NingzetaSampleRedisApplicationTests {

    @Autowired
    RedisRepository redisRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void addNoteBookTest() {
        redisRepository.save(getANotebook());
    }

    @Test
    public void getNoteBookTest() {
        NoteBook noteBook = getANotebook();
        NoteBook fetchNoteBook = redisRepository.findNoteBook(noteBook.getTitle());
        assert (fetchNoteBook.getTitle().equals(noteBook.getTitle()));
    }

    private NoteBook getANotebook() {
        NoteBook noteBook = new NoteBook();
        noteBook.setTitle("Life of Pie");
        noteBook.setDescription("The story of a boy lost in sea.");
        noteBook.setNumberOfPages(200);

        return noteBook;
    }

}
