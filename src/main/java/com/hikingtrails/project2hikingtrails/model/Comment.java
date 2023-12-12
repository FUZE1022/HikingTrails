package com.hikingtrails.project2hikingtrails.model;

import java.io.Serializable;
import java.util.Objects;

public class Comment implements Serializable {
    private String username, comment;

    public Comment(String username, String comment) {
        this.username = username;
        this.comment =  comment;
    }

    public String getUsername() {
        return username;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "username='" + username + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment user = (Comment) o;
        return getUsername().equals(user.getUsername()) && getComment().equals(user.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getComment());
    }
}
