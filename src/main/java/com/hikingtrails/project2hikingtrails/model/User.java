package com.hikingtrails.project2hikingtrails.model;

import com.hikingtrails.project2hikingtrails.util.BackUp;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable, Comparable<User> {
    private String username, password, phoneNumber, profilePicture;
    private HikingHistoryLinkedList hikingHistory;
    private UserReviewsLinkedList reviews;
    private CommentLinkedList comments;
    private FollowersTreeSet followersTreeSet;
    private FollowingTreeSet followingTreeSet;
    private boolean isAdmin = false;


    public User(String username, String password) {
        this(username, password, "", "");
    }

    public User(Admin admin) {
        this(admin.getUsername(), admin.getPassword(), admin.getPhoneNumber(), admin.getProfilePicture());
    }

    public User(String username, String password, String phoneNumber, String profilePicture) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
        this.hikingHistory = new HikingHistoryLinkedList();
        this.reviews = new UserReviewsLinkedList();
        this.comments = new CommentLinkedList();
        this.followersTreeSet = new FollowersTreeSet();
        this.followingTreeSet = new FollowingTreeSet();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        BackUp.saveData();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        BackUp.saveData();
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
        BackUp.saveData();
    }

    public HikingHistoryLinkedList getHikingHistory() {
        return hikingHistory;
    }

    public UserReviewsLinkedList getReviews() {
        return reviews;
    }

    public CommentLinkedList getComments() {
        return comments;
    }

    public FollowersTreeSet getFollowersTreeSet() {
        return followersTreeSet;
    }

    public FollowingTreeSet getFollowingTreeSet() {
        return followingTreeSet;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", hikingHistory='" + hikingHistory + '\'' +
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
                Objects.equals(hikingHistory, user.hikingHistory) && Objects.equals(reviews, user.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, phoneNumber, profilePicture, hikingHistory, reviews);
    }


    @Override
    public int compareTo(User o) {
        return this.username.compareTo(o.username);
    }
}
