package com.hikingtrails.project2hikingtrails.model;

import com.hikingtrails.project2hikingtrails.util.BackUp;

import java.io.Serializable;
import java.util.TreeSet;

public class FollowingTreeSet implements Serializable {
    private TreeSet<User> following;

    public FollowingTreeSet() {
        this.following = new TreeSet<>();
    }

    public void addFollowing(User user) {
        following.add(user);
        BackUp.saveData();
    }

    public void removeFollowing(User user) {
        following.remove(user);
        BackUp.saveData();
    }

    public boolean containsFollowing(User user) {
        return following.contains(user);
    }

    public User getFollowing(User user) {
        return following.ceiling(user);
    }

    public TreeSet<User> getFollowing() {
        return following;
    }

    public int size() {
        return following.size();
    }

    public boolean isEmpty() {
        return following.isEmpty();
    }
}
