package com.hikingtrails.project2hikingtrails.model;

import java.io.Serializable;
import java.util.Objects;

public class Review implements Serializable {
    private String username, trailName, review, time, date ,rating, photos;

    private CommentLinkedList comments;

    public Review(String username, String trailName, String review, String time, String date, String rating,
                  String photos) {
        this.username = username;
        this.trailName = trailName;
        this.review = review;
        this.time = time;
        this.date = date;
        this.rating = rating;
        this.comments = new CommentLinkedList();
        this.photos = photos;
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

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getRating() {
        return rating;
    }

    public CommentLinkedList getComments() {
        return comments;
    }

    public String getPhotos() {
        return photos;
    }

    @Override
    public String toString() {
        return "Review{" +
                "username='" + username + '\'' +
                ", trailName='" + trailName + '\'' +
                ", review='" + review + '\'' +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", rating='" + rating + '\'' +
                ", photos='" + photos + '\'' +
                ", comments=" + comments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review1 = (Review) o;
        return Objects.equals(username, review1.username) && Objects.equals(trailName, review1.trailName) &&
                Objects.equals(review, review1.review) && Objects.equals(time, review1.time) &&
                Objects.equals(rating, review1.rating) && Objects.equals(comments, review1.comments) &&
                Objects.equals(photos, review1.photos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, trailName, review, time, rating, comments, photos);
    }
}
