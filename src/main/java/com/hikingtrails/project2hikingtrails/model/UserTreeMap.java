package com.hikingtrails.project2hikingtrails.model;

import com.hikingtrails.project2hikingtrails.util.BackUp;

import java.io.Serializable;
import java.util.TreeMap;

public class UserTreeMap implements Serializable {
//    private TreeMap<String, String> userTreeMap;
//
//    public UserTreeMap() {
//        this.userTreeMap = new TreeMap<>();
//    }
//
//    public void addUser(User user) {
//        userTreeMap.put(user.getUsername(), user.getPassword());
//        BackUp.saveData();
//    }
//
//    public void removeUser(User user) {
//        userTreeMap.remove(user.getUsername());
//        BackUp.saveData();
//    }
//
//    public boolean containsUser(String username) {
//        return userTreeMap.containsKey(username);
//    }
//
//    public boolean isValidUser(String username, String password) {
//        return userTreeMap.containsKey(username) && userTreeMap.get(username).equals(password);
//    }
//
//    public TreeMap<String, String> getUserTreeMap() {
//        return userTreeMap;
//    }
//
//    public void displayUserTreeMap() {
//        for (String username : userTreeMap.keySet()) {
//            System.out.println(username + " " + userTreeMap.get(username));
//        }
//    }
}