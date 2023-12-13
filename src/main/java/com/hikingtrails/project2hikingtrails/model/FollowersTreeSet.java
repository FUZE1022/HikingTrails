package com.hikingtrails.project2hikingtrails.model;

import com.hikingtrails.project2hikingtrails.util.BackUp;

import java.io.Serializable;
import java.util.TreeSet;

public class FollowersTreeSet implements Serializable {
    private TreeSet<String> followers;

    public FollowersTreeSet() {
        this.followers = new TreeSet<>();
    }

    public void addFollower(String user) {
        followers.add(user);
        BackUp.saveData();
    }

    public void removeFollower(String user) {
        followers.remove(user);
        BackUp.saveData();
    }

    public boolean containsFollower(String user) {
        return followers.contains(user);
    }

    public String getFollower(String user) {
        return followers.ceiling(user);
    }

    public TreeSet<String> getFollowersTree() {
        return followers;
    }

    public int size() {
        return followers.size();
    }
}
