package com.hikingtrails.project2hikingtrails.model;

import com.hikingtrails.project2hikingtrails.util.BackUp;

import java.io.Serializable;
import java.util.*;

public class ReviewSetContainer implements Serializable {
    private Set<Review> reviewSetContainer;
    private final int MAX_SIZE = 50000;

    public ReviewSetContainer() {
        this.reviewSetContainer = new HashSet<>(MAX_SIZE);
    }

    public boolean addReviewToSet(Review review, User user) {
        if (reviewSetContainer.size() == MAX_SIZE) {
            return false;
        }
        reviewSetContainer.add(review);
        BackUp.saveData();
        return true;
    }

    public void removeReviewFromSet(Review review) {
        reviewSetContainer.remove(review);
    }

    public boolean containsReviewInSet(Review review) {
        return reviewSetContainer.contains(review);
    }

    public Optional<Review> getReview(String review) {
        for (Review review1 : reviewSetContainer) {
            if (review1.getReview().equals(review))
                return Optional.of(review1);
        }
        return Optional.empty();
    }

    public void displayReviewSet() {
        for (Review review : reviewSetContainer) {
            System.out.println(review);
        }
    }
}
