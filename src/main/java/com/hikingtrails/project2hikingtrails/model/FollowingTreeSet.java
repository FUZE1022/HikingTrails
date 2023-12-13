package com.hikingtrails.project2hikingtrails.model;

import com.hikingtrails.project2hikingtrails.util.BackUp;

import java.io.Serializable;
import java.util.TreeSet;

public class FollowingTreeSet implements Serializable {
    private TreeSet<String> following;

    public FollowingTreeSet() {
        this.following = new TreeSet<>();
    }

    public void addFollowing(String user) {
        following.add(user);
        BackUp.saveData();
    }

    public void removeFollowing(String user) {
        following.remove(user);
        BackUp.saveData();
    }

    public boolean containsFollowing(String user) {
        return following.contains(user);
    }

    public String getFollowingUser(String user) {
        return following.ceiling(user);
    }

    public TreeSet<String> getFollowing() {
        return following;
    }

    public int size() {
        return following.size();
    }
}
