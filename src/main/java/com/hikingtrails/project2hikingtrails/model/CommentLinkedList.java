package com.hikingtrails.project2hikingtrails.model;

import com.hikingtrails.project2hikingtrails.util.BackUp;

import java.io.Serializable;
import java.util.LinkedList;

public class CommentLinkedList implements Serializable {
    private LinkedList<Comment> commentLinkedList;

    public CommentLinkedList() {
        this.commentLinkedList = new LinkedList<>();
    }

    public void addComment(Comment comment) {
        commentLinkedList.add(comment);
        BackUp.saveData();
    }

    public void removeComment(Comment comment) {
        commentLinkedList.remove(comment);
        BackUp.saveData();
    }

    public boolean containsComment(Comment comment) {
        return commentLinkedList.contains(comment);
    }

    public Comment getComment(Comment comment) {
        return commentLinkedList.get(commentLinkedList.indexOf(comment));
    }

    public LinkedList<Comment> getCommentLinkedList() {
        return commentLinkedList;
    }

    public Comment getPage(Integer pageIndex) {
        return commentLinkedList.get(pageIndex);
    }

    public int size() {
        return commentLinkedList.size();
    }

    public boolean isEmpty() {
        return commentLinkedList.isEmpty();
    }
}
