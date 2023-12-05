package com.hikingtrails.project2hikingtrails.model;

import com.hikingtrails.project2hikingtrails.util.BackUp;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class UserReviewsTreeMap implements Serializable {
    private Map<String, Review> userReviewsTreeMap;

    public UserReviewsTreeMap() {
        this.userReviewsTreeMap = new TreeMap<>();
    }

    public void addReview(Review review) {
        userReviewsTreeMap.put(review.getUsername(), review);
        BackUp.saveData();
    }

    public void removeReview(Review review) {
        userReviewsTreeMap.remove(review.getUsername());
    }

    public boolean containsReview(String username) {
        return userReviewsTreeMap.containsKey(username);
    }

    public Review getReview(String username) {
        return userReviewsTreeMap.get(username);
    }

    public Map<String, Review> getUserReviewsTreeMap() {
        return userReviewsTreeMap;
    }

    public void displayUserReviewsTreeMap() {
        for (String username : userReviewsTreeMap.keySet()) {
            System.out.println(username + " " + userReviewsTreeMap.get(username));
        }
    }
}
