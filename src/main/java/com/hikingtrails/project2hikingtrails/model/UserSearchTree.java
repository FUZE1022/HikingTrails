package com.hikingtrails.project2hikingtrails.model;

import java.util.Optional;
import java.util.TreeMap;

public class UserTree {
    private TreeMap<String, String> userTreeMap;

    public UserTree() {
        this.userTreeMap = new TreeMap<>();
    }

    public void addUser(User user) {
        userTreeMap.put(user.getUsername(), user.getPassword());
    }

    public void removeUser(User user) {
        userTreeMap.remove(user.getUsername());
    }

    public boolean containsUser(String username) {
        return userTreeMap.containsKey(username);
    }

    public Optional<User> searchUser(String username) {
        if (userTreeMap.containsKey(username)) {
            return Optional.of(new User(username, userTreeMap.get(username)));
        } else {
            return Optional.empty();
        }
    }

    public User getUser(String username) {
        return new User(username, userTreeMap.get(username));
    }

    public boolean isValidUser(String username, String password) {
        return userTreeMap.containsKey(username) && userTreeMap.get(username).equals(password);
    }

    public TreeMap<String, String> getUserTreeMap() {
        return userTreeMap;
    }

}