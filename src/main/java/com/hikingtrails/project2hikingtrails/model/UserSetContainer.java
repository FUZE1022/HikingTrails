package com.hikingtrails.project2hikingtrails.model;

import com.hikingtrails.project2hikingtrails.util.BackUp;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public class UserSetContainer implements Serializable, Comparator<User> {
    private Set<User> userSet;

    public UserSetContainer() {
        this.userSet = new TreeSet<>(this);
    }

    public void addUserToSet(User user) {
        userSet.add(user);
        BackUp.saveData();
    }

    public void removeUserFromSet(User user) {
        userSet.remove(user);
    }

    public boolean containsUsernameInSet(String username) {
        for (User user : userSet) {
            if (user.getUsername().equals(username))
                return true;
        }
        return false;
    }

    public boolean containsUserNumberInSet(String phonenumber) {
        for (User user : userSet) {
            if (user.getPhoneNumber().equals(phonenumber))
                return true;
        }
        return false;
    }

    public boolean containsUserInSet(User user) {
        return userSet.contains(user);
    }

    public User getUser(String username) {
        for (User user : userSet) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
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

    @Override
    public int compare(User o1, User o2) {
        return o1.getUsername().compareTo(o2.getUsername());
    }
}
