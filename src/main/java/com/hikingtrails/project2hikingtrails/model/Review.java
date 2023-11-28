package com.hikingtrails.project2hikingtrails.model;

public class Review {
    private String username, trailName, review;

    public Review(String username, String trailName, String review) {
        this.username = username;
        this.trailName = trailName;
        this.review = review;
    }

    public String getUsername() {
        return username;
    }

    public String getTrailName() {
        return trailName;
    }

    public String getReview() {
        return review;
    }

    @Override
    public String toString() {
        return "Review{" +
                "username='" + username + '\'' +
                ", trailName='" + trailName + '\'' +
                ", review='" + review + '\'' +
                '}';
    }
}
