package com.hikingtrails.project2hikingtrails.model;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private String username, password, phoneNumber, profilePicture, hikingHistory, followers, following, reviews;

    public User(String username, String password, String phoneNumber, String profilePicture) {
        this(username, password, phoneNumber, profilePicture, "", "", "", "");
    }

    public User(String username, String password, String phoneNumber, String profilePicture, String hikingHistory,
                String followers, String following, String reviews) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
        this.hikingHistory = hikingHistory;
        this.followers = followers;
        this.following = following;
        this.reviews = reviews;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getHikingHistory() {
        return hikingHistory;
    }

    public String getFollowers() {
        return followers;
    }

    public String getFollowing() {
        return following;
    }

    public String getReviews() {
        return reviews;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", hikingHistory='" + hikingHistory + '\'' +
                ", followers='" + followers + '\'' +
                ", following='" + following + '\'' +
                ", reviews='" + reviews + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) &&
                Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(profilePicture, user.profilePicture) &&
                Objects.equals(hikingHistory, user.hikingHistory) && Objects.equals(followers, user.followers) &&
                Objects.equals(following, user.following) && Objects.equals(reviews, user.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, phoneNumber, profilePicture, hikingHistory, followers, following,
                reviews);
    }
}
