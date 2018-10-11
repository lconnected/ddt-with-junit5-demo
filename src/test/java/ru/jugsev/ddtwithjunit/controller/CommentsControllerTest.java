package ru.jugsev.ddtwithjunit.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by lconnected on 11/10/2018.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class CommentsControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    public void testListComments() throws Exception {
        mockMvc.perform(get("/comment"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$[0]", Matchers.notNullValue()))
                .andExpect(jsonPath("$[0].body", Matchers.notNullValue()))
                .andExpect(jsonPath("$[0].author", Matchers.notNullValue()));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/good-comments.csv")
    public void testAddComment(String body, String author) throws Exception {
        mockMvc.perform(post("/comment")
                .content(createComment(body, author))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$.body", Matchers.notNullValue()))
                .andExpect(jsonPath("$.body", Matchers.is(body)))
                .andExpect(jsonPath("$.author", Matchers.notNullValue()))
                .andExpect(jsonPath("$.author", Matchers.is(author)))
                .andExpect(jsonPath("$.id", Matchers.notNullValue()))
                .andExpect(jsonPath("$.added", Matchers.notNullValue()));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/bad-comments.csv", delimiter = ';')
    public void testBrokenCensorship(String body, String author) throws Exception {
        mockMvc.perform(post("/comment")
                .content(createComment(body, author))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private static String createComment(String body, String author) {
        return "{\"body\": \"" + body + "\",\"author\": \"" + author + "\"}";
    }

}