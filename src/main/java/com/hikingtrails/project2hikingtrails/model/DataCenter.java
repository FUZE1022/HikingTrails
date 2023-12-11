package com.hikingtrails.project2hikingtrails.model;

import com.hikingtrails.project2hikingtrails.util.BackUp;

import java.io.Serializable;

public class DataCenter implements Serializable {

    private static DataCenter instance = null;

    private User currentUser;
    private UserTreeSet userTreeSet;
    private UserTreeMap userTreeMap;
    private TrailTreeSet trailTreeSet;

    private DataCenter() {
        userTreeSet = new UserTreeSet();
        userTreeMap = new UserTreeMap();
        trailTreeSet = new TrailTreeSet();
    }

    public static DataCenter getInstance() {
        if (instance == null) {
            instance = new DataCenter();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        currentUser = user;
        BackUp.saveData();
    }

    public void setUserTreeSet(UserTreeSet userTreeSet) {
        this.userTreeSet = userTreeSet;
        BackUp.saveData();
    }

    public UserTreeSet getUserTreeSet() {
        return userTreeSet;
    }

    public UserTreeMap getUserTreeMap() {
        return userTreeMap;
    }

    public void setUserTreeMap(UserTreeMap userTreeMap) {
        this.userTreeMap = userTreeMap;
        BackUp.saveData();
    }

    public TrailTreeSet getTrailTreeSet() {
        return trailTreeSet;
    }

    public void setTrailTreeSet(TrailTreeSet trailTreeSet) {
        this.trailTreeSet = trailTreeSet;
        BackUp.saveData();
    }
}
