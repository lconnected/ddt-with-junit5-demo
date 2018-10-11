package ru.jugsev.ddtwithjunit.models;

import java.util.Date;

/**
 * The comment transfer object
 * Created by lconnected on 10/10/2018.
 */
public class Comment {

    public Integer id;
    public String body;
    public String author;
    public Date added;

    public Comment() {
    }

    public Comment(Integer id, String body, String author, Date added) {
        this.id = id;
        this.body = body;
        this.author = author;
        this.added = added;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }
}
