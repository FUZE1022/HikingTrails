package com.hikingtrails.project2hikingtrails.model;

import java.io.Serializable;
import java.util.Objects;

public class Trail implements Serializable, Comparable<Trail> {
    private String name, headAddress, elevationGain, length, difficulty, type;
    private Integer id;
    private static int uID = 0;
    private TrailReviewTreeMap reviews;

    public Trail(String name, String headAddress, String length, String elevationGain, String difficulty, String type
            ,TrailReviewTreeMap reviews) {
        this.name = name;
        this.headAddress = headAddress;
        this.length = length;
        this.elevationGain = elevationGain;
        this.difficulty = difficulty;
        this.type = type;
        this.reviews = reviews;
        this.id = uID;
        incID();
    }

    public String getName() {
        return name;
    }

    public String getHeadAddress() {
        return headAddress;
    }

    public String getLength() {
        return length;
    }

    public String getElevationGain() {
        return elevationGain;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getType() {
        return type;
    }

    public TrailReviewTreeMap getReviews() {
        return reviews;
    }

    public int getId() {
        return id;
    }

    public void incID() {
        uID++;
    }

    @Override
    public String toString() {
        return "Trail{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", headAddress='" + headAddress + '\'' +
                ", length='" + length + '\'' +
                ", elevationGain='" + elevationGain + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", type='" + type + '\'' +
                ", reviews='" + reviews + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trail trail = (Trail) o;
        return Objects.equals(name, trail.name) && Objects.equals(headAddress, trail.headAddress) &&
                Objects.equals(length, trail.length) && Objects.equals(elevationGain, trail.elevationGain) &&
                Objects.equals(difficulty, trail.difficulty) && Objects.equals(type, trail.type) &&
                Objects.equals(reviews, trail.reviews) && Objects.equals(id, trail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, headAddress, length, elevationGain, difficulty, type, reviews, id);
    }

    @Override
    public int compareTo(Trail o) {
        return this.id.compareTo(o.id);
    }
}
