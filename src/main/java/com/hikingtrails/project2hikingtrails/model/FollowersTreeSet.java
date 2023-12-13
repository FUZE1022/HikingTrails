package com.hikingtrails.project2hikingtrails.model;

import java.io.Serializable;
import java.util.TreeSet;

public class FollowersTreeSet implements Serializable {
    private TreeSet<User> followers;

    public FollowersTreeSet() {
        this.followers = new TreeSet<>();
    }

    public void addFollower(User user) {
        followers.add(user);
    }

    public void removeFollower(User user) {
        followers.remove(user);
    }

    public boolean containsFollower(User user) {
        return followers.contains(user);
    }

    public User getFollower(User user) {
        return followers.ceiling(user);
    }

    public TreeSet<User> getFollowers() {
        return followers;
    }

    public int size() {
        return followers.size();
    }

    public boolean isEmpty() {
        return followers.isEmpty();
    }
}
