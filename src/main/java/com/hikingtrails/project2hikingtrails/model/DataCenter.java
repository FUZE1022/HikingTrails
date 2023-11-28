package com.hikingtrails.project2hikingtrails.model;

import com.hikingtrails.project2hikingtrails.util.BackUp;

import java.io.Serializable;
import java.util.LinkedList;

public class DataCenter implements Serializable {

    private static DataCenter instance = null;

    private User currentUser;
    private UserSetContainer userSetContainer;
    private UserSearchTree userSearchTree;
    private TrailSetContainer trailSetContainer;

    private DataCenter() {
        userSetContainer = new UserSetContainer();
        userSearchTree = new UserSearchTree();
        trailSetContainer = new TrailSetContainer();
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

    public void setUserSetContainer(UserSetContainer userSetContainer) {
        this.userSetContainer = userSetContainer;
        BackUp.saveData();
    }

    public UserSetContainer getUserSetContainer() {
        return userSetContainer;
    }

    public UserSearchTree getUserSearchTree() {
        return userSearchTree;
    }

    public void setUserSearchTree(UserSearchTree userSearchTree) {
        this.userSearchTree = userSearchTree;
        BackUp.saveData();
    }

    public TrailSetContainer getTrailSetContainer() {
        return trailSetContainer;
    }

    public void setTrailSetContainer(TrailSetContainer trailSetContainer) {
        this.trailSetContainer = trailSetContainer;
        BackUp.saveData();
    }
}
