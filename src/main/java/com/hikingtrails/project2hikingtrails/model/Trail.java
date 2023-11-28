package com.hikingtrails.project2hikingtrails.model;

import java.io.Serializable;
import java.util.Objects;

public class Trail implements Serializable, Comparable<Trail> {
    private String name, headAddress, length, elevationGain, difficulty, type;
    private Review reviews;

    public Trail(String name, String headAddress, String length, String elevationGain, String difficulty, String type
            ,Review reviews) {
        this.name = name;
        this.headAddress = headAddress;
        this.length = length;
        this.elevationGain = elevationGain;
        this.difficulty = difficulty;
        this.type = type;
        this.reviews = reviews;
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

    public Review getReviews() {
        return reviews;
    }

    @Override
    public String toString() {
        return "Trail{" +
                "name='" + name + '\'' +
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
                Objects.equals(reviews, trail.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, headAddress, length, elevationGain, difficulty, type, reviews);
    }

    @Override
    public int compareTo(Trail o) {
        return this.name.compareTo(o.name);
    }
}
