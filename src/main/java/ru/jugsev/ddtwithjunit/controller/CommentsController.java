package ru.jugsev.ddtwithjunit.controller;

import io.codearte.jfairy.Fairy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.jugsev.ddtwithjunit.models.ErrorMessage;
import ru.jugsev.ddtwithjunit.service.CensorshipService;
import ru.jugsev.ddtwithjunit.controller.exception.CensorshipException;
import ru.jugsev.ddtwithjunit.models.Comment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController("/comment")
public class CommentsController {

    @Autowired
    private CensorshipService censorshipService;

    private Fairy faker = Fairy.create();
    private static final int MAX_ID_VALUE = 100000;

    @GetMapping
    public List<Comment> listComments() {
        List<Comment> comments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            comments.add(new Comment(
                    faker.baseProducer().randomInt(MAX_ID_VALUE),
                    faker.textProducer().text(),
                    faker.textProducer().word(),
                    new Date()
            ));
        }
        return comments;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment addComment(@RequestBody Comment comment) {
        if (!censorshipService.checkCensorship(comment.getBody())) {
            throw new CensorshipException();
        }
        comment.setId(faker.baseProducer().randomInt(MAX_ID_VALUE));
        comment.setAdded(new Date());
        return comment;
    }

    @ExceptionHandler(CensorshipException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleErrors() {
        return new ErrorMessage("Bad words forbidden");
    }

}
