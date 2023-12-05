package com.hikingtrails.project2hikingtrails.model;

import com.hikingtrails.project2hikingtrails.util.BackUp;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class TrailReviewTreeMap implements Serializable {
    private Map<String, Review> trailReviewsTreeMap;

    public TrailReviewTreeMap() {
        this.trailReviewsTreeMap = new TreeMap<>();
    }

    public void addReview(Review review) {
        trailReviewsTreeMap.put(review.getTrailName(), review);
        BackUp.saveData();
    }

    public void removeReview(Review review) {
        trailReviewsTreeMap.remove(review.getTrailName());
    }

    public boolean containsReview(String trailName) {
        return trailReviewsTreeMap.containsKey(trailName);
    }

    public Review getReview(String trailName) {
        return trailReviewsTreeMap.get(trailName);
    }

    public Map<String, Review> getTrailReviewsTreeMap() {
        return trailReviewsTreeMap;
    }

    public void displayTrailReviewsTreeMap() {
        for (String trailName : trailReviewsTreeMap.keySet()) {
            System.out.println(trailName + " " + trailReviewsTreeMap.get(trailName));
        }
    }
}
