package com.hikingtrails.project2hikingtrails.model;

import com.hikingtrails.project2hikingtrails.util.BackUp;

import java.io.Serializable;
import java.util.TreeSet;

public class BlockTreeSet implements Serializable {
    private TreeSet<String> blocked;

    public BlockTreeSet() {
        this.blocked = new TreeSet<>();
    }

    public void addBlocked(String user) {
        blocked.add(user);
        BackUp.saveData();
    }

    public void removeBlocked(String user) {
        blocked.remove(user);
        BackUp.saveData();
    }

    public boolean isUserBlocked(String username) {
        return blocked.contains(username);
    }

    public String getBlocked(String user) {
        return blocked.ceiling(user);
    }

    public TreeSet<String> getBlockedTree() {
        return blocked;
    }

    public int size() {
        return blocked.size();
    }
}
