package com.hikingtrails.project2hikingtrails.model;

import com.hikingtrails.project2hikingtrails.util.BackUp;

import java.io.Serializable;
import java.util.TreeSet;

public class UserTreeSet implements Serializable {
    private TreeSet<User> userSet;

    public UserTreeSet() {
        this.userSet = new TreeSet<>();
    }

    public void addUserToSet(User user) {
        userSet.add(user);
        BackUp.saveData();
    }

    public void removeUserFromSet(User user) {
        userSet.remove(user);
        BackUp.saveData();
    }

    public boolean containsUsernameInSet(String username) {
        return userSet.contains(new User(username, ""));
    }

    public boolean containsUserNumberInSet(String phoneNumber) {
        return userSet.stream().anyMatch(user -> user.getPhoneNumber().equals(phoneNumber));
    }

    public boolean isValidUser(String username, String password) {
        return userSet.contains(new User(username, "")) &&
                userSet.ceiling(new User(username, "")).getPassword().equals(password);
    }

    public User getUser(String username) {
        return userSet.ceiling(new User(username, ""));
    }
    public TreeSet<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(TreeSet<User> userSet) {
        this.userSet = userSet;
    }

    public void displayUserSet() {
        for (User user : userSet) {
            System.out.println(user);
        }
    }

    @Override
    public String toString() {
        return "UserSetContainer{" +
                "userSet=" + userSet +
                '}';
    }
}