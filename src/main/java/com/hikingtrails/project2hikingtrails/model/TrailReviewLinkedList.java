package com.hikingtrails.project2hikingtrails.model;

import com.hikingtrails.project2hikingtrails.util.BackUp;

import java.io.Serializable;
import java.util.LinkedList;

public class TrailReviewLinkedList implements Serializable {
    private LinkedList<Review> trailReviewLinkedList;

    public TrailReviewLinkedList() {
        this.trailReviewLinkedList = new LinkedList<>();
    }

    public void addReview(Review review) {
        trailReviewLinkedList.add(review);
        BackUp.saveData();
    }

    public void removeReview(Review review) {
        trailReviewLinkedList.remove(review);
        BackUp.saveData();
    }

    public boolean containsReview(Review review) {
        return trailReviewLinkedList.contains(review);
    }

    public Review getReview(Review review) {
        return trailReviewLinkedList.get(trailReviewLinkedList.indexOf(review));
    }

    public LinkedList<Review> getTrailReviewLinkedList() {
        return trailReviewLinkedList;
    }

    public void setTrailReviewLinkedList(LinkedList<Review> trailReviewLinkedList) {
        this.trailReviewLinkedList = trailReviewLinkedList;
        BackUp.saveData();
    }
}
