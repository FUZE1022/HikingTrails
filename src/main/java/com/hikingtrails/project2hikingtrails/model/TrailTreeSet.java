package com.hikingtrails.project2hikingtrails.model;

import com.hikingtrails.project2hikingtrails.util.BackUp;

import java.io.Serializable;
import java.util.*;

public class TrailTreeSet implements Serializable {
    private TreeSet<Trail> trailSet;

    public TrailTreeSet() {
        this.trailSet = new TreeSet<>();
    }

    public void addTrail(Trail trail) throws Exception {
        int MAX_SIZE = 50000;
        if(trailSet.size() == MAX_SIZE) {
            throw new Exception("Can not go over 50,000 Trails");
        }
        this.trailSet.add(trail);
        BackUp.saveData();
    }

    public void removeTrailFromSet(Trail trail) {
        trailSet.remove(trail);
        BackUp.saveData();
    }

    public boolean containsTrailInSet(Trail trail) {
        return trailSet.contains(trail);
    }

    public Trail getTrail(Trail trail) {
        return trailSet.ceiling(trail);
    }

    public Trail getTrail(String name) {
        return trailSet.stream().filter(trail -> trail.getName().equals(name)).findFirst().orElse(null);
    }

    public Set<Trail> getTrailSet() {
        return trailSet;
    }

    public void setTrailSet(TreeSet<Trail> trailSet) {
        this.trailSet = trailSet;
    }

    public void displayTrailSet() {
        for (Trail trail : trailSet) {
            System.out.println(trail);
        }
    }
}
