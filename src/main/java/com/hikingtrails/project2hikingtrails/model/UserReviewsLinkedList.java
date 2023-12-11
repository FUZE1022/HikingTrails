package com.hikingtrails.project2hikingtrails.model;

import com.hikingtrails.project2hikingtrails.util.BackUp;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class UserReviewsLinkedList implements Serializable {
    private LinkedList<Review> userReviewsLinkedList;

    public UserReviewsLinkedList() {
        this.userReviewsLinkedList = new LinkedList<>();
    }

    public void addReview(Review review) {
        userReviewsLinkedList.add(review);
        BackUp.saveData();
    }

    public void removeReview(Review review) {
        userReviewsLinkedList.remove(review);
        BackUp.saveData();
    }

    public boolean containsReview(Review review) {
        return userReviewsLinkedList.contains(review);
    }

    public Review getReview(Review review) {
        return userReviewsLinkedList.get(userReviewsLinkedList.indexOf(review));
    }

    public LinkedList<Review> getUserReviewsLinkedList() {
        return userReviewsLinkedList;
    }

    public void setUserReviewsLinkedList(LinkedList<Review> userReviewsLinkedList) {
        this.userReviewsLinkedList = userReviewsLinkedList;
        BackUp.saveData();
    }

    public void displayReviews() {
        for (Review review : userReviewsLinkedList) {
            System.out.println(review);
        }
    }
}
